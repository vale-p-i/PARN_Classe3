<%@ page import="storage.entity.*" %>
<%@ page import="org.apache.catalina.Session" %>
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
                    <a href="#user" class="mod-a"><img height="100" width="100" class="circle" src="resource/img.png"></a>
                </div>
            </li>
            <li><a href="#!" class="waves-effect waves-light btn-large white default-color-text">Homepage</a></li>
            <li><a href="#!" class="waves-effect waves-light btn-large white default-color-text">AreaPersonale</a></li>
            <li><a href="#!" class="waves-effect waves-light btn-large white default-color-text">Curriculum</a></li>
            <li><a href="#!" class="waves-effect waves-light btn-large white default-color-text">Candidature Inviate</a></li>
            <li><a href="#!" class="waves-effect waves-light btn-large red white-text">Logout</a></li>
        </ul>
        <a href="#" data-target="slide-out" class="sidenav-trigger show-on-large"><i class="material-icons white-text">menu</i></a>
        <a id="logo-container" href="index.html" class="brand-logo"><img src="resource/logo.png" width="250" height="80" class="responsive-img"></a>
    </div>
</nav>


<div class="container">
    <div class="section-main">
        <div class="row">
            <div class="curriculum">
                <h4>Curriculum</h4>
                <%
                session = request.getSession(false);
                Persona p=(Persona) session.getAttribute("utente");
                Curriculum c= null;
                if (p!=null) {
                    c = p.getCurriculum();
                    System.out.println("persona non è null");
                }
                %>
                <div class="esperienzaLavorativa" id="esperienzaLavorativa">
                    <div class="row">
                        <div class="input-field col s11 m11">
                            <h5>Esperienza lavorativa</h5>
                        </div>
                        <div class="input-field col s1 m1">
                            <a class="btn-floating btn-small waves-effect waves-light default-color" onclick="addEsperienza()"><i class="material-icons">add</i></a>
                        </div>
                    </div>
                <%
                    for (EsperienzaLavorativa e: c.getEsperienze()){
                %>

                    <div class="esperienza">
                        <div class="row">
                            <div class="input-field col s12 m4">
                                <input placeholder="Nome azienda" id="nomeAziendaEsperienza" name="nomeAziendaEsperienza" value="<%=e.getNomeAzienda()%>" type="text" class="validate">
                                <label for="nomeAziendaEsperienza">Inserisci il nome del azienda</label>
                            </div>
                            <div class="input-field col s12 m4">
                                <input placeholder="Tipo azienda" id="tipoAzienda" name="tipoAzienda" value="<%=e.getTipoAzienda()%>" type="text" class="validate">
                                <label for="tipoAzienda">Inserisci il tipo di azienda</label>
                            </div>
                            <div class="input-field col s12 m4">
                                <input placeholder="Tipo impiego" id="tipoImpiego" value="<%=e.getTipoImpiego()%>" name="tipoImpiego" type="text" class="validate">
                                <label for="tipoImpiego">Inserisci il tipo di impiego svolto</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12 m3">
                                <input placeholder="Nome Datore" id="nomeDatore" name="nomeDatore" value="<%=e.getDatore()%>" type="text" class="validate">
                                <label for="nomeDatore">Inserisci il nome del datore</label>
                            </div>
                            <div class="input-field col s12 m3">
                                <input placeholder="Contatto" id="contattoAzienda" name="contattoAzienda" value="<%=e.getContatto()%>" type="text" class="validate">
                                <label for="contattoAzienda">Inserisci il contatto del referente</label>
                            </div>
                            <div class="input-field col s12 m6">
                                <input placeholder="Mansioni" id="mansioni" name="mansioni" type="text" value="<%=e.getMansioniPrincipali()%>" class="validate">
                                <label for="mansioni">Inserisci le mansioni svolte</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12 m6">
                                <input placeholder=" inizio" type="text" id="data_in_e" name="data_in_e" value="<%=e.getDataInizio()%>" class="datepicker">
                                <label for="data_in_e">Data di inizio esperienza:</label>
                            </div>
                            <div class="input-field col s12 m6">
                                <input placeholder="Data fine" type="text" id="data_fin_e" name="data_fin_e" value="<%=e.getDataFine()%>" class="datepicker">
                                <label for="data_fin_e">Data di inizio esperienza:</label>
                            </div>
                        </div>
                    </div>
                    <%
                        }
                    %>
                </div>
                <div class="Lingua" id="lingua">
                    <div class="input-field col s11 m11">
                        <h5>Lingua</h5>
                    </div>
                    <div class="input-field col s1 m1">
                        <a class="btn-floating btn-small waves-effect waves-light default-color" onclick="addLingua()"><i class="material-icons">add</i></a>
                    </div>
                    <div class="row">
                        <div class="input-field col s12 m6">
                            <input placeholder="Nome lingua" id="nomeLingua" name="nomeLingua" type="text" class="validate">
                            <label for="nomeLingua">Inserisci il nome della lingua</label>
                        </div>
                        <div class="input-field col s12 m6">
                            <input placeholder="Livello Lingua" id="livelloLingua" name="livelloLingua" type="text" class="validate">
                            <label for="livelloLingua">Inserisci il livello della lingua</label>
                        </div>
                    </div>
                </div>
                <div class="Istruzione" id="istruzione">
                    <div class="input-field col s11 m11">
                        <h5>Istruzione</h5>
                    </div>
                    <div class="input-field col s1 m1">
                        <a class="btn-floating btn-small waves-effect waves-light default-color" onclick="addIstruzione()"><i class="material-icons">add</i></a>
                    </div>
                    <div class="row">
                        <div class="input-field col s12 m4">
                            <input placeholder="Nome Istituto" id="nomeIstituto" name="nomeIstituto" type="text" class="validate">
                            <label for="nomeIstituto">Inserisci il nome del istituto</label>
                        </div><div class="input-field col s12 m4">
                        <input placeholder="Tipo Istruttore" id="tipoIstruzione" name="tipoIstruzione" type="text" class="validate">
                        <label for="tipoIstruzione">Inserisci il tipo di istruzione</label>
                    </div><div class="input-field col s12 m4">
                        <input placeholder="Nome Qualifica" id="nomeQualifica" name="nomeQualifica" type="text" class="validate">
                        <label for="nomeQualifica">Inserisci il nome della qualifica</label>
                    </div>
                    </div>
                    <div class="row">
                        <div class="input-field col s12 m6">
                            <input placeholder=" inizio" type="text" id="data_in_i" name="data_in_i"  class="datepicker">
                            <label for="data_in_i">Data di inizio Istruzione:</label>
                        </div>
                        <div class="input-field col s12 m6">
                            <input placeholder="Data fine" type="text" id="data_fin_i" name="data_fin_i"  class="datepicker">
                            <label for="data_fin_i">Data di inizio Istruzione:</label>
                        </div>
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
                    <li><a class="white-text" href="register.html">Registrazione</a></li>
                    <li><a class="white-text" href="accesso.html">Login</a></li>
                    <li><a class="white-text" href="index.html">Home</a></li>
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