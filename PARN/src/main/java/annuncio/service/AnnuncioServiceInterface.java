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
     * @return Ritorna l'annuncio che ha come id l'id che è stato passsato, null altrimenti
     * @pre L'id deve essere di un formato valido
     * @post L'annuncio che viene restituito dal metodo non deve essere null
     */
    Annuncio getAnnuncioById(int id);

    /**
     * Permette di persistere un annuncio nel database, aggiungendo l'annuncio alla lista che viene
     * mantenuta dall'azienda che lo ha pubblicato.
     * In caso di successo ritorna true, altrimenti false
     * @param annuncio oggetto Annuncio
     * @return Ritorna true se ha avuto successo, false altrimenti
     * @pre L'oggetto annuncio non deve essere null
     * @post Nel database sarà presente l'entry corrispondente all'annuncio passato come parametro
     */
    boolean creaAnnuncio(Annuncio annuncio);

    /**
     * Permette di modificare un annuncio presente nel DB.
     * @param annuncio oggetto Annuncio
     * @return Ritorna true se ha avuto successo, false altrimenti
     * @pre L'oggetto annuncio non deve essere null
     * @post Nel database vengono riportate le modifiche apportate sull'oggetto annuncio che è stato
     * passato come parametro
     */
    boolean modificaAnnuncio(Annuncio annuncio);

    /**
     * Permette di eliminare un annuncio
     * @param annuncio oggetto Annuncio
     * @return Ritorna true se ha avuto successo, false altrimenti
     * @pre L'oggetto annuncio non deve essere null
     * @post Nel database non ci sarà più la entry corrispondente all'annuncio passato come parametro
     * e l'annuncio non sarà più contenuto nella lista degli annunci dell'azienda che l'ha pubblicato
     */
    boolean eliminaAnnuncio(Annuncio annuncio);

    /**
     * Permette di chiudere un annuncio
     * @param annuncio oggetto Annuncio
     * @return Ritorna true se ha avuto successo, false altrimenti
     * @pre L'oggetto annuncio non deve essere null
     * @post L'attributo attivo dell'annuncio sarà settato a false nell'oggetto annuncio e nel database
     */
    boolean chiusuraAnnuncio(Annuncio annuncio);

    /**
     * Permette di aggiungere una candidatura alla lista di candidature mantenuta dall'annuncio e aggiornare
     * il database
     * @param annuncio oggetto Annuncio
     * @param candidatura oggetto candidatura
     * @return Ritorna il valore ritornato dal metodo creaCandidatura del service CandidaturaService, il quale può essere
     * true se è andato a buon fine, false altrimenti
     * @pre L'oggetto annuncio e l'oggetto candidatura non devono essere null
     * @post Nell'oggetto annuncio, la lista di candidature conterrà anche la nuova candidatura. L'attributo "annuncio"
     * dell'oggetto candidatura conterrà l'oggetto annuncio per il quale è stata effettuata la candidatura. Sarà creata
     * anche la entry nel database per la candidatura
     */
    boolean aggiungiCandidatura(Annuncio annuncio, Candidatura candidatura);

    /**
     * Permette di ottenere gli annunci pubblicati da un azienda interrogando il database
     * @param azienda oggetto Annuncio
     * @return Ritorna una lista di annunci
     * @pre L'oggetto azienda non deve essere null
     * @post Viene restituito la lista di annunci pubblicati dall'azienda passata come parametro
     */
    List<Annuncio> getAnnuncioByAzienda(Azienda azienda);

    /**
     * Permette di ottenere gli annunci con un certo stato, pubblicati una azienda.
     * @param in_corso la stringa che specifica lo stato dell'annuncio (in corso, scaduto, chiuso)
     * @param azienda oggetto Azienda
     * @return Ritorna una lista di annunci
     * @pre L'oggetto azienda non deve essere null e la stringa in_corso deve essere uguale a uno
     * dei tre stati (in corso, scaduto, chiuso)
     * @post Viene restituita una lista di annunci che hanno come stato quello passato come parametro, e che sono
     * stati pubblicati dall'azienda che viene passata come parametro
     */
    List<Annuncio> getAnnunciByStato(Azienda azienda, String in_corso);

    /**
     * Permette di ottenere gli annunci con un certo stato interrogando il database
     * @param in_corso la stringa che specifica lo stato dell'annuncio (in corso, scaduto, chiuso)
     * @return Ritorna una lista di annunci
     * @pre La stringa in_corso deve essere uguale a uno dei tre stati (in corso, scaduto, chiuso)
     * @post Viene restituita la lista di tutti gli annunci presenti nel database che hanno come stato quello
     * passato come parametro.
     */
    List<Annuncio> getAnnunciByStato(String in_corso);

    /**
    * Permette di ottenere un annuncio pubblicato da una azienda
    * @param azienda oggetto Azienda
    * @param id dell'annuncio
    * @return Annuncio
     * @pre L'oggetto azienda non deve essere null e l'id deve essere di un formato corretto
     * @post Viene restituito l'annuncio nella lista di annunci pubblicati dall'azienda passata come parametro
     * che ha l'id uguale a quello passato come parametro
    */
    Annuncio getAnnuncioById(Azienda azienda,int id);

    /**
    * Permette di ottenere lo stato di un annuncio
    * @param annuncio oggetto Annuncio
    * @return String
     * @pre L'oggetto annuncio non deve essere null
     * @post Viene restituito lo stato dell'annuncio in base al valore dell'attributo "attivo" e alla data di
     * scadenza impostata per l'annuncio passato come parametro
    */
    String getStato(Annuncio annuncio);

    /**
     * Permette di controllare se per un determinato annuncio si può accedere alla funzionalità
     * di ricerca avanzata
     * @param a oggetto Annuncio
     * @return true se l'annuncio può accedere alla funzionalità, false altrimenti
     * @pre L'oggetto annuncio non deve essere null
     * @post Viene restituito true se l'azienda può accedere alla funzionalità di ricerca avanzata per l'annuncio
     * passato come parametro, false altrimenti
     */
    boolean canAnnuncioAccessToSearch(Annuncio a);
}
