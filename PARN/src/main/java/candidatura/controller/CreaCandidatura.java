package candidatura.controller;

import annuncio.service.AnnuncioService;
import annuncio.service.AnnuncioServiceInterface;
import candidatura.service.CandidaturaService;
import candidatura.service.CandidaturaServiceInterface;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.entity.*;
import utente.service.UtenteService;
import utente.service.UtenteServiceInterface;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "CreaCandidatura", value = "/CreaCandidatura")
public class CreaCandidatura extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        AnnuncioServiceInterface serviceAnnuncio = new AnnuncioService();
        UtenteServiceInterface serviceUtente = new UtenteService();
        System.out.println("1");
        if(utente != null && utente instanceof Persona){
            Persona persona = (Persona) utente;
            System.out.println("2");
            if (persona.getCandidature()==null){
                CandidaturaServiceInterface service=new CandidaturaService();
                persona.setCandidature(service.getCandidatureByPersona(persona));
            }
            String idAnnuncioString = request.getParameter("id_annuncio");
            if(idAnnuncioString != null){
                System.out.println("3");
                int idAnnuncio = -1;
                try{
                    idAnnuncio = Integer.parseInt(idAnnuncioString);
                }catch (NumberFormatException n){
                    throw new IllegalArgumentException(n);
                }
                if(idAnnuncio!=-1) {
                    System.out.println("4");
                    Annuncio annuncio=null;
                    List<Annuncio> matching = (List<Annuncio>) session.getAttribute("myList");
                    if (matching != null) {
                        System.out.println("5");
                        for (Annuncio a : matching)
                            if (a.getId() == idAnnuncio)
                                annuncio=a;
                    }
                    else {
                        System.out.println("6");
                        annuncio=serviceAnnuncio.getAnnuncioById(idAnnuncio);
                    }
                    if(annuncio!=null) {
                        System.out.println("7");
                        Candidatura candidatura = new Candidatura(persona, annuncio, persona.getCurriculum(), LocalDate.now());
                        serviceAnnuncio.aggiungiCandidatura(annuncio, candidatura);
                        matching.remove(annuncio);
                        session.setAttribute("myList",matching);
                        serviceUtente.aggiornaPersona(persona);
                        session.setAttribute("utente", persona);
                        request.getRequestDispatcher("./WEB-INF/homepagePersona.jsp").forward(request, response);
                    }else response.sendRedirect(".");
                }else response.sendRedirect(".");
            }else response.sendRedirect(".");
        }else response.sendRedirect(".");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
