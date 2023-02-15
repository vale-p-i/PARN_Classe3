package curriculum.facade;

import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import storage.entity.Curriculum;

import java.io.File;
import java.io.IOException;

public class PDFCurriculum {

    private static final String REGULAR = "./font/arialn.ttf";

    /**
     * Questo metodo prende un oggetto curriculum, crea il formato pdf del curriculum e
     * restituisce la path al file pdf.
     * @param curriculum è il curriculum da scaricare
     * @return una stringa che rappresenta la path al pdf
     * @throws IOException se non riesce a creare il PDfWriter o non riesce a creare il Font
     */
    public String downloadCurriculum(Curriculum curriculum) throws IOException {
        String filename = "./pdf/";
        filename = filename +
                curriculum.getPersona().getCognome() +
                curriculum.getPersona().getNome() +
                curriculum.getPersona().getTelefono() +
                ".pdf";
        File file = new File(filename);
        PdfWriter writer = new PdfWriter(filename);
        PdfDocument pdf = new PdfDocument(writer);
        Document doc = new Document(pdf);

        FontProgram fontProgram = FontProgramFactory.createFont(REGULAR);
        PdfFont font = PdfFontFactory.createFont(fontProgram, PdfEncodings.WINANSI, PdfFontFactory.EmbeddingStrategy.PREFER_EMBEDDED);

        Table table = new Table(4).useAllAvailableWidth().setFixedLayout();

        StrutturaCurriculum struttura = new StrutturaCurriculum(font, table);

        String foto = curriculum.getPersona().getFoto();
        if(foto == null){
            struttura.aggiungiIntestazione("./image/no-profile-picture-icon.png");
        } else {
            struttura.aggiungiIntestazione(foto);
        }

        struttura.aggiungiInformazioniPersonali(curriculum.getPersona());
        struttura.aggiungiEsperienzaLavorativa(curriculum.getEsperienze());
        struttura.aggiungiIstruzione(curriculum.getIstruzioni());
        struttura.aggiungiCompetenzeLinguistiche(curriculum.getLingue());
        doc.add(struttura.getTable());

        int numberOfPages = pdf.getNumberOfPages();
        for (int i = 1; i <= numberOfPages; i++) {
            doc.showTextAligned(new Paragraph(String.format("Pagina %s di %s", i, numberOfPages)).setFont(font).setFontSize(10),
                    550,
                    15,
                    i,
                    TextAlignment.RIGHT,
                    VerticalAlignment.BOTTOM,
                    0);
        }

        doc.close();
        return "" + curriculum.getPersona().getCognome() + curriculum.getPersona().getNome() +
                curriculum.getPersona().getTelefono() + ".pdf";
    }
}
