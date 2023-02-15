package storage.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Curriculum {
    private List<String> softSkill;
    private Persona persona;
    private List<EsperienzaLavorativa> esperienze;
    private List<Lingua> lingue;
    private List<Istruzione> istruzioni;

    public Curriculum(Persona persona, List<String> softSkill, List<EsperienzaLavorativa> esperienze, List<Lingua> lingue, List<Istruzione> istruzioni) {
        this.softSkill = softSkill;
        this.persona = persona;
        this.esperienze = esperienze;
        this.lingue = lingue;
        this.istruzioni = istruzioni;
    }
    public Curriculum(Persona persona, List<String> softSkill) {
        this.softSkill = softSkill;
        this.persona = persona;
        this.esperienze = new ArrayList<EsperienzaLavorativa>();
        this.lingue = new ArrayList<Lingua>();
        this.istruzioni = new ArrayList<Istruzione>();
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

    public List<EsperienzaLavorativa> getEsperienze() {
        return esperienze;
    }

    public void setEsperienze(List<EsperienzaLavorativa> esperienze) {
        this.esperienze = esperienze;
    }

    public List<Lingua> getLingue() {
        return lingue;
    }

    public void setLingue(List<Lingua> lingue) {
        this.lingue = lingue;
    }

    public List<Istruzione> getIstruzioni() {
        return istruzioni;
    }

    public void setIstruzioni(List<Istruzione> istruzioni) {
        this.istruzioni = istruzioni;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curriculum that = (Curriculum) o;
        return Objects.equals(softSkill, that.softSkill) && Objects.equals(persona, that.persona) && Objects.equals(esperienze, that.esperienze) && Objects.equals(lingue, that.lingue) && Objects.equals(istruzioni, that.istruzioni);
    }

    @Override
    public int hashCode() {
        return Objects.hash(softSkill, persona, esperienze, lingue, istruzioni);
    }
}
