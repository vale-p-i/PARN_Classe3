package utente.cotroller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.entity.Azienda;
import storage.entity.Utente;
import utente.service.UtenteService;
import utils.PasswordEncrypter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ModificaAzienda", value = "/ModificaAzienda")
public class ModificaAzienda extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        UtenteService service = new UtenteService();

        if(utente instanceof Azienda){
            Azienda azienda = (Azienda) utente;

            azienda.setNome(request.getParameter("nomeAzienda"));
            azienda.setPartitaIVA(request.getParameter("piva"));
            azienda.setTelefono(request.getParameter("telefonoAzienda"));
            azienda.setRagioneSociale(request.getParameter("ragioneSociale"));
            azienda.setLink(request.getParameter("sitoWeb"));
            azienda.setRegione(request.getParameter("regioneAzienda"));
            azienda.setProvincia(request.getParameter("provinciaAzienda"));
            azienda.setCitta(request.getParameter("cittaAzienda"));
            azienda.setVia(request.getParameter("viaAzienda"));
            azienda.setCap(request.getParameter("capAzienda"));
            azienda.setAreaInteresse(request.getParameter("areaInteresse"));

            String settoriCompetenzaString = request.getParameter("settoriCompetenza");
            List<String> settoriComptenza = new ArrayList<>();
            for(String s:settoriCompetenzaString.split(","))
                settoriComptenza.add(s);

            azienda.setSettoriCompetenza(settoriComptenza);
            azienda.setNumeroDipendenti(Integer.parseInt(request.getParameter("dipendenti")));
            azienda.setMail(request.getParameter("emailAzienda"));
            azienda.setFoto(request.getParameter("logo"));

            String oldPassword = PasswordEncrypter.encryptThisString(request.getParameter("old_Password"));
            if(oldPassword.equals(azienda.getPassword())){
                azienda.setPassword(PasswordEncrypter.encryptThisString(request.getParameter("password_Azienda")));
                service.aggiornaAzienda(azienda);
                session.setAttribute("utente", azienda);
                request.getRequestDispatcher("./WEB_INF/modificaInfoAzienda.jsp").forward(request, response);
            }
        }else response.sendRedirect(".");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
