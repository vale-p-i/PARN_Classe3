package utente.cotroller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.entity.Azienda;
import storage.entity.Utente;
import utente.service.UtenteService;
import utente.service.UtenteServiceInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ModificaAzienda", value = "/ModificaAzienda")
public class ModificaAzienda extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        UtenteServiceInterface service = new UtenteService();

        if(utente instanceof Azienda && utente != null){
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
            String numeroDipendentiString = request.getParameter("dipendenti");
            int numeroDipendenti = 0;
            try{
                numeroDipendenti = Integer.parseInt(numeroDipendentiString);
            }catch (NumberFormatException n){
                System.err.println("Conversion error " + n);
            }
            azienda.setNumeroDipendenti(numeroDipendenti);

            azienda.setMail(request.getParameter("emailAzienda"));
            azienda.setFoto(request.getParameter("logo"));

            if(service.aggiornaAzienda(azienda))
                System.out.println("aggiornaAzienda pass");
            else
                System.out.println("aggiornaAzienda err");
            session.setAttribute("utente", azienda);
            request.getRequestDispatcher("./WEB-INF/areaPersonaleAzienda.jsp").forward(request, response);
        }else response.sendRedirect(".");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
