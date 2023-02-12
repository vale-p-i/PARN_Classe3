package annuncio.service;

import annuncio.dao.AnnuncioDAO;
import storage.entity.Annuncio;

import java.util.List;

public class AnnuncioService implements AnnuncioServiceInterface{

    AnnuncioDAO annuncioDAO;

    public AnnuncioService(){
        annuncioDAO = new AnnuncioDAO();
    }

    @Override
    public List<Annuncio> getAnnuncioById(int id) {
        return annuncioDAO.getAnnuncioById(id);
    }
}
