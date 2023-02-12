package candidatura.service;

import storage.entity.Annuncio;
import storage.entity.Candidatura;
import storage.entity.Persona;

import java.sql.SQLException;
import java.util.List;

public interface CandidaturaServiceInterface {
    public List<Candidatura> getCandidatureByAnnuncio(Annuncio annuncio) throws SQLException;
    public Candidatura getCandidaturaByPersonaAndAnnuncio(Persona persona, Annuncio annuncio) throws SQLException;
    public List<Candidatura> getCandidatueByPersona(Persona persona) throws SQLException;
    public boolean creaCandidatura(Candidatura candidatura);
    public boolean eliminaCandidatura(Candidatura candidatura);
}
