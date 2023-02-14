package utente.cotroller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.entity.Azienda;
import storage.entity.Persona;
import storage.entity.Utente;
import utente.service.UtenteService;
import utente.service.UtenteServiceInterface;
import utils.PasswordEncrypter;

import java.io.IOException;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String tmppass=request.getParameter("password");
        String password=null;
        if(tmppass!=null)
             password= PasswordEncrypter.encryptThisString(tmppass);
        UtenteServiceInterface service = new UtenteService();
        HttpSession session = request.getSession();

        if(email != null && password != null){
            Utente utente = service.autenticazione(email, password);
            if(utente!=null && utente instanceof Azienda){
                System.out.println("azienda");
                Azienda azienda = (Azienda) utente;
                session.setAttribute("utente", azienda);
                request.getRequestDispatcher("./WEB-INF/homepageAzienda.jsp").forward(request, response);
            } else if(utente!=null && utente instanceof Persona) {
                System.out.println("persona");
                Persona persona = (Persona) utente;
                session.setAttribute("utente", persona);
                request.getRequestDispatcher("./WEB-INF/homepagePersona.jsp").forward(request, response);
            }
            else response.sendRedirect(".");
        }else response.sendRedirect(".");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
