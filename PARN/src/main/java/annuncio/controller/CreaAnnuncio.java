package annuncio.controller;

import annuncio.service.AnnuncioService;
import annuncio.service.AnnuncioServiceInterface;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.entity.Annuncio;
import storage.entity.Azienda;
import storage.entity.Utente;
import utente.service.UtenteService;
import utente.service.UtenteServiceInterface;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CreaAnnuncio", value = "/CreaAnnuncio")
public class CreaAnnuncio extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        UtenteServiceInterface serviceUtente = new UtenteService();
        AnnuncioServiceInterface serviceAnnuncio = new AnnuncioService();

        if(utente instanceof Azienda && utente != null){
            Azienda azienda = (Azienda) utente;
            int idSede = -1;
            int numeroPersone = 0;
            try {
                idSede = Integer.parseInt(request.getParameter("id_Sede"));
                numeroPersone = Integer.parseInt(request.getParameter("numero_Persone"));
            }catch (NumberFormatException n){
                System.out.println("Conversion error " + n);
            }
            String descrizione = request.getParameter("descrizione");
            LocalDateTime scadenza = LocalDateTime.parse(request.getParameter("scadenza"), formatter);
            List<String> requisiti = new ArrayList<>();
            for(String s:request.getParameter("requisiti").split(","))
                requisiti.add(s);
            List<String> keywords = new ArrayList<>();
            for(String s:request.getParameter("keywords").split(","))
                keywords.add(s);
            List<String> preferenze = new ArrayList<>();
            for(String s:request.getParameter("preferenze").split(","))
                preferenze.add(s);
            String ruolo = request.getParameter("ruolo");

            Annuncio annuncio = new Annuncio(-1, azienda, true, serviceUtente.getSedeById(azienda, idSede),
                    numeroPersone, descrizione, scadenza, requisiti, keywords, preferenze, ruolo, null);

            serviceAnnuncio.creaAnnuncio(annuncio);
            serviceUtente.aggiornaAzienda(azienda);
            session.setAttribute("utente", azienda);
            request.getRequestDispatcher("./WEB_INF/visualizzaAnnunci.jsp").forward(request, response);
        }else response.sendRedirect(".");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
