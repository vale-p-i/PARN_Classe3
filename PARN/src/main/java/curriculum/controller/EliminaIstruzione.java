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
import java.util.List;

@WebServlet(name = "EliminaIstruzione", value = "/EliminaIstruzione")
public class EliminaIstruzione extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if(utente != null) {
            if(utente instanceof Persona){
                Persona persona = (Persona) utente;

                String nomeIstituto = request.getParameter("nomeIstituto");
                String tipoIstruzione = request.getParameter("tipoIstruzione");

                Curriculum curriculum = persona.getCurriculum();
                Istruzione istruzione = null;

                for(Istruzione i: curriculum.getIstruzioni()){
                    if(i.getIstituto().equals(nomeIstituto) && i.getTipo().equals(tipoIstruzione)){
                        istruzione = i;
                        continue;
                    }
                }

                if(istruzione != null){

                    CurriculumServiceInterface serviceInterface = new CurriculumService();
                    int returnValue = serviceInterface.eliminaIstruzione(istruzione);
                    if(returnValue == 0){
                        System.err.println("Non puoi eliminare l'istruzione se è l'unica.");
                    } else if (returnValue == 1) {
                        System.err.println("Eliminazione non riuscita");
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
