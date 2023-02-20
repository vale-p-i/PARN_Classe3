package annuncio.service;

import storage.entity.Annuncio;
import storage.entity.Azienda;
import storage.entity.Candidatura;

import java.sql.*;
import java.util.List;

/**
 * Un oggetto di tipo AnnuncioServiceInterface permette di accedere a tutti i metodi che
 * effettuano operazioni sugli annunci
 */
public interface AnnuncioServiceInterface {

    /**
     * Permette di ottenere un Annuncio dato il suo ID
     * @param id con il quale cercare un annuncio
     * @return Ritorna l'annuncio che ha come id l'id che è stato passato, null altrimenti
     */
    Annuncio getAnnuncioById(int id);

    /**
     * Permette di persistere un annuncio nel database, aggiungendo l'annuncio alla lista che viene
     * mantenuta dall'azienda che lo ha pubblicato.
     * In caso di successo ritorna true, altrimenti false
     * @param annuncio è l'annuncio che si vuole creare
     * @return true se la creazione ha avuto successo, false altrimenti
     * Pre not getAnnunciByAzienda(annuncio.azienda).contains(annuncio)
     * Post getAnnunciByAzienda(annuncio.azienda).contains(annuncio) and
     * getAnnunciByAzienda(annuncio.azienda).size() == @pre getAnnunciByAzienda(annuncio.azienda).size() + 1
     */
    boolean creaAnnuncio(Annuncio annuncio);

    /**
     * Permette di modificare un annuncio presente nel DB.
     * @param annuncio è l'annuncio che si vuole modificare
     * @return true se la modifica ha avuto successo, false altrimenti
     * Pre getAnnunciByAzienda(annuncio.azienda).contains(annuncio)
     * Post getAnnunciByAzienda(annuncio.azienda).contains(annuncio) and
     * getAnnunciByAzienda(annuncio.azienda).size() == @pre getAnnunciByAzienda(annuncio.azienda).size()
     */
    boolean modificaAnnuncio(Annuncio annuncio);

    /**
     * Permette di eliminare un annuncio
     * @param annuncio è l'annuncio che si vuole eliminare
     * @return true se l'eliminazione ha avuto successo, false altrimenti
     * Pre getAnnunciByAzienda(annuncio.azienda).contains(annuncio)
     * Post not getAnnunciByAzienda(annuncio.azienda).contains(annuncio) and
     * getAnnunciByAzienda(annuncio.azienda).size() == @pre getAnnunciByAzienda(annuncio.azienda).size() - 1
     */
    boolean eliminaAnnuncio(Annuncio annuncio);

    /**
     * Permette di chiudere un annuncio
     * @param annuncio è l'annuncio che si vuole chiudere
     * @return true se l'annuncio è stato chiuso correttamente, altrimenti false
     * Pre getAnnuncioByStato(annuncio.azienda, attivo).contains(annuncio)
     * Post not getAnnuncioByStato(annuncio.azienda, attivo).contains(annuncio)
     */
    boolean chiusuraAnnuncio(Annuncio annuncio);

    /**
     * Permette di aggiungere una candidatura alla lista di candidature mantenuta dall'annuncio e aggiornare
     * il database
     * @param annuncio è l'annuncio a cui si vuole aggiungere la candidatura
     * @param candidatura è la candidatura che si vuole aggiungere all'annuncio
     * @return Ritorna il valore ritornato dal metodo creaCandidatura del service CandidaturaService, il quale può essere
     * true se è andato a buon fine, false altrimenti
     * Pre not getCandidatureByAnnuncio(annuncio).contains(candidatura)
     * Post getCandidatureByAnnuncio(annuncio).contains(candidatura) and
     * getCandidatureByAnnuncio(annuncio).size() = @pre getCandidatureByAnnuncio(annuncio).size() + 1
     */
    boolean aggiungiCandidatura(Annuncio annuncio, Candidatura candidatura);

    /**
     * Permette di ottenere gli annunci pubblicati da un azienda interrogando il database
     * @param azienda è l'azienda di cui si vogliono gli annunci
     * @return la lista degli annunci dell'azienda
     */
    List<Annuncio> getAnnunciByAzienda(Azienda azienda);

    /**
     * Permette di ottenere gli annunci con un certo stato, pubblicati una azienda.
     * @param in_corso la stringa che specifica lo stato dell'annuncio (in corso, scaduto, chiuso)
     * @param azienda è l'azienda di cui si vogliono gli annunci
     * @return la lista degli annunci del'azienda che hanno lo stato passato
     */
    List<Annuncio> getAnnunciByStato(Azienda azienda, String in_corso);

    /**
     * Permette di ottenere gli annunci con un certo stato interrogando il database
     * @param in_corso la stringa che specifica lo stato dell'annuncio (in corso, scaduto, chiuso)
     * @return la lista di annuncio che hanno lo stato passato
     */
    List<Annuncio> getAnnunciByStato(String in_corso);

    /**
    * Permette di ottenere un annuncio pubblicato da una azienda
    * @param azienda è l'azienda che ha pubblicato l'annincio
    * @param id è l'id dell'annuncio
    * @return l'oggetto annuncio che ha l'id passato
    */
    Annuncio getAnnuncioById(Azienda azienda, int id);

    /**
    * Permette di ottenere lo stato di un annuncio
    * @param annuncio è l'annuncio di cui si vuole conoscere lo stato
    * @return una stringa che rappresenta lo stato
    */
    String getStato(Annuncio annuncio);

    /**
     * Permette di controllare se per un determinato annuncio si può accedere alla funzionalità
     * di ricerca avanzata
     * @param a è l'annuncio che vuole accedere ala ricerac di figure specializzate
     * @return true se l'annuncio può accedere alla funzionalità, false altrimenti
     */
    boolean canAnnuncioAccessToSearch(Annuncio a);
}
