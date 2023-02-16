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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EliminaEsperienzaLavorativa", value = "/EliminaEsperienza")
public class EliminaEsperienzaLavorativa extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if(utente != null) {
            if(utente instanceof Persona){
                Persona persona = (Persona) utente;

                String nomeAziendaEsperienza = request.getParameter("nomeAziendaEsperienza");
                String tipoImpiego = request.getParameter("tipoImpiego");

                Curriculum curriculum = persona.getCurriculum();
                EsperienzaLavorativa esperienzaLavorativa = null;
                for(EsperienzaLavorativa e: curriculum.getEsperienze()){
                    if(e.getNomeAzienda().equals(nomeAziendaEsperienza) && e.getTipoImpiego().equals(tipoImpiego)){
                        esperienzaLavorativa = e;
                        continue;
                    }
                }

                if(esperienzaLavorativa != null){
                    CurriculumServiceInterface serviceInterface = new CurriculumService();
                    if(!serviceInterface.eliminaEsperienzaLavorativa(esperienzaLavorativa)){
                        System.err.println("L'eliminazione non è riuscita");
                    }

                    MatchingServiceInterface serviceMat = new MatchingService();
                    List<Annuncio> list= serviceMat.personalizzaAnnunci(persona.getCurriculum());
                    session.setAttribute("myList",list);
                    session.setAttribute("utente", persona);
                    request.getRequestDispatcher("./WEB-INF/areaCurriculum.jsp").forward(request, response);
                } else  response.sendRedirect(".");

            } else response.sendRedirect(".");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
