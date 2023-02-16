package utente.cotroller;

import candidatura.service.CandidaturaService;
import candidatura.service.CandidaturaServiceInterface;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.entity.Persona;
import storage.entity.Utente;

import java.io.IOException;

@WebServlet(name = "RedirectServlet", value = "/RedirectServlet")
public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect = request.getParameter("redirect");
        if(redirect != null) {
            HttpSession session=request.getSession();
            Utente utente= (Utente) session.getAttribute("utente");
            System.out.println("1");
            if (utente instanceof Persona){
                System.out.println("2");
                Persona p=(Persona) utente;
                if (p.getCandidature()==null){
                    System.out.println("3");
                    CandidaturaServiceInterface service=new CandidaturaService();
                    p.setCandidature(service.getCandidatureByPersona(p));
                }
            }
            request.getRequestDispatcher("./WEB-INF/" + redirect + ".jsp").forward(request, response);
        }
        else response.sendRedirect(".");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
