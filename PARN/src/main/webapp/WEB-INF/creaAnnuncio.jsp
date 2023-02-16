<%--
  Created by IntelliJ IDEA.
  User: danielerusso
  Date: 14/02/23
  Time: 18:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="storage.entity.Annuncio" %>
<%@ page import="storage.entity.Azienda" %>
<%@ page import="java.util.List" %>
<%@ page import="storage.entity.Sede" %>
<%@ page import="storage.entity.Utente" %>
<%@ page import="java.time.LocalDate" %>
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
    <div class="section-main min">
        <div class="row">
            <h5>Crea Annuncio</h5>
        </div>
        <%
            session=request.getSession();
            Azienda az= (Azienda) session.getAttribute("utente");
            List<Sede> list=null;
            if (az!=null)
                list=az.getSedi();
        %>
        <form action="CreaAnnuncio">
            <div class="row">
                <div class="input-field col s6 m9">
                    <input placeholder="Roulo" type="text" id="ruolo" name="ruolo"  class="validate">
                    <label for="ruolo">Ruolo</label>
                </div>
                <div class="input-field col s6 m3">
                    <!-- Dropdown Structure -->

                    <select name="sedelist" id="sedelist">
                        <option value="" disabled selected>Scegli la sede</option>
                        <%
                            if(list!=null){
                                if (!list.isEmpty())
                                    for (Sede s:list){
                                    %>
                            <option value="<%=s.getId()%>"><%=s.getCitta()%></option>
                        <%
                                }
                            else{
                                System.out.println("vuota");
                                }
                            }else{
                                System.out.println("null");
                                }
                        %>
                    </select>
                    <label>Scegli la sede</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12 m12">
                    <textarea id="descrizione" name="descrizione" class="materialize-textarea"></textarea>
                    <label for="descrizione">Descrizione</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12 m6">
                    <input placeholder="Data fine" type="text" id="data_scad" name="data_scad" min="<%=LocalDate.now()%>" class="datepicker">
                    <label for="data_scad">Data di scadenza:</label>
                </div>
                <div class="input-field col s12 m6">
                    <textarea id="keywords" name="keywords" class="materialize-textarea"></textarea>
                    <label for="keywords">Keyword</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12 m6">
                    <input placeholder="Numero necessario" type="text" id="numeroDipendenti" name="numeroDipendenti"  class="validate">
                    <label for="numeroDipendenti">Numero di dipendenti necessari</label>
                </div>
                <div class="input-field col s12 m6">
                    <textarea id="preferenze" name="preferenze" class="materialize-textarea"></textarea>
                    <label for="preferenze">Preferenze</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12 m12">
                    <textarea id="requisiti" name="requisiti" class="materialize-textarea"></textarea>
                    <label for="requisiti">Requisiti</label>
                </div>
            </div>
            <div class="row">
                <div class="right">
                    <button class="btn waves-effect waves-light" type="submit" name="action">Salva<i class="material-icons right">send</i></button>
                </div>
            </div>
        </form>
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
<script src="js/data.js"></script>

</body>
</html>