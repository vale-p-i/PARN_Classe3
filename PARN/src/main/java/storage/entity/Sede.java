package storage.entity;

public class Sede {

    private String regione;
    private String provincia;
    private String citta;
    private int cap;
    private String via;
    private long telefono;
    private String mail;
    private Long id;

    public Sede(String regione, String provincia, String citta, int cap, String via, long telefono, String mail) {
        this.regione = regione;
        this.provincia = provincia;
        this.citta = citta;
        this.cap = cap;
        this.via = via;
        this.telefono = telefono;
        this.mail = mail;
    }

    public Sede() {

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

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}