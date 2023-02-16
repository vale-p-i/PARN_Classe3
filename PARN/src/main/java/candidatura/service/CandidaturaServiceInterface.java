package candidatura.service;

import storage.entity.Annuncio;
import storage.entity.Candidatura;
import storage.entity.Persona;

import java.util.List;

public interface CandidaturaServiceInterface {

    /**
     * Ritorna una lista di Candidature appartenenti a un annuncio.
     * @param annuncio Oggetto Annuncio
     * @return Ritorna una lista di Candidature appartenenti a un annuncio.
     */
    public List<Candidatura> getCandidatureByAnnuncio(Annuncio annuncio);
    /**
     * Ritorna una lista di Candidature alle quali la persona ha sottoscritto.
     * @param persona Oggetto Persona
     * @return List\<Candidatura\>
     */
    public List<Candidatura> getCandidatureByPersona(Persona persona);
    /**
     * Persiste una candidatura nel database. In caso di successo ritorna true, altrimenti false
     * @param candidatura Oggetto Candidatura
     * @return boolean
     */
    public boolean creaCandidatura(Candidatura candidatura);
    /**
     * Elimina una candidatura dal database. In caso di successo ritorna true, false altrimenti
     * @param candidatura Oggetto Candidatura
     * @return boolean
     */
    public boolean eliminaCandidatura(Candidatura candidatura);
}
