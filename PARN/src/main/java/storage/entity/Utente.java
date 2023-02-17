package storage.entity;

/**
 * L'oggetto <code>Utente</code> rappresenta un utente del sistema ed è caratterizzato da un id, un nome, una mail, una
 * password, una regione, una provincia, una foto, il CAP, il telefono, la città e la via
 */
public abstract class Utente {
    private int id;
    private String nome;
    private String mail;
    private String password;
    private String regione;
    private String provincia;
    private String foto;
    private String cap;
    private String telefono;

    private String citta;
    private String via;

    public Utente(int id, String nome, String mail, String password, String regione, String provincia, String foto, String cap, String telefono, String citta, String via) {
        this.id = id;
        this.nome = nome;
        this.mail = mail;
        this.password = password;
        this.regione = regione;
        this.provincia = provincia;
        this.foto = foto;
        this.cap = cap;
        this.telefono = telefono;
        this.citta = citta;
        this.via = via;
    }

    public Utente(String nome, String mail, String password, String regione, String provincia, String foto, String cap, String telefono, String citta, String via) {
        this.nome = nome;
        this.mail = mail;
        this.password = password;
        this.regione = regione;
        this.provincia = provincia;
        this.foto = foto;
        this.cap = cap;
        this.telefono = telefono;
        this.citta = citta;
        this.via = via;
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

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
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