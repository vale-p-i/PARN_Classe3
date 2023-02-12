package candidatura.dao;

import annuncio.service.AnnuncioService;
import storage.entity.Annuncio;
import storage.entity.Candidatura;
import storage.entity.Curriculum;
import storage.entity.Persona;
import utils.ConPool;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CandidaturaDAO {

    public List<Candidatura> getCandidatueByPersona(Persona persona) throws SQLException {
        List<Candidatura> result = new ArrayList<>();
        AnnuncioService annuncioService = new AnnuncioService();
        CurriculumService curriculumService = new CurriculumService();

        Connection connection = ConPool.getConnection();
        Statement stmt = (Statement) connection.createStatement();
        PreparedStatement pdstmt = connection.prepareStatement("SELECT * FROM Candidatura c WHERE c.Persona = ?1");
        pdstmt.setInt(1, persona.getId());

        ResultSet rs = pdstmt.executeQuery();
        while (rs.next()) {
            result.add(
                    new Candidatura(
                         persona,
                        (Annuncio) annuncioService.getAnnuncioById(rs.getInt(1)),
                        (Curriculum) curriculumService.getCurriculumByPersona(persona)));
                        rs.getDate(3);
        }
        return result;
    }

    public List<Candidatura> getCandidatureByAnnuncio(Annuncio annuncio) throws SQLException {
        List<Candidatura> result = new ArrayList<>();
        PersonaService personaService = new PersonaService();
        CurriculumService curriculumService = new CurriculumService();
        PersonaService personaService = new PersonaService();

        Connection connection = ConPool.getConnection();
        Statement stmt = (Statement) connection.createStatement();
        PreparedStatement pdstmt = connection.prepareStatement("SELECT * FROM Candidatura c WHERE c.Annuncio = ?1");
        pdstmt.setInt(1, annuncio.getId());

        ResultSet rs = pdstmt.executeQuery();
        while (rs.next()) {
            result.add(
                    new Candidatura(
                            (Persona) personaService.getPersonaById(rs.getInt(2)),
                            annuncio,
                            curriculumService.getCurriculumByPersona(personaService.getPersonaById(rs.getInt(2))),
                            rs.getDate(3)
                    )
            );
        }
        return result;
    }

    public Candidatura getCandidaturaByPersonaAndAnnuncio(Persona persona, Annuncio annuncio) throws SQLException {
        Connection connection = ConPool.getConnection();
        Statement stmt = (Statement) connection.createStatement();
        PreparedStatement pdstmt = connection.prepareStatement("SELECT * FROM Candidatura c WHERE c.Persona = ?1 AND c.Annuncio = ?2");
        pdstmt.setInt(1, annuncio.getId());

        ResultSet rs = pdstmt.executeQuery();

        return new Candidatura(
                persona,
                annuncio,
                CurriculumService.getCurriculumByPersona(persona),
                rs.getDate(3)
        );
    }

    public static void creaCandidatura(Candidatura candidatura) throws SQLException {
        Connection connection = ConPool.getConnection();
        Statement stmt = (Statement) connection.createStatement();
        PreparedStatement pdstmt = connection.prepareStatement(
                "INSERT INTO Candidatura(Annuncio, Persona, Data_Pub) VALUES ($1, $2, $3)");
        pdstmt.setInt(1, candidatura.getAnnuncio().getId());
        pdstmt.setInt(2, candidatura.getPersona().getId());
        pdstmt.setDate(3, candidatura.getData());

        pdstmt.executeUpdate();
    }

    public static void eliminaCandidatura(Candidatura candidatura) throws SQLException {
        Connection connection = ConPool.getConnection();
        Statement stmt = (Statement) connection.createStatement();
        PreparedStatement pdstmt = connection.prepareStatement(
                "DELETE FROM Candidatura c WHERE c.Annuncio = $1 AND c.Persona = $2");
        pdstmt.setInt(1, candidatura.getAnnuncio().getId());
        pdstmt.setInt(2, candidatura.getPersona().getId());

        pdstmt.execute();
    }


}
