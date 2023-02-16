package candidatura.controller;

import annuncio.service.AnnuncioService;
import annuncio.service.AnnuncioServiceInterface;
import candidatura.service.CandidaturaService;
import candidatura.service.CandidaturaServiceInterface;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import matching.service.MatchingService;
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

            String idAnnuncioString = request.getParameter("id_annuncio");
            if(idAnnuncioString != null){
                int idAnnuncio = -1;
                try {
                    idAnnuncio = Integer.parseInt(idAnnuncioString);
                }catch (NumberFormatException n){
                    System.out.println("Conversion error " + n);
                }
                Candidatura res=null;
                for(Candidatura c:persona.getCandidature())
                    if(idAnnuncio==c.getAnnuncio().getId())
                        res=c;
                serviceCandidatura.eliminaCandidatura(res);
                serviceUtente.aggiornaPersona(persona);
                session.setAttribute("utente", persona);
                MatchingService serviceMat=new MatchingService();
                session.setAttribute("myList",serviceMat.personalizzaAnnunci(persona.getCurriculum()));
                request.getRequestDispatcher("./WEB-INF/areaCandidatureInviate.jsp").forward(request, response);
            }else response.sendRedirect(".");
        }else response.sendRedirect(".");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
