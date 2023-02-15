package storage.entity;

import java.time.LocalDateTime;
import java.util.List;

public class Annuncio {

    public static final String  IN_CORSO="In Corso";
    public static final String  SCADUTO="Scaduto";
    public static final String  CHIUSO="Chiuso";


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
        this.id = id;               //1
        this.azienda = azienda;     //2
        this.attivo = attivo;       //3
        this.sede = sede;           //4
        this.numeroPersone = numeroPersone; //5
        this.descrizione = descrizione;     //6
        this.dataScadenza = dataScadenza;   //7
        this.requisiti = requisiti;         //8
        this.keyword = keyword;             //9
        this.preferenze = preferenze;       //10
        this.ruolo = ruolo;                 //11
        this.candidature = candidature;     //12
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
