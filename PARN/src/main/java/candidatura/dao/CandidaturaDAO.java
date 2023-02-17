package candidatura.dao;

import annuncio.service.AnnuncioService;
import annuncio.service.AnnuncioServiceInterface;
import curriculum.service.CurriculumService;
import curriculum.service.CurriculumServiceInterface;
import storage.entity.Annuncio;
import storage.entity.Candidatura;
import storage.entity.Persona;
import utente.service.UtenteService;
import utente.service.UtenteServiceInterface;
import utils.ConPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CandidaturaDAO {

    private static Connection connection;

    public CandidaturaDAO() {}
    
    /**
     * Ritorna una lista di Candidature alle quali la persona ha sottoscritto. Altrimenti null
     * @param persona oggetto Persona
     * @return List\<Candidatura\>
     * @throws SQLException in caso ci sia un eccezione nell'esecuzione della query
     */
    public List<Candidatura> getCandidatureByPersona(Persona persona) throws SQLException {
        List<Candidatura> result = new ArrayList<>();
        AnnuncioServiceInterface annuncioService = new AnnuncioService();
        CurriculumServiceInterface curriculumService = new CurriculumService();
        connection=ConPool.getConnection();

        PreparedStatement pdstmt = connection.prepareStatement("SELECT * FROM Candidatura WHERE Persona = ?");
        pdstmt.setInt(1, persona.getId());

        ResultSet rs = pdstmt.executeQuery();
        while (rs.next()) {
            result.add(
                    new Candidatura(
                         persona,
                        (Annuncio) annuncioService.getAnnuncioById(rs.getInt(1)),
                        persona.getCurriculum(),
                        rs.getDate(3).toLocalDate()));
        }
        return result;
    }

    /**
     * Ritorna una lista di Candidature appartenenti a un annuncio. Altrimenti null
     * @param annuncio oggetto Annuncio
     * @return List\<Candidatura\>
     * @throws SQLException in caso ci sia un eccezione nell'esecuzione della query
     */
    public List<Candidatura> getCandidatureByAnnuncio(Annuncio annuncio) throws SQLException {
        List<Candidatura> result = new ArrayList<>();
        UtenteServiceInterface personaService = new UtenteService();
        connection=ConPool.getConnection();

        PreparedStatement pdstmt = connection.prepareStatement("SELECT * FROM Candidatura WHERE Annuncio = ?");
        pdstmt.setInt(1, annuncio.getId());

        ResultSet rs = pdstmt.executeQuery();
        while (rs.next()) {
            Persona tmp=personaService.getPersonaById(rs.getInt(2));
            result.add(
                    new Candidatura(
                            tmp,
                            annuncio,
                            tmp.getCurriculum(),
                            rs.getDate(3).toLocalDate()
                    )
            );
        }
        return result;
    }

    /**
     * Persiste una candidatura all'interno del database
     * @param candidatura oggetto Candidatura
     * @throws SQLException in caso ci sia un eccezione nell'esecuzione della query
     */
    public void creaCandidatura(Candidatura candidatura) throws SQLException {
        connection=ConPool.getConnection();

        PreparedStatement pdstmt = connection.prepareStatement(
                "INSERT INTO Candidatura(Annuncio, Persona, Data_Pub) VALUES (?, ?, ?)");
        pdstmt.setInt(1, candidatura.getAnnuncio().getId());
        pdstmt.setInt(2, candidatura.getPersona().getId());
        pdstmt.setDate(3, java.sql.Date.valueOf(candidatura.getData()));

        pdstmt.executeUpdate();
    }

    /**
     * Elimina una candidatura dal database
     * @param candidatura oggetto Candidatura
     * @throws SQLException in caso ci sia un eccezione nell'esecuzione della query
     */
    public void eliminaCandidatura(Candidatura candidatura) throws SQLException {
        connection=ConPool.getConnection();

        PreparedStatement pdstmt = connection.prepareStatement(
                "DELETE FROM Candidatura WHERE Annuncio = ? AND Persona = ?");
        pdstmt.setInt(1, candidatura.getAnnuncio().getId());
        pdstmt.setInt(2, candidatura.getPersona().getId());

        pdstmt.execute();
    }


}
