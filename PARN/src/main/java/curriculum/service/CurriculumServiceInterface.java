package curriculum.service;

import storage.entity.*;

import java.io.IOException;
import java.util.List;

public interface CurriculumServiceInterface {
    /**
     * Questo metodo permette di ottenere tutti i curriculum nel database
     * @return una lista di Curriculum
     */
    List<Curriculum> getAllCurriculum();

    /**
     * Questo metodo permette di ottenere il curriculum di una persona
     * @param persona è la persona
     * @return l'oggetto Curriculum, null ne si è verificato qualche problema
     */
    Curriculum getCurriculumByPersona(Persona persona);

    /**
     * Questo metodo crea un curriculum all'interno del database
     * @pre not getCurriculumByPersona(curriculum.persona)
     * @post getCurriculumByPersona(curriculum.persona)
     * @param curriculum è il curriculum da craere
     * @return true se la creazione è andata a buon fine false
     * altrimenti
     */
    boolean creaCurriculum(Curriculum curriculum);

    /**
     * Questo metodo permette di aggiornare un curriculum
     * @param curriculum è il curriculum aggiornato
     * @return true se l'aggiornamento è andato a buon fine, altrimenti false
     */
    boolean aggiornaCurriculum(Curriculum curriculum);

    /**
     * Questo metodo permette di scaricare un curriculum, creando il file pdf a partire da un oggetto curriculum.
     * @param curriculum è il curriculm da creare
     * @return la path sottoforma di stringa nel caso in cui la creazione del docuemnto pdf sia andata a buon fine,
     * altirmenti null
     */
    String downloadCurriculum(Curriculum curriculum) throws IOException;

    /**
     * Questo metodo permette di aggiungere un campo esperienza al curriculum
     * @pre not getEsperienzeLavorative(esperienza.curriculum).includes(esperienza)
     * @post getEsperienzeLavorative(esperienza.curriculum).includes(esperienza) &&
     * getEsperienzeLavorative(esperienza.curriculum).size == @pre.getEsperienzeLavorative(esperienza.curriculum).size + 1
     * @param esperienzaLavorativa è il campo da aggiungere
     * @return true se l'inserimento è andato a buon fine, altrimenti false
     */
    boolean aggiungiEsperienzaLavorativa(EsperienzaLavorativa esperienzaLavorativa);

    /**
     * Questo metodo permette di aggiungere un campo lingua al curriculum
     * @pre not getLingue(lingua.curriculum).includes(lingua)
     * @post getLingue(lingua.curriculum).includes(lingua) &&
     * getLingue(lingua.curriculum).size == @pre.getLingue(lingua.curriculum).size + 1
     * @param lingua è il campo da inserire
     * @return true se l'inserimento è andato a buon fine, altrimenti false
     */
    boolean aggiungiLingua(Lingua lingua);

    /**
     * Questo metodo permette di inserire un campo istruzione nel curriculum
     * @pre not getIstruzioni(istruzione.curriculum).includes(istruzione)
     * @post getIstruzioni(istruzione.curriculum).includes(istruzione) &&
     * getIstruzioni(istruzione.curriculum).size == @pre.getIstruzioni(istruzione.curriculum).size + 1
     * @param istruzione è il campo istruzione da inserire
     * @return true se l'inserimento è andato a buon fine, altrimenti false
     */
    boolean aggiungiIstruzione(Istruzione istruzione);

    /**
     * Questo metodo permette di aggiornare un campo esperienza lavorativa del curriculum
     * @pre getEsperienzeLavorative(esperienza.curriculum).includes(esperienza)
     * @post getEsperienzeLavorative(esperienza.curriculum).includes(esperienza) &&
     * getEsperienzeLavorative(esperienza.curriculum).size == @pre.getEsperienzeLavorative(esperienza.curriculum).size
     * @param esperienzaLavorativa è il campo aggiornato
     * @return true se l'aggiornamento è andato a buon fine, altrimenti false
     */
    boolean aggiornaEsperienzaLavorativa(EsperienzaLavorativa esperienzaLavorativa);

    /**
     * Questo metodo permette di aggiornare un campo lingua del curriculum
     * @pre getLingue(lingua.curriculum).includes(lingua)
     * @post getLingue(lingua.curriculum).includes(lingua) &&
     * getLingue(lingua.curriculum).size == @pre.getLingue(lingua.curriculum).size
     * @param lingua è il campo aggiornato
     * @return true se l'aggiornamento è andato a buon fine, altriemnti false
     */
    boolean aggiornaLingua(Lingua lingua);

    /**
     * Questo metodo permette di aggiornare un campo istruzione nel curriculum
     * @pre getIstruzioni(istruzione.curriculum).includes(istruzione)
     * @post getIstruzioni(istruzione.curriculum).includes(istruzione) &&
     * getIstruzioni(istruzione.curriculum).size == @pre.getIstruzioni(istruzione.curriculum).size
     * @param istruzione è il campo aggiornato
     * @return true se l'aggiornamento è andato a buon fine, altriemnti false
     */
    boolean aggiornaIstruzione(Istruzione istruzione);

    /**
     * Questo metodo permette di eliminare il campo esperienza all'intenro del curriculum.
     * @pre getEsperienzeLavorative(esperienza.curriculum).includes(esperienza
     * @post not getEsperienzeLavorative(esperienza.curriculum).includes(esperienza) &&
     * getEsperienzeLavorative(esperienza.curriculum).size == @pre.getEsperienzeLavorative(esperienza.curriculum).size - 1
     * @param esperienzaLavorativa è il campo da eliminare
     * @return true se la cancellazione è andata a buon fine, altirmenti false
     */
    boolean eliminaEsperienzaLavorativa(EsperienzaLavorativa esperienzaLavorativa);

    /**
     * Questo metodo permette di eliminare un campo lingua nel curriculum. Se il campo che si vuole eliminare è l'ultimo,
     * viene vietata la cancellazione e viene restituito 0. Altrimenti viene restituito 2 se la cancellazione è andata a buon fine,
     * in caso contrario restituisce 1.
     * @pre getLingue(lingua.curriculum).includes(lingua)
     * @param lingua è il campo lingua che si vuole eliminare.
     * @return 0 se è l'ultimo campo lingua nel curriculum, 1 se non è riuscito a effettuare la cancellazione,
     * 2 se la cancellazione è andata a buon fine.
     * @post not getLingue(lingua.curriculum).includes(lingua) &&
     *      * getLingue(lingua.curriculum).size == @pre.getLingue(lingua.curriculum).size - 1
     */
    int eliminaLingua(Lingua lingua);

    /**
     * Questo metodo permette di eliminare un campo istruzione nel curriculum. Se il campo che si vuole eliminare è l'ultimo,
     * viene vietata la cancellazione e viene restituito 0. Altrimenti viene restituito 2 se la cancellazione è andata a buon fine,
     *in caso contrario restituisce 1.
     * @pre getIstruzioni(istruzione.curriculum).includes(istruzione)
     * @param istruzione è il campo istruzione che si vuole eliminare.
     * @return 0 se è l'ultimo campo istruzione nel curriculum, 1 se non è riuscito a effettuare la cancellazione, 2 se
     * la cancellazione è andata a buon fine.
     * @post not getIstruzioni(istruzione.curriculum).includes(istruzione) &&
     * getIstruzioni(istruzione.curriculum).size == @pre.getIstruzioni(istruzione.curriculum).size - 1
     */
    int eliminaIstruzione(Istruzione istruzione);

}
