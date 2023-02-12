package candidatura.service;

import candidatura.dao.CandidaturaDAO;
import storage.entity.Annuncio;
import storage.entity.Candidatura;
import storage.entity.Persona;

import java.sql.SQLException;
import java.util.List;

import static net.sf.saxon.om.EnumSetTool.except;

public class CandidaturaService implements CandidaturaServiceInterface{

    private CandidaturaDAO candidaturaDAO;

    public CandidaturaService(){
        candidaturaDAO = new CandidaturaDAO();
    }

    @Override
    public List<Candidatura> getCandidatureByAnnuncio(Annuncio annuncio) throws SQLException {
        return candidaturaDAO.getCandidatureByAnnuncio(annuncio);
    }

    @Override
    public Candidatura getCandidaturaByPersonaAndAnnuncio(Persona persona, Annuncio annuncio) throws SQLException {
        return candidaturaDAO.getCandidaturaByPersonaAndAnnuncio(persona, annuncio);
    }

    @Override
    public List<Candidatura> getCandidatueByPersona(Persona persona) throws SQLException {
        return candidaturaDAO.getCandidatueByPersona(persona);
    }

    @Override
    public boolean creaCandidatura(Candidatura candidatura) {
        try {
            CandidaturaDAO.creaCandidatura(candidatura);
        }
        catch (SQLException e){
            return false;
        }
        return true;
    }

    /**
     * @param candidatura 
     * @return
     */
    @Override
    public boolean eliminaCandidatura(Candidatura candidatura) {
        try {
            CandidaturaDAO.eliminaCandidatura(candidatura);
        }
        catch (SQLException e){
            return false;
        }
        return true;
    }


}
