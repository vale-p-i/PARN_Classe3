package storage.entity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Un oggetto <code>Persona</code> è una specifica dell'oggetto <code>Utente</code> ed è caratterizzata, oltre dagli
 * attributi dell'utente, anche dal cognome, codice fiscale, data di nascita, filtro della macroarea, la posizone
 * desiderata, il curriculum e le candidature.
 */
public class Persona extends Utente {

    public static final List<String> FILTRO_MACROAREA= Arrays.asList("Tutti","Area di competenza","Settore specifico");

    private String cognome;
    private String codiceFiscale;
    private LocalDate dataDiNascita;
    private String filtroMacroarea;
    private String posizioneDesiderata;
    private Curriculum curriculum;
    private List<Candidatura> candidature;

    public Persona(int id, String nome, String mail, String password, String regione, String provincia, String foto, String cap, String telefono, String citta, String via, String cognome, String codiceFiscale, LocalDate dataDiNascita, String filtroMacroarea, String posizioneDesiderata, Curriculum curriculum, List<Candidatura> candidature) {
        super(id, nome, mail, password, regione, provincia, foto, cap, telefono, citta, via);
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.dataDiNascita = dataDiNascita;
        this.filtroMacroarea = filtroMacroarea;
        this.posizioneDesiderata = posizioneDesiderata;
        this.curriculum = curriculum;
        this.candidature = candidature;
    }

    public Persona(String nome, String mail, String password, String regione, String provincia, String foto, String cap, String telefono, String citta, String via, String cognome, String codiceFiscale, LocalDate dataDiNascita, String filtroMacroarea, String posizioneDesiderata, Curriculum curriculum, List<Candidatura> candidature) {
        super(nome, mail, password, regione, provincia, foto, cap, telefono, citta, via);
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.dataDiNascita = dataDiNascita;
        this.filtroMacroarea = filtroMacroarea;
        this.posizioneDesiderata = posizioneDesiderata;
        this.curriculum = curriculum;
        this.candidature = candidature;
    }

    public Persona(){}
    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    public void setCodiceFiscale(String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }

    public LocalDate getDataDiNascita() {
        return dataDiNascita;
    }

    public void setDataDiNascita(LocalDate dataDiNascita) {
        this.dataDiNascita = dataDiNascita;
    }

    public String getFiltroMacroarea() {
        return filtroMacroarea;
    }

    public void setFiltroMacroarea(String filtroMacroarea) {
        if (FILTRO_MACROAREA.contains(filtroMacroarea))
            this.filtroMacroarea = filtroMacroarea;
        else throw new IllegalArgumentException("Filtro macroarea non valido");
    }

    public String getPosizioneDesiderata() {
        return posizioneDesiderata;
    }

    public void setPosizioneDesiderata(String posizioneDesiderata) {
        this.posizioneDesiderata = posizioneDesiderata;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public List<Candidatura> getCandidature() {
        return candidature;
    }

    public void setCandidature(List<Candidatura> candidature) {
        this.candidature = candidature;
    }
}