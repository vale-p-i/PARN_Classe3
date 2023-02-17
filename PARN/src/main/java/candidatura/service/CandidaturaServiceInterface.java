package candidatura.service;

import storage.entity.Annuncio;
import storage.entity.Candidatura;
import storage.entity.Persona;
import java.util.List;

public interface CandidaturaServiceInterface {

    /**
     * Questo metodo fornisce una lista di Candidature appartenenti a un annuncio.
     * @param annuncio l'annuncio di cui si vogliono le candidature
     * @return la lista di candidatura dell'annuncio
     */
    List<Candidatura> getCandidatureByAnnuncio(Annuncio annuncio);

    /**
     * Questo metodo fornisce tutte candidature di una persona
     * @param persona la persona di cui si vogliono le candidature
     * @return la lista di candidature della persona
     */
    List<Candidatura> getCandidatureByPersona(Persona persona);

    /**
     * Questo metodo permette di creare una nuova candidatura
     * @pre not getAllCandidature(candidatura.persona).contains(candidatura)
     * @param candidatura è l'oggetto Candidatura da inserire
     * @return true se la creazione della candidatura è andata a buon fine, altriemnti false
     * @post getAllCandidature(candidatura.persona).contains(candidatura) &&
     * getAllCandidature(candidatura.persona).size() = @pre.getAllCandidature(candidatura.persona).size() + 1
     */
    boolean creaCandidatura(Candidatura candidatura);

    /**
     * Questo metodo elimina una candidatura di una persona.
     * @pre getAllCandidature(candidatura.persona).contains(candidatura)
     * @param candidatura è la candidatura da eliminare
     * @return true se la cancellazione è andata a buon fine, altrimenti false
     * @post not getAllCandidature(candidatura.persona).contains(candidatura) &&
     * getAllCandidature(candidatura.persona).size() = @pre.getAllCandidature(candidatura.persona).size() - 1
     */
    boolean eliminaCandidatura(Candidatura candidatura);
}
