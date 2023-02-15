<%@ page import="java.util.List" %>
<%@ page import="storage.entity.*" %>
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
    Azienda a=(Azienda) session.getAttribute("utente");
    if (a==null){%>
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
                    <a href="#user" class="mod-a"><img height="100" width="100" class="circle" onerror="this.onerror=null; this.src='resource/img.png'" src="<%=a.getFoto()%>"></a>
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
        <%
            session = request.getSession(false);
            Azienda a=(Azienda) session.getAttribute("utente");
        %>
        <div class="row">
            <div class="input-field col s12 m12">
                <h5>Modifica dati:</h5>
            </div>
        </div>
        <form action="ModificaAzienda">
            <%
                if(a!=null){
            %>
            <div class="row">
                <div class="input-field col s12 m3">
                    <input placeholder="Nome" id="nomeAzienda" name="nomeAzienda" value="<%=a.getNome()%>" type="text" class="validate">
                    <label for="nomeAzienda">Inserisci il nome</label>
                </div>
                <div class="input-field col s12 m6">
                    <input placeholder="P.Iva" id="piva" name="piva" type="text" value="<%=a.getPartitaIVA()%>" class="validate">
                    <label for="piva">Inserisci la partita iva</label>
                </div>
                <div class="input-field col s12 m3">
                    <input placeholder="Telefono" id="telefonoAzienda" name="telefonoAzienda" value="<%=a.getTelefono()%>" type="text" class="validate">
                    <label for="telefonoAzienda">Inserisci il telefono</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12 m3">
                    <input placeholder="Ragione Sociale" type="text" id="ragioneSociale" value="<%=a.getRagioneSociale()%>" name="ragioneSociale"  class="validate">
                    <label for="ragioneSociale">Ragione sociale</label>
                </div>
                <div class="input-field col s12 m3">
                    <input placeholder="Sito web" id="sitoWeb" name="sitoWeb" type="text" value="<%=a.getLink()%>" class="validate">
                    <label for="sitoWeb">Inserisci il link del sito web</label>
                </div>
                <div class="input-field col s12 m3">
                    <input placeholder="Regione" id="regioneAzienda" name="regioneAzienda" value="<%=a.getRegione()%>" type="text" class="validate">
                    <label for="regioneAzienda">Inserisci la regione</label>
                </div>
                <div class="input-field col s12 m3">
                    <input placeholder="Via" id="viaAzienda" name="viaAzienda" type="text" value="<%=a.getVia()%>" class="validate">
                    <label for="viaAzienda">Inserisci la regione</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12 m3">
                    <input placeholder="Provincia" type="text" id="provinciaAzienda" value="<%=a.getProvincia()%>" name="provinciaAzienda"  class="validate">
                    <label for="provinciaAzienda">Inserici la provincia</label>
                </div>
                <div class="input-field col s12 m3">
                    <input placeholder="Citta" type="text" id="cittaAzienda" name="cittaAzienda" value="<%=a.getCitta()%>" class="validate">
                    <label for="cittaAzienda">Inserici la citta</label>
                </div>

                <div class="input-field col s12 m3">
                    <input placeholder="CAP" id="capAzienda" name="capAzienda" value="<%=a.getCap()%>" type="text" class="validate">
                    <label for="capAzienda">Inserisci il CAP</label>
                </div>
                <div class="input-field col s12 m3">
                    <input placeholder="e-mail" id="emailAzienda" name="emailAzienda" value="<%=a.getMail()%>" type="email" class="validate">
                    <label for="emailAzienda">Inserisci la Mail:</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12 m5">
                    <input placeholder="Area di interesse" id="areaInteresse" name="areaInteresse" value="<%=a.getAreaInteresse()%>" type="text" class="validate">
                    <label for="areaInteresse">Inserisci il l'area di competenza</label>
                </div>
                <div class="input-field col s12 m5">
                    <input placeholder="Settore di competenza" id="settoriCompetenza" name="settoriCompetenza" value="<%=String.join(",",a.getSettoriCompetenza())%>" type="text" class="validate">
                    <label for="settoriCompetenza">Inserisci i settori di competenza</label>
                </div>
                <div class="input-field col s12 m2">
                    <input placeholder="Numero" id="dipendenti" name="dipendenti" value="<%=a.getNumeroDipendenti()%>" type="text" class="validate">
                    <label for="dipendenti">Numero di dipendenti</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12 m12">
                    <div class="file-field input-field">
                        <div class="btn">
                            <span>File</span>
                            <input type="file">
                        </div>
                        <div class="file-path-wrapper">
                            <input class="file-path validate" type="text" id="logo" value="<%=a.getFoto()%>" name="logo" placeholder="Logo azienda">
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="right">
                    <button class="btn waves-effect waves-light" type="submit" name="action">Modifica<i class="material-icons right">send</i></button>
                </div>
            </div>
            <%
                }
            %>
        </form>
        <div class="row">
            <div class="input-field col s12 m12">
                <h5>Modifica password</h5>
            </div>
        </div>
        <form action="ModificaPassword">
            <%
                if(a!= null){
            %>
            <div class="row">
                <div class="input-field col s12 m12">
                    <input placeholder="Password Attuale" id="oldPassword" name="oldPassword" type="password" class="validate" required>
                    <label for="oldPassword">Inserisci la password attuale:</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12 m6">
                    <input placeholder="Nuova password" id="newPassword" name="newPassword" type="password" class="validate" required>
                    <label for="newPassword">Inserisci la nuova password:</label>
                </div>
                <div class="input-field col s12 m6">
                    <input placeholder="Conferma password" id="newPasswordConferma" name="newPasswordConferma" type="password" class="validate" required>
                    <label for="newPasswordConferma">Conferma la nuova password:</label>
                </div>
            </div>
            <div class="row">
                <div class="right">
                    <button class="btn waves-effect waves-light" type="submit" name="action">Modifica<i class="material-icons right">send</i></button>
                </div>
            </div>
            <%
                }
            %>
        </form>

        <div class="sedi">
            <div class="input-field col s12 m12">
                <h5>Modifica sedi</h5>
            </div>
            <%
                List<Sede> list=null;
                if(a!=null)
                    list=a.getSedi();
                if(list!=null)
                    for(Sede s: list){
            %>
            <div class="row">
                <div class="row">
                    <div class="input-field col s12 m12">
                        <h6>Modifica dati sede:</h6>
                    </div>
                </div>
                <form action="ModificaSede">
                    <div class="row">
                        <input type="hidden" value="<%=s.getId()%>" id="id_Sede" name="id_Sede">
                        <div class="row">
                            <div class="input-field col s12 m3">
                                <input placeholder="Regione Sede" type="text" id="regione_Sede" name="regione_Sede" value="<%=s.getRegione()%>" class="validate">
                                <label for="regione_Sede">Inserici la regione</label>
                            </div>
                            <div class="input-field col s12 m3">
                                <input placeholder="Provincia Sede" type="text" id="provincia_Sede" value="<%=s.getProvincia()%>" name="provincia_Sede"  class="validate">
                                <label for="provincia_Sede">Inserici la provincia</label>
                            </div>
                            <div class="input-field col s12 m3">
                                <input placeholder="Citta Sede" type="text" id="citta_Sede" name="citta_Sede"  value="<%=s.getCitta()%>" class="validate">
                                <label for="citta_Sede">Inserici la citta</label>
                            </div>
                            <div class="input-field col s12 m3">
                                <input placeholder="CAP Sede" id="cap_Sede" name="cap_Sede" type="text" value="<%=s.getCap()%>" class="validate">
                                <label for="cap_Sede">Inserisci il CAP</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12 m4">
                                <input placeholder="Telefono Sede" id="telefono_Sede" name="telefono_Sede" value="<%=s.getTelefono()%>" type="text" class="validate">
                                <label for="telefono_Sede">Inserisci il telefono</label>
                            </div>
                            <div class="input-field col s12 m4">
                                <input placeholder="Via Sede" type="text" id="via_Sede" name="via_Sede" value="<%=s.getVia()%>" class="validate">
                                <label for="via_Sede">Inserici la via della sede</label>
                            </div>
                            <div class="input-field col s12 m4">
                                <input placeholder="e-mail" id="email_Sede" name="email_Sede" type="email" value="<%=s.getMail()%>" class="validate">
                                <label for="email_Sede">Inserisci la Mail:</label>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="right">
                            <button class="btn waves-effect waves-light" type="submit" name="action">Modifica<i class="material-icons right">send</i></button>
                        </div>
                    </div>
                </form>
            </div>
            <hr class="default-color">
            <%
                    }
            %>
            <div class="row">
                <div class="row">
                    <div class="input-field col s12 m12">
                        <h5>Aggiungi sede sede:</h5>
                    </div>
                </div>
                <form action="AggiungiSede">
                    <div class="row">
                        <div class="row">
                            <div class="input-field col s12 m3">
                                <input placeholder="Regione Sede" type="text" id="regioneSede" name="regioneSede" class="validate">
                                <label for="regioneSede">Inserici la regione</label>
                            </div>
                            <div class="input-field col s12 m3">
                                <input placeholder="Provincia Sede" type="text" id="provinciaSede"  name="provinciaSede"  class="validate">
                                <label for="provinciaSede">Inserici la provincia</label>
                            </div>
                            <div class="input-field col s12 m3">
                                <input placeholder="Citta Sede" type="text" id="cittaSede" name="cittaSede"   class="validate">
                                <label for="cittaSede">Inserici la citta</label>
                            </div>
                            <div class="input-field col s12 m3">
                                <input placeholder="CAP Sede" id="capSede" name="capSede" type="text" class="validate">
                                <label for="capSede">Inserisci il CAP</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12 m4">
                                <input placeholder="Telefono Sede" id="telefonoSede" name="telefonoSede"  type="text" class="validate">
                                <label for="telefonoSede">Inserisci il telefono</label>
                            </div>
                            <div class="input-field col s12 m4">
                                <input placeholder="Via Sede" type="text" id="viaSede" name="viaSede"  class="validate">
                                <label for="viaSede">Inserici la via della sede</label>
                            </div>
                            <div class="input-field col s12 m4">
                                <input placeholder="e-mail" id="emailSede" name="emailSede" type="email" class="validate">
                                <label for="emailSede">Inserisci la Mail:</label>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="right">
                            <button class="btn waves-effect waves-light" type="submit" name="action">Salva<i class="material-icons right">send</i></button>
                        </div>
                    </div>
                </form>
            </div>
            <hr class="default-color">
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