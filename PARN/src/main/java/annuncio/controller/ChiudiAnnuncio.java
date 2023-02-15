package annuncio.controller;

import annuncio.service.AnnuncioService;
import annuncio.service.AnnuncioServiceInterface;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.entity.Annuncio;
import storage.entity.Azienda;
import storage.entity.Utente;
import utente.service.UtenteService;
import utente.service.UtenteServiceInterface;

import java.io.IOException;

@WebServlet(name = "ChiudiAnnuncio", value = "/ChiudiAnnuncio")
public class ChiudiAnnuncio extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        AnnuncioServiceInterface serviceAnnuncio = new AnnuncioService();
        UtenteServiceInterface serviceUtente = new UtenteService();

        Utente utente = (Utente) session.getAttribute("utente");
        if(utente instanceof Azienda && utente != null){
            Azienda azienda = (Azienda) utente;

            String idAnnuncioString = request.getParameter("id_annuncio");
            if(idAnnuncioString!=null){
                int idAnnuncio = -1;
                try{
                    idAnnuncio = Integer.parseInt(idAnnuncioString);
                }catch (NumberFormatException n){
                    System.out.println("Conversion error " + n);
                    throw new IllegalArgumentException();
                }
                Annuncio annuncio = serviceAnnuncio.getAnnuncioById(azienda,idAnnuncio);
                serviceAnnuncio.chiusuraAnnuncio(annuncio);
                serviceUtente.aggiornaAzienda(azienda);
                session.setAttribute("utente", azienda);
                request.getRequestDispatcher("./WEB-INF/annunciChiusi.jsp").forward(request, response);
            }else response.sendRedirect(".");
        }else response.sendRedirect(".");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
