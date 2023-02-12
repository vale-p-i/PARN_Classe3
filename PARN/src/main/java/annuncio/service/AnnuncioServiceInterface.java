package annuncio.service;

import storage.entity.Annuncio;

import java.util.List;

public interface AnnuncioServiceInterface {

    public List<Annuncio> getAnnuncioById(int id);

    List<Annuncio> getAnnunciByStato(String in_corso);
}
