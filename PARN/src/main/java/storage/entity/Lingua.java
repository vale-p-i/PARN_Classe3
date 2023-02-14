package storage.entity;

public class Lingua  {

    private String nome;
    private String livello;
    private Curriculum curriculum;
    public Lingua(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public Lingua(String nome, String livello, Curriculum curriculum) {
        this.nome = nome;
        this.livello = livello;
        this.curriculum = curriculum;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLivello() {
        return livello;
    }

    public void setLivello(String livello) {
        this.livello = livello;
    }
}
