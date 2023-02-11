package storage.dao;

import storage.entity.Annuncio;
import storage.entity.Candidatura;
import storage.entity.Curriculum;
import storage.entity.Persona;
import utils.ConPool;

import java.sql.*;
import java.time.LocalDateTime;

public class CandidaturaDAO {

    public static Candidatura getCandidaturaById(Long ID) throws SQLException {

        Connection connection = ConPool.getConnection();
        Statement stmt = (Statement) connection.createStatement();
        PreparedStatement pdstmt = connection.prepareStatement("SELECT * FROM Candidatura WHERE id = $1");
        pdstmt.setLong(1, ID);
        ResultSet rs = pdstmt.executeQuery();

        return createCandidaturaFromResultSet(rs);
    }

    private static Candidatura createCandidaturaFromResultSet(ResultSet rs) throws SQLException {
        return new Candidatura(
                (Persona)rs.getObject(1), (Annuncio) rs.getObject(2), (Curriculum) rs.getObject(3), (LocalDateTime) rs.getObject(4)
        );

    }



}
