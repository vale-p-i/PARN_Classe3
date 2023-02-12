package matching.service;

import Storage.Entity.Annuncio;
import Storage.Entity.Curriculum;

import java.util.List;

public interface MatchingServiceInterface {

    List<Annuncio> personalizzaAnnunci(Curriculum curriculum);
    List<Annuncio> personalizzaAnnunciKeyword(List<Annuncio> annunci, String keyword);
    List<Curriculum> selezioneFigureSpecializzate(Annuncio annuncio);




}
