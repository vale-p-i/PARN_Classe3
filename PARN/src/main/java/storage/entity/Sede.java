package storage.entity;

public class Sede {

    private int id;
    private String regione;
    private String provincia;
    private String citta;
    private String cap;
    private String via;
    private String telefono;

    private Azienda azienda;

    public Sede(int id, String regione, String provincia, String citta, String cap, String via, String telefono, Azienda azienda) {
        this.id = id;
        this.regione = regione;
        this.provincia = provincia;
        this.citta = citta;
        this.cap = cap;
        this.via = via;
        this.telefono = telefono;
        this.azienda = azienda;
    }

    public Sede(String regione, String provincia, String citta, String cap, String via, String telefono, Azienda azienda) {
        this.regione = regione;
        this.provincia = provincia;
        this.citta = citta;
        this.cap = cap;
        this.via = via;
        this.telefono = telefono;
        this.azienda = azienda;
    }

    public Azienda getAzienda() {
        return azienda;
    }

    public void setAzienda(Azienda azienda) {
        this.azienda = azienda;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegione() {
        return regione;
    }

    public void setRegione(String regione) {
        this.regione = regione;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}