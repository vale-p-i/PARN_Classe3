package annuncio.dao;

import candidatura.service.CandidaturaService;
import storage.entity.Annuncio;
import storage.entity.Azienda;
import storage.entity.Candidatura;
import utente.service.UtenteService;
import utente.service.UtenteServiceInterface;
import utils.ConPool;
import utils.KeywordUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnnuncioDAO {
    public List<Annuncio> getAnnuncioById(int id) throws SQLException {
        List<Annuncio> result = new ArrayList<>();

        Connection connection = ConPool.getConnection();
        Statement stmt = (Statement) connection.createStatement();
        PreparedStatement pdstmt = connection.prepareStatement("SELECT ID, Azienda, Attivo, Sede,N_Persone,Descrizione,Scadenza,Requisiti,Keyword,Preferenze,Ruolo FROM Annuncio WHERE ID = ?");
        pdstmt.setInt(1, id);

        UtenteServiceInterface utenteService = new UtenteService();
        CandidaturaService candidaturaService = new CandidaturaService();

        ResultSet rs = pdstmt.executeQuery();
        while (rs.next()) {
            Azienda az=utenteService.getAziendaById(rs.getInt(2));
            Annuncio tmp = new Annuncio(id,
                    az,
                    rs.getBoolean(3),
                    utenteService.getSedeById(az,rs.getInt(4)),
                    rs.getInt(5),
                    rs.getString(6),
                    rs.getDate(7).toLocalDate().atStartOfDay(),
                    KeywordUtils.getKeywordListFromString(rs.getString(8)),
                    KeywordUtils.getKeywordListFromString(rs.getString(9)),
                    KeywordUtils.getKeywordListFromString(rs.getString(10)),
                    rs.getString(11),
                    null
            );
            tmp.setCandidature(candidaturaService.getCandidatureByAnnuncio(tmp));
            result.add(tmp);
        }
        return result;
    }
}
