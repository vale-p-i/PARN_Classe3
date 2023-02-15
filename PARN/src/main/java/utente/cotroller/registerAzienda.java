package utente.cotroller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.entity.Annuncio;
import storage.entity.Azienda;
import storage.entity.Sede;
import utente.service.UtenteService;
import utente.service.UtenteServiceInterface;
import utils.PasswordEncrypter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "registerAzienda", value = "/registerAzienda")
public class registerAzienda extends HttpServlet {
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
        String logo = request.getParameter("logo");

        String settoriCompetenzaString = request.getParameter("settoriCompetenza");
        List<String> settoriCompetenza = new ArrayList<>();
        for(String s:settoriCompetenzaString.split(","))
            settoriCompetenza.add(s);

        int numeroDipendenti = 0;
        try{
            numeroDipendenti = Integer.parseInt(request.getParameter("dipendenti"));
        } catch (NumberFormatException n) {
            System.err.println("Conversion error "+n);
        }

        String regioneSede = request.getParameter("regioneSede");
        String provinciaSede = request.getParameter("provinciaSede");
        String cittaSede = request.getParameter("cittaSede");
        String capSede= request.getParameter("capSede");
        String telefonoSede = request.getParameter("telefonoSede");
        String viaSede = request.getParameter("viaSede");
        String mailSede = request.getParameter("emailSede");

        if(!settoriCompetenzaString.isEmpty() && numeroDipendenti >= 0){

            Azienda azienda = new Azienda(nome, email, password, regione, provincia, logo, cap, telefono, citta, via, partitaIva, ragioneSociale, sitoWeb, areaInteresse, numeroDipendenti, settoriCompetenza, null, new ArrayList<Annuncio>());
            service.registraAzienda(azienda);
            List<Sede> sedi = new ArrayList<>();
            Sede sede;
            Sede newSede;
            if(regioneSede != null){
                newSede = new Sede(regioneSede, provinciaSede, cittaSede, capSede, viaSede, telefonoSede, azienda, mailSede);
            }
            else {
                newSede = new Sede(regione, provincia, citta, cap, via, telefono, azienda, email);
            }
            sede = newSede;
            service.registraSede(sede);
            sedi.add(sede);
            if(service.autenticazione(email, password) != null){
                session.setAttribute("utente", azienda);
                request.getRequestDispatcher("./WEB-INF/areaAzienda.jsp").forward(request, response);
            }else response.sendRedirect(".");
        }else response.sendRedirect(".");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
