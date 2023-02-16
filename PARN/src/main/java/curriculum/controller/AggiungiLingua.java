package curriculum.controller;

import curriculum.service.CurriculumService;
import curriculum.service.CurriculumServiceInterface;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import matching.service.MatchingService;
import matching.service.MatchingServiceInterface;
import storage.entity.*;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "AggiungiLingua", value = "/creaLingua")
public class AggiungiLingua extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if(utente != null) {
            if(utente instanceof Persona){
                Persona persona = (Persona) utente;

                String nomeLingua = request.getParameter("nomeLingua");
                String livelloLingua = request.getParameter("livelloLingua");

                if(nomeLingua != null && livelloLingua != null) {
                    Curriculum curriculum = persona.getCurriculum();
                    Lingua lingua = new Lingua(nomeLingua, livelloLingua, curriculum);

                    CurriculumServiceInterface serviceInterface = new CurriculumService();
                    if(!serviceInterface.aggiungiLingua(lingua)){
                        System.err.println("L'aggiunta della lingua non è andata a buon fine");
                    }

                    MatchingServiceInterface serviceMat=new MatchingService();
                    List<Annuncio> list= serviceMat.personalizzaAnnunci(persona.getCurriculum());
                    session.setAttribute("myList",list);
                    session.setAttribute("utente", persona);
                    request.getRequestDispatcher("./WEB-INF/areaCurriculum.jsp").forward(request, response);

                } else response.sendRedirect(".");

            } else response.sendRedirect(".");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
