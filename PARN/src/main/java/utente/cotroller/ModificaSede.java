package utente.cotroller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.entity.Azienda;
import storage.entity.Sede;
import storage.entity.Utente;
import utente.service.UtenteService;

import java.io.IOException;

@WebServlet(name = "ModificaSede", value = "/ModificaSede")
public class ModificaSede extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if(utente instanceof Azienda){
            Azienda azienda = (Azienda) utente;

            UtenteService service = new UtenteService();
            Sede sede = service.getSedeById(azienda, Integer.parseInt(request.getParameter("id_Sede")));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
