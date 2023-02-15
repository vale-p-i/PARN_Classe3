package storage.entity;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lingua lingua = (Lingua) o;
        return Objects.equals(nome, lingua.nome) && Objects.equals(livello, lingua.livello) && Objects.equals(curriculum, lingua.curriculum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, livello, curriculum);
    }
}
