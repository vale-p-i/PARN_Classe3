package candidatura.dao;

import annuncio.service.AnnuncioService;
import annuncio.service.AnnuncioServiceInterface;
import curriculum.service.CurriculumService;
import curriculum.service.CurriculumServiceInterface;
import storage.entity.Annuncio;
import storage.entity.Candidatura;
import storage.entity.Curriculum;
import storage.entity.Persona;
import utente.service.UtenteService;
import utente.service.UtenteServiceInterface;
import utils.ConPool;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CandidaturaDAO {

    private static Connection connection;

    public CandidaturaDAO() {}


    public List<Candidatura> getCandidatueByPersona(Persona persona) throws SQLException {
        List<Candidatura> result = new ArrayList<>();
        AnnuncioServiceInterface annuncioService = new AnnuncioService();
        CurriculumServiceInterface curriculumService = new CurriculumService();
        if (connection.isClosed())
            connection=ConPool.getConnection();
        Statement stmt = (Statement) connection.createStatement();
        PreparedStatement pdstmt = connection.prepareStatement("SELECT * FROM Candidatura WHERE Persona = ?");
        pdstmt.setInt(1, persona.getId());

        ResultSet rs = pdstmt.executeQuery();
        connection.close();
        while (rs.next()) {
            result.add(
                    new Candidatura(
                         persona,
                        (Annuncio) annuncioService.getAnnuncioById(rs.getInt(1)),
                        (Curriculum) curriculumService.getCurriculumByPersona(persona),
                        rs.getDate(3).toLocalDate().atStartOfDay()));
        }
        return result;
    }

    public List<Candidatura> getCandidatureByAnnuncio(Annuncio annuncio) throws SQLException {
        List<Candidatura> result = new ArrayList<>();
        UtenteServiceInterface personaService = new UtenteService();
        if (connection.isClosed())
            connection=ConPool.getConnection();
        Statement stmt = (Statement) connection.createStatement();
        PreparedStatement pdstmt = connection.prepareStatement("SELECT * FROM Candidatura WHERE Annuncio = ?");
        pdstmt.setInt(1, annuncio.getId());

        ResultSet rs = pdstmt.executeQuery();
        connection.close();
        while (rs.next()) {
            Persona tmp=personaService.getPersonaById(rs.getInt(2));
            result.add(
                    new Candidatura(
                            tmp,
                            annuncio,
                            tmp.getCurriculum(),
                            rs.getDate(3).toLocalDate().atStartOfDay()
                    )
            );
        }
        return result;
    }

    public Candidatura getCandidaturaByPersonaAndAnnuncio(Persona persona, Annuncio annuncio) throws SQLException {

        CurriculumServiceInterface curriculumServiceInterface = new CurriculumService();
        if (connection.isClosed())
            connection=ConPool.getConnection();
        PreparedStatement pdstmt = connection.prepareStatement("SELECT Annuncio, Persona, Data_Pub FROM Candidatura" +
                " WHERE Persona = ? AND Annuncio = ?");
        pdstmt.setInt(1, persona.getId());
        pdstmt.setInt(2, annuncio.getId());
        ResultSet rs = pdstmt.executeQuery();
        connection.close();
        Candidatura candidatura = null;

        while (rs.next()) {
            candidatura = new Candidatura(
                    persona,
                    annuncio,
                    null, //curriculumServiceInterface.getCurriculumByPersona(persona), //TODO: rimuovere null quando vale ha finito
                    rs.getObject(3, LocalDateTime.class)
            );
        }
        return candidatura;
    }

    public void creaCandidatura(Candidatura candidatura) throws SQLException {
        if (connection.isClosed())
            connection=ConPool.getConnection();
        Statement stmt = (Statement) connection.createStatement();
        PreparedStatement pdstmt = connection.prepareStatement(
                "INSERT INTO Candidatura(Annuncio, Persona, Data_Pub) VALUES (?, ?, ?)");
        pdstmt.setInt(1, candidatura.getAnnuncio().getId());
        pdstmt.setInt(2, candidatura.getPersona().getId());
        pdstmt.setDate(3, java.sql.Date.valueOf(candidatura.getData().toLocalDate()));

        pdstmt.executeUpdate();
        connection.close();
    }

    public void eliminaCandidatura(Candidatura candidatura) throws SQLException {
        if (connection.isClosed())
            connection=ConPool.getConnection();
        Statement stmt = (Statement) connection.createStatement();
        PreparedStatement pdstmt = connection.prepareStatement(
                "DELETE FROM Candidatura WHERE Annuncio = ? AND Persona = ?");
        pdstmt.setInt(1, candidatura.getAnnuncio().getId());
        pdstmt.setInt(2, candidatura.getPersona().getId());

        pdstmt.execute();
        connection.close();
    }


}
