package curriculum.facade;

import storage.entity.Curriculum;

import java.io.IOException;

public interface PDFCurriculumInterface {
    String downloadCurriculum(Curriculum curriculum) throws IOException;
}
