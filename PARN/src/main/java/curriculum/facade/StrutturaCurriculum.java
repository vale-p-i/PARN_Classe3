package curriculum.facade;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import storage.entity.EsperienzaLavorativa;
import storage.entity.Istruzione;
import storage.entity.Lingua;
import storage.entity.Persona;

import java.net.MalformedURLException;
import java.util.List;

public class StrutturaCurriculum {

    private PdfFont font;
    private Table table;
    protected StrutturaCurriculum(PdfFont font, Table table) {
        this.font = font;
        this.table = table;
    }
    protected Table getTable() {
        return table;
    }
    protected void setTable(Table table) {
        this.table = table;
    }

    /**
     * Questo metodo inserisce nel pdf del curriculum l'intestazione "FORMATO EUROPEO
     * PER IL CURRCULUM VITAE" e l'immagine di profilo della persona.
     * @param img è l'immagine della persona.
     * @throws MalformedURLException nel caso in cui non riesce a creare l'immagine.
     */
    protected void aggiungiIntestazione(String img) throws MalformedURLException {
        Image image = new Image(ImageDataFactory.create(img), 450, 710, 95);
        Cell c1 = new Cell(1, 1).
                add(new Paragraph("FORMATO EUROPEO PER IL CURRICULUM VITAE").
                        setFont(font).
                        setFontSize(13).
                        setBold().
                        setTextAlignment(TextAlignment.RIGHT));
        Cell c2 = new Cell(1, 3).add(image);
        togliBordi(c1, c2);
        aggiungiRiga(c1, c2);
        aggiungiRigaVuota();
    }

    /**
     * Questo metodo prende una persona e inserisce le sue informazioni personali all'interno
     * del pdf curriculum.
     * @param persona è la persona di cui verranno stampate le informazioni personali
     */
    protected void aggiungiInformazioniPersonali(Persona persona) {
        aggiungiHeader("INFORMAZIONI PERSONALI");
        aggiungiRigaNormale("Nome", persona.getNome() + " " + persona.getCognome());
        aggiungiRigaNormale("Indirizzo", "Via " + persona.getVia() + ", " + persona.getCap() + " " + persona.getCitta() + " " + persona.getProvincia());
        aggiungiRigaNormale("Telefono", persona.getTelefono());
        aggiungiRigaNormale("E-mail", persona.getMail());
        aggiungiRigaNormale("Data di nascita",persona.getDataDiNascita().toString());
        aggiungiRigaVuota();
    }

    /**
     * Questo metodo inserisce tutte le esperienze lavorative all'interno del pdf del
     * curriculum*
     * @param esperienzeLavorative è la lista delle esperienze lavorative da inserire
     */
    protected void aggiungiEsperienzaLavorativa(List<EsperienzaLavorativa> esperienzeLavorative) {
        aggiungiHeader("ESPERIENZA LAVORATIVA");
        for(EsperienzaLavorativa esperienza: esperienzeLavorative){
            aggiungiRigaNormale("Date", esperienza.getDataInizio()+ " - " + esperienza.getDataFine());
            aggiungiRigaNormale("Nome e indirizzo del datore di lavoro", esperienza.getNomeAzienda() + ", " + esperienza.getDatore());
            aggiungiRigaNormale("Tipo di azienda o settore", esperienza.getTipoAzienda());
            aggiungiRigaNormale("Tipo di impiego", esperienza.getTipoImpiego());
            String mansioni = String.join(",", esperienza.getMansioniPrincipali());
            aggiungiRigaNormale("Principali mansioni e responsabilità", mansioni);
            aggiungiRigaVuota();
        }
    }

    /**
     * Questo metodo inserisce tutte i vari tipi di istruzione all'interno del pdf del curriculum
     * @param istruzioneList è la liste dei tipi di istruzione da inserire
     */
    protected void aggiungiIstruzione(List<Istruzione> istruzioneList){
        aggiungiHeader("ISTRUZIONE");
        for(Istruzione istruzione: istruzioneList){
            aggiungiRigaNormale("Date", istruzione.getDataInizio() + " - " + istruzione.getDataFine());
            aggiungiRigaNormale("Nome e tipo di istituto di istruzione o formazione", istruzione.getTipo() + ", " + istruzione.getIstituto());
            aggiungiRigaNormale("Qualifica conseguita", istruzione.getQualifica());
            aggiungiRigaVuota();
        }
    }

    /**
     * Questo metodo aggiunge tutte le lingue nel pdf del curriculum
     * @param lingue è la liste di lingue da inserire
     */
    protected void aggiungiCompetenzeLinguistiche(List<Lingua> lingue){
        aggiungiHeader("COMPETENZE PERSONALI");
        for(Lingua lingua: lingue){
            if(lingua.getLivello().equals("Madrelingua"))
                aggiungiHeader2("MADRELINGUA", lingua.getNome());
        }
        aggiungiHeader2("ALTRE LINGUE", " ");
        for(Lingua lingua: lingue){
            if(!lingua.getLivello().equals("Madrelingua")){
                aggiungiRigaNormale("Lingua", lingua.getNome());
                aggiungiRigaNormale("Lingua", lingua.getNome());
            }
        }
        aggiungiRigaVuota();
    }

    /**
     * Questo metodo aggiunge un header all'interno della tabella
     * @param header è la stringa da inserire come header
     */
    private void aggiungiHeader(String header){
        Cell c1 = new Cell(1,1).add(new Paragraph(header)).
                setFont(font).
                setFontSize(12).
                setBold().
                setTextAlignment(TextAlignment.RIGHT);
        Cell c2 = new Cell(1, 3);
        togliBordi(c1, c2);
        aggiungiRiga(c1, c2);
    }

    /**
     * Questo metodo inserisce un header e un valore nella tabella
     * @param header è la stringa da inserire come header
     * @param value è la stringa da inserire come valore
     */
    private void aggiungiHeader2(String header, String value){
        Cell c1 = new Cell(1,1).add(new Paragraph(header)).
                setFont(font).
                setFontSize(11).
                setTextAlignment(TextAlignment.RIGHT);
        Cell c2 = new Cell(1, 3).add(new Paragraph(value)).
                setFont(font).
                setFontSize(10);
        togliBordi(c1, c2);
        aggiungiRiga(c1, c2);
    }

    /**
     * Questo metodo inserisce una riga normale all'interno della tabella
     * @param campo è la stringa che deve essere messa come nome del campo
     * @param descrizione è la stringa che deve essere messa come descrizione
     */
    private void aggiungiRigaNormale(String campo, String descrizione) {
        Cell c1 = new Cell(1, 1).add(new Paragraph(campo)).
                setFont(font).
                setFontSize(10).
                setTextAlignment(TextAlignment.RIGHT);
        Cell c2 = new Cell(1, 3).add(new Paragraph(descrizione).
                setFont(font).
                setFontSize(10));
        togliBordi(c1, c2);
        aggiungiRiga(c1,c2);
    }

    /**
     * Questo metodo prende due celle e le inserisce in una tabella come un'unica riga
     * @param c1 è la cella di sinistra
     * @param c2 è la cella di destra
     */
    private void aggiungiRiga(Cell c1, Cell c2){
        table.addCell(c1);
        table.addCell(c2);
    }

    /**
     * Questo metodo prende due celle e toglie alla cella di sinistra il bordo superiore,
     * inferiore e sinistro e a quella di destra toglie tutti i bordi
     * @param c1 è la cella di sinistra
     * @param c2 è la cella di destra
     */
    private void togliBordi(Cell c1, Cell c2){
        c1.setBorderTop(Border.NO_BORDER);
        c1.setBorderBottom(Border.NO_BORDER);
        c1.setBorderLeft(Border.NO_BORDER);
        c1.setPaddingRight(10);
        c2.setBorder(Border.NO_BORDER);
        c2.setPaddingLeft(10);
    }

    /**
     * Questo metodo aggiunge una riga vuota alla tabella
     */
    private void aggiungiRigaVuota(){
        Cell c1 = new Cell(1,1);
        Cell c2 = new Cell(1, 3);
        c1.setHeight(15);
        c2.setHeight(15);
        togliBordi(c1, c2);
        aggiungiRiga(c1, c2);
    }
}
