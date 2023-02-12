package utente.service;

import storage.entity.Azienda;
import storage.entity.Persona;
import storage.entity.Sede;

public interface UtenteServiceInterface {
    Persona getPersonaById(int anInt);
    Azienda getAziendaById(int id);

    Sede getSedeById(Azienda s,int id);
}
