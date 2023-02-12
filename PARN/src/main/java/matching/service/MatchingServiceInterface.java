package matching.service;

import storage.entity.Annuncio;
import storage.entity.Curriculum;

import java.util.List;

public interface MatchingServiceInterface {

    List<Annuncio> personalizzaAnnunci(Curriculum curriculum);
    List<Annuncio> personalizzaAnnunciKeyword(List<Annuncio> annunci, String keyword);
    List<Curriculum> selezioneFigureSpecializzate(Annuncio annuncio);




}
