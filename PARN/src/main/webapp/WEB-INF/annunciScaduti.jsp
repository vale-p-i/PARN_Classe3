<%@ page import="storage.entity.Annuncio" %>
<%@ page import="storage.entity.Azienda" %>
<%@ page import="org.checkerframework.checker.units.qual.A" %>
<%@ page import="java.util.List" %>
<%@ page import="annuncio.service.AnnuncioServiceInterface" %>
<%@ page import="annuncio.service.AnnuncioService" %>
<%@ page import="storage.entity.Utente" %>
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
            <li><a href="RedirectServlet?redirect=annunciScaduti" class="waves-effect waves-light btn-large white default-color-text">Scaduti</a></li>
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
            <div class="left">
                <a href="RedirectServlet?redirect=homepageAzienda"><i class="material-icons tiny">arrow_back</i> Torna indietro</a>
            </div>
        </div>
        <div class="row">
            <h5>Annunci Scaduti :</h5>
        </div>
        <table>
            <thead>
            <tr>
                <th>Ruolo </th>
                <th>Numero di persone</th>
                <th>Sede</th>
                <th>Descrizione</th>
                <th>Data di scadenza</th>
                <th>Link</th>
                <th>Archivia</th>
                <th>Elimina</th>
            </tr>
            </thead>
            <tbody>
            <%
                AnnuncioServiceInterface service=new AnnuncioService();
                if (session.getAttribute("annuncio")!=null)
                    session.removeAttribute("annuncio");
                List<Annuncio> list=service.getAnnunciByStato(a,Annuncio.SCADUTO);
                if(list!=null){
                    if(list.isEmpty()){
            %>
                    <tr><td colspan="6">Nessun annuncio scaduto</td> </tr>
            <%
                    }
                    for(Annuncio ann: list){
            %>
            <tr>
                <td><%=ann.getRuolo()%> </td>
                <td><%=ann.getNumeroPersone()%></td>
                <td><%=ann.getSede().toString()%></td>
                <td><%=ann.getDescrizione()%></td>
                <td><%=ann.getDataScadenza()%></td>
                <td><a href="RedirectAnnuncio?redirect=<%=ann.getId()%>"><i class="material-icons">info</i></a></td>
                <td><a href="ChiudiAnnuncio?id_annuncio=<%=ann.getId()%>"><i class="material-icons">archive</i></a></td>
                <td><a href="EliminaAnnuncio?id_annuncio=<%=ann.getId()%>"><i class="material-icons red-text">cancel</i></a></td>

            </tr>
            <%
                    }
                }
                else{
            %>
                <tr><td colspan="6">Nessun annuncio scaduto</td> </tr>
            <%
                }
            %>
            </tbody>
        </table>
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
                    <li><a class="white-text" href="accesso.jsp">Login</a></li>
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