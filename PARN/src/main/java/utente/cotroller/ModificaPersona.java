package utente.cotroller;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.entity.Persona;
import storage.entity.Utente;
import utente.service.UtenteService;
import utils.PasswordEncrypter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "ModificaPersona", value = "/ModificaPersona")
public class ModificaPersona extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");
        if(utente instanceof Persona){
            Persona persona = (Persona) utente;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
            UtenteService service = new UtenteService();

            persona.setNome(request.getParameter("nomePersona"));
            persona.setCognome(request.getParameter("cognome"));
            persona.setTelefono(request.getParameter("telefonoPersona"));
            persona.setCodiceFiscale(request.getParameter("coficeFiscale"));
            persona.setDataDiNascita(LocalDateTime.parse(request.getParameter("data_n"), formatter));
            persona.setRegione(request.getParameter("regionePersona"));
            persona.setProvincia(request.getParameter("provinciaPersona"));
            persona.setCitta(request.getParameter("cittaPersona"));
            persona.setVia(request.getParameter("viaPersona"));
            persona.setCap(request.getParameter("capPersona"));
            persona.setPosizioneDesiderata(request.getParameter("posizione"));
            persona.setFiltroMacroarea(request.getParameter("filtroMacroarea"));
            persona.setFoto(request.getParameter("fotoPersona"));
            persona.setMail(request.getParameter("mailPersona"));
            String oldPassword = request.getParameter("old_Password");
            if(oldPassword.equals(persona.getPassword())){
                persona.setPassword(PasswordEncrypter.encryptThisString(request.getParameter("password_Persona")));
                service.aggiornaPersona(persona);
                session.setAttribute("utente", persona);
                request.getRequestDispatcher("./WEB_INF/modificaInfoPersona.jsp").forward(request, response);
            }
        }else response.sendRedirect(".");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
