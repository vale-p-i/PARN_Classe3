package matching.service;

import storage.entity.Annuncio;
import storage.entity.Curriculum;

import java.util.List;

/**
 * Un oggetto MatchingServiceInterface offre tutti i metodi per la ricerca personalizzata
 */
public interface MatchingServiceInterface {

    /**
     * Questo metodo offre la personalizzazione degli annunci dato un curriculum
     * @param curriculum è il curriculum della persona di cui si vogliono personalizzare gli annunci
     * @return gli annunci personalizzati secondo il curriculum della persona
     */
    List<Annuncio> personalizzaAnnunci(Curriculum curriculum);

    /**
     * Prendendo come input la lista di annunci personalizzata, ricerca all'interno di esse la/e keyword inserite
     * pre personalizzaAnnunci(persona.curriculum).contains(annunci)
     * @param annunci gli annunci personalizzati di un utente
     * @param keyword è la keyword inserita dalla persona
     * @return gli annunci che contengono la keyword inserita
     */
    List<Annuncio> personalizzaAnnunciKeyword(List<Annuncio> annunci, String keyword);

    /**
     * Prendendo come input l'annuncio e mostro una serie di persone adatte a
     * ricoprire quel ruolo, basandosi sulle competenze espresse da loro nei curriculum
     * pre canAnnuncioAccessToSearch(annuncio) == true
     * @param annuncio oggetto di tipo annuncio
     * @return lista di Curriculum
     */
    List<Curriculum> selezioneFigureSpecializzate(Annuncio annuncio);




}
