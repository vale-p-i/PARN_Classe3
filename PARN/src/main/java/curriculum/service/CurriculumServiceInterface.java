package curriculum.service;

import storage.entity.*;

import java.io.IOException;
import java.util.List;

public interface CurriculumServiceInterface {
    List<Curriculum> getAllCurriculum();
    Curriculum getCurriculumByPersona(Persona persona);
    boolean creaCurriculum(Curriculum curriculum);
    boolean aggiornaCurriculum(Curriculum curriculum);
    String downloadCurriculum(Curriculum curriculum) throws IOException;
    boolean aggiungiEsperienzaLavorativa(EsperienzaLavorativa esperienzaLavorativa);
    boolean aggiungiLingua(Lingua lingua);
    boolean aggiungiIstruzione(Istruzione istruzione);
    boolean aggiornaEsperienzaLavorativa(EsperienzaLavorativa esperienzaLavorativa);
    boolean aggiornaLingua(Lingua lingua);
    boolean aggiornaIstruzione(Istruzione istruzione);
    boolean eliminaEsperienzaLavorativa(EsperienzaLavorativa esperienzaLavorativa);
    int eliminaLingua(Lingua lingua);
    int eliminaIstruzione(Istruzione istruzione);
}
