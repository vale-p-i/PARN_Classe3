package utente.cotroller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.entity.Annuncio;
import storage.entity.Azienda;
import storage.entity.Sede;
import utente.service.UtenteService;
import utente.service.UtenteServiceInterface;
import utils.ImageManager.ImageManager;
import utils.PasswordEncrypter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(name = "RegisterAzienda", value = "/registerAzienda")
public class RegisterAzienda extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        UtenteServiceInterface service = new UtenteService();

        String nome = request.getParameter("nomeAzienda");
        String partitaIva = request.getParameter("piva");
        String telefono = request.getParameter("telefonoAzienda");
        String ragioneSociale = request.getParameter("ragioneSociale");
        String sitoWeb = request.getParameter("sitoWeb");
        String regione = request.getParameter("regioneAzienda");
        String provincia = request.getParameter("provinciaAzienda");
        String citta = request.getParameter("cittaAzienda");
        String via = request.getParameter("viaAzienda");
        String cap = request.getParameter("capAzienda");
        String areaInteresse = request.getParameter("areaInteresse");
        String email = request.getParameter("emailAzienda");
        String password = PasswordEncrypter.encryptThisString(request.getParameter("password_Azienda"));
        Part logo = request.getPart("immagine");

        String settoriCompetenzaString = request.getParameter("settoriCompetenza");
        List<String> settoriCompetenza = new ArrayList<>(Arrays.asList(settoriCompetenzaString.split(",")));

        String numeroDipendentiString = request.getParameter("dipendenti");
        int numeroDipendenti = 0;
        try{
            numeroDipendenti = Integer.parseInt(numeroDipendentiString);
        } catch (NumberFormatException n) {
            System.err.println("Conversion error "+n);
        }

        String regioneSede = request.getParameter("regioneSede");
        String provinciaSede = request.getParameter("provinciaSede");
        String cittaSede = request.getParameter("cittaSede");
        String capSede= request.getParameter("capSede");
        String telefonoSede = request.getParameter("telefonoSede");
        String viaSede = request.getParameter("viaSede");
        String emailSede = request.getParameter("emailSede");

            //Upload immagine sul server
            String rootPath = String.valueOf(request.getServletContext().getResource("/").getPath());
            String fileExtention = logo.getSubmittedFileName().substring(logo.getSubmittedFileName().indexOf('.'));
            ImageManager imageManager = new ImageManager(rootPath, email, logo, fileExtention);
            String imagePath = imageManager.saveImage();

            Azienda azienda = new Azienda(nome, email, password, regione, provincia, imagePath, cap, telefono, citta, via, partitaIva, ragioneSociale, sitoWeb, areaInteresse, numeroDipendenti, settoriCompetenza, null, new ArrayList<Annuncio>());
            List<Sede> sedi = new ArrayList<>();
            Sede sede;
            if(regioneSede != null && provinciaSede != null && cittaSede != null && capSede != null &&
                    telefonoSede != null && viaSede != null && emailSede != null){
                sede = new Sede(regioneSede, provinciaSede, cittaSede, capSede, viaSede, telefonoSede, azienda, emailSede);
            }
            else {
                sede = new Sede(regione, provincia, citta, cap, via, telefono, azienda, email);
            }
            sedi.add(sede);
            azienda.setSedi(sedi);
            boolean success = service.registraAzienda(azienda);

            if(service.autenticazione(email, password) != null){
                session.setAttribute("utente", azienda);
                request.getRequestDispatcher("./WEB-INF/areaPersonaleAzienda.jsp").forward(request, response);
            }else response.sendRedirect(".");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
