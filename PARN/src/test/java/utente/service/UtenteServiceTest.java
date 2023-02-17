package utente.service;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import storage.entity.Annuncio;
import storage.entity.Azienda;
import storage.entity.Sede;
import utente.dao.UtenteDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UtenteServiceTest {
/*
    private Azienda azienda;
    private UtenteServiceInterface utenteServiceInterface;
    @Mock
    private UtenteDAO utenteDAO;

    @Before
    public void setUp() throws SQLException {
        utenteDAO = mock(UtenteDAO.class);
        when(utenteDAO.addAzienda(azienda)).thenReturn(azienda);
        utenteServiceInterface = new UtenteService(utenteDAO);
    }

    @Test
    public void doTestRegistrazione(){


        ArrayList<Object> parameterList = new ArrayList<>();
        for (Profile correctProfile : Profile.values())
            parameterList.add( correctProfile.getValue() );

        ArrayList<Object> wrongParameterList = new ArrayList<>();
        for (ProfileWithWrongValues wrongProfile : ProfileWithWrongValues.values())
            wrongParameterList.add(wrongProfile.getValue() );

        int j = 0; //Contatore per wrongParameterList

        boolean result;

        for (int i = 0; i < Profile.values().length; i++){

            Object oldValue = parameterList.get(i); //Prendo il valore da sostituire corrente
            Object wrongValue = wrongParameterList.get(i); //Prendo il valore corrispondente errato
            parameterList.set(i, wrongValue); //Setta il parametro di test nella lista

            System.out.println("TESTING: "+ProfileWithWrongValues.values()[j].getName()+" WITH VALUE "+ProfileWithWrongValues.values()[j].getValue());

            //result = tryRegistrationWithParams(parameterList); //prova registrazione
            //assertThrows(IllegalArgumentException.class, () ->  {tryRegistrationWithParams(parameterList);});

            try {
                result = tryRegistrationWithParams(parameterList);
            } catch (IllegalArgumentException e){
                result = false;
            }
            assertFalse(result);

            //assertFalse(result); //Controlla match con oracolo. La registrazione non deve andare a buon fine
            System.out.println("RESULT: "+result);

            parameterList.set(i, oldValue); //Reimposta il parametro corretto nella lista

            if (ProfileWithWrongValues.values()[j].name.contains("TOO_LONG")) //In caso devo testare anche la lunghezza indietreggio di un parametro
                i--;

            j++; //Avanza wrongParameterList
            result = true;
        }


    }

    private boolean tryRegistrationWithParams(List<Object> argList){

        if (argList.size() != Profile.values().length)
            throw new IllegalArgumentException("Numero di argomenti non valido. EXPECTED: "+Profile.values().length+" BUT GOT: "+argList.size());

        String nome = (String) argList.get(Profile.NOME.ordinal());
        String partitaIva = (String) argList.get(Profile.P_IVA.ordinal());
        String telefono = (String) argList.get(Profile.TELEFONO.ordinal());
        String ragioneSociale = (String) argList.get(Profile.RAGIONE_SOCIALE.ordinal());
        String sitoWeb = (String) argList.get(Profile.LINK.ordinal());
        String regione = (String) argList.get(Profile.REGIONE.ordinal());
        String provincia = (String) argList.get(Profile.PROVINCIA.ordinal());
        String citta = (String) argList.get(Profile.CITTA.ordinal());
        String via = (String) argList.get(Profile.VIA.ordinal());
        String cap = (String) argList.get(Profile.CAP.ordinal());
        String areaInteresse = (String) argList.get(Profile.AREA_INTERESSE.ordinal());
        String settoriCompetenzaString = (String) argList.get(Profile.SETTORI_DI_COMPETENZA.ordinal());
        Integer numeroDipendenti = null;
        try {
            numeroDipendenti = Integer.parseInt((String) argList.get(Profile.NUMERO_DIPARTIMENTI.ordinal()));
        } catch (NumberFormatException e) {
            return false;
        }

        String email = (String) argList.get(Profile.MAIL.ordinal());
        String password = (String) argList.get(Profile.PASSWORD.ordinal());
        String logo = (String) argList.get(Profile.FOTO.ordinal());
        String regioneSede = (String) argList.get(Profile.REGIONE_SEDE.ordinal());
        String provinciaSede = (String) argList.get(Profile.PROVINCIA.ordinal());
        String cittaSede = (String) argList.get(Profile.CITTA_SEDE.ordinal());
        String capSede = (String) argList.get(Profile.CAP_SEDE.ordinal());
        String telefonoSede = (String) argList.get(Profile.TELEFONO_SEDE.ordinal());
        String viaSede = (String) argList.get(Profile.VIA_SEDE.ordinal());
        String mailSede = (String) argList.get(Profile.MAIL_SEDE.ordinal());

        boolean success = true;

        List<String> settoriComptenza = new ArrayList<>(Arrays.asList(settoriCompetenzaString.split(",")));

        Azienda azienda = new Azienda(nome, email, password, regione, provincia, logo, cap, telefono, citta, via,
                partitaIva, ragioneSociale, sitoWeb, areaInteresse, numeroDipendenti, settoriComptenza,
                null, new ArrayList<Annuncio>());

        UtenteService utenteService = new UtenteService();
        success = success && utenteService.registraAzienda(azienda);

        List<Sede> sedi = new ArrayList<>();
        Sede sede = new Sede();

        if(regioneSede != null){
            Sede newSede = new Sede(regioneSede, provinciaSede, cittaSede, capSede, viaSede, telefonoSede, azienda, mailSede);
            sede = newSede;
        }
        else {
            Sede newSede = new Sede(regione, provincia, citta, cap, via, telefono, azienda, email);
            sede = newSede;
        }
        sedi.add(sede);

        success = success && utenteService.registraSede(sede);


        return success;
    }

    public enum Profile {
        MAIL("Mail", "xamplemario@mailservice.ex"),
        PASSWORD("Password", "Aver1g00dP4ssw0r6"),
        CONFERMA_PASSWORD("Conferma Password", "Aver1g00dP4ssw0r6"),
        REGIONE("Regione", "Campania"),
        PROVINCIA("Provincia", "SA"),
        CITTA("Città", "Salerno"),
        VIA("Via", "Via Roma 1"),
        TELEFONO("Telefono", "1234567890"),
        CAP("CAP", "84121"),
        FOTO("Foto", "/immagini/image.jpg"),
        NOME("Nome", "Rossi Edile"),
        P_IVA("Partita IVA", "86334519757"),
        RAGIONE_SOCIALE("Ragione Sociale", "Rossi Edile di Mario Rossi"),
        LINK("Link al sito web", "https://www.mariorossi.com"),
        AREA_INTERESSE("Area di Interesse", "Edile"),
        NUMERO_DIPARTIMENTI("Numero di Dipartimenti", "1"),
        SETTORI_DI_COMPETENZA("Settori di competenza", "Ristrutturazioni"),
        SEDE_LEGALE_EQ_GIURIDICA("La sede legale è uguale alla sede giuridica", "NO"),
        CITTA_SEDE("Città Sede", "Napoli"),
        PROVINCIA_SEDE("Provincia Sede", "NA"),
        CAP_SEDE("CAP Sede", "80016"),
        TELEFONO_SEDE("Telefono Sede", "9123456780"),
        VIA_SEDE("Via Sede", "Via Roma 4"),
        MAIL_SEDE("Mail Sede", "infonapoli@mailservice.ex"),
        REGIONE_SEDE("Regione Sede", "Campania");

        private String name;
        private String value;

        Profile(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }
    }

    public enum ProfileWithWrongValues {
        MAIL("Mail", "examplemail@wrongformat"),
        MAIL_TOO_LONG("Mail L", "exampleofaveryveryveryextrajuicylongsupermail@mailservice.ex"),
        PASSWORD("Password", "pass"),
        CONFERMA_PASSWORD("Conferma Password", "Aver1g00dP4sswor6"),
        REGIONE("Regione", "S"),
        REGIONE_TOO_LONG("Regione", "Campaniaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"),
        PROVINCIA("Provincia", "Salerno"),
        CITTA("Città", "Salernooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo"),
        VIA("Via", "Via della meravigliosa percorrenza infinitamente piacevolmente eterna 1"),
        TELEFONO("Telefono", "telefoni casa"),
        CAP("CAP", "unCap"),
        FOTO("Foto", "/immagini/im@ge.jpg"),
        FOTO_TOO_LONG("Foto", "/immagini/imagineBellissimaCheHoScattatoQuelGiornoAlMareConGliAmiciInVacanza.jpg"),
        NOME("Nome", "R0ss] + f&/$($%&/()/&%)"),
        P_IVA("Partita IVA", ")(/&%$£%&/()/&%$£$%&/("),
        RAGIONE_SOCIALE("Ragione Sociale", "Rossi Edile di Mario Rossi Con Suo Cugino Che Fa Il Carpentiere E Sa Un Sacco Di Cose Belle Per Fare Affari Con Suo Cugino"),
        LINK("Link al sito web", "www.mariorossi.com"),
        AREA_INTERESSE("Area di Interesse", "£$%&/(/&%$£$%&/("),
        NUMERO_DIPARTIMENTI("Numero di Dipartimenti", "10000000000000000000000000"),
        SETTORI_DI_COMPETENZA("Settori di competenza", "(/&%&/(/&%&/(/&%$%&/("),
        SEDE_LEGALE_EQ_GIURIDICA("La sede legale è uguale alla sede giuridica", "NO"),
        CITTA_SEDE("Città Sede", "/&(&(&(&(&(%(%(%(&(&&"),
        PROVINCIA_SEDE("Provincia Sede", ")&(%)/&(/&/&(&/(%&("),
        CAP_SEDE("CAP Sede", "unCap"),
        TELEFONO_SEDE("Telefono Sede", "DammiIlTelefonoKaren"),
        VIA_SEDE("Via Sede", "Via della infinitamente meravigliosa ed eternamente lunga percorrenza eterna ma soddisfacente 4"),
        MAIL_SEDE("Mail Sede", "examplemail#mails9vice.ex"),
        MAIL_SEDE_TOO_LONG("Mail Sede L", "Unaemaildavveromoltolungamagiurochemiservivacositantolungaperchefacciounlavorospecificoeh@mailservice.example"),
        REGIONE_SEDE("Regione Sede", "C@mpania=");

        private String name;
        private String value;

        ProfileWithWrongValues(String name, String value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }
    }
    */
}