package storage.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Annuncio {

    private int id;
    private Azienda azienda;
    private boolean attivo;
    private Sede sede;
    private int numeroPersone;
    private String descrizione;
    private LocalDateTime dataScadenza;
    private List<String> requisiti;
    private List<String> keyword;
    private List<String> preferenze;
    private String ruolo;
    private List<Candidatura> candidature;

    public Annuncio(int id, Azienda azienda, boolean attivo, Sede sede, int numeroPersone, String descrizione,
                    LocalDateTime dataScadenza, List<String> requisiti, List<String> keyword, List<String> preferenze,
                    String ruolo, List<Candidatura> candidature) {
        this.id = id;
        this.azienda = azienda;
        this.attivo = attivo;
        this.sede = sede;
        this.numeroPersone = numeroPersone;
        this.descrizione = descrizione;
        this.dataScadenza = dataScadenza;
        this.requisiti = requisiti;
        this.keyword = keyword;
        this.preferenze = preferenze;
        this.ruolo = ruolo;
        this.candidature = candidature;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Azienda getAzienda() {
        return azienda;
    }

    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
    }

    public boolean isAttivo() {
        return attivo;
    }

    public void setAttivo(boolean attivo) {
        this.attivo = attivo;
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

    public List<String> getKeyword() {
        return keyword;
    }

    public void setKeyword(List<String> keyword) {
        this.keyword = keyword;
    }

    public List<String> getPreferenze() {
        return preferenze;
    }

    public void setPreferenze(List<String> preferenze) {
        this.preferenze = preferenze;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public List<Candidatura> getCandidature() {
        return candidature;
    }

    public void setCandidature(List<Candidatura> candidature) {
        this.candidature = candidature;
    }
}
