package utente.service;

import storage.entity.Azienda;
import storage.entity.Persona;
import storage.entity.Sede;
import storage.entity.Utente;
import utente.dao.UtenteDAO;

import java.sql.SQLException;

public class UtenteService implements UtenteServiceInterface{
    private static UtenteDAO utenteDAO = new UtenteDAO();
    @Override
    public Persona getPersonaById(int id) {
        Persona persona = new Persona();
        try{
            persona = utenteDAO.getPersonaById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return persona;
    }

    @Override
    public Azienda getAziendaById(int id) {
        Azienda azienda = new Azienda();
        try{
            azienda = utenteDAO.getAziendaById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return azienda;
    }

    @Override
    public Sede getSedeById(Azienda s,int id) {
            for (Sede se: s.getSedi())
                if (se.getId()==id)
                    return se;
            return null;
        }

    @Override
    public void registraPersona(Persona persona) {
        try{
            utenteDAO.addPersona(persona);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void registraAzienda(Azienda azienda) {
        try{
            utenteDAO.addAzienda(azienda);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void aggiornaPersona(Persona persona) {
        try{
            utenteDAO.aggiornaPersona(persona);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void aggiornaAzienda(Azienda azienda) {
        try{
            utenteDAO.aggiornaAzienda(azienda);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Utente autenticazione(String mail, String password) {
        Utente utente;
        try{
            utente = utenteDAO.autenticazione(mail, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return utente;
    }
}
