package curriculum.controller;

import curriculum.service.CurriculumService;
import curriculum.service.CurriculumServiceInterface;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.entity.Curriculum;
import storage.entity.Lingua;
import storage.entity.Persona;
import storage.entity.Utente;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

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

                    session.setAttribute("utente", persona);
                    request.getRequestDispatcher("./WEB_INF/areaCurriculum.jsp").forward(request, response);

                } else response.sendRedirect(".");

            } else response.sendRedirect(".");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
