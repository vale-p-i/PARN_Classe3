package annuncio.service;

import annuncio.dao.AnnuncioDAO;
import candidatura.service.CandidaturaService;
import candidatura.service.CandidaturaServiceInterface;
import storage.entity.Annuncio;
import storage.entity.Azienda;
import storage.entity.Candidatura;
import utente.dao.UtenteDAO;
import utente.service.UtenteService;
import utente.service.UtenteServiceInterface;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AnnuncioService implements AnnuncioServiceInterface {

    private static AnnuncioDAO annuncioDAO = new AnnuncioDAO();

    public AnnuncioService(){

    }

    public AnnuncioService(AnnuncioDAO annuncioDAO){
        this.annuncioDAO = annuncioDAO;
    }

    @Override
    public Annuncio getAnnuncioById(int id) {
        if(id > 0)
            try {
                return annuncioDAO.getAnnuncioById(id);
            } catch (SQLException e) {
                return null;
            }
        else return null;
    }

    @Override
    public boolean creaAnnuncio(Annuncio annuncio) {
        UtenteServiceInterface seviceUtente = new UtenteService();
        String requisiti = String.join(",", annuncio.getRequisiti());
        int sizeOfRequisiti = requisiti.length();
        String preferenze = String.join(",", annuncio.getPreferenze());
        int sizeOfPreferenze = preferenze.length();
        String keywords = String.join(",", annuncio.getKeyword());
        int sizeOfKeywords = keywords.length();

        if(annuncio == null) throw new IllegalArgumentException("L'oggetto annuncio è null");
        if(annuncio.getRuolo().length() > 100 || annuncio.getRuolo().length() <= 0)
            throw new IllegalArgumentException("Il campo ruolo è troppo lungo o troppo corto");
        if(seviceUtente.getSedeById(annuncio.getAzienda(), annuncio.getSede().getId()) == null)
            throw new IllegalArgumentException("La sede è null");
        if(annuncio.getNumeroPersone() < 0 || annuncio.getNumeroPersone() > 999999)
            throw new IllegalArgumentException("Il campo numero persone è troppo grande o troppo piccolo");
        if(annuncio.getDescrizione().length() > 2000 || annuncio.getDescrizione().length() <= 0)
            throw new IllegalArgumentException("Il campo descrizione è troppo lungo o troppo corto");
        if(annuncio.getDataScadenza() != null){
            if(annuncio.getDataScadenza().getYear() > 2030 ||
                    (!annuncio.getDataScadenza().isAfter(LocalDate.now()) && !annuncio.getDataScadenza().equals(LocalDate.now())))
                throw new IllegalArgumentException("La scadenza è antecedente alla data odierna");
        } else throw new IllegalArgumentException("La data di scadenza è null");
        if(sizeOfRequisiti > 500)
            throw new IllegalArgumentException("Requisiti troppo lunghi");
        if(!requisiti.matches("^[\\w\\d\\s.,;:!?()'\"-àáèéíìóòúù]*$"))
            throw new IllegalArgumentException("I requisiti non rispettano il formato");
        if(sizeOfRequisiti <= 0)
            throw new IllegalArgumentException("Requisiti troppo corti");
        if(sizeOfPreferenze > 1000)
            throw new IllegalArgumentException("Preferenze troppo lunghe");
        if(!preferenze.matches("^[\\w\\d\\s.,;:!?()'\"-àáèéíìóòúù]*$"))
                throw new IllegalArgumentException("Le preferenze non rispettano il formato");
        if(sizeOfPreferenze <= 0)
            throw new IllegalArgumentException("Preferenze troppo corte");
        if(sizeOfKeywords > 150)
            throw new IllegalArgumentException("Keywords troppo lunghe");
        if(!keywords.matches("^[\\w\\d\\s.,;:!?()'\"-àáèéíìóòúù]*$"))
            throw new IllegalArgumentException("Le keywords non rispettano il formato");
        if(sizeOfKeywords <= 0)
            throw new IllegalArgumentException("Le keywords sono troppo corte");

        boolean b=annuncioDAO.creaAnnuncio(annuncio);


        Azienda azienda = annuncio.getAzienda();
        azienda.getAnnunci().add(annuncio);
        return b;
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
    public List<Annuncio> getAnnunciByAzienda(Azienda azienda) {
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
        if (azienda.getAnnunci()!=null && id > 0)
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
