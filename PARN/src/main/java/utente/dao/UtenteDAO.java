package utente.dao;

import org.mariadb.jdbc.Statement;
import storage.entity.Azienda;
import storage.entity.Persona;
import storage.entity.Utente;
import utils.ConPool;
import utils.SettoriCompetenzaUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.List;

public class UtenteDAO {
    public Azienda addAzienda(Azienda azienda) throws SQLException{
        Connection connection = ConPool.getConnection();
        PreparedStatement pdstmt = connection.prepareStatement("INSERT INTO Azienda (P_IVA, Rag_Soc, Link, " +
                "ADI, N_Dip, Sett_Comp) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        pdstmt.setString(1, azienda.getPartitaIVA());
        pdstmt.setString(2, azienda.getRagioneSociale());
        pdstmt.setString(3, azienda.getLink());
        pdstmt.setString(4, azienda.getAreaInteresse());
        pdstmt.setInt(5, azienda.getNumeroDipendenti());
        String dbContent = SettoriCompetenzaUtils.getStringFromList(azienda.getSettoriCompetenza());
        pdstmt.setString(6, dbContent);

        pdstmt.executeUpdate();

        ResultSet rs = pdstmt.getGeneratedKeys();
        rs.next();
        azienda.setId(rs.getInt(1));
        return azienda;
    }

    public Persona addPersona(Persona persona) throws SQLException{
        Connection connection = ConPool.getConnection();
        PreparedStatement pdstmt = connection.prepareStatement("INSERT INTO Persona (Cognome, CF, DDN, " +
                "F_Macroarea, Pos_Des) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        pdstmt.setString(1, persona.getCognome());
        pdstmt.setString(2, persona.getCodiceFiscale());
        java.sql.Date sqlDate = java.sql.Date.valueOf(persona.getDataDiNascita().toLocalDate());
        pdstmt.setDate(3, sqlDate);
        pdstmt.setString(4, persona.getFiltroMacroarea());
        pdstmt.setString(5, persona.getPosizioneDesiderata());

        pdstmt.executeUpdate();

        ResultSet rs = pdstmt.getGeneratedKeys();
        rs.next();
        persona.setId(rs.getInt(1));
        return persona;
    }

    public void aggiornaAzienda(Azienda azienda) throws SQLException{
        Connection connection = ConPool.getConnection();
        PreparedStatement pdstmt = connection.prepareStatement("UPDATE Azienda SET  P_IVA = ?, Rag_Soc = ?, Link = ?, ADI = ?, N_Dip = ?, Sett_Comp = ? WHERE Utente = ?", Statement.RETURN_GENERATED_KEYS);
        pdstmt.setString(1, azienda.getPartitaIVA());
        pdstmt.setString(2, azienda.getRagioneSociale());
        pdstmt.setString(3, azienda.getLink());
        pdstmt.setString(4, azienda.getAreaInteresse());
        pdstmt.setInt(5, azienda.getNumeroDipendenti());
        String dbContent = SettoriCompetenzaUtils.getStringFromList(azienda.getSettoriCompetenza());
        pdstmt.setString(6, dbContent);
        pdstmt.setInt(7, azienda.getId());

        pdstmt.executeUpdate();
    }

    public void aggiornaPerson(Persona persona) throws SQLException{
        Connection connection = ConPool.getConnection();
        PreparedStatement pdstmt = connection.prepareStatement("UPDATE Persona SET Utente = ?, Cognome = ?, CF" +
                " = ?, DDN = ?, F_Macroarea = ?, Pos_Des = ? WHERE Utente = ?", Statement.RETURN_GENERATED_KEYS);
        pdstmt.setInt(1, persona.getId());
        pdstmt.setString(2, persona.getCognome());
        pdstmt.setString(3, persona.getCodiceFiscale());
        java.sql.Date sqlDate = java.sql.Date.valueOf(persona.getDataDiNascita().toLocalDate());
        pdstmt.setDate(3, sqlDate);
        pdstmt.setString(5, persona.getFiltroMacroarea());
        pdstmt.setString(6, persona.getPosizioneDesiderata());
        pdstmt.setInt(7, persona.getId());

        pdstmt.executeUpdate();
    }

    public void rimuoviPersona(Persona persona) throws SQLException{
        System.out.println("Questo metodo non è stato ancora implementato");
    }

    public void rimuoviAzienda(Azienda azienda) throws SQLException{
        System.out.println("Questo metodo non è stato ancora implementato");
    }

    public Utente autenticazione(String mail, String password) throws SQLException{
          Connection connection = ConPool.getConnection();
          PreparedStatement pdstmt = connection.prepareStatement("SELECT * FROM Utente WHERE Mail = ?");
          pdstmt.setString(1, mail);
          ResultSet rs = pdstmt.executeQuery();
          int id = rs.getInt(1);
          Azienda azienda = findAzienda(rs, id);
          Persona persona = findPersona(rs, id);
          if(azienda != null)
              return azienda;
          else if(persona != null)
              return persona;
          return null;
    }

    private Azienda findAzienda(ResultSet rs, int id) throws SQLException {
        Connection connection = ConPool.getConnection();
        PreparedStatement pdstmt = connection.prepareStatement("SELECT * FROM Azienda WHERE Utente = ?");
        ResultSet newRs = pdstmt.executeQuery();
        if(newRs.next()){
            Azienda azienda = new Azienda();
            azienda.setNome(rs.getString(2));
            azienda.setMail(rs.getString(3));
            azienda.setPassword(rs.getString(4));
            azienda.setRegione(rs.getString(5));
            azienda.setProvincia(rs.getString(6));
            azienda.setFoto(rs.getString(7));
            azienda.setCap(rs.getString(8));
            azienda.setTelefono(rs.getString(9));
            azienda.setCitta(rs.getString(10));
            azienda.setVia(rs.getString(11));
            azienda.setPartitaIVA(newRs.getString(2));
            azienda.setRagioneSociale(newRs.getString(3));
            azienda.setLink(newRs.getString(4));
            azienda.setAreaInteresse(newRs.getString(5));
            azienda.setNumeroDipendenti(newRs.getInt(6));
            List<String> settori = SettoriCompetenzaUtils.getSplittedString(newRs.getString(7));
            azienda.setSettoriCompetenza(settori);
            return azienda;
        }
        else return null;
    }

    private Persona findPersona(ResultSet rs, int id) throws SQLException {
        Connection connection = ConPool.getConnection();
        PreparedStatement pdstmt = connection.prepareStatement("SELECT * FROM Azienda WHERE Utente = ?");
        ResultSet newRs = pdstmt.executeQuery();
        if(newRs.next()){
            Persona persona = new Persona();
            persona.setNome(rs.getString(2));
            persona.setMail(rs.getString(3));
            persona.setPassword(rs.getString(4));
            persona.setRegione(rs.getString(5));
            persona.setProvincia(rs.getString(6));
            persona.setFoto(rs.getString(7));
            persona.setCap(rs.getString(8));
            persona.setTelefono(rs.getString(9));
            persona.setCitta(rs.getString(10));
            persona.setVia(rs.getString(11));
            persona.setCognome(newRs.getString(2));
            persona.setCodiceFiscale(newRs.getString(3));
            java.sql.Date date = newRs.getDate(4);
            persona.setDataDiNascita(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
            persona.setFiltroMacroarea(newRs.getString(5));
            persona.setPosizioneDesiderata(newRs.getString(6));
            return persona;
        }
        else return null;
    }
}
