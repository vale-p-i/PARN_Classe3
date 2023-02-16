package candidatura.service;

import candidatura.dao.CandidaturaDAO;
import storage.entity.Annuncio;
import storage.entity.Candidatura;
import storage.entity.Persona;

import java.sql.SQLException;
import java.util.List;

public class CandidaturaService implements CandidaturaServiceInterface{

    private final CandidaturaDAO candidaturaDAO = new CandidaturaDAO();

    @Override
    public List<Candidatura> getCandidatureByAnnuncio(Annuncio annuncio) {
        try {
            return candidaturaDAO.getCandidatureByAnnuncio(annuncio);
        } catch (SQLException e){
            return null;
        }
    }

    @Override
    public List<Candidatura> getCandidatureByPersona(Persona persona) {
        try {
            return candidaturaDAO.getCandidatureByPersona(persona);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean creaCandidatura(Candidatura candidatura) {
        try {
            candidaturaDAO.creaCandidatura(candidatura);
        }
        catch (SQLException e){
            return false;
        }

        Persona persona = candidatura.getPersona();
        persona.getCandidature().add(candidatura);
        return true;
    }

    /**
     * @param candidatura 
     * @return
     */
    @Override
    public boolean eliminaCandidatura(Candidatura candidatura) {
        try {
            candidaturaDAO.eliminaCandidatura(candidatura);
            Persona persona = candidatura.getPersona();
            persona.getCandidature().remove(candidatura);
            return true;
        }
        catch (SQLException e){
            return false;
        }
    }


}
