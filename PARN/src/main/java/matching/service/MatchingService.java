package matching.service;

import annuncio.service.AnnuncioService;
import annuncio.service.AnnuncioServiceInterface;
import curriculum.service.CurriculumService;
import curriculum.service.CurriculumServiceInterface;
import storage.entity.*;

import java.util.ArrayList;
import java.util.List;

public class MatchingService implements MatchingServiceInterface{

    @Override
    public List<Annuncio> personalizzaAnnunci(Curriculum curriculum) {
        List<Annuncio> returnment=new ArrayList<>();
        AnnuncioServiceInterface serviceAnnucio=new AnnuncioService();
        List<Annuncio> all=serviceAnnucio.getAnnunciByStato(Annuncio.IN_CORSO);
        if(all != null){
            List<String> QualificheIstruzione=new ArrayList<>();
            for (Istruzione i:curriculum.getIstruzioni())
                QualificheIstruzione.add(i.getQualifica());
            List<String> QualificheLavorative=new ArrayList<>();
            for (EsperienzaLavorativa i:curriculum.getEsperienze())
                QualificheLavorative.add(i.getTipoImpiego());
            for (Annuncio a: all) {
                boolean flag=false,flag1=true;
                if (QualificheIstruzione.containsAll(a.getRequisiti())||QualificheLavorative.containsAll(a.getRequisiti()))
                    flag=true;
                for (Candidatura c: a.getCandidature())
                    if(c.getPersona().getId()==curriculum.getPersona().getId())
                        flag1=false;
                if(flag&&flag1)
                    returnment.add(a);
            }
            return returnment;
        }else return null;
    }

    @Override
    public List<Annuncio> personalizzaAnnunciKeyword(List<Annuncio> annunci, String keyword) {
        List<Annuncio> returnment=new ArrayList<>();
        for (Annuncio a: annunci)
            if (a.getKeyword().contains(keyword))
                returnment.add(a);
        return returnment;
    }

    @Override
    public List<Curriculum> selezioneFigureSpecializzate(Annuncio annuncio) {
        List<Curriculum> returnment=new ArrayList<>();
        CurriculumServiceInterface serviceCurriculum = new CurriculumService();
        List<Curriculum> all=serviceCurriculum.getAllCurriculum();
        for (Curriculum c: all){
            List<String> Qualifiche=new ArrayList<>();
            for (Istruzione i:c.getIstruzioni())
                Qualifiche.add(i.getQualifica());
            if (Qualifiche.containsAll(annuncio.getRequisiti()))
                    returnment.add(c);
            }
        return returnment;
    }
}
