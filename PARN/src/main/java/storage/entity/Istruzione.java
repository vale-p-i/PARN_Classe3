package storage.entity;

import java.time.LocalDateTime;

public class Istruzione {

    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;
    private String qualifica;
    private String tipoIstruzione;
    private String nomeIstituto;
    private Long id;

    public Istruzione() {
    }

    public Istruzione(LocalDateTime dataInizio, LocalDateTime dataFine, String qualifica, String tipoIstruzione, String nomeIstituto) {
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.qualifica = qualifica;
        this.tipoIstruzione = tipoIstruzione;
        this.nomeIstituto = nomeIstituto;
    }

    public LocalDateTime getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDateTime dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDateTime getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDateTime dataFine) {
        this.dataFine = dataFine;
    }

    public String getQualifica() {
        return qualifica;
    }

    public void setQualifica(String qualifica) {
        this.qualifica = qualifica;
    }

    public String getTipoIstruzione() {
        return tipoIstruzione;
    }

    public void setTipoIstruzione(String tipoIstruzione) {
        this.tipoIstruzione = tipoIstruzione;
    }

    public String getNomeIstituto() {
        return nomeIstituto;
    }

    public void setNomeIstituto(String nomeIstituto) {
        this.nomeIstituto = nomeIstituto;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
