package curriculum.dao;

import org.checkerframework.checker.units.qual.C;
import storage.entity.*;
import utente.service.UtenteService;
import utente.service.UtenteServiceInterface;
import utils.ConPool;
import utils.StringListUtils;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CurriculumDAO {

    public Curriculum getCurriculumByPersona(Persona persona) throws SQLException {
        int id = persona.getId();

        Connection connection = ConPool.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT Soft_Skills" +
                "FROM Curriculum" +
                "WHERE Curriculum = ?");
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()) {

            String skills = resultSet.getString(1);
            List<String> listaSkills = StringListUtils.getSplittedString(skills);
            Curriculum curriculum = new Curriculum(persona, listaSkills);
            curriculum.setLingue(getLingueByCurriculum(curriculum));
            curriculum.setIstruzioni(getIstruzioniByCurriculum(curriculum));
            curriculum.setEsperienze(getEsperienzeLavorativeByCurriculum(curriculum));
            return curriculum;
        }

        return null;
    }
    public List<Curriculum> getAllCurriculum() throws SQLException {
        UtenteServiceInterface utenteServiceInterface = new UtenteService();
        List<Curriculum> listaCurriculum = new ArrayList<Curriculum>();

        Connection connection = ConPool.getConnection();
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM Curriculum");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            //prendo la persona
            int id = resultSet.getInt(1);
            Persona persona = utenteServiceInterface.getPersonaById(id);
            //Prendo le soft_skills
            String skills = resultSet.getString(2);
            List<String> listaSkills = StringListUtils.getSplittedString(skills);

            Curriculum curriculum = new Curriculum(persona, listaSkills);
            curriculum.setEsperienze(getEsperienzeLavorativeByCurriculum(curriculum));
            curriculum.setIstruzioni(getIstruzioniByCurriculum(curriculum));
            curriculum.setLingue(getLingueByCurriculum(curriculum));
            listaCurriculum.add(curriculum);
        }

        connection.close();
        return  listaCurriculum;
    }
    public List<Lingua> getLingueByCurriculum(Curriculum curriculum) throws SQLException {
        int id = curriculum.getPersona().getId();
        List<Lingua> listaLingue = new ArrayList<Lingua>();

        Connection connection = ConPool.getConnection();
        PreparedStatement statement = connection.
                prepareStatement("SELECT Nome, Livello " +
                        "FROM Lingua " +
                        "WHERE Curriculum = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Lingua lingua = new Lingua(curriculum);
            lingua.setNome(resultSet.getString(1));
            lingua.setLivello(resultSet.getString(2));

            listaLingue.add(lingua);
        }

        connection.close();
        return listaLingue;
    }
    public List<Istruzione> getIstruzioniByCurriculum(Curriculum curriculum) throws SQLException {
        int id = curriculum.getPersona().getId();
        List<Istruzione> listaIstruzione = new ArrayList<Istruzione>();

        Connection connection = ConPool.getConnection();
        PreparedStatement statement = connection.
                prepareStatement("SELECT Tipo, Istituto, DDI, DDF, Qualifica " +
                        "FROM Istruzione" +
                        "WHERE Curriculum = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            Istruzione istruzione = new Istruzione(curriculum);
            istruzione.setTipo(resultSet.getString(1));
            istruzione.setIstituto(resultSet.getString(2));

            LocalDateTime ddi = resultSet.getObject(3, LocalDateTime.class);
            istruzione.setDataInizio(ddi);

            LocalDateTime ddf = resultSet.getObject(4, LocalDateTime.class);
            istruzione.setDataInizio(ddf);

            istruzione.setQualifica(resultSet.getString(5));
            listaIstruzione.add(istruzione);
        }

        connection.close();
        return listaIstruzione;
    }
    public List<EsperienzaLavorativa> getEsperienzeLavorativeByCurriculum(Curriculum curriculum) throws SQLException {
        int id = curriculum.getPersona().getId();
        List<EsperienzaLavorativa> listaEsperienze = new ArrayList<EsperienzaLavorativa>();

        Connection connection = ConPool.getConnection();
        PreparedStatement statement = connection.
                prepareStatement("SELECT Nome_Azienda, Tipo_Impiego, Mansioni, Datore, Contatto, Tipo_Azienda, DDI, DDF " +
                        "FROM Esperienza " +
                        "WHERE Curriculum = ?");
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            EsperienzaLavorativa esperienzaLavorativa = new EsperienzaLavorativa(curriculum);
            esperienzaLavorativa.setNomeAzienda(resultSet.getString(1));
            esperienzaLavorativa.setTipoImpiego(resultSet.getString(2));

            List<String> mansioni = StringListUtils.getSplittedString(resultSet.getString(3));
            esperienzaLavorativa.setMansioniPrincipali(mansioni);

            esperienzaLavorativa.setDatore(resultSet.getString(4));
            esperienzaLavorativa.setContatto(resultSet.getString(5));
            esperienzaLavorativa.setTipoAzienda(resultSet.getString(6));

            LocalDateTime ddi = resultSet.getObject(7, LocalDateTime.class);
            esperienzaLavorativa.setDataInizio(ddi);

            LocalDateTime ddf = resultSet.getObject(8, LocalDateTime.class);
            esperienzaLavorativa.setDataFine(ddf);
            listaEsperienze.add(esperienzaLavorativa);
        }

        connection.close();
        return listaEsperienze;
    }
    public void addCurriculum(Curriculum curriculum) throws SQLException {
        Connection connection = ConPool.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Curriculum (Persona, Soft_Skills) VALUES (?, ?)");
        statement.setInt(1, curriculum.getPersona().getId());
        String skills = StringListUtils.getStringFromList(curriculum.getSoftSkill());
        statement.setString(2, skills);

        statement.executeUpdate();
        connection.close();
    }
    public void updateCurriculum(Curriculum curriculum) throws SQLException {
        Connection connection = ConPool.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE Curriculum SET Soft_Skills = ?" +
                "WHERE Persona = ?");

        //parametri set
        String skills = StringListUtils.getStringFromList(curriculum.getSoftSkill());
        statement.setString(1, skills);

        //parametri where
        statement.setInt(2, curriculum.getPersona().getId());

        statement.executeUpdate();
        connection.close();
    }
    public void addEsperienzaLavorativa(EsperienzaLavorativa esperienzaLavorativa) throws SQLException {
        Connection connection = ConPool.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Esperienza (Curriculum, Nome_Azienda, Tipo_Impiego, Mansioni, Datore, Contatto, Tipo_Azienda, DDI, DDF)" +
                " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");

        int id = esperienzaLavorativa.getCurriculum().getPersona().getId();
        statement.setInt(1, id);
        statement.setString(2, esperienzaLavorativa.getNomeAzienda());
        statement.setString(3, esperienzaLavorativa.getTipoImpiego());
        String mansioni = StringListUtils.getStringFromList(esperienzaLavorativa.getMansioniPrincipali());
        statement.setString(4, mansioni);
        statement.setString(5, esperienzaLavorativa.getDatore());
        statement.setString(6, esperienzaLavorativa.getContatto());
        statement.setString(7, esperienzaLavorativa.getTipoAzienda());
        java.sql.Date ddi = java.sql.Date.valueOf(esperienzaLavorativa.getDataInizio().toLocalDate());
        statement.setDate(8, ddi);
        if(esperienzaLavorativa.getDataFine() == null){
            statement.setNull(9,Types.DATE);
        } else{
            java.sql.Date ddf = java.sql.Date.valueOf(esperienzaLavorativa.getDataFine().toLocalDate());
            statement.setDate(9, ddf);
        }

        statement.executeUpdate();
        connection.close();
    }
    public  void removeEsperienzaLavorativa(EsperienzaLavorativa esperienzaLavorativa) throws SQLException {
        Connection connection = ConPool.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Esperienza WHERE " +
                "Curriculum = ?, Nome_Azienda = ?, Tipo_Impiego = ?");
        statement.setInt(1, esperienzaLavorativa.getCurriculum().getPersona().getId());
        statement.setString(2, esperienzaLavorativa.getNomeAzienda());
        statement.setString(3, esperienzaLavorativa.getTipoImpiego());

        statement.executeUpdate();
        connection.close();
    }
    public void updateEsperienzaLavorativa(EsperienzaLavorativa esperienzaLavorativa) throws SQLException {
        Connection connection = ConPool.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE Esperienza SET " +
                "Mansioni = ?, Datore = ?, Contatto = ?, Tipo_Azienda = ?, DDI = ?, DDF, = ? WHERE Curriculum = ?, Nome_Azienda = ?, Tipo_Impiego = ?");

        //parametri set
        String mansioni = StringListUtils.getStringFromList(esperienzaLavorativa.getMansioniPrincipali());
        statement.setString(1, mansioni);
        statement.setString(2, esperienzaLavorativa.getDatore());
        statement.setString(3, esperienzaLavorativa.getContatto());
        statement.setString(4, esperienzaLavorativa.getTipoAzienda());
        java.sql.Date ddi = java.sql.Date.valueOf(esperienzaLavorativa.getDataInizio().toLocalDate());
        statement.setDate(5, ddi);
        if(esperienzaLavorativa.getDataFine() == null){
            statement.setNull(6,Types.DATE);
        } else{
            java.sql.Date ddf = java.sql.Date.valueOf(esperienzaLavorativa.getDataFine().toLocalDate());
            statement.setDate(6, ddf);
        }

        //parametri where
        statement.setInt(7, esperienzaLavorativa.getCurriculum().getPersona().getId());
        statement.setString(8, esperienzaLavorativa.getNomeAzienda());
        statement.setString(9, esperienzaLavorativa.getTipoImpiego());

        statement.executeUpdate();
        connection.close();
    }
    public void addIstruzione(Istruzione istruzione) throws SQLException {
        Connection connection = ConPool.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Istruzione (Curriculum, Tipo, Istituto, DDI, DDF, Qualifica)" +
                " VALUES (?, ?, ?, ?, ?, ?)");
        int id = istruzione.getCurriculum().getPersona().getId();
        statement.setInt(1, id);
        statement.setString(2, istruzione.getTipo());
        statement.setString(3, istruzione.getIstituto());
        java.sql.Date ddi = java.sql.Date.valueOf(istruzione.getDataInizio().toLocalDate());
        statement.setDate(4, ddi);
        if(istruzione.getDataFine() == null){
            statement.setNull(5,Types.DATE);
        } else{
            java.sql.Date ddf = java.sql.Date.valueOf(istruzione.getDataFine().toLocalDate());
            statement.setDate(5, ddf);
        }
        statement.setString(6, istruzione.getQualifica());

        statement.executeUpdate();
        connection.close();
    }
    public void removeIstruzione(Istruzione istruzione) throws SQLException {
        Connection connection = ConPool.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Istruzione WHERE " +
                "Curriculum = ?, Tipo = ?, Istituto = ?");
        statement.setInt(1, istruzione.getCurriculum().getPersona().getId());
        statement.setString(2, istruzione.getTipo());
        statement.setString(3, istruzione.getIstituto());

        statement.executeUpdate();
        connection.close();
    }
    public void updateIstruzione(Istruzione istruzione) throws SQLException {
        Connection connection = ConPool.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE Istruzione SET " +
                "DDI = ?, DDF = ?, Qualifica = ? WHERE Curriculum = ?, Tipo = ?, Istituto = ?");

        //parametri set
        java.sql.Date ddi = java.sql.Date.valueOf(istruzione.getDataInizio().toLocalDate());
        statement.setDate(1, ddi);
        if(istruzione.getDataFine() == null){
            statement.setNull(2,Types.DATE);
        } else{
            java.sql.Date ddf = java.sql.Date.valueOf(istruzione.getDataFine().toLocalDate());
            statement.setDate(2, ddf);
        }
        statement.setString(3, istruzione.getQualifica());

        //parametri where
        statement.setInt(4, istruzione.getCurriculum().getPersona().getId());
        statement.setString(5, istruzione.getTipo());
        statement.setString(6, istruzione.getIstituto());

        statement.executeUpdate();
        connection.close();
    }
    public void addLingua(Lingua lingua) throws SQLException {
        Connection connection = ConPool.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Lingua (Curriculum, Nome, Livello) VALUES (?, ?, ?)");
        int id = lingua.getCurriculum().getPersona().getId();
        statement.setInt(1, id);
        statement.setString(2, lingua.getNome());
        statement.setString(3, lingua.getLivello());

        statement.executeUpdate();
        connection.close();
    }
    public void removeLingua(Lingua lingua) throws SQLException {
        Connection connection = ConPool.getConnection();
        PreparedStatement statement = connection.prepareStatement("DELETE FROM Lingua WHERE Curriculum = ?, Nome = ?");
        statement.setInt(1, lingua.getCurriculum().getPersona().getId());
        statement.setString(2, lingua.getNome());

        statement.executeUpdate();
        connection.close();
    }
    public void updateLingua(Lingua lingua) throws SQLException {
        Connection connection = ConPool.getConnection();
        PreparedStatement statement = connection.prepareStatement("UPDATE Lingua SET Livello = ? WHERE Curriculum = ?, Nome = ?");

        //parametri set
        statement.setString(1, lingua.getLivello());

        //parametri where
        statement.setInt(2, lingua.getCurriculum().getPersona().getId());
        statement.setString(3, lingua.getNome());

        statement.executeUpdate();
        connection.close();
    }
}
