package storage.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Persona extends Utente {

    private String cognome;
    private String codiceFiscale;
    private LocalDateTime dataDiNascita;
    private String filtroMacroarea;
    private String posizioneDesiderata;
    private Curriculum curriculum;
    private List<Candidatura> candidature;

    public Persona(String nome, String mail, String password, String regione, String provincia, int cap, String citta,
                   String via, String telefono, String foto, String cognome, String codiceFiscale,
                   LocalDateTime dataDiNascita, String filtroMacroarea, String posizioneDesiderata,
                   Curriculum curriculum, List<Candidatura> candidature) {
        super(nome, mail, password, regione, provincia, cap, citta, via, telefono, foto);
        this.cognome = cognome;
        this.codiceFiscale = codiceFiscale;
        this.dataDiNascita = dataDiNascita;
        this.filtroMacroarea = filtroMacroarea;
        this.posizioneDesiderata = posizioneDesiderata;
        this.curriculum = curriculum;
        this.candidature = candidature;
    }
}