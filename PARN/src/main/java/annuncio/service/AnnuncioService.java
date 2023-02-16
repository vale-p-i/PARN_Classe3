package annuncio.service;

import annuncio.dao.AnnuncioDAO;
import candidatura.service.CandidaturaService;
import candidatura.service.CandidaturaServiceInterface;
import storage.entity.Annuncio;
import storage.entity.Azienda;
import storage.entity.Candidatura;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static net.sf.saxon.om.EnumSetTool.except;

public class AnnuncioService implements AnnuncioServiceInterface {

    private static AnnuncioDAO annuncioDAO = new AnnuncioDAO();


    @Override
    public Annuncio getAnnuncioById(int id) {
        try {
            return annuncioDAO.getAnnuncioById(id);
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public List<Annuncio> getAnnunciByStato(Azienda azienda,String in_corso) {
        LocalDate today= LocalDate.now();
        List<Annuncio> returnment=new ArrayList<>();
        if (azienda.getAnnunci()!=null)
            for(Annuncio a: azienda.getAnnunci()){
                switch (in_corso){
                    case Annuncio.IN_CORSO:
                        if (a.isAttivo()&&a.getDataScadenza().isAfter(today))
                            returnment.add(a);
                        break;
                    case Annuncio.SCADUTO:
                        if (a.isAttivo()&&(a.getDataScadenza().isBefore(today)||a.getDataScadenza().equals(today)))
                            returnment.add(a);
                        break;
                    case Annuncio.CHIUSO:
                        if(!a.isAttivo())
                            returnment.add(a);
                        break;
                }
            }
        if (returnment.isEmpty())
            return null;
        return returnment;
    }

    public List<Annuncio> getAnnunciByStato(String in_corso) {
        try {
            return annuncioDAO.getAnnunciByStato(in_corso);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Annuncio getAnnuncioById(Azienda azienda, int id) {
        if (azienda.getAnnunci()!=null)
            for (Annuncio a:azienda.getAnnunci())
                if (a.getId()==id)
                    return a;
        return null;
    }

    @Override
    public String getStato(Annuncio a) {
        LocalDate today=LocalDate.now();
        if (a.isAttivo()&&a.getDataScadenza().isAfter(today))
            return Annuncio.IN_CORSO;
        else if (a.isAttivo()&&(a.getDataScadenza().isBefore(today)||a.getDataScadenza().equals(today)))
            return Annuncio.SCADUTO;
        else if(!a.isAttivo())
            return Annuncio.CHIUSO;
        return null;
    }

    @Override
    public boolean canAnnuncioAccessToSearch(Annuncio a) {
        if (getStato(a).equals(Annuncio.SCADUTO)){
            double percentuale=(100*a.getCandidature().size())/a.getNumeroPersone();
            percentuale/=100;
            if (percentuale<=0.5)
                return true;
            else
                return false;
        }
        return false;
    }


    @Override
    public boolean creaAnnuncio(Annuncio annuncio) {
        try {
            annuncioDAO.creaAnnuncio(annuncio);
        } catch (SQLException e) {
            return false;
        }

        Azienda azienda = annuncio.getAzienda();
        azienda.getAnnunci().add(annuncio);
        return true;
    }

    @Override
    public boolean modificaAnnuncio(Annuncio annuncio) {
        try {
            annuncioDAO.modificaAnnuncio(annuncio);
        } catch (SQLException e) {
            return false;
        }

        Azienda azienda = annuncio.getAzienda();
        for(Annuncio a : azienda.getAnnunci())
            if(a.getId() == annuncio.getId())
                azienda.getAnnunci().remove(a);
        return true;
    }

    @Override
    public boolean eliminaAnnuncio(Annuncio annuncio) {
        try {
            annuncioDAO.eliminaAnnuncio(annuncio);
        } catch (SQLException e) {
            return false;
        }

        Azienda azienda = annuncio.getAzienda();
        for(Annuncio a : azienda.getAnnunci())
            if(a.getId() == annuncio.getId())
                azienda.getAnnunci().remove(a);
        return true;
    }

    @Override
    public boolean chiusuraAnnuncio(Annuncio annuncio) {
        try {
            annuncio.setAttivo(false);
            annuncioDAO.chiusuraAnnuncio(annuncio);
            return true;
        } catch (SQLException e) {
            return false;
        }
        
    }

    @Override
    public boolean aggiungiCandidatura(Annuncio annuncio, Candidatura candidatura) {
        CandidaturaServiceInterface candidaturaServiceInterface = new CandidaturaService();
        candidatura.setAnnuncio(annuncio);
        return candidaturaServiceInterface.creaCandidatura(candidatura);
    }

    @Override
    public List<Annuncio> getAnnuncioByAzienda(Azienda azienda) {
       try {
           return annuncioDAO.getAnnunciByAzienda(azienda);
       } catch (SQLException e) {
           return null;
       }
    }
}
