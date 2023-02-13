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

    private String mail;

    public Sede(){}

    public Sede(int id, String regione, String provincia, String citta, String cap, String via, String telefono, Azienda azienda, String mail) {
        this.id = id;
        this.regione = regione;
        this.provincia = provincia;
        this.citta = citta;
        this.cap = cap;
        this.via = via;
        this.telefono = telefono;
        this.azienda = azienda;
        this.mail = mail;
    }

    public Sede(String regione, String provincia, String citta, String cap, String via, String telefono, Azienda azienda, String mail) {
        this.regione = regione;
        this.provincia = provincia;
        this.citta = citta;
        this.cap = cap;
        this.via = via;
        this.telefono = telefono;
        this.azienda = azienda;
        this.mail = mail;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}