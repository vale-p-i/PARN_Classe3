package utente.service;

import storage.entity.Azienda;
import storage.entity.Persona;
import storage.entity.Sede;
import storage.entity.Utente;

public interface UtenteServiceInterface {
    Persona getPersonaById(int id);
    Azienda getAziendaById(int id);

    Sede getSedeById(Azienda s,int id);
    boolean registraPersona(Persona persona);
    boolean registraAzienda(Azienda azienda);
    boolean registraSede(Sede sede);
    boolean aggiornaPersona(Persona persona);
    boolean aggiornaAzienda(Azienda azienda);
    boolean aggiornaSede(Sede sede);

    boolean aggiornaUtente(Utente utente);
    Utente autenticazione(String mail, String password);
    boolean eliminaAzienda(Azienda azienda);
    boolean eliminaPersona(Persona persona);

}
