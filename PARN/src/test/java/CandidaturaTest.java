import annuncio.service.AnnuncioService;
import annuncio.service.AnnuncioServiceInterface;
import candidatura.service.CandidaturaService;
import candidatura.service.CandidaturaServiceInterface;
import org.junit.jupiter.api.Test;
import storage.entity.*;
import utente.service.UtenteService;
import utente.service.UtenteServiceInterface;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CandidaturaTest {

    @Test
    public void TestDBCreation(){
        CandidaturaServiceInterface candidaturaServiceInterface = new CandidaturaService();
        UtenteServiceInterface utenteServiceInterface = new UtenteService();
        AnnuncioServiceInterface annuncioServiceInterface = new AnnuncioService();

        Persona persona = utenteServiceInterface.getPersonaById(1);
        Annuncio annuncio = annuncioServiceInterface.getAnnuncioById(1);

        Candidatura candidatura = candidaturaServiceInterface.getCandidaturaByPersonaAndAnnuncio(persona, annuncio);

        assertTrue(candidaturaServiceInterface.creaCandidatura(candidatura));
    }



}
