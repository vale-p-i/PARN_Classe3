package storage.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Annuncio {

    private String ruolo;
    private Sede sede;
    private int numeroPersone;
    private String descrizione;
    private LocalDateTime dataScadenza;
    private List<String> requisiti;
    private List<String> preferenze;

    private List<String> keyword;
    private String stato;
    private List<Candidatura> candidature;
    private Azienda azienda;
    private Long id;

    public Annuncio(String ruolo, Sede sede, int numeroPersone, String descrizione, LocalDateTime dataScadenza, List<String> requisiti, List<String> preferenze, List<String> keyword, String stato, List<Candidatura> candidature, Azienda azienda) {
        this.ruolo = ruolo;
        this.sede = sede;
        this.numeroPersone = numeroPersone;
        this.descrizione = descrizione;
        this.dataScadenza = dataScadenza;
        this.requisiti = requisiti;
        this.preferenze = preferenze;
        this.keyword = keyword;
        this.stato = stato;
        this.candidature = candidature;
        this.azienda = azienda;
    }

    public Annuncio() {

    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }

    public int getNumeroPersone() {
        return numeroPersone;
    }

    public void setNumeroPersone(int numeroPersone) {
        this.numeroPersone = numeroPersone;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public LocalDateTime getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDateTime dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public List<String> getRequisiti() {
        return requisiti;
    }

    public void setRequisiti(List<String> requisiti) {
        this.requisiti = requisiti;
    }

    public List<String> getPreferenze() {
        return preferenze;
    }

    public void setPreferenze(List<String> preferenze) {
        this.preferenze = preferenze;
    }

    public List<String> getKeyword() {
        return keyword;
    }

    public void setKeyword(List<String> keyword) {
        this.keyword = keyword;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public List<Candidatura> getCandidature() {
        return candidature;
    }

    public void setCandidature(List<Candidatura> candidature) {
        this.candidature = candidature;
    }

    public Azienda getAzienda() {
        return azienda;
    }

    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
