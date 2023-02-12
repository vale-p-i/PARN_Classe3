package annuncio.service;

import annuncio.dao.AnnuncioDAO;
import candidatura.service.CandidaturaService;
import candidatura.service.CandidaturaServiceInterface;
import storage.entity.Annuncio;
import storage.entity.Candidatura;

import java.sql.SQLException;
import java.util.List;

import static net.sf.saxon.om.EnumSetTool.except;

public class AnnuncioService implements AnnuncioServiceInterface{


    /**
     * @param id 
     * @return
     */
    @Override
    public List<Annuncio> getAnnuncioById(int id) {
        try {
            return AnnuncioDAO.getAnnuncioById(id);
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * @param annuncio 
     * @return
     */
    @Override
    public boolean creaAnnuncio(Annuncio annuncio) {
        try {
            AnnuncioDAO.creaAnnuncio(annuncio);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * @param annuncio 
     * @return
     */
    @Override
    public boolean modificaAnnuncio(Annuncio annuncio) {
        try {
            AnnuncioDAO.modificaAnnuncio(annuncio);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * @param annuncio 
     * @return
     */
    @Override
    public boolean eliminaAnnuncio(Annuncio annuncio) {
        try {
            AnnuncioDAO.eliminaAnnuncio(annuncio);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * @param annuncio 
     * @return
     */
    @Override
    public boolean chiusuraAnnuncio(Annuncio annuncio) {
        try {
            AnnuncioDAO.chiusuraAnnuncio(annuncio);
            return true;
        } catch (SQLException e) {
            return false;
        }
        
    }

    /**
     * @param annuncio 
     * @param candidatura
     * @return
     */
    @Override
    public boolean aggiungiCandidatura(Annuncio annuncio, Candidatura candidatura) {
        CandidaturaServiceInterface candidaturaServiceInterface = new CandidaturaService();
        candidatura.setAnnuncio(annuncio);
        return candidaturaServiceInterface.creaCandidatura(candidatura);
    }

    /**
     * @param annuncio 
     * @param candidatura
     * @return
     */
    @Override
    public boolean rimuoviCandidatura(Annuncio annuncio, Candidatura candidatura) {
        CandidaturaServiceInterface candidaturaServiceInterface = new CandidaturaService();

        for (int i = 0; i < annuncio.getCandidature().size(); i++){
            if (annuncio.getCandidature().get(i).equals(candidatura))
                annuncio.getCandidature().remove(i);
        }

        return candidaturaServiceInterface.eliminaCandidatura(candidatura);
    }

    /**
     * @param annuncio 
     * @return
     */
    @Override
    public List<Candidatura> visualizzaCandidatura(Annuncio annuncio) {
        CandidaturaServiceInterface candidaturaServiceInterface = new CandidaturaService();
        try {
            return candidaturaServiceInterface.getCandidatureByAnnuncio(annuncio);
        } catch (SQLException e) {
            return null;
        }
    }
}
