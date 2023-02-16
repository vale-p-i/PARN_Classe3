package curriculum.controller;

import curriculum.service.CurriculumService;
import curriculum.service.CurriculumServiceInterface;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.entity.Curriculum;
import storage.entity.Persona;
import storage.entity.Utente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "ModificaCurriculum", value = "/ModificaCurriculum")
public class ModificaCurriculum extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if(utente != null){
            if(utente instanceof Persona) {
                Persona persona = (Persona) utente;

                List<String> listaSkills = new ArrayList<>();
                for(String s:request.getParameter("soft_skills").split(",")){
                    listaSkills.add(s);
                }

                Curriculum curriculum = persona.getCurriculum();
                curriculum.setSoftSkill(listaSkills);

                CurriculumServiceInterface service = new CurriculumService();
                if(!service.aggiornaCurriculum(curriculum)){
                    System.err.println("La modifica del curriculum non è andata a buon fine");
                }

                session.setAttribute("utente", persona);
                request.getRequestDispatcher("./WEB-INF/areaCurriculum.jsp").forward(request, response);
            } else  response.sendRedirect(".");
        } else response.sendRedirect(".");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
