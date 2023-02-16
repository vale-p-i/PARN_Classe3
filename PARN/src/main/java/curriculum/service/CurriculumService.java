package curriculum.service;

import curriculum.dao.CurriculumDAO;
import curriculum.facade.PDFCurriculum;
import curriculum.facade.PDFCurriculumInterface;
import storage.entity.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CurriculumService implements  CurriculumServiceInterface{
    private CurriculumDAO curriculumDAO = new CurriculumDAO();

    @Override
    public List<Curriculum> getAllCurriculum() {
        try {
            return curriculumDAO.getAllCurriculum();
        } catch (SQLException e) {
            return null;
        }
    }


    @Override
    public Curriculum getCurriculumByPersona(Persona persona) {
        try {
            return curriculumDAO.getCurriculumByPersona(persona);
        } catch (SQLException e) {
            return null;
        }
    }


    @Override
    public boolean creaCurriculum(Curriculum curriculum) {
        try {
            curriculumDAO.addCurriculum(curriculum);
        } catch (SQLException e){
            return false;
        }

        boolean flag = true;
        for(EsperienzaLavorativa e: curriculum.getEsperienze()){
            flag = aggiungiEsperienzaLavorativa(e) && flag;
        }

        for(Lingua l: curriculum.getLingue()) {
            flag = aggiungiLingua(l) && flag;
        }

        for(Istruzione i: curriculum.getIstruzioni()) {
            flag = aggiungiIstruzione(i) && flag;
        }

        return flag;
    }


    @Override
    public boolean aggiornaCurriculum(Curriculum curriculum) {
        try {
            curriculumDAO.updateCurriculum(curriculum);
        } catch (SQLException e) {
            return false;
        }

        return true;
    }


    @Override
    public String downloadCurriculum(Curriculum curriculum){
        PDFCurriculumInterface pdf = (PDFCurriculumInterface) new PDFCurriculum();
        try{
           return pdf.downloadCurriculum(curriculum);
        } catch (IOException e) {
            return null;
        }
    }


    @Override
    public boolean aggiungiEsperienzaLavorativa(EsperienzaLavorativa esperienzaLavorativa) {

        //viene aggiunta l'esperienza lavorativa nel database
        try{
            curriculumDAO.addEsperienzaLavorativa(esperienzaLavorativa);
        } catch (SQLException e) {
            return false;
        }

        //viene aggiunta l'esperienza lavorativa all'oggetto Curriculum
        Curriculum curriculum = esperienzaLavorativa.getCurriculum();
        if(!curriculum.getEsperienze().contains(esperienzaLavorativa)) {
            curriculum.getEsperienze().add(esperienzaLavorativa);
        }
        return  true;
    }


    @Override
    public boolean aggiungiLingua(Lingua lingua) {
        //viene aggiunta la lingua al database
        try{
            curriculumDAO.addLingua(lingua);
        } catch (SQLException e) {
            return false;
        }

        //viene aggiunta la lingua all'oggetto curriculum
        Curriculum curriculum = lingua.getCurriculum();
        if(!curriculum.getLingue().contains(lingua)){
            curriculum.getLingue().add(lingua);
        }

        return true;
    }


    @Override
    public boolean aggiungiIstruzione(Istruzione istruzione) {
        //viene aggiunta l'istruzione al databse
        try{
            curriculumDAO.addIstruzione(istruzione);
        } catch (SQLException e) {
            return false;
        }

        //viene aggiunta l'istruzione all'oggetto curriculum
        Curriculum curriculum = istruzione.getCurriculum();
        if(!curriculum.getIstruzioni().contains(istruzione)){
            curriculum.getIstruzioni().add(istruzione);
        }

        return true;
    }


    @Override
    public boolean aggiornaEsperienzaLavorativa(EsperienzaLavorativa esperienzaLavorativa) {
        //aggiorna l'esperienza lavorativa nel database
        try {
            curriculumDAO.updateEsperienzaLavorativa(esperienzaLavorativa);
        } catch (SQLException e) {
            return false;
        }

        return true;
    }


    @Override
    public boolean aggiornaLingua(Lingua lingua) {
        //aggiorna la lingua lavorativa nel database
        try {
            curriculumDAO.updateLingua(lingua);
        } catch (SQLException e) {
            return false;
        }

        return true;
    }


    @Override
    public boolean aggiornaIstruzione(Istruzione istruzione) {
        //aggiorna l'istruzione nel database
        try {
            curriculumDAO.updateIstruzione(istruzione);
        } catch (SQLException e) {
            return false;
        }

        return true;
    }


    @Override
    public boolean eliminaEsperienzaLavorativa(EsperienzaLavorativa esperienzaLavorativa) {
        //elimina l'esperienza lavorativa dal database
        try{
            curriculumDAO.removeEsperienzaLavorativa(esperienzaLavorativa);
        } catch (SQLException e) {
            return false;
        }

        Curriculum curriculum = esperienzaLavorativa.getCurriculum();

        for(int i = 0; i < curriculum.getEsperienze().size(); i++){
            EsperienzaLavorativa e = curriculum.getEsperienze().get(i);
            if(esperienzaLavorativa.equals(e)){
                curriculum.getEsperienze().remove(e);
            }
        }
        return true;
    }


    @Override
    public int eliminaLingua(Lingua lingua) {
        //controlla che ci sia almeno un campo lingua nel database
        Curriculum curriculum = lingua.getCurriculum();
        int numLingue = curriculum.getLingue().size();
        if(numLingue == 1) {
            return 0;
        }

        //rimuove la lingua dal database
        try {
            curriculumDAO.removeLingua(lingua);
        } catch (SQLException e) {
            return 1;
        }

        //rimuove la lingua dall'oggetto curriculum

        for(int i = 0; i < curriculum.getLingue().size(); i++){
            Lingua l = curriculum.getLingue().get(i);
            if(lingua.equals(l)){
                curriculum.getLingue().remove(l);
            }
        }
        return 2;
    }


    @Override
    public int eliminaIstruzione(Istruzione istruzione) {
        //controlla che ci sia almeno un campo istruzione nel database
        Curriculum curriculum = istruzione.getCurriculum();
        int numInstruzioneCurriculum = curriculum.getIstruzioni().size();
        if(numInstruzioneCurriculum == 1) {
            return 0;
        }

        //rimuove l'istruzione dal database
        try {
            curriculumDAO.removeIstruzione(istruzione);
        } catch (SQLException e){
            return 1;
        }

        //rimuove l'istruzione dall'oggetto curriculum
        for(int i = 0; i < curriculum.getIstruzioni().size(); i++){
            Istruzione is = curriculum.getIstruzioni().get(i);
            if(istruzione.equals(is)) {
                curriculum.getIstruzioni().remove(is);
            }
        }
        return 2;
    }
}
