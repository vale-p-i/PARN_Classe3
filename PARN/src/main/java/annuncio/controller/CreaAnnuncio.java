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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CreaAnnuncio", value = "/CreaAnnuncio")
public class CreaAnnuncio extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        UtenteServiceInterface serviceUtente = new UtenteService();
        AnnuncioServiceInterface serviceAnnuncio = new AnnuncioService();

        if(utente instanceof Azienda && utente != null){
            Azienda azienda = (Azienda) utente;
            String idSedeString = request.getParameter("sedelist");
            System.out.println("sede"+idSedeString);
            if(idSedeString != null){
                int idSede = -1;
                int numeroPersone = 0;
                try {
                    idSede = Integer.parseInt(idSedeString);
                    numeroPersone = Integer.parseInt(request.getParameter("numeroDipendenti"));
                }catch (NumberFormatException n){
                    throw  new IllegalArgumentException(n);
                }
                String descrizione = request.getParameter("descrizione");
                LocalDate data_S=LocalDate.parse(request.getParameter("data_scad"));
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

                Annuncio annuncio = new Annuncio(idSede, azienda, true, serviceUtente.getSedeById(azienda, idSede),
                        numeroPersone, descrizione, data_S, requisiti, keywords, preferenze, ruolo, null);

                serviceAnnuncio.creaAnnuncio(annuncio);
                serviceUtente.aggiornaAzienda(azienda);
                session.setAttribute("utente", azienda);
                request.getRequestDispatcher("./WEB-INF/annunciAttivi.jsp").forward(request, response);
            }else response.sendRedirect(".");
        }else response.sendRedirect(".");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
