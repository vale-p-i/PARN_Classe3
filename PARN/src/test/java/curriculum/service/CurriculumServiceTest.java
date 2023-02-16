package curriculum.service;

import curriculum.dao.CurriculumDAO;
import org.checkerframework.checker.units.qual.C;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import storage.entity.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Implementa il testing di unità per TC_3_1 (Modifica curriculum)
 * @author Daniele Russo
 */
public class CurriculumServiceTest {

    CurriculumDAO mockDao;
    CurriculumServiceInterface service;
    Curriculum cur;

    @Before
    public void before(){
        mockDao=Mockito.mock(CurriculumDAO.class);
        when(mockDao.updateCurriculum(getCurriculum())).thenReturn(true);
        service = new CurriculumService(mockDao);
    }

    private Curriculum getCurriculum() {
        EsperienzaLavorativa es=null;
        Lingua l=new Lingua("Inglese","Avanzato",null);
        List<Lingua> ll = new ArrayList<>();
        ll.add(l);
        Istruzione i=new Istruzione(null, LocalDate.now(),null,"Laurea in informatica","Secondaria di secondo grado","Università di Salerno");
        List<Istruzione> li = new ArrayList<>();
        li.add(i);
        Curriculum cur=new Curriculum(null, Arrays.asList("Bello","Alto"),new ArrayList<>(),ll,li);
        return cur;
    }


    @Test (expected = IllegalArgumentException.class)
    public void aggiornaCurriculum_TC_1_1() {
        List<String> softSkill=Arrays.asList("bello bello bello","bravo bravo bravo bravo","Alto Alto Alto Alto","bravo bravo bravo bravo","ottimo ottimo ottimo ottimo");
        String stringa=String.join(",",softSkill);
        System.out.println("Lunghezza soft skill:"+stringa.length());
        cur=new Curriculum(null,softSkill,null,null,null);
        service.aggiornaCurriculum(cur);
    }

    @Test (expected = IllegalArgumentException.class)
    public void aggiornaCurriculum_TC_1_2() {
        List<String> softSkill=Arrays.asList("bello bello bello","bravo bravo bravo bravo","Alto Alto Alto Alto","bravo bravo bravo bravo","ottimo ottimo ottimo ottimo");
        String stringa=String.join(",",softSkill);
        System.out.println("Lunghezza soft skill:"+stringa.length());
        cur=new Curriculum(null,softSkill,null,null,null);
        service.aggiornaCurriculum(cur);
    }
}