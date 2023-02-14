package storage.entity;

public class Lingua  {

    private String Nome;
    private String Livello;
    private Curriculum curriculum;//?
    public Lingua(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public Lingua(String nome, String livello) {
        Nome = nome;
        Livello = livello;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public String getLivello() {
        return Livello;
    }

    public void setLivello(String livello) {
        Livello = livello;
    }
}
