package candidatura.controller;

import annuncio.service.AnnuncioService;
import annuncio.service.AnnuncioServiceInterface;
import candidatura.service.CandidaturaService;
import candidatura.service.CandidaturaServiceInterface;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.entity.Annuncio;
import storage.entity.Candidatura;
import storage.entity.Persona;
import storage.entity.Utente;
import utente.service.UtenteService;
import utente.service.UtenteServiceInterface;

import java.io.IOException;

@WebServlet(name = "CancellaCandidatura", value = "/CancellaCandidatura")
public class CancellaCandidatura extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        CandidaturaServiceInterface serviceCandidatura = new CandidaturaService();
        AnnuncioServiceInterface serviceAnnuncio = new AnnuncioService();
        UtenteServiceInterface serviceUtente = new UtenteService();

        if(utente != null && utente instanceof Persona){
            Persona persona = (Persona) utente;

            String idAnnuncioString = request.getParameter("id_Annuncio");
            if(idAnnuncioString != null){
                int idAnnuncio = -1;
                try {
                    idAnnuncio = Integer.parseInt(idAnnuncioString);
                }catch (NumberFormatException n){
                    System.out.println("Conversion error " + n);
                }
                Annuncio annuncio = serviceAnnuncio.getAnnuncioById(idAnnuncio);
                Candidatura candidatura = serviceCandidatura.getCandidaturaByPersonaAndAnnuncio(persona, annuncio);
                serviceCandidatura.eliminaCandidatura(candidatura);
                serviceUtente.aggiornaPersona(persona);
                session.setAttribute("utente", persona);
                request.getRequestDispatcher("./WEB_INF/visualizzaCandidature.jsp").forward(request, response);
            }else response.sendRedirect(".");
        }else response.sendRedirect(".");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
