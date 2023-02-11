package storage.entity;

public abstract class Utente {
    private String nome;
    private String mail;
    private String password;
    private String regione;
    private String provincia;
    private String foto;
    private int cap;
    private String telefono;

    private String citta;
    private String via;

    private int id;

    public Utente(String nome, String mail, String password, String regione, String provincia, int cap, String citta,
                  String via, String telefono, String foto) {
        this.nome = nome;
        this.mail = mail;
        this.password = password;
        this.regione = regione;
        this.provincia = provincia;
        this.cap = cap;
        this.citta = citta;
        this.via = via;
        this.telefono = telefono;
        this.foto = foto;
    }

    public Utente() {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
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

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}