import annuncio.service.AnnuncioService;
import annuncio.service.AnnuncioServiceInterface;
import candidatura.service.CandidaturaService;
import candidatura.service.CandidaturaServiceInterface;
import org.junit.jupiter.api.Test;
import storage.entity.*;
import utente.service.UtenteService;
import utente.service.UtenteServiceInterface;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CandidaturaTest {

    @Test
    public void TestDBCreation(){
        CandidaturaServiceInterface candidaturaServiceInterface = new CandidaturaService();
        UtenteServiceInterface utenteServiceInterface = new UtenteService();
        AnnuncioServiceInterface annuncioServiceInterface = new AnnuncioService();

        Persona persona = utenteServiceInterface.getPersonaById(2);
        Annuncio annuncio = annuncioServiceInterface.getAnnuncioById(1);

        System.out.println(annuncio.getId()+" "+ annuncio.getSede().getId()+" "+ annuncio.getAzienda().getId());
        System.out.println("CERCO CANDIDATURA PER UTENTE: "+persona.getId()+" ANNUNCIO: "+annuncio.getId());
        Candidatura candidatura = candidaturaServiceInterface.getCandidaturaByPersonaAndAnnuncio(persona, annuncio);

        System.out.println(candidatura.toString());

        if (candidatura == null)
            System.out.println("ERRORE, NULL CANDIDATURA");

        assertNotNull(candidatura);
    }



}
