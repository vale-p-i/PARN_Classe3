/**
 Classe per la gestione dell'Annuncio nel database.
 */

package annuncio.dao;

import candidatura.service.CandidaturaService;
import candidatura.service.CandidaturaServiceInterface;
import storage.entity.Annuncio;
import storage.entity.Azienda;
import storage.entity.Candidatura;
import utente.service.UtenteService;
import utente.service.UtenteServiceInterface;
import utils.ConPool;
import utils.StringListUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnnuncioDAO {
    private static Connection connection;

    /**
     * Costruttore vuoto della classe.
     */
    public AnnuncioDAO(){}

    /**
     * Metodo per ottenere un Annuncio dal database tramite l'ID.
     * @param id L'ID dell'Annuncio da cercare.
     * @return L'Annuncio con l'ID richiesto.
     * @throws SQLException Errore nella comunicazione con il database.
     */
    public Annuncio getAnnuncioById(int id) throws SQLException {
        connection=ConPool.getConnection();
        PreparedStatement pdstmt = connection.prepareStatement("SELECT ID, Azienda, Attivo, Sede,N_Persone,Descrizione,Scadenza,Requisiti,Keyword,Preferenze,Ruolo FROM Annuncio WHERE ID = ?");
        pdstmt.setInt(1, id);

        UtenteServiceInterface utenteService = new UtenteService();
        CandidaturaService candidaturaService = new CandidaturaService();
        Annuncio annuncio = null;

        ResultSet rs = pdstmt.executeQuery();
        connection.close();
        while (rs.next()) {
            Azienda azienda = utenteService.getAziendaById(rs.getInt(2));
            annuncio = new Annuncio(
                    id,
                    azienda,
                    rs.getBoolean(3),
                    utenteService.getSedeById(azienda, rs.getInt(4)),
                    rs.getInt(5),
                    rs.getString(6),
                    rs.getDate(7).toLocalDate().atStartOfDay(),
                    StringListUtils.getSplittedString(rs.getString(8)),
                    StringListUtils.getSplittedString(rs.getString(9)),
                    StringListUtils.getSplittedString(rs.getString(10)),
                    rs.getString(11),
                    new ArrayList<Candidatura>()
            );
        }
        annuncio.setCandidature(candidaturaService.getCandidatureByAnnuncio(annuncio));
        return annuncio;
    }

    /**
     * Metodo per ottenere una lista di Annunci dal database in base allo stato richiesto.
     * @param in_corso Lo stato degli Annunci richiesti. Accetta solo i valori "In corso", "Scaduto" e "Chiuso".
     * @return Una lista di Annunci con lo stato richiesto.
     * @throws SQLException Errore nella comunicazione con il database.
     * @throws IllegalArgumentException Se lo stato richiesto non è valido.
     */
    public List<Annuncio> getAnnunciByStato(String in_corso) throws SQLException {
        connection=ConPool.getConnection();

        String query = "";
        UtenteServiceInterface utenteService = new UtenteService();
        CandidaturaService candidaturaService = new CandidaturaService();
        List<Annuncio> result = new ArrayList<>();

        if (in_corso.toLowerCase().contains("in") && in_corso.toLowerCase().contains("corso"))
            query = "SELECT * FROM Annuncio a WHERE a.Attivo = 1 AND a.Scadenza > GETDATE()";
        else if (in_corso.toLowerCase().contains("scaduto"))
            query = "SELECT * FROM Annuncio a WHERE a.Attivo = 1 AND a.Scadenza < GETDATE()";
        else if (in_corso.toLowerCase().contains("chiuso"))
            query = "SELECT * FROM Annuncio a WHERE a.Attivo = 0";
        else
            throw new IllegalArgumentException();

        PreparedStatement pdstmt = connection.prepareStatement(query);
        ResultSet rs = pdstmt.executeQuery();
        connection.close();
        while (rs.next()) {
            Azienda azienda=utenteService.getAziendaById(rs.getInt(2));
            Annuncio annuncio = new Annuncio (
                    rs.getInt(1),
                    azienda,
                    rs.getBoolean(3),
                    utenteService.getSedeById(azienda,rs.getInt(4)),
                    rs.getInt(5),
                    rs.getString(6),
                    rs.getDate(7).toLocalDate().atStartOfDay(),
                    StringListUtils.getSplittedString(rs.getString(8)),
                    StringListUtils.getSplittedString(rs.getString(9)),
                    StringListUtils.getSplittedString(rs.getString(10)),
                    rs.getString(11),
                    null
            );
            annuncio.setCandidature(candidaturaService.getCandidatureByAnnuncio(annuncio));
            result.add(annuncio);
        }
        return result;
    }


    /**
     * Crea un nuovo annuncio nel database.
     * @param annuncio l'annuncio da creare
     * @throws SQLException se si verifica un errore di accesso al database
     */
    public void creaAnnuncio(Annuncio annuncio) throws SQLException {
        connection=ConPool.getConnection();

        PreparedStatement pdstmt = connection.prepareStatement(
                "INSERT INTO Annuncio(ID, Azienda, Attivo, Sede, N_Persone, Descrizione, Scadenza, Requisiti, Keyword, Preferenze, Ruolo)"+
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        pdstmt.setInt(1, annuncio.getId());
        pdstmt.setInt(2, annuncio.getAzienda().getId());
        pdstmt.setBoolean(3, annuncio.isAttivo());
        pdstmt.setInt(4, annuncio.getSede().getId());
        pdstmt.setInt(5, annuncio.getNumeroPersone());
        pdstmt.setString(6, annuncio.getDescrizione());
        pdstmt.setDate(7, java.sql.Date.valueOf(annuncio.getDataScadenza().toLocalDate().toString()));
        pdstmt.setString(8, StringListUtils.getStringFromList(annuncio.getRequisiti()));
        pdstmt.setString(9, StringListUtils.getStringFromList(annuncio.getKeyword()));
        pdstmt.setString(10, StringListUtils.getStringFromList(annuncio.getPreferenze()));
        pdstmt.setString(11, annuncio.getRuolo());
        pdstmt.executeQuery();
        connection.close();
    }

    /**
     * Modifica l'annuncio specificato nel database.
     * @param annuncio l'annuncio da modificare
     * @throws SQLException se si verifica un errore di accesso al database
     */
    public void modificaAnnuncio(Annuncio annuncio) throws SQLException{
        connection=ConPool.getConnection();

        PreparedStatement pdstmt = connection.prepareStatement(
                "UPDATE Annuncio a SET a.Azienda = ?, a.Attivo = ?, a.Sede = ?, a.N_Persone = ?, " +
                        "a.Descrizione = ?, a.Scadenza = ?, a.Requisiti = ?, a.Keyword = ?, a.Preferenze = ?, " +
                        "a.Ruolo = ? " +
                        "WHERE a.ID = ?");
        pdstmt.setInt(1, annuncio.getAzienda().getId());
        pdstmt.setBoolean(2, annuncio.isAttivo());
        pdstmt.setInt(3, annuncio.getSede().getId());
        pdstmt.setInt(4, annuncio.getNumeroPersone());
        pdstmt.setString(5, annuncio.getDescrizione());
        pdstmt.setDate(6, java.sql.Date.valueOf(annuncio.getDataScadenza().toLocalDate().toString()));
        pdstmt.setString(7, StringListUtils.getStringFromList(annuncio.getRequisiti()));
        pdstmt.setString(8, StringListUtils.getStringFromList(annuncio.getKeyword()));
        pdstmt.setString(9, StringListUtils.getStringFromList(annuncio.getPreferenze()));
        pdstmt.setString(10, annuncio.getRuolo());
        pdstmt.setInt(11, annuncio.getId());
        pdstmt.executeUpdate();
        connection.close();
    }

    /**
     * Elimina l'annuncio con l'ID specificato dal database.
     * @param  annuncio l'ID dell'annuncio da eliminare
     * @throws SQLException se si verifica un errore di accesso al database
     */
    public void eliminaAnnuncio(Annuncio annuncio) throws SQLException {
        connection=ConPool.getConnection();

        PreparedStatement pdstmt = connection.prepareStatement(
                "DELETE FROM Annuncio a WHERE a.ID = ?");
        pdstmt.setInt(1, annuncio.getId());
        pdstmt.execute();
        connection.close();
    }

    /**
     * Chiude l'annuncio specificato dal database.
     * @param annuncio l'annuncio da eliminare
     * @throws SQLException se si verifica un errore di accesso al database
     */
    public void chiusuraAnnuncio(Annuncio annuncio) throws SQLException {
        connection=ConPool.getConnection();

        PreparedStatement pdstmt = connection.prepareStatement(
                "UPDATE Annuncio SET Attivo = ? WHERE ID = ?");
        pdstmt.setBoolean(1, false);
        pdstmt.setInt(2, annuncio.getId());
        pdstmt.execute();
        connection.close();
    }

    /**
     * Restituisce tutti gli annunci pubblicati da un'azienda.
     * @param azienda l'azienda di cui si vogliono recuperare gli annunci
     * @return una lista contenente tutti gli annunci pubblicati dall'azienda
     */
    public List<Annuncio> getAnnunciByAzienda(Azienda azienda) throws SQLException {
        connection=ConPool.getConnection();
        PreparedStatement pdstmt = connection.prepareStatement(
               "SELECT ID, Azienda, Attivo, Sede,N_Persone,Descrizione,Scadenza,Requisiti,Keyword,Preferenze,Ruolo FROM Annuncio a WHERE a.Azienda = ?");
        pdstmt.setInt(1, azienda.getId());
        ResultSet rs = pdstmt.executeQuery();
        connection.close();
        List<Annuncio> results = new ArrayList<>();
        UtenteServiceInterface utenteService=new UtenteService();
        CandidaturaServiceInterface candidaturaService=new CandidaturaService();
        while (rs.next()) {
            Annuncio annuncio = new Annuncio(
                    rs.getInt(1),
                    azienda,
                    rs.getBoolean(3),
                    utenteService.getSedeById(azienda, rs.getInt(4)),
                    rs.getInt(5),
                    rs.getString(6),
                    rs.getDate(7).toLocalDate().atStartOfDay(),
                    StringListUtils.getSplittedString(rs.getString(8)),
                    StringListUtils.getSplittedString(rs.getString(9)),
                    StringListUtils.getSplittedString(rs.getString(10)),
                    rs.getString(11),
                    null);
            annuncio.setCandidature(candidaturaService.getCandidatureByAnnuncio(annuncio));
            results.add(annuncio);
        }
        return results;

    }
}
