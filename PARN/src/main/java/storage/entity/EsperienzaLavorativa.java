package storage.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class EsperienzaLavorativa {

    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;
    private String tipoAzienda;
    private String datore;
    private String contatto;
    private String tipoImpiego;
    private List<String> mansioniPrincipali;
    private String nomeAzienda;
    private Curriculum curriculum;

    public EsperienzaLavorativa(Curriculum curriculum){
        this.curriculum = curriculum;
    }

    public EsperienzaLavorativa(LocalDateTime dataInizio, LocalDateTime dataFine, String tipoAzienda, String datore, String contatto, String tipoImpiego, List<String> mansioniPrincipali, String nomeAzienda, Curriculum curriculum) {
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.tipoAzienda = tipoAzienda;
        this.datore = datore;
        this.contatto = contatto;
        this.tipoImpiego = tipoImpiego;
        this.mansioniPrincipali = mansioniPrincipali;
        this.nomeAzienda = nomeAzienda;
        this.curriculum = curriculum;
    }

    public String getNomeAzienda() {
        return nomeAzienda;
    }

    public void setNomeAzienda(String nomeAzienda) {
        this.nomeAzienda = nomeAzienda;
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

    public String getTipoAzienda() {
        return tipoAzienda;
    }

    public void setTipoAzienda(String tipoAzienda) {
        this.tipoAzienda = tipoAzienda;
    }

    public String getDatore() {
        return datore;
    }

    public void setDatore(String datore) {
        this.datore = datore;
    }

    public String getContatto() {
        return contatto;
    }

    public void setContatto(String contatto) {
        this.contatto = contatto;
    }

    public String getTipoImpiego() {
        return tipoImpiego;
    }

    public void setTipoImpiego(String tipoImpiego) {
        this.tipoImpiego = tipoImpiego;
    }

    public List<String> getMansioniPrincipali() {
        return mansioniPrincipali;
    }

    public void setMansioniPrincipali(List<String> mansioniPrincipali) {
        this.mansioniPrincipali = mansioniPrincipali;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EsperienzaLavorativa that = (EsperienzaLavorativa) o;
        return Objects.equals(dataInizio, that.dataInizio) && Objects.equals(dataFine, that.dataFine) && Objects.equals(tipoAzienda, that.tipoAzienda) && Objects.equals(datore, that.datore) && Objects.equals(contatto, that.contatto) && Objects.equals(tipoImpiego, that.tipoImpiego) && Objects.equals(mansioniPrincipali, that.mansioniPrincipali) && Objects.equals(nomeAzienda, that.nomeAzienda) && Objects.equals(curriculum, that.curriculum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataInizio, dataFine, tipoAzienda, datore, contatto, tipoImpiego, mansioniPrincipali, nomeAzienda, curriculum);
    }
}