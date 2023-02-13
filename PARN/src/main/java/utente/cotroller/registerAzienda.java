package utente.cotroller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "registerAzienda", value = "/registerAzienda")
public class registerAzienda extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nome = request.getParameter("nomeAzienda");
        String partitaIva = request.getParameter("piva");
        String telefono = request.getParameter("telefonoAccount");
        String ragioneSociale = request.getParameter("ragioneSociale");
        String sitoWeb = request.getParameter("sitoWeb");
        String regione = request.getParameter("regioneAzienda");
        String provincia = request.getParameter("provinciaAzienda");
        String citta = request.getParameter("cittaAzienda");
        String civico = request.getParameter("civicoAzienda");
        String cap = request.getParameter("capAzienda");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
