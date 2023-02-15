package curriculum.controller;

import curriculum.service.CurriculumService;
import curriculum.service.CurriculumServiceInterface;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.entity.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "ModificaIstruzione", value = "/modificaIstruzione")
public class ModificaIstruzione extends HttpServlet {
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
                if(request.getParameter("data_fin_i") != null && request.getParameter("data_fin_i").length() >1){
                    ddf = LocalDate.parse(request.getParameter("data_fin_i"));
                }


                if(nomeIstituto != null && tipoIstruzione != null && nomeQualifica != null
                        && ddi != null) {
                    Curriculum curriculum = persona.getCurriculum();
                    Istruzione istruzione = null;

                    for(Istruzione i: curriculum.getIstruzioni()){
                        if(i.getIstituto().equals(nomeIstituto) && i.getTipo().equals(tipoIstruzione)){
                            istruzione = i;
                            continue;
                        }
                    }

                    if(istruzione != null){
                        istruzione.setQualifica(nomeQualifica);
                        istruzione.setDataInizio(ddi);
                        istruzione.setDataFine(ddf);

                        CurriculumServiceInterface serviceInterface = new CurriculumService();
                        if(!serviceInterface.aggiornaIstruzione(istruzione)){
                            System.err.println("L'aggiornamento dell'istruzione non è andato a buon fine");
                        }

                        session.setAttribute("utente", persona);
                        request.getRequestDispatcher("./WEB-INF/areaCurriculum.jsp").forward(request, response);
                    } else response.sendRedirect(".");

                } else response.sendRedirect(".");

            } else response.sendRedirect(".");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
