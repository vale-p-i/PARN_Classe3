package utente.cotroller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.entity.Azienda;
import storage.entity.Sede;
import storage.entity.Utente;
import utente.service.UtenteService;
import utente.service.UtenteServiceInterface;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "AggiungiSede", value = "/AggiungiSede")
public class AggiungiSede extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        UtenteServiceInterface service = new UtenteService();

        if(utente instanceof Azienda){
            Azienda azienda = (Azienda) utente;
            String citta = request.getParameter("ciattaSede");
            String provincia = request.getParameter("provinciaSede");
            String cap = request.getParameter("capSede");
            String via = request.getParameter("viaSede");
            String regione = request.getParameter("regioneSede");
            String telefono = request.getParameter("telefonoSede");
            String mail = request.getParameter("mailSede");

            if(azienda!=null && citta != null && provincia != null && cap != null && via != null && regione != null &&
                    telefono != null && mail != null){
                Sede sede = new Sede(azienda.getId(), regione, provincia, citta, cap, via, telefono, azienda, mail);
                List<Sede> sedi = azienda.getSedi();
                sedi.add(sede);
                azienda.setSedi(sedi);

                service.registraSede(sede);
                service.aggiornaAzienda(azienda);

                session.setAttribute("utente", azienda);
                request.getRequestDispatcher("./WEB-INF/modificaInfoAzienda.jsp").forward(request, response);
            }
        }
        response.sendRedirect(".");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
