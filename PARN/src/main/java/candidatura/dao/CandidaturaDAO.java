package candidatura.dao;

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

        Connection connection = ConPool.getConnection();
        Statement stmt = (Statement) connection.createStatement();
        PreparedStatement pdstmt = connection.prepareStatement("SELECT * FROM Candidatura c WHERE c.Persona = ?1");
        pdstmt.setInt(1, persona.getId());

        ResultSet rs = pdstmt.executeQuery();
        while (rs.next()) {
            result.add(
                    new Candidatura(
                         persona,
                        (Annuncio) AnnuncioService.getAnnuncioById(rs.getInt(1)),
                        (Curriculum) CurriculumService.getCurriculumByPersona(persona)));
                        rs.getDate(3);
        }
        return result;
    }

    public List<Candidatura> getCandidatureByAnnuncio(Annuncio annuncio) throws SQLException {
        List<Candidatura> result = new ArrayList<>();

        Connection connection = ConPool.getConnection();
        Statement stmt = (Statement) connection.createStatement();
        PreparedStatement pdstmt = connection.prepareStatement("SELECT * FROM Candidatura c WHERE c.Annuncio = ?1");
        pdstmt.setInt(1, annuncio.getId());

        ResultSet rs = pdstmt.executeQuery();
        while (rs.next()) {
            result.add(
                    new Candidatura(
                            (Persona) PersonaService.getPersonaById(rs.getInt(2)),
                            annuncio,
                            CurriculumService.getCurriculumByPersona(PersonaService.getPersonaById(rs.getInt(2))),
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


}
