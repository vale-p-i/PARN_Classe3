package storage.entity;

import java.util.List;

public class Curriculum {
    private List<String> softSkill;
    private Persona persona;

    public Curriculum(List<String> softSkill, Persona persona) {
        this.softSkill = softSkill;
        this.persona = persona;
    }

    public List<String> getSoftSkill() {
        return softSkill;
    }

    public void setSoftSkill(List<String> softSkill) {
        this.softSkill = softSkill;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
