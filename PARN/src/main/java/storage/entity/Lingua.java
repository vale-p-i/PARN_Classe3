package storage.entity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * un oggetto <code>Lingua</code> rappresneta la lingua conosciuta da una persona ed è caratterizzato dal nome della
 * lingua e il livello conosciuto dalla persona che può essere solo uno di quelli specificati, ossia "Madrelingua",
 * "Avanzato", "Medio", "Sufficiente", "Insufficiente", e infine il <code>Curriculum</code> di riferimento
 */
public class Lingua  {

    public static final List<String> LIVELLI= Arrays.asList("Madrelingua","Avanzato","Medio","Sufficiente","Insufficiente");
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
        if (LIVELLI.contains(livello))
            this.livello = livello;
        else
            throw new IllegalArgumentException("Livello lingua non valido");
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
