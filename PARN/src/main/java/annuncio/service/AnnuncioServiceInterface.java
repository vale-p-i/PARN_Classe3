package annuncio.service;

import storage.entity.Annuncio;
import storage.entity.Azienda;
import storage.entity.Candidatura;

import java.sql.*;
import java.util.List;

public interface AnnuncioServiceInterface {

    /**
     * @param id L'ID dell'Annuncio da cercare.
     * @return Ritorna true se ha avuto successo, altrimenti false
     */
    public Annuncio getAnnuncioById(int id);
    /**
     * @param annuncio L'Annuncio da creare.
     * @return Ritorna true se ha avuto successo, altrimenti false
     */
    public boolean creaAnnuncio(Annuncio annuncio);
    /**
     * @param annuncio l'annuncio da modificare
     * @return Ritorna true se ha avuto successo, altrimenti false
     */
    public boolean modificaAnnuncio(Annuncio annuncio);
    /**
     * @param annuncio l'annuncio da eliminare
     * @return Ritorna true se ha avuto successo, altrimenti false
     */
    public boolean eliminaAnnuncio(Annuncio annuncio);
    /**
     * @param annuncio l'annuncio da chiudere
     * @return Ritorna true se ha avuto successo, altrimenti false
     */
    public boolean chiusuraAnnuncio(Annuncio annuncio);
    /**
     * @param annuncio l'annuncio al quale aggiungere la candidatura
     * @param candidatura la candidatura da aggiungere all'annuncio
     * @return Ritorna true se ha avuto successo, altrimenti false
     */
    public boolean aggiungiCandidatura(Annuncio annuncio, Candidatura candidatura);
    /**
     * @param annuncio l'annuncio dal quale rimuovere la candidatura
     * @param candidatura la candidatura da rimuovere dall'annuncio
     * @return Ritorna true se ha avuto successo, altrimenti false
     */
    public boolean rimuoviCandidatura(Annuncio annuncio, Candidatura candidatura);
    /**
     * @param annuncio l'annuncio dal quale prendere le candidature
     * @return Ritorna una lista di candidature
     */
    public List<Candidatura> visualizzaCandidatura(Annuncio annuncio);
    /**
     * @param azienda l'azienda sulla quale cercare gli annunci
     * @return Ritorna una lista di annunci
     */
    public List<Annuncio> getAnnuncioByAzienda(Azienda azienda);
    /**
     * @param stato la stringa che specifica lo stato dell'annuncio ES: in corso, chiuso, ecc
     * @return Ritorna una lista di annunci
     */
    List<Annuncio> getAnnunciByStato(String stato);
}
