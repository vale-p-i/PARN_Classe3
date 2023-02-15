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

@WebServlet(name = "EliminaAnnuncio", value = "/EliminaAnnuncio")
public class EliminaAnnuncio extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        AnnuncioServiceInterface serviceAnnuncio = new AnnuncioService();
        UtenteServiceInterface serviceUtente = new UtenteService();

        if(utente != null && utente instanceof Azienda){
            System.out.println("2");
            Azienda azienda = (Azienda) utente;
            String idAnnuncioString = request.getParameter("id_annuncio");
            if(idAnnuncioString != null){
                System.out.println("3");
                int idAnnuncio = -1;
                try{
                    idAnnuncio = Integer.parseInt(idAnnuncioString);
                }catch (NumberFormatException n){
                    throw new IllegalArgumentException(n);
                }
                System.out.println("3 "+idAnnuncio);
                Annuncio annuncio = serviceAnnuncio.getAnnuncioById(azienda,idAnnuncio);
                if(serviceAnnuncio.eliminaAnnuncio(annuncio)){
                    System.out.println("pass ");
                }else{
                    System.out.println("faild");
                }

                serviceUtente.aggiornaAzienda(azienda);
                session.setAttribute("utente", azienda);
                request.getRequestDispatcher("./WEB-INF/annunciAttivi.jsp").forward(request, response);
            }else response.sendRedirect(".");
        }else response.sendRedirect(".");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
