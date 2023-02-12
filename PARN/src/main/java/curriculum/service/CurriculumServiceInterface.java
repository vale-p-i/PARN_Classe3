package curriculum.service;

import storage.entity.Curriculum;
import storage.entity.Persona;

import java.util.List;

public interface CurriculumServiceInterface {
    List<Curriculum> getAllCurriculum();

    Object getCurriculumByPersona(Persona persona);
}
