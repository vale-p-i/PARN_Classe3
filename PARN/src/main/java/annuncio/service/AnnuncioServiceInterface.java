package annuncio.service;

import candidatura.service.CandidaturaService;
import storage.entity.Annuncio;
import storage.entity.Candidatura;
import utils.ConPool;
import utils.KeywordUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public interface AnnuncioServiceInterface {

    public List<Annuncio> getAnnuncioById(int id);
    public boolean creaAnnuncio(Annuncio annuncio);
    public boolean modificaAnnuncio(Annuncio annuncio);
    public boolean eliminaAnnuncio(Annuncio annuncio);
    public boolean chiusuraAnnuncio(Annuncio annuncio);
    public boolean aggiungiCandidatura(Annuncio annuncio, Candidatura candidatura);
    public boolean rimuoviCandidatura(Annuncio annuncio, Candidatura candidatura);
    public List<Candidatura> visualizzaCandidatura(Annuncio annuncio);

}
