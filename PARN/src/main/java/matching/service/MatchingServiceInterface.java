package matching.service;

import storage.entity.Annuncio;
import storage.entity.Curriculum;

import java.util.List;

/**
 * Un oggetto MatchingServiceInterface offre tutti i metodi per la ricerca personalizzata
 */
public interface MatchingServiceInterface {

    /**
     * Metodo offre la personalizzazione degli annunci dato un curriculum
     * @pre curruculum non è null
     * @param curriculum oggetto di tipo Curriculum
     * @return List<Annuncio> lista di Annuncio
     * @post List<Annuncio> non è null
     */
    List<Annuncio> personalizzaAnnunci(Curriculum curriculum);

    /**
     * Prendendo come input la lista di annunci personalizzata, ricerca all'interno di esse la/e keyword inserite
     * @pre List<Annuncio> e Keyword non null
     * @param annunci oggetto di tipo annuncio
     * @param keyword oggetto di tipo String
     * @return List<Annuncio> lista di Annuncio
     * @post List<Annuncio> non null
     */
    List<Annuncio> personalizzaAnnunciKeyword(List<Annuncio> annunci, String keyword);
    /**
     * Prendendo come input l'annuncio e mostro una serie di persone adatte a
     * ricoprire quel ruolo, basandosi sulle competenze espresse da loro nei curriculum
     * @pre Annuncio non è null
     * @param annuncio oggetto di tipo annuncio
     * @return List<Curriculum> lista di Curriculum
     * @post List<Curriculum> non null
     */
    List<Curriculum> selezioneFigureSpecializzate(Annuncio annuncio);




}
