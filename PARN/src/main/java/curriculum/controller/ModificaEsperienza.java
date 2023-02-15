package curriculum.controller;

import curriculum.service.CurriculumService;
import curriculum.service.CurriculumServiceInterface;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import storage.entity.Curriculum;
import storage.entity.EsperienzaLavorativa;
import storage.entity.Persona;
import storage.entity.Utente;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ModificaEsperienza", value = "/modificaEsperienza")
public class ModificaEsperienza extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("utente");

        if(utente != null) {

            if(utente instanceof  Persona){
                Persona persona = (Persona) utente;

                String nomeAziendaEsperienza = request.getParameter("nomeAziendaEsperienza");
                String tipoAzienda = request.getParameter("tipoAzienda");
                String tipoImpiego = request.getParameter("tipoImpiego");
                String nomeDatore = request.getParameter("nomeDatore");
                String contattoAzienda = request.getParameter("contattoAzienda");

                List<String> mansioni = new ArrayList<>();
                for(String s:request.getParameter("mansioni").split(",")){
                    mansioni.add(s);
                }

                LocalDate ddi = null;
                if(request.getParameter("data_in_e") != null){
                    ddi = LocalDate.parse(request.getParameter("data_in_e"));
                }

                LocalDate ddf = null;
                if(request.getParameter("data_fin_e") != null && request.getParameter("data_fin_e").length() > 1){
                    ddf = LocalDate.parse(request.getParameter("data_fin_e"));
                }

                if(nomeAziendaEsperienza != null && tipoAzienda != null && tipoImpiego != null
                        && nomeDatore != null && contattoAzienda != null && !mansioni.isEmpty()
                        && ddi != null) {
                    System.out.println("4");

                    Curriculum curriculum = persona.getCurriculum();
                    EsperienzaLavorativa esperienzaLavorativa = null;
                    for(EsperienzaLavorativa e: curriculum.getEsperienze()){
                        if(e.getNomeAzienda().equals(nomeAziendaEsperienza) && e.getTipoImpiego().equals(tipoImpiego)){
                            esperienzaLavorativa = e;
                            continue;
                        }
                    }

                    if(esperienzaLavorativa != null) {
                        esperienzaLavorativa.setTipoAzienda(tipoAzienda);
                        esperienzaLavorativa.setTipoImpiego(tipoImpiego);
                        esperienzaLavorativa.setDatore(nomeDatore);
                        esperienzaLavorativa.setContatto(contattoAzienda);
                        esperienzaLavorativa.setMansioniPrincipali(mansioni);
                        esperienzaLavorativa.setDataInizio(ddi);
                        esperienzaLavorativa.setDataFine(ddf);

                        CurriculumServiceInterface serviceInterface = new CurriculumService();
                        if(!serviceInterface.aggiornaEsperienzaLavorativa(esperienzaLavorativa)){
                            System.err.println("L'aggiornamento dell'esperienza non è andata a buon fine");
                        }

                        session.setAttribute("utente", persona);
                        request.getRequestDispatcher("./WEB-INF/areaCurriculum.jsp").forward(request, response);
                    } else response.sendRedirect(".");

                } else response.sendRedirect(".");

            } else response.sendRedirect(".");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
