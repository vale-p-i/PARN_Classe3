package utente.service;

import storage.entity.Azienda;
import storage.entity.Persona;
import storage.entity.Sede;
import storage.entity.Utente;

public interface UtenteServiceInterface {
    Persona getPersonaById(int id);
    Azienda getAziendaById(int id);

    Sede getSedeById(Azienda s,int id);
    void registraPersona(Persona persona);
    void registraAzienda(Azienda azienda);
    void registraSede(Sede sede);
    void aggiornaPersona(Persona persona);
    void aggiornaAzienda(Azienda azienda);
    Utente autenticazione(String mail, String password);
}
