package utente.service;

import storage.entity.Azienda;
import storage.entity.Persona;
import storage.entity.Sede;
import storage.entity.Utente;

public interface UtenteServiceInterface {
    /**
     * Il metodo permette di ottenere un oggetto di tipo Persona fornendo l'ID.
     * @param id è l'id della persona
     * @return una persona in caso di successo, altrimenti null
     * */
    Persona getPersonaById(int id);

    /**
     * Il metodo permette di ottenere un oggetto di tipo Azienda fornendo l'ID.
     * @param id è l'id dell'azienda
     * @return l'oggetto Azienda in caso di successo, altrimenti null
     * */
    Azienda getAziendaById(int id);

    /**
     * Il metodo permette di ottenere un oggetto di tipo Sede fornendo l'ID e l'azienda di appartenenza.
     * @param azienda l'azienda di cui si vuole la sede
     * @param id è l'id della sede
     * @return in caso di successo restituisce l'oggetto Sede, altrimenti null
     * */
    Sede getSedeById(Azienda azienda,int id);

    /**
     * Il metodo permette di registrare una persona.
     * @param persona è la persona che si vuole registrare
     * @return true se la registrazione è andata a buon fine, altrimenti false
     * */
    boolean registraPersona(Persona persona);

    /**
     * Il metodo permette di registrare una Azienda.
     * @param azienda è l'azienda che si vuole registrare
     * @return true se la registrazione ha avuto successo, altrimenti false
     * */
    boolean registraAzienda(Azienda azienda);

    /**
     * Il metodo permette di registrare una Sede nel database fornendo l'oggetto Sede.
     * @pre not getSediByAzienda(sede.azienda).contains(sede)
     * @post getSediByAzienda(sede.azienda).contains(sede) &&
     * getSediByAzienda(sede.azienda).size() == @pre.getSediByAzienda(sede.azienda).size() + 1
     * @param sede è la sede che si vuole registrare
     * @return true se la registrazione ha avuto successo, altrimenti false
     * */
    boolean registraSede(Sede sede);

    /**
     * Il metodo permette di aggiornare una persona nel database fornendo l'oggetto Persona.
     * @param persona è la persona da aggiornare
     * @return true se l'aggiornamento ha avuto successo, altrimenti false
     * */
    boolean aggiornaPersona(Persona persona);

    /**
     * Il metodo permette di aggiornare una azienda nel database fornendo l'oggetto Azienda.
     * @param azienda è l'azienda da aggiornare
     * @return true se l'aggiornamento ha avuto successo, altrimenti false
     * */
    boolean aggiornaAzienda(Azienda azienda);

    /**
     * Il metodo permette di aggiornare una sede nel database fornendo l'oggetto Sede.
     * @pre getSediByAzienda(sede.azienda).contains(sede)
     * @post getSediByAzienda(sede.azienda).contains(sede) &&
     * getSediByAzienda(sede.azienda).size() == @pre.getSediByAzienda(sede.azienda).size()
     * @param sede è la sede da aggiornare
     * @return true se l'aggiornamento ha avuto successo, altrimenti false
     * */
    boolean aggiornaSede(Sede sede);

    /**
     * Il metodo permette di aggiornare un Utente nel database fornendo l'oggetto Utente.
     * @param utente è l'utente da aggiornare
     * @return true se l'0aggiornamento è andato a buon fine, altriemnti false
     * */
    boolean aggiornaUtente(Utente utente);

    /**
     * Il metodo permette di autenticare un utente.
     * @param mail è una stringa che rappresenta la mail
     * @param password è una stringa che rappresenta la password
     * @return in caso di successo restituisce un oggetto di tipo Utente, altrimenti null
     * */
    Utente autenticazione(String mail, String password);

}
