package storage.entity;

import java.time.LocalDateTime;

public class Candidatura {
    private Persona persona;
    private Annuncio annuncio;
    private Curriculum curriculum;
    private LocalDateTime data;
    private Long id;

    public Candidatura(Persona persona, Annuncio annuncio, Curriculum curriculum, LocalDateTime data) {
        this.persona = persona;
        this.annuncio = annuncio;
        this.curriculum = curriculum;
        this.data = data;
    }

    public Candidatura() {

    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Annuncio getAnnuncio() {
        return annuncio;
    }

    public void setAnnuncio(Annuncio annuncio) {
        this.annuncio = annuncio;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

    public void setCurriculum(Curriculum curriculum) {
        this.curriculum = curriculum;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
