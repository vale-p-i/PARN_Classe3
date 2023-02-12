package annuncio.service;

import annuncio.dao.AnnuncioDAO;
import storage.entity.Annuncio;
import storage.entity.Candidatura;

import java.sql.SQLException;
import java.util.List;

public class AnnuncioService implements AnnuncioServiceInterface{

    AnnuncioDAO annuncioDAO;

    public AnnuncioService(){
        annuncioDAO = new AnnuncioDAO();
    }

    @Override
    public List<Annuncio> getAnnuncioById(int id) throws SQLException {
        return null;
    }

    @Override
    public boolean creaAnnuncio(Annuncio annuncio) {
        return false;
    }

    @Override
    public boolean modificaAnnuncio(Annuncio annuncio) {
        return false;
    }

    @Override
    public boolean eliminaAnnuncio(Annuncio annuncio) {
        return false;
    }

    @Override
    public boolean chiusuraAnnuncio(Annuncio annuncio) {
        return false;
    }

    @Override
    public boolean aggiungiCandidatura(Annuncio annuncio, Candidatura candidatura) {
        return false;
    }

    @Override
    public boolean rimuoviCandidatura(Annuncio annuncio, Candidatura candidatura) {
        return false;
    }

    @Override
    public List<Annuncio> getAnnunciByStato(String in_corso) {
        return null;
    }
}
