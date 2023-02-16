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
        if(id >= 0)
            try {
                return annuncioDAO.getAnnuncioById(id);
            } catch (SQLException e) {
                return null;
            }
        else return null;
    }

    @Override
    public boolean creaAnnuncio(Annuncio annuncio) {
        if(annuncio != null){
            try {
                annuncioDAO.creaAnnuncio(annuncio);
            } catch (SQLException e) {
                return false;
            }

            Azienda azienda = annuncio.getAzienda();
            azienda.getAnnunci().add(annuncio);
            return true;
        }else return false;
    }

    @Override
    public boolean modificaAnnuncio(Annuncio annuncio) {
        if(annuncio != null){
            try {
                annuncioDAO.modificaAnnuncio(annuncio);
            } catch (SQLException e) {
                return false;
            }
            return true;
        }else return false;
    }

    @Override
    public boolean eliminaAnnuncio(Annuncio annuncio) {
        if(annuncio != null){
            try {
                annuncioDAO.eliminaAnnuncio(annuncio);
            } catch (SQLException e) {
                return false;
            }

            Azienda azienda = annuncio.getAzienda();
            for(Annuncio a : azienda.getAnnunci())
                if(a.getId() == annuncio.getId()){
                    azienda.getAnnunci().remove(a);
                    continue;
                }
            return true;
        }else return false;
    }

    @Override
    public boolean chiusuraAnnuncio(Annuncio annuncio) {
        if(annuncio != null){
            try {
                annuncio.setAttivo(false);
                annuncioDAO.chiusuraAnnuncio(annuncio);
                return true;
            } catch (SQLException e) {
                return false;
            }
        }else return false;
    }

    @Override
    public boolean aggiungiCandidatura(Annuncio annuncio, Candidatura candidatura) {
        if(annuncio != null && candidatura != null){
            CandidaturaServiceInterface candidaturaServiceInterface = new CandidaturaService();
            candidatura.setAnnuncio(annuncio);
            annuncio.getCandidature().add(candidatura);
            return candidaturaServiceInterface.creaCandidatura(candidatura);
        }else return false;
    }

    @Override
    public List<Annuncio> getAnnuncioByAzienda(Azienda azienda) {
        if(azienda != null){
            try {
                return annuncioDAO.getAnnunciByAzienda(azienda);
            } catch (SQLException e) {
                return null;
            }
        }else return null;
    }

    @Override
    public List<Annuncio> getAnnunciByStato(Azienda azienda,String in_corso) {
        if(azienda != null){
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
        }else return null;
    }

    public List<Annuncio> getAnnunciByStato(String in_corso) {
        if(in_corso.equals(Annuncio.IN_CORSO) || in_corso.equals(Annuncio.SCADUTO) || in_corso.equals(Annuncio.CHIUSO)){
            try {
                return annuncioDAO.getAnnunciByStato(in_corso);
            } catch (SQLException e) {
                return null;
            }
        }else return null;
    }

    @Override
    public Annuncio getAnnuncioById(Azienda azienda, int id) {
        if (azienda.getAnnunci()!=null && id >= 0)
            for (Annuncio a:azienda.getAnnunci())
                if (a.getId()==id)
                    return a;
        return null;
    }

    @Override
    public String getStato(Annuncio a) {
        if(a != null){
            LocalDate today=LocalDate.now();
            if (a.isAttivo()&&a.getDataScadenza().isAfter(today))
                return Annuncio.IN_CORSO;
            else if (a.isAttivo()&&(a.getDataScadenza().isBefore(today)||a.getDataScadenza().equals(today)))
                return Annuncio.SCADUTO;
            else if(!a.isAttivo())
                return Annuncio.CHIUSO;
            return null;
        }else return null;
    }

    @Override
    public boolean canAnnuncioAccessToSearch(Annuncio a) {
        if(a != null){
            if (getStato(a).equals(Annuncio.SCADUTO)){
                double percentuale=(100*a.getCandidature().size())/a.getNumeroPersone();
                percentuale/=100;
                if (percentuale<=0.5)
                    return true;
                else
                    return false;
            }
            return false;
        }else return false;
    }
}
