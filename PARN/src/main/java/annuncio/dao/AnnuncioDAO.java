package annuncio.dao;

import candidatura.service.CandidaturaService;
import storage.entity.Annuncio;
import storage.entity.Azienda;
import storage.entity.Candidatura;
import utils.ConPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnnuncioDAO {
    public List<Annuncio> getAnnuncioById(int id) throws SQLException {
        List<Candidatura> result = new ArrayList<>();

        Connection connection = ConPool.getConnection();
        Statement stmt = (Statement) connection.createStatement();
        PreparedStatement pdstmt = connection.prepareStatement("SELECT * FROM Annuncio a WHERE a.ID = ?1");
        pdstmt.setInt(1, id);

        AziendaService aziendaService = new AziendaService();
        SedeService sedeService = new SedeService();
        CandidaturaService candidaturaService = new CandidaturaService();

        ResultSet rs = pdstmt.executeQuery();
        while (rs.next()) {
            result.add(
                    new Annuncio(id,
                            aziendaService.getAziendaByUtente(rs.getInt(2)),
                            rs.getBoolean(3),
                            SedeService.getSedeById(rs.getInt(4)),
                            rs.getInt(5),
                            rs.getString(6),
                            rs.getDate(7),
                            rs.getString(8),
                            KeywordUtils.getKeywordListFromString(9),
                            PreferenzeUtils.getPreferenzeListFromString(10),
                            rs.getString(11),
                            CandidaturaService.getCandidatureByAnnuncio(id)
                            )
            )
        }
    }
}
