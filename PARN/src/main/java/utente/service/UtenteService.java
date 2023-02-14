package utente.service;

import storage.entity.Azienda;
import storage.entity.Persona;
import storage.entity.Sede;
import storage.entity.Utente;
import utente.dao.UtenteDAO;

import java.sql.SQLException;

public class UtenteService implements UtenteServiceInterface{
    private UtenteDAO utenteDAO;
    public UtenteService(){
        this.utenteDAO = new UtenteDAO();
    }

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
    public boolean registraPersona(Persona persona) {
        try{
            utenteDAO.addPersona(persona);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean registraAzienda(Azienda azienda) {
        try{
            utenteDAO.addAzienda(azienda);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean registraSede(Sede sede){
        try{
            utenteDAO.addSede(sede);
            return true;
        } catch (SQLException e){
            return false;
        }
    }
    @Override
    public boolean aggiornaPersona(Persona persona) {
        try{
            utenteDAO.aggiornaPersona(persona);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public boolean aggiornaAzienda(Azienda azienda) {
        try{
            utenteDAO.aggiornaAzienda(azienda);
            return true;
        } catch (SQLException e) {
            return false;
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
