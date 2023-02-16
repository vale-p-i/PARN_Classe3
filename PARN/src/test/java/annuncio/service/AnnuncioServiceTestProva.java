package annuncio.service;

import annuncio.dao.AnnuncioDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import storage.entity.Annuncio;
import storage.entity.Azienda;
import storage.entity.Sede;
import utils.StringListUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AnnuncioServiceTestProva {
    @Mock
    private AnnuncioDAO mockAnnuncioDAO;

    private AnnuncioServiceInterface service;

    Sede getSede(){
        Sede sede = new Sede("Calabria", "CS", "Bolzano", "80808", "Via delle rose", "39+ 0000000000", null, "wearsoft@gmail.com");
        return sede;
    }

    Sede getWrongSede(){
        Sede sede = new Sede(-1,"Calabria", "CS", "Bolzano", "80808", "Via delle rose", "39+ 0000000000", null, "wearsoft@gmail.com");
        return sede;
    }

    Azienda getAzienda(){
        List<String> settori = new ArrayList<>();
        settori.add("settore1");
        settori.add("settore2");
        settori.add("settore3");
        List<Sede> sedi = new ArrayList<>();
        sedi.add(getSede());
        List<Annuncio> annunci = new ArrayList<>();
        Azienda azienda = new Azienda("WareSoft srl", "waresoft@gmail.com", "ware2023soft", "Calabria", "CS", "", "80808", "39+ 0000000000", "Bolzano", "Via delle rose", "testPIVa", "testRagSoc", "testlink", "testAreaInteresse", 15, settori, sedi, annunci);
        return azienda;
    }

    Annuncio getAnnuncio(){
        LocalDate scadenza = LocalDate.of(2023, 6, 20);
        List<String> requisiti = new ArrayList<>();
        requisiti.add("Formazione accademica in amministrazione o ingegneria.");
        requisiti.add("Esperienza nella gestione di progetti di riorganizzazione degli spazi lavorativi.");
        requisiti.add("Collaborazione con HR e IT.");
        requisiti.add("Competenze di pianificazione e coordinamento.");
        requisiti.add("Comunicazione leadership e problem solving.");
        requisiti.add("Indipendenza e lavoro di gruppo.");
        requisiti.add("Passion per la gestione degli spazi lavorativi.");
        requisiti.add("Conoscenza delle esigenze tecnologiche e della sicurezza sul lavoro");
        List<String> keywords = new ArrayList<>();
        keywords.add("design");
        keywords.add("moda");
        keywords.add("arredamento");
        keywords.add("storiadellarte");
        keywords.add("lavoro");
        keywords.add("ufficio");
        keywords.add("IT");
        keywords.add("HR");
        List<String> preferenze = new ArrayList<>();
        preferenze.add("Comunicazione efficace: Il candidato dovrà essere in grado di comunicare in modo chiaro e conciso con i dipartimenti HR e IT i colleghi e i dipendenti");
        preferenze.add("Leadership: Il responsabile dovrà essere un leader motivato e di successo in grado di motivare e ispirare il team a raggiungere obiettivi comuni");
        preferenze.add("Problem solving: Il candidato dovrà essere in grado di analizzare e risolvere i problemi in modo rapido e creativo per garantire che i progetti vengano portati a termine in modo efficiente");
        preferenze.add("Lavoro di gruppo: Il responsabile dovrà essere in grado di lavorare in modo efficace con il team creando un ambiente di lavoro collaborativo e positivo");
        return new Annuncio(1, getAzienda(), true, getSede(), 100, "Il candidato" +
                " gestirà la disposizione delle scrivanie e degli uffici direzionali per garantirne l'efficienza" +
                " e la funzionalità. Collaborando con HR e IT, il responsabile coordinerà progetti di riorganizzazione" +
                " e garantirà il rispetto del budget e dei tempi. Richiesta formazione accademica e esperienza in" +
                " progetti simili. Opportunità di lavorare in un ambiente dinamico e in continua evoluzione",
                scadenza, requisiti, keywords, preferenze, "Interior Designer", null);
    }

    List<String> getSplittedString(String requisitiString){
        List<String> requisit = new ArrayList<>();
        String [] tmp = requisitiString.split(",");
        for(String s : tmp)
            requisit.add(s);
        return requisit;
    }

    @BeforeEach
    void setUp() {
        mockAnnuncioDAO = Mockito.mock(AnnuncioDAO.class);
        when(mockAnnuncioDAO.creaAnnuncio(getAnnuncio())).thenReturn(true);
        service = new AnnuncioService(mockAnnuncioDAO);
    }

    @Test
    void creaAnnuncioTC_2_1() {
        Annuncio annuncio = getAnnuncio();
        annuncio.setRuolo("Gestore della disposizione delle scrivanie del personale e degli uffici della direzione amministrativa");
        System.out.println(assertThrows(IllegalArgumentException.class, () -> {
            service.creaAnnuncio(annuncio);
        }));
    }

    @Test
    void creaAnnuncioTC_2_2() {
        Annuncio annuncio = getAnnuncio();
        annuncio.setSede(getWrongSede());
        System.out.println(assertThrows(IllegalArgumentException.class, () -> {
            service.creaAnnuncio(annuncio);
        }));
    }

    @Test
    void creaAnnuncioTC_2_3() {
        Annuncio annuncio = getAnnuncio();
        annuncio.setNumeroPersone(10000000);
        System.out.println(assertThrows(IllegalArgumentException.class, () -> {
            service.creaAnnuncio(annuncio);
        }));
    }

    @Test
    void creaAnnuncioTC_2_4() {
        Annuncio annuncio = getAnnuncio();
        annuncio.setDescrizione("Responsabile dell'Organizzazione degli Spazi Lavorativi e degli Uffici Direzionali." +
                " In questa posizione, il candidato avrà la responsabilità di gestire l'utilizzo efficiente degli" +
                " spazi lavorativi e degli uffici direzionali all'interno dell'azienda. Sarà incaricato di pianificare" +
                " e coordinare la disposizione delle scrivanie del personale e degli ambienti di lavoro per garantire" +
                " che siano ergonomici, sicuri e funzionali. Il responsabile dovrà essere in grado di lavorare a" +
                " stretto contatto con i dipartimenti HR e IT per garantire che tutte le esigenze del personale siano" +
                " soddisfatte e che l'ambiente di lavoro sia adeguato alle attuali esigenze tecnologiche." +
                " Inoltre, dovrà essere in grado di gestire progetti di riorganizzazione degli spazi lavorativi," +
                " coordinando le attività con i fornitori di servizi e assicurando che i progetti vengano consegnati" +
                " in tempo e dentro il budget. Il candidato ideale avrà una formazione accademica in amministrazione," +
                " ingegneria o un campo simile e avrà maturato esperienza nella gestione di progetti di riorganizzazione" +
                " degli spazi lavorativi. Avrà eccellenti capacità di comunicazione, leadership e problem solving," +
                " e sarà in grado di lavorare in modo indipendente e in team. In questa posizione, il candidato avrà" +
                " l'opportunità di lavorare in un ambiente dinamico e in continua evoluzione, contribuendo a creare" +
                " un ambiente di lavoro sicuro, efficiente e produttivo per tutto il personale. Se sei una persona" +
                " motivata e hai una forte passione per la gestione degli spazi lavorativi, questa potrebbe essere" +
                " la posizione perfetta per te.\n");
        System.out.println(assertThrows(IllegalArgumentException.class, () -> {
            service.creaAnnuncio(annuncio);
        }));
    }

    @Test
    void creaAnnuncioTC_2_5() {
        Annuncio annuncio = getAnnuncio();
        annuncio.setDataScadenza(LocalDate.of(2023, 02, 10));
        System.out.println(assertThrows(IllegalArgumentException.class, () -> {
            service.creaAnnuncio(annuncio);
        }));
    }

    @Test
    void creaAnnuncioTC_2_6() {
        Annuncio annuncio = getAnnuncio();
        annuncio.setDataScadenza(null);
        System.out.println(assertThrows(IllegalArgumentException.class, () -> {
            service.creaAnnuncio(annuncio);
        }));
    }

    @Test
    void creaAnnuncioTC_2_7() {
        Annuncio annuncio = getAnnuncio();
        annuncio.setRequisiti(getSplittedString("Master in design di interni, Laurea in architettura #####"));
        System.out.println(assertThrows(IllegalArgumentException.class, () -> {
            service.creaAnnuncio(annuncio);
        }));
    }

    @Test
    void creaAnnuncioTC_2_8() {
        Annuncio annuncio = getAnnuncio();
        annuncio.setRequisiti(getSplittedString("Formazione accademica in amministrazione ingegneria o campo simile, \n" +
                "Esperienza pregressa nella gestione di progetti di riorganizzazione degli spazi lavorativi,\n" +
                "Capacità di lavorare a stretto contatto con i dipartimenti HR e IT,\n" +
                "Competenze di pianificazione e coordinamento,\n" +
                "Eccellenti capacità di comunicazione leadership e problem solving,\n" +
                "Capacità di lavorare in modo indipendente e in team,\n" +
                "Forte passione per la gestione degli spazi lavorativi,\n" +
                "Conoscenza delle attuali esigenze tecnologiche e dei requisiti di sicurezza sul lavoro"));
        System.out.println(assertThrows(IllegalArgumentException.class, () -> {
            service.creaAnnuncio(annuncio);
        }));
    }

    @Test
    void creaAnnuncioTC_2_9() {
        Annuncio annuncio = getAnnuncio();
        annuncio.setPreferenze(getSplittedString("Conoscenza della storia dell’arte moderna " +
                "@@@@@@@@"));
        System.out.println(assertThrows(IllegalArgumentException.class, () -> {
            service.creaAnnuncio(annuncio);
        }));
    }

    @Test
    void creaAnnuncioTC_2_10() {
        Annuncio annuncio = getAnnuncio();
        annuncio.setPreferenze(getSplittedString("Comunicazione efficace: Il candidato dovrà essere in grado di comunicare in modo chiaro e conciso con i dipartimenti HR e IT i colleghi e i dipendenti,\n" +
                "Leadership: Il responsabile dovrà essere un leader motivato e di successo in grado di motivare e ispirare il team a raggiungere obiettivi comuni, \n" +
                "Problem solving: Il candidato dovrà essere in grado di analizzare e risolvere i problemi in modo rapido e creativo per garantire che i progetti vengano portati a termine in modo efficiente,\n" +
                "Lavoro di gruppo: Il responsabile dovrà essere in grado di lavorare in modo efficace con il team creando un ambiente di lavoro collaborativo e positivo,\n" +
                "Adattabilità: Il candidato dovrà essere in grado di adattarsi rapidamente a situazioni e circostanze cambianti per garantire il successo del progetto,\n" +
                "Pianificazione e organizzazione: Il responsabile dovrà essere in grado di pianificare e organizzare in modo efficace il proprio tempo e quello del team per garantire il rispetto del budget e dei tempi,\n" +
                "Empatia: Il candidato dovrà essere in grado di comprendere le esigenze e i punti di vista degli altri per garantire una gestione degli spazi lavorativi che rispetti le esigenze di tutti i dipendenti,\n" +
                "Visione: Il responsabile dovrà essere in grado di vedere il quadro generale e di prevedere le tendenze future per garantire una gestione degli spazi lavorativi che sia sempre al passo con i tempi,"));
        System.out.println(assertThrows(IllegalArgumentException.class, () -> {
            service.creaAnnuncio(annuncio);
        }));
    }

    @Test
    void creaAnnuncioTC_2_11() {
        Annuncio annuncio = getAnnuncio();
        System.out.println(annuncio.getPreferenze());
        annuncio.setKeyword(getSplittedString("#design#moda#arredamento#storiadellarte#lavoro#ufficio#IT#HR#mancatoartista#Artistaincompreso#PicassoWay#Lavoroconnoi"));
        System.out.println(assertThrows(IllegalArgumentException.class, () -> {
            service.creaAnnuncio(annuncio);
        }));
    }

    @Test
    void creaAnnuncioTC_2_12() {
        Annuncio annuncio = getAnnuncio();
        System.out.println(annuncio.getPreferenze());
        annuncio.setKeyword(getSplittedString("design, moda, arredamento, storiadellarte, lavoro, ufficio, IT, HR, mancatoartista, Artistaincompreso, PicassoWay, Lavoroconnoi, Assumiamo, InteriorDesign, InteriorDesigner, Bellezza"));
        System.out.println(assertThrows(IllegalArgumentException.class, () -> {
            service.creaAnnuncio(annuncio);
        }));
    }

    @Test
    void creaAnnuncioTC_2_13() {
        Annuncio annuncio = getAnnuncio();
        System.out.println(annuncio.getPreferenze());
        System.out.println(assertThrows(IllegalArgumentException.class, () -> {
            service.creaAnnuncio(annuncio);
        }));
    }
}