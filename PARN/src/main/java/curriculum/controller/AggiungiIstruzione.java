package curriculum.controller;

import curriculum.service.CurriculumService;
import curriculum.service.CurriculumServiceInterface;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.entity.Curriculum;
import storage.entity.Istruzione;
import storage.entity.Persona;
import storage.entity.Utente;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "AggiungiIstruzione", value = "/creaIstruzione")
public class AggiungiIstruzione extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if(utente != null) {
            if(utente instanceof Persona){
                Persona persona = (Persona) utente;

                String nomeIstituto = request.getParameter("nomeIstituto");
                String tipoIstruzione = request.getParameter("tipoIstruzione");
                String nomeQualifica = request.getParameter("nomeQualifica");

                LocalDate ddi = null;
                if(request.getParameter("data_in_i") != null){
                    ddi = LocalDate.parse(request.getParameter("data_in_i"));
                }

                LocalDate ddf = null;
                if(request.getParameter("data_fin_i") != null && request.getParameter("data_fin_i").length()>1){
                    ddf = LocalDate.parse(request.getParameter("data_fin_i"));
                }


                if(nomeIstituto != null && tipoIstruzione != null && nomeQualifica != null
                        && ddi != null) {
                    Curriculum curriculum = persona.getCurriculum();
                    Istruzione istruzione = new Istruzione(curriculum, ddi, ddf, nomeQualifica, tipoIstruzione, nomeIstituto);
                    CurriculumServiceInterface serviceInterface = new CurriculumService();
                    if(!serviceInterface.aggiungiIstruzione(istruzione)){
                        System.err.println("L'aggiunta dell'istruzione non è andata a buon fine");
                    }

                    session.setAttribute("utente", persona);
                    request.getRequestDispatcher("./WEB-INF/areaCurriculum.jsp").forward(request, response);

                } else response.sendRedirect(".");

            } else response.sendRedirect(".");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
