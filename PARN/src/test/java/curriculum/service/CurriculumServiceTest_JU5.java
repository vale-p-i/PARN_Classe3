package curriculum.service;

import curriculum.dao.CurriculumDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import storage.entity.Lingua;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;


class CurriculumServiceTest_JU5 {

    CurriculumDAO mockDao;
    CurriculumServiceInterface service;


    private Lingua getLingua() {
        return new Lingua("Inglese","Avanzato",null);
    }


    @BeforeEach
    public void init() {
        mockDao= Mockito.mock(CurriculumDAO.class);
        when(mockDao.updateLingua(getLingua())).thenReturn(true);
        service = new CurriculumService(mockDao);
    }


    @Test
    public void aggiornaLingua_TC_3_1() {
        assertThrows(IllegalArgumentException.class,()->{service.aggiornaLingua(new Lingua("IngleseIngleseInglese","Sufficente",null));});
        System.out.println("TC_1_1:pass");
    }

    @Test
    public void aggiornaLingua_TC_3_2() {
        assertThrows(IllegalArgumentException.class,()->{service.aggiornaLingua(new Lingua("Inglese","Buono",null));});
        System.out.println("TC_1_2:pass");
    }

    @Test
    public void aggiornaLingua_TC_3_3() {
        assertThrows(IllegalArgumentException.class,()->{service.aggiornaLingua(new Lingua("","Buono",null));});
        System.out.println("TC_1_2:pass");
    }

}