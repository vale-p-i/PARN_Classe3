package utente.service;

import storage.entity.Azienda;
import storage.entity.Persona;
import storage.entity.Sede;

public class UtenteService implements UtenteServiceInterface{
    @Override
    public Persona getPersonaById(int anInt) {
        return null;
    }

    @Override
    public Azienda getAziendaById(int id) {
        return null;
    }

    @Override
    public Sede getSedeById(Azienda s,int id) {
            for (Sede se: s.getSedi())
                if (se.getId()==id)
                    return se;
            return null;
        }
}
