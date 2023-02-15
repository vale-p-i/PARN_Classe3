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

@WebServlet(name = "ModificaPassword", value = "/ModificaPassword")
public class ModificaPassword extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        UtenteServiceInterface service = new UtenteService();

        //newPassword, oldPassword
        String oldPassword = request.getParameter("oldPassword");
        String oldPasswordEncripted = null;
        if(oldPassword!=null){
            oldPasswordEncripted = PasswordEncrypter.encryptThisString(oldPassword);
        }
        if(oldPasswordEncripted.equals(utente.getPassword())){
            String newPassword = request.getParameter("newPassword");
            String newPasswordEncripted = null;
            if(newPassword!=null){
                newPasswordEncripted = PasswordEncrypter.encryptThisString(newPassword);
            }

            utente.setPassword(newPasswordEncripted);
            service.aggiornaUtente(utente);
            session.setAttribute("utente", utente);
            if(utente instanceof Persona)
                request.getRequestDispatcher("./WEB-INF/areaPersonalePersona.jsp").forward(request, response);
            else if (utente instanceof Azienda)
                request.getRequestDispatcher("./WEB-INF/areaPersonaleAzienda.jsp").forward(request, response);
            else response.sendRedirect(".");
        }else response.sendRedirect(".");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
