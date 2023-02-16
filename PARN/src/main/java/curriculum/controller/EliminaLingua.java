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
import java.util.List;

@WebServlet(name = "EliminaLingua", value = "/EliminaLingua")
public class EliminaLingua extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if(utente != null) {
            if(utente instanceof Persona){
                Persona persona = (Persona) utente;

                String nomeLingua = request.getParameter("nomeLingua");

                Curriculum curriculum = persona.getCurriculum();
                Lingua lingua = null;
                for(Lingua l: curriculum.getLingue()){
                    if(l.getNome().equals(nomeLingua)){
                        lingua = l;
                        continue;
                    }
                }

                if(lingua != null){

                    CurriculumServiceInterface serviceInterface = new CurriculumService();
                    int returnValue = serviceInterface.eliminaLingua(lingua);
                    if(returnValue == 0){
                        System.err.println("Non puoi eliminare la lingua se è l'unica");
                    } else if (returnValue == 1) {
                        System.err.println("Eliminazione non riuscita");
                    }

                    MatchingServiceInterface serviceMat = new MatchingService();
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
