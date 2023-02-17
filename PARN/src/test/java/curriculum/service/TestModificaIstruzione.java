package curriculum.service;

import curriculum.dao.CurriculumDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import storage.entity.Istruzione;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class TestModificaIstruzione {

    private CurriculumService curriculumService;
    private CurriculumDAO mockCurriculumDAO;


    private Istruzione getIstruzione() {
        LocalDate dataInizio = LocalDate.of(2010, Month.SEPTEMBER, 10);
        return new Istruzione(null, dataInizio, null, "Laurea in Informatica", "Scuola secondaria di secondo grado", "Università di Salerno" );
    }

    @BeforeEach
    public void init() {
        mockCurriculumDAO = Mockito.mock(CurriculumDAO.class);
        when(mockCurriculumDAO.updateIstruzione(getIstruzione())).thenReturn(true);
        curriculumService = new CurriculumService(mockCurriculumDAO);
    }

    @Test
    public void aggiornaIstruzione_TC_4_1() {
        assertThrows(IllegalArgumentException.class, () -> {curriculumService.aggiornaIstruzione(new Istruzione(null,
                LocalDate.of(2030, Month.JANUARY, 1),
                LocalDate.of(2015, Month.JUNE, 21),
                "Diploma di maturità",
                "Scuola secondaria di primo grado",
                "Liceo Classico Alessandro Lombardi"));});
        System.out.println("TC_4_1:pass");
    }

    @Test
    public void aggiornaIstruzione_TC_4_2() {
        assertThrows(IllegalArgumentException.class, () -> {curriculumService.aggiornaIstruzione(new Istruzione(null,
                LocalDate.of(2010, Month.JANUARY, 1),
                LocalDate.of(2035, Month.JUNE, 21),
                "",
                "Scuola secondaria di primo grado",
                "Liceo Classico Alessandro Lombardi"));});
        System.out.println("TC_4_2:pass");
    }

    @Test
    public void aggiornaIstruzione_TC_4_3() {
        assertThrows(IllegalArgumentException.class, () -> {curriculumService.aggiornaIstruzione(new Istruzione(null,
                LocalDate.of(2010, Month.JANUARY, 1),
                LocalDate.of(2015, Month.JUNE, 21),
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
                "Scuola secondaria di primo grado",
                "Liceo Classico Alessandro Lombardi"));});
        System.out.println("TC_4_3:pass");
    }

    @Test
    public void aggiornaIstruzione_TC_4_4() {
        assertThrows(IllegalArgumentException.class, () -> {curriculumService.aggiornaIstruzione(new Istruzione(null,
                LocalDate.of(2010, Month.JANUARY, 1),
                LocalDate.of(2015, Month.JUNE, 21),
                "Diploma di maturità",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua",
                "Liceo Classico Alessandro Lombardi"));});
        System.out.println("TC_4_4:pass");
    }

    @Test
    public void aggiornaIstruzione_TC_4_5() {
        assertThrows(IllegalArgumentException.class, () -> {curriculumService.aggiornaIstruzione(new Istruzione(null,
                LocalDate.of(2010, Month.JANUARY, 1),
                LocalDate.of(2015, Month.JUNE, 21),
                "Diploma di maturità",
                "Scuola secondaria di primo grado",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua"));});
        System.out.println("TC_4_4:pass");
    }

}