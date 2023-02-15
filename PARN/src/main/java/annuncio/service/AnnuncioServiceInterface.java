package annuncio.service;

import storage.entity.Annuncio;
import storage.entity.Azienda;
import storage.entity.Candidatura;

import java.sql.*;
import java.util.List;

public interface AnnuncioServiceInterface {

    /**
     * Permette di ottenere un Annuncio dato il suo ID
     * @param id int
     * @return Ritorna true se ha avuto successo, altrimenti false
     */
    public Annuncio getAnnuncioById(int id);
    /**
     * Permette di persistere un annuncio nel database. In caso di successo ritorna true, altrimenti false
     * @param annuncio oggetto Annuncio
     * @return Ritorna true se ha avuto successo, altrimenti false
     */
    public boolean creaAnnuncio(Annuncio annuncio);
    /**
     * Permette di modificare un annuncio presente nel DB.
     * @param annuncio oggetto Annuncio
     * @return Ritorna true se ha avuto successo, altrimenti false
     */
    public boolean modificaAnnuncio(Annuncio annuncio);
    /**
     * Permette di eliminare un annuncio dal db.
     * @param annuncio oggetto Annuncio
     * @return Ritorna true se ha avuto successo, altrimenti false
     */
    public boolean eliminaAnnuncio(Annuncio annuncio);
    /**
     * Permette di chiudere un annuncio
     * @param annuncio oggetto Annuncio
     * @return Ritorna true se ha avuto successo, altrimenti false
     */
    public boolean chiusuraAnnuncio(Annuncio annuncio);
    /**
     * Permette di aggiungere una candidatura ad un annuncio
     * @param annuncio oggetto Annuncio
     * @param candidatura oggetto candidatura
     * @return Ritorna true se ha avuto successo, altrimenti false
     */
    public boolean aggiungiCandidatura(Annuncio annuncio, Candidatura candidatura);
    /**
     * Permette di rimuovere una candidatura da un annuncio
     * @param annuncio oggetto Annuncio
     * @param candidatura oggetto Candidatura
     * @return Ritorna true se ha avuto successo, altrimenti false
     */
    public boolean rimuoviCandidatura(Annuncio annuncio, Candidatura candidatura);
    /**
     * Permette di ottenere le candidature dato un annuncio
     * @param annuncio oggetto Annuncio
     * @return Ritorna una lista di candidature
     */
    public List<Candidatura> visualizzaCandidatura(Annuncio annuncio);
    /**
     * Permette di ottenere gli annunci pubblicati da un azienda
     * @param azienda oggetto Annuncio
     * @return Ritorna una lista di annunci
     */
    public List<Annuncio> getAnnuncioByAzienda(Azienda azienda);
    /**
     * Permette di ottenere gli annunci con un certo stato
     * @param stato la stringa che specifica lo stato dell'annuncio ES: in corso, chiuso, ecc
     * @return Ritorna una lista di annunci
     */
    List<Annuncio> getAnnunciByStato(String stato);
}
