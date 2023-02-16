<%@ page import="storage.entity.*" %>
<%@ page import="curriculum.service.CurriculumServiceInterface" %>
<%@ page import="curriculum.service.CurriculumService" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>PARN</title>

    <!-- CSS  -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link href="css/materialize.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="css/progetto.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <script>
        <%session = request.getSession(false);
    Azienda azz=(Azienda) session.getAttribute("utente");
    if (azz==null){%>
        window.location.href = "./index.jsp";
        <%
            }
        %>
    </script>
</head>
<body>
<nav class="default-color" role="navigation">
    <div class="nav-wrapper">
        <ul id="slide-out" class="sidenav">
            <li>
                <div class="right-align">
                    <a class="sidenav-close" href="#!">
                        <i class="medium material-icons  close-close white-text">close</i>
                    </a>
                </div>
            </li>
            <li>
                <div class="center-image">
                    <a href="#user" class="mod-a"><img height="100" width="100" class="circle" onerror="this.onerror=null; this.src='resource/img.png'" src="<%=azz.getFoto()%>"></a>
                </div>
            </li>
            <li><a href="RedirectServlet?redirect=homepageAzienda" class="waves-effect waves-light btn-large white default-color-text">Homepage</a></li>
            <li><a href="RedirectServlet?redirect=areaPersonaleAzienda" class="waves-effect waves-light btn-large white default-color-text">AreaPersonale</a></li>
            <li><hr style="margin: 0 10% 0 10%"></li>
            <li><div class="center"><h6>Annunci</h6></div></li>
            <li><a href="RedirectServlet?redirect=annunciAttivi" class="waves-effect waves-light btn-large white default-color-text">In Corso</a></li>
            <li><a href="RedirectServlet?redirect=annunciScaduti" class="waves-effect waves-light btn-large white default-color-text">Scadute</a></li>
            <li><a href="RedirectServlet?redirect=annunciChiusi" class="waves-effect waves-light btn-large white default-color-text">Chiusi</a></li>
            <li><a href="RedirectServlet?redirect=creaAnnuncio" class="waves-effect waves-light btn-large white default-color-text">Crea Annuncio</a></li>
            <li><a href="Logout" class="waves-effect waves-light btn-large red white-text">Logout</a></li>
        </ul>
        <a href="#" data-target="slide-out" class="sidenav-trigger show-on-large"><i class="material-icons white-text">menu</i></a>
        <a id="logo-container" href="index.jsp" class="brand-logo"><img src="resource/logo.png" width="250" height="80" class="responsive-img"></a>
    </div>
</nav>

<div class="container">
    <div class="section-main">
        <div class="row">
            <div class="curriculum">
                <h4>Curriculum Candidato</h4>
                <%
                    Annuncio a= (Annuncio) session.getAttribute("annuncio");
                    int id= Integer.parseInt(request.getParameter("id"));
                    Curriculum c= null;
                    if (a!=null) {
                        if (a.getCandidature()!=null)
                            for(Candidatura can: a.getCandidature())
                                if(can.getPersona().getId()==id)
                                    c=can.getCurriculum();
                    }
                %>
                <div class="esperienzeLavorative" id="esperienzeLavorative">
                    <div class="row">
                        <div class="input-field col s12 m12">
                            <h5>Esperienze lavorative</h5>
                        </div>

                    </div>
                    <%
                        if (c!=null)
                            for (EsperienzaLavorativa e: c.getEsperienze()){
                    %>
                    <div class="row">
                        <div class="input-field col s12 m12">
                            <h6>Esperienza:</h6>
                        </div>

                    </div>
                    <div class="esperienza">
                            <div class="row">
                                <div class="input-field col s12 m4">
                                    <input placeholder="Nome azienda" id="nomeAziendaEsperienza" disabled name="nomeAziendaEsperienza" value="<%=e.getNomeAzienda()%>" type="text" class="validate">
                                    <label for="nomeAziendaEsperienza">Inserisci il nome del azienda</label>
                                </div>
                                <div class="input-field col s12 m4">
                                    <input placeholder="Tipo azienda" disabled id="tipoAzienda" name="tipoAzienda" disabled value="<%=e.getTipoAzienda()%>" type="text" class="validate">
                                    <label for="tipoAzienda">Inserisci il tipo di azienda</label>
                                </div>
                                <div class="input-field col s12 m4">
                                    <input placeholder="Tipo impiego" disabled id="tipoImpiego" disabled value="<%=e.getTipoImpiego()%>" name="tipoImpiego" type="text" class="validate">
                                    <label for="tipoImpiego">Inserisci il tipo di impiego svolto</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12 m3">
                                    <input placeholder="Nome Datore" disabled id="nomeDatore" name="nomeDatore" value="<%=e.getDatore()%>" type="text" class="validate">
                                    <label for="nomeDatore">Inserisci il nome del datore</label>
                                </div>
                                <div class="input-field col s12 m3">
                                    <input placeholder="Contatto" disabled id="contattoAzienda" name="contattoAzienda" value="<%=e.getContatto()%>" type="text" class="validate">
                                    <label for="contattoAzienda">Inserisci il contatto del referente</label>
                                </div>
                                <div class="input-field col s12 m6">
                                    <input placeholder="Mansioni" disabled id="mansioni" name="mansioni" type="text" value="<%=e.getMansioniPrincipali()%>" class="validate">
                                    <label for="mansioni">Inserisci le mansioni svolte</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12 m6">
                                    <input placeholder=" inizio" disabled type="text" id="data_in_e" name="data_in_e" value="<%=e.getDataInizio()%>" class="datepicker">
                                    <label for="data_in_e">Data di inizio esperienza:</label>
                                </div>
                                <div class="input-field col s12 m6">
                                    <input placeholder="Data fine"  disabled type="text" id="data_fin_e" name="data_fin_e" value="<%=e.getDataFine()%>" class="datepicker">
                                    <label for="data_fin_e">Data di inizio esperienza:</label>
                                </div>
                            </div>
                    </div>
                    <hr class="default-color">
                    <%
                            }

                    %>
                </div>
                <div class="Lingue" id="lingua">
                    <div class="row">
                        <div class="input-field col s12 m12">
                            <h5>Lingue</h5>
                        </div>

                    </div>
                    <%
                        if(c!=null)
                            for(Lingua l :c.getLingue()){
                    %>
                    <div class="lingua">
                        <div class="row">
                            <div class="input-field col s12 m12">
                                <h6>Lingua:</h6>
                            </div>

                        </div>
                            <div class="row">
                                <div class="input-field col s12 m6">
                                    <input placeholder="Nome lingua" id="nomeLingua" disabled name="nomeLingua" value="<%=l.getNome()%>" type="text" class="validate">
                                    <label for="nomeLingua">Inserisci il nome della lingua</label>
                                </div>
                                <div class="input-field col s12 m6">
                                    <input placeholder="Livello Lingua" disabled id="livelloLingua" name="livelloLingua" value="<%=l.getLivello()%>" type="text" class="validate">
                                    <label for="livelloLingua">Inserisci il livello della lingua</label>
                                </div>
                            </div>
                    </div>
                    <hr class="default-color">
                    <%
                            }
                    %>
                </div>
                <div class="Istruzioni" id="istruzione">
                    <div class="row">
                        <div class="input-field col s12 m12">
                            <h5>Istruzioni:</h5>
                        </div>

                    </div>
                    <%
                        if(c!=null)
                            for(Istruzione i: c.getIstruzioni()){
                                System.out.println(c.getIstruzioni().size());
                    %>
                    <div class="istruzione">
                        <div class="row">
                            <div class="input-field col s12 m12">
                                <h6>Istruzione:</h6>
                            </div>

                        </div>
                            <div class="row">
                                <div class="input-field col s12 m4">
                                    <input placeholder="Nome Istituto" disabled id="nomeIstituto" value="<%=i.getIstituto()%>" name="nomeIstituto" type="text" class="validate">
                                    <label for="nomeIstituto">Inserisci il nome del istituto</label>
                                </div>
                                <div class="input-field col s12 m4">
                                    <input placeholder="Tipo Istruttore" disabled id="tipoIstruzione" value="<%=i.getTipo()%>" name="tipoIstruzione" type="text" class="validate">
                                    <label for="tipoIstruzione">Inserisci il tipo di istruzione</label>
                                </div>
                                <div class="input-field col s12 m4">
                                    <input placeholder="Nome Qualifica" disabled id="nomeQualifica" value="<%=i.getQualifica()%>" name="nomeQualifica" type="text" class="validate">
                                    <label for="nomeQualifica">Inserisci il nome della qualifica</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12 m6">
                                    <input placeholder=" inizio" disabled type="text" id="data_in_i" value="<%=i.getDataInizio()%>" name="data_in_i"  class="datepicker">
                                    <label for="data_in_i">Data di inizio Istruzione:</label>
                                </div>
                                <div class="input-field col s12 m6">
                                    <input placeholder="Data fine" disabled type="text" id="data_fin_i" value="<%=i.getDataFine()%>" name="data_fin_i"  class="datepicker">
                                    <label for="data_fin_i">Data di inizio Istruzione:</label>
                                </div>
                            </div>
                    </div>
                    <hr class="default-color">
                    <%
                            }
                    %>

                </div>
                <div class="row">
                    <div class="right">
                        <%
                            CurriculumServiceInterface service=new CurriculumService();
                        %>
                        <a href="/pdf/<%=service.downloadCurriculum(c)%>" download="pdf">
                            <button class="btn waves-effect waves-light"  name="action" type="button">Download<i class="material-icons right">file_download</i></button>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<footer class="page-footer default-color">
    <div class="container">
        <div class="row">
            <div class="col l6 s12">
                <h5 class="white-text">PARN</h5>
                <p class="grey-text text-lighten-4">Siamo un gruppo di studenti universitari che ha ideato questo progetto per il corso di Ingegneria del Software.</p>


            </div>
            <div class="col l3 s12">
            </div>
            <div class="col l3 s12">
                <h5 class="white-text">Link</h5>
                <ul>
                    <li><a class="white-text" href="register.jsp">Registrazione</a></li>
                    <li><a class="white-text" href="accesso.html">Login</a></li>
                    <li><a class="white-text" href="index.jsp">Home</a></li>
                </ul>
            </div>
        </div>
    </div>
    <div class="footer-copyright">
        <div class="container">
            Made by <a class="brown-text text-lighten-3" href="http://materializecss.com">Materialize</a>
        </div>
    </div>
</footer>


<!--  Scripts-->
<script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
<script src="js/materialize.js"></script>
<script src="js/init.js"></script>

</body>
</html>
