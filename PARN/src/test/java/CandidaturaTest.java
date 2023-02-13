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

        System.out.println(annuncio.getId());

        Candidatura candidatura = candidaturaServiceInterface.getCandidaturaByPersonaAndAnnuncio(persona, annuncio);

        assertNotNull(candidatura);
    }



}
