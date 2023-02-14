package utente.dao;

import org.mariadb.jdbc.Statement;
import storage.entity.Azienda;
import storage.entity.Persona;
import storage.entity.Sede;
import storage.entity.Utente;
import utils.ConPool;
import utils.StringListUtils;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO {
    private static Connection connection;

    public UtenteDAO() {
        try {
            connection = ConPool.getConnection();
        } catch (Exception e) {
            System.err.println("ERRORE: IMPOSSIBILE AALOCARE CONNESSIONE: "+e);
        }
    }

    private int addUtente(Utente utente) throws SQLException{
        PreparedStatement pdstmt = connection.prepareStatement("INSERT INTO Utente (Nome, Mail, Pass, Regione," +
                " Provincia, Foto, CAP, Telefono, Citta, Via) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        pdstmt.setString(1, utente.getNome());
        pdstmt.setString(2, utente.getMail());
        pdstmt.setString(3, utente.getPassword());
        pdstmt.setString(4, utente.getRegione());
        pdstmt.setString(5, utente.getProvincia());
        pdstmt.setString(6, utente.getFoto());
        pdstmt.setString(7, utente.getCap());
        pdstmt.setString(8, utente.getTelefono());
        pdstmt.setString(9, utente.getCitta());
        pdstmt.setString(10, utente.getVia());
        pdstmt.executeUpdate();
        ResultSet rs = pdstmt.getGeneratedKeys();
        if(rs.next())
            return rs.getInt(1);
        else return -10;
    }
    public Azienda addAzienda(Azienda azienda) throws SQLException{
        int id = addUtente(azienda);
        if(id!=-10){
            PreparedStatement pdstmt = connection.prepareStatement("INSERT INTO Azienda (Utente, P_IVA, Rag_Soc, Link, " +
                    "ADI, N_Dip, Sett_Comp) VALUES (?, ?, ?, ?, ?, ?, ?)");
            pdstmt.setInt(1, id);
            pdstmt.setString(2, azienda.getPartitaIVA());
            pdstmt.setString(3, azienda.getRagioneSociale());
            pdstmt.setString(4, azienda.getLink());
            pdstmt.setString(5, azienda.getAreaInteresse());
            pdstmt.setInt(6, azienda.getNumeroDipendenti());
            String dbContent = StringListUtils.getStringFromList(azienda.getSettoriCompetenza());
            pdstmt.setString(7, dbContent);

            pdstmt.executeUpdate();

            azienda.setId(id);
            return azienda;
        }else return null;
    }

    public Persona addPersona(Persona persona) throws SQLException{
        int id = addUtente(persona);
        PreparedStatement pdstmt = connection.prepareStatement("INSERT INTO Persona (Utente, Cognome, CF, DDN, " +
                "F_Macroarea, Pos_Des) VALUES (?, ?, ?, ?, ?, ?)");
        pdstmt.setInt(1, id);
        pdstmt.setString(2, persona.getCognome());
        pdstmt.setString(3, persona.getCodiceFiscale());
        java.sql.Date sqlDate = java.sql.Date.valueOf(persona.getDataDiNascita().toLocalDate());
        pdstmt.setDate(4, sqlDate);
        pdstmt.setString(5, persona.getFiltroMacroarea());
        pdstmt.setString(6, persona.getPosizioneDesiderata());

        pdstmt.executeUpdate();

        persona.setId(id);
        return persona;
    }

    public Sede addSede(Sede sede) throws SQLException{
        PreparedStatement pdstmt = connection.prepareStatement("INSERT INTO Sede (Azienda, Citta, Provincia, Cap," +
                "Via, Regione, Telefono, Mail) VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        pdstmt.setInt(1, sede.getAzienda().getId());
        pdstmt.setString(2, sede.getCitta());
        pdstmt.setString(3, sede.getProvincia());
        pdstmt.setString(4, sede.getCap());
        pdstmt.setString(5, sede.getVia());
        pdstmt.setString(6, sede.getRegione());
        pdstmt.setString(7, sede.getTelefono());
        pdstmt.setString(8, sede.getMail());
        pdstmt.executeUpdate();
        ResultSet rs = pdstmt.getGeneratedKeys();
        if(rs.next()){
            sede.setId(rs.getInt(1));
            return sede;
        }
        return null;
    }

    public void aggiornaAzienda(Azienda azienda) throws SQLException{
        PreparedStatement pdstmt = connection.prepareStatement("UPDATE Azienda SET  P_IVA = ?, Rag_Soc = ?, Link = ?, ADI = ?, N_Dip = ?, Sett_Comp = ? WHERE Utente = ?", Statement.RETURN_GENERATED_KEYS);
        pdstmt.setString(1, azienda.getPartitaIVA());
        pdstmt.setString(2, azienda.getRagioneSociale());
        pdstmt.setString(3, azienda.getLink());
        pdstmt.setString(4, azienda.getAreaInteresse());
        pdstmt.setInt(5, azienda.getNumeroDipendenti());
        String dbContent = StringListUtils.getStringFromList(azienda.getSettoriCompetenza());
        pdstmt.setString(6, dbContent);
        pdstmt.setInt(7, azienda.getId());

        pdstmt.executeUpdate();
    }

    public void aggiornaPersona(Persona persona) throws SQLException{
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
          PreparedStatement pdstmt = connection.prepareStatement("SELECT u.N_Reg, u.Pass, u.Mail FROM Utente u WHERE u.Mail = ?");
          pdstmt.setString(1, mail);
          ResultSet rs = pdstmt.executeQuery();
          System.out.println(rs);
          if(rs.next()){
              if(password.equals(rs.getString(2))){
                  System.out.println("Le password sono uguali");
                  Azienda azienda = getAziendaById(rs.getInt(1));
                  Persona persona = getPersonaById(rs.getInt(1));
                  if(azienda != null)
                      return azienda;
                  else if(persona != null)
                      return persona;
              }
              else
                  System.out.println("else1");
          }
          return null;
    }

    public Persona getPersonaById(int id) throws SQLException{
        PreparedStatement pdstmt = connection.prepareStatement("SELECT u.Nome, u.Mail, u.Pass, " +
                "u.Regione, u.Provincia, u.Foto, u.CAP, u.Telefono, u.Citta, u.via, p.Cognome, p.CF, p.DDN, p.F_Macroarea," +
                "p.Pos_Des FROM Persona p, Utente u WHERE u.N_Reg = ? AND p.Utente = u.N_Reg");
        pdstmt.setInt(1, id);
        ResultSet rs = pdstmt.executeQuery();
        Persona persona = new Persona();
        if(rs.next()){
            persona.setId(id);
            persona.setNome(rs.getString(1));
            persona.setMail(rs.getString(2));
            persona.setPassword(rs.getString(3));
            persona.setRegione(rs.getString(4));
            persona.setProvincia(rs.getString(5));
            persona.setFoto(rs.getString(6));
            persona.setCap(rs.getString(7));
            persona.setTelefono(rs.getString(8));
            persona.setCitta(rs.getString(9));
            persona.setVia(rs.getString(10));
            persona.setCognome(rs.getString(11));
            persona.setCodiceFiscale(rs.getString(12));
            //java.sql.Timestamp sqlTimestamp = rs.getTimestamp(13);
            //persona.setDataDiNascita(sqlTimestamp.toLocalDateTime());
            persona.setDataDiNascita(rs.getObject(13, LocalDateTime.class));
            persona.setFiltroMacroarea(rs.getString(14));
            persona.setPosizioneDesiderata(rs.getString(15));
            return persona;
        }
        return null;
    }

    public Azienda getAziendaById(int id) throws SQLException{
        PreparedStatement pdstmt = connection.prepareStatement("SELECT u.Nome, u.Mail, u.Pass, " +
                "u.Regione, u.Provincia, u.Foto, u.CAP, u.Telefono, u.Citta, u.via, a.P_IVA, a.Rag_Soc, a.Link, a.ADI," +
                "a.N_Dip, a.Sett_Comp FROM Azienda a, Utente u WHERE u.N_Reg = ? AND a.Utente = u.N_Reg");
        pdstmt.setInt(1, id);
        ResultSet rs = pdstmt.executeQuery();
        Azienda azienda = new Azienda();
        if(rs.next()){
            azienda.setId(id);
            azienda.setNome(rs.getString(1));
            azienda.setMail(rs.getString(2));
            azienda.setPassword(rs.getString(3));
            azienda.setRegione(rs.getString(4));
            azienda.setProvincia(rs.getString(5));
            azienda.setFoto(rs.getString(6));
            azienda.setCap(rs.getString(7));
            azienda.setTelefono(rs.getString(8));
            azienda.setCitta(rs.getString(9));
            azienda.setVia(rs.getString(10));
            azienda.setPartitaIVA(rs.getString(11));
            azienda.setRagioneSociale(rs.getString(12));
            azienda.setLink(rs.getString(13));
            azienda.setAreaInteresse(rs.getString(14));
            azienda.setNumeroDipendenti(rs.getInt(15));
            List<String> settori = StringListUtils.getSplittedString(rs.getString(16));
            azienda.setSettoriCompetenza(settori);
            List<Sede> sedi = getSediByAzienda(azienda);
            azienda.setSedi(sedi);
            return azienda;
        }
        return null;
    }

    public List<Sede> getSediByAzienda(Azienda azienda) throws SQLException{
        PreparedStatement pdstmt = connection.prepareStatement("SELECT * FROM Sede WHERE Azienda = ?");
        pdstmt.setInt(1, azienda.getId());
        ResultSet rs = pdstmt.executeQuery();
        List<Sede> sedi = new ArrayList<>();
        while(rs.next()){
            Sede sede = new Sede(rs.getInt(1), rs.getString(7), rs.getString(4), rs.getString(3), rs.getString(5), rs.getString(6), rs.getString(8), getAziendaById(rs.getInt(2)), rs.getString(9));
            sedi.add(sede);
        }
        return sedi;
    }

    public void eliminaAzienda(Azienda azienda) {
        /*PreparedStatement pdstmt = connection.prepareStatement("DELETE FROM Azienda a WHERE a.Utente = ?1");
        pdstmt.setInt(1, azienda.getId());*/
    }
}
