package annuncio.service;

import annuncio.dao.AnnuncioDAO;
import candidatura.service.CandidaturaService;
import candidatura.service.CandidaturaServiceInterface;
import storage.entity.Annuncio;
import storage.entity.Azienda;
import storage.entity.Candidatura;

import java.sql.SQLException;
import java.util.List;

import static net.sf.saxon.om.EnumSetTool.except;

public class AnnuncioService implements AnnuncioServiceInterface {

    private static AnnuncioDAO annuncioDAO = new AnnuncioDAO();


    /**
     * @param id 
     * @return
     */
    @Override
    public Annuncio getAnnuncioById(int id) {
        try {
            return annuncioDAO.getAnnuncioById(id);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public List<Annuncio> getAnnunciByStato(String in_corso) {
        try{
            return annuncioDAO.getAnnunciByStato(in_corso);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public boolean creaAnnuncio(Annuncio annuncio) {
        try {
            annuncioDAO.creaAnnuncio(annuncio);
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
            annuncioDAO.modificaAnnuncio(annuncio);
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
            annuncioDAO.eliminaAnnuncio(annuncio);
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
            annuncioDAO.chiusuraAnnuncio(annuncio);
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
        return candidaturaServiceInterface.getCandidatureByAnnuncio(annuncio);
    }

    /**
     * @param azienda 
     * @return
     */
    @Override
    public List<Annuncio> getAnnuncioByAzienda(Azienda azienda) {
       try {
           return annuncioDAO.getAnnunciByAzienda(azienda);
       } catch (SQLException e) {
           return null;
       }
    }
}
