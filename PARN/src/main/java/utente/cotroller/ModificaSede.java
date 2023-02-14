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

@WebServlet(name = "ModificaSede", value = "/ModificaSede")
public class ModificaSede extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        UtenteServiceInterface service = new UtenteService();

        if(utente instanceof Azienda){
            Azienda azienda = (Azienda) utente;

            String idString = request.getParameter("id_Sede");
            int id = 0;
            try{
                id = Integer.parseInt(idString);
            } catch (NumberFormatException n){
                System.err.println("Conversion error " + n);
            }
            Sede sede = service.getSedeById(azienda, id);
            sede.setCitta(request.getParameter("citta_Sede"));
            sede.setProvincia(request.getParameter("provincia_Sede"));
            sede.setCap(request.getParameter("cap_Sede"));
            sede.setVia(request.getParameter("via_Sede"));
            sede.setRegione(request.getParameter("regione_Sede"));
            sede.setTelefono(request.getParameter("telefono_Sede"));
            sede.setMail(request.getParameter("mail_Sede"));

            if(sede.getCitta() != null && sede.getProvincia() != null && sede.getCap() != null && sede.getVia() != null
                    && sede.getRegione() != null && sede.getTelefono() != null && sede.getMail() != null){
                service.aggiornaSede(sede);
                session.setAttribute("utente", azienda);
                request.getRequestDispatcher("./WEB_INF/modificaInfoAzienda.jsp").forward(request, response);
            }else{
                response.sendRedirect(".");
            }
        }else{
            response.sendRedirect(".");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
