package utente.service;

import storage.entity.Azienda;
import storage.entity.Persona;
import storage.entity.Sede;
import storage.entity.Utente;

public interface UtenteServiceInterface {
    /**
     * Il metodo permette di ottenere un oggetto di tipo Persona fornendo l'ID.
     * In caso di successo restituisce l'oggetto Persona, altrimenti null
     * @param id int
     * @return oggetto Persona
     * */
    Persona getPersonaById(int id);
    /**
     * Il metodo permette di ottenere un oggetto di tipo Azienda fornendo l'ID.
     * In caso di successo restituisce l'oggetto Azienda, altrimenti null
     * @param id int
     * @return oggetto Azienda
     * */
    Azienda getAziendaById(int id);
    /**
     * Il metodo permette di ottenere un oggetto di tipo Sede fornendo l'ID e l'azienda di appartenenza.
     * In caso di successo restituisce l'oggetto Sede, altrimenti null
     * @param azienda Oggetto Azienda
     * @param id int
     * @return oggetto Sede
     * */
    Sede getSedeById(Azienda azienda,int id);
    /**
     * Il metodo permette di registrare una persona nel database fornendo l'oggetto Persona.
     * In caso di successo restituisce true altrimenti false
     * @param persona oggetto Persona
     * @return boolean
     * */
    boolean registraPersona(Persona persona);
    /**
     * Il metodo permette di registrare una Azienda nel database fornendo l'oggetto Azienda.
     * In caso di successo restituisce true altrimenti false
     * @param azienda oggetto Azienda
     * @return boolean
     * */
    boolean registraAzienda(Azienda azienda);
    /**
     * Il metodo permette di registrare una Sede nel database fornendo l'oggetto Sede.
     * In caso di successo restituisce true altrimenti false
     * @param sede oggetto Sede
     * @return boolean
     * */
    boolean registraSede(Sede sede);
    /**
     * Il metodo permette di aggiornare una persona nel database fornendo l'oggetto Persona.
     * In caso di successo restituisce true altrimenti false
     * @param persona oggetto Persona
     * @return boolean
     * */
    boolean aggiornaPersona(Persona persona);
    /**
     * Il metodo permette di aggiornare una azienda nel database fornendo l'oggetto Azienda.
     * In caso di successo restituisce true altrimenti false
     * @param azienda oggetto Azienda
     * @return boolean
     * */
    boolean aggiornaAzienda(Azienda azienda);
    /**
     * Il metodo permette di aggiornare una sede nel database fornendo l'oggetto Sede.
     * In caso di successo restituisce true altrimenti false
     * @param sede oggetto Sede
     * @return boolean
     * */
    boolean aggiornaSede(Sede sede);
    /**
     * Il metodo permette di aggiornare un Utente nel database fornendo l'oggetto Utente.
     * In caso di successo restituisce true altrimenti false
     * @param utente oggetto Utente
     * @return boolean
     * */
    boolean aggiornaUtente(Utente utente);
    /**
     * Il metodo permette di autenticare un utente.
     * In caso di successo restituisce un oggetto di tipo Utente, altrimenti null
     * @param mail String
     * @param password String
     * @return oggetto Utente
     * */
    Utente autenticazione(String mail, String password);

}
