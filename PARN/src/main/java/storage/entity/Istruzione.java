package storage.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Istruzione {

    private Curriculum curriculum;//?
    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;
    private String qualifica;
    private String tipo;
    private String istituto;

    public Istruzione(Curriculum curriculum){
        this.curriculum = curriculum;
    }

    public Istruzione(Curriculum curriculum, LocalDateTime dataInizio, LocalDateTime dataFine, String qualifica,
                      String tipo, String istituto) {
        this.curriculum = curriculum;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.qualifica = qualifica;
        this.tipo = tipo;
        this.istituto = istituto;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getIstituto() {
        return istituto;
    }

    public void setIstituto(String istituto) {
        this.istituto = istituto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Istruzione that = (Istruzione) o;
        return Objects.equals(curriculum, that.curriculum) && Objects.equals(dataInizio, that.dataInizio) && Objects.equals(dataFine, that.dataFine) && Objects.equals(qualifica, that.qualifica) && Objects.equals(tipo, that.tipo) && Objects.equals(istituto, that.istituto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(curriculum, dataInizio, dataFine, qualifica, tipo, istituto);
    }
}
