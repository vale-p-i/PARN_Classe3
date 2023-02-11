package storage.entity;

import java.time.LocalDateTime;
import java.util.List;

public class EsperienzaLavorativa {

    private LocalDateTime dataInizio;
    private LocalDateTime dataFine;
    private String nomeAzienda;
    private String tipoAzienda;
    private String datore;
    private String tipoImpiego;
    private List<String> mansioniPrincipale;
    private Long id;

    public EsperienzaLavorativa() {
    }

    public EsperienzaLavorativa(LocalDateTime dataInizio, LocalDateTime dataFine, String nomeAzienda, String tipoAzienda, String datore, String tipoImpiego, List<String> mansioniPrincipale) {
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.nomeAzienda = nomeAzienda;
        this.tipoAzienda = tipoAzienda;
        this.datore = datore;
        this.tipoImpiego = tipoImpiego;
        this.mansioniPrincipale = mansioniPrincipale;
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

    public String getNomeAzienda() {
        return nomeAzienda;
    }

    public void setNomeAzienda(String nomeAzienda) {
        this.nomeAzienda = nomeAzienda;
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

    public String getTipoImpiego() {
        return tipoImpiego;
    }

    public void setTipoImpiego(String tipoImpiego) {
        this.tipoImpiego = tipoImpiego;
    }

    public List<String> getMansioniPrincipale() {
        return mansioniPrincipale;
    }

    public void setMansioniPrincipale(List<String> mansioniPrincipale) {
        this.mansioniPrincipale = mansioniPrincipale;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}