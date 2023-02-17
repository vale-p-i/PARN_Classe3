<%@ page import="storage.entity.Annuncio" %>
<%@ page import="storage.entity.Azienda" %>
<%@ page import="matching.service.MatchingService" %>
<%@ page import="matching.service.MatchingServiceInterface" %>
<%@ page import="java.util.List" %>
<%@ page import="storage.entity.Persona" %>
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
        Persona p=(Persona) session.getAttribute("utente");
        if (p==null){%>
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
                <a href="#user" class="mod-a"><img height="100" width="100" class="circle" onerror="this.onerror=null; this.src='resource/img.png'" src="<%=p.getFoto()%>"></a>
            </div>
        </li>
        <li><a href="RedirectServlet?redirect=homepagePersona" class="waves-effect waves-light btn-large white default-color-text">Homepage</a></li>
        <li><a href="RedirectServlet?redirect=areaPersonalePersona" class="waves-effect waves-light btn-large white default-color-text">AreaPersonale</a></li>
        <li><a href="RedirectServlet?redirect=areaCurriculum" class="waves-effect waves-light btn-large white default-color-text">Curriculum</a></li>
        <li><a href="RedirectServlet?redirect=areaCandidatureInviate" class="waves-effect waves-light btn-large white default-color-text">Candidature Inviate</a></li>
        <li><a href="Logout" class="waves-effect waves-light btn-large red white-text">Logout</a></li>
    </ul>
    <a href="#" data-target="slide-out" class="sidenav-trigger show-on-large"><i class="material-icons white-text">menu</i></a>
    <a id="logo-container" href="index.jsp" class="brand-logo"><img src="resource/logo.png" width="250" height="80" class="responsive-img"></a>
    </div>
</nav>

<%
    List<Annuncio> list= (List<Annuncio>) session.getAttribute("myList");
%>

<div class="container">
    <div class="section-main min">
        <div class="row">
            <div class="row">
                <div class="col s6 m6">
                    <h4 class="header">Annunci per te</h4>
                </div>
                <div class="input-field col s6 m6">
                    <input placeholder="Ricerca" id="keyword" type="text" class="validate">
                </div>
            </div>
        </div>
        <%
            if (list!=null){
                if(!list.isEmpty()){
                    for (Annuncio acc:list){
        %>
        <div class="row">
            <div class="col s12 m12">
            <div class="card horizontal min-annuncio">
                <div class="card-image mrgin">
                    <img width="100" class="activator" height="100" src="<%=acc.getAzienda().getFoto()%>" onerror="this.onerror=null; this.src='resource/ibm.jpeg'" >
                </div>
                <div class="card-content prova">
                    <span class="card-title activator grey-text text-darken-4">   <%=acc.getAzienda().getNome()%> <i class="material-icons right">more_vert</i></span>
                    <p> <%=acc.getDescrizione()%></p>
                </div>
                <div class="card-reveal">
                    <span class="card-title grey-text text-darken-4"><%=acc.getAzienda().getNome()%><i class="material-icons right">close</i></span>
                    <p>Ruolo:<%=acc.getRuolo()%></p>
                    <p>Sede:<%=acc.getSede()%></p>
                    <p>Preferenze:<%=String.join(",", acc.getPreferenze())%></p>
                    <form action="CreaCandidatura">
                        <input type="hidden" name="id_persona" id="id_persona" value="<%=p.getId()%>">
                        <input type="hidden" name="id_annuncio" id="id_annuncio" value="<%=acc.getId()%>">
                        <button class="btn waves-effect waves-light default-color" type="submit" name="action">Candidati
                            <i class="material-icons right">send</i>
                        </button>
                    </form>
                </div>
            </div>
        </div>
        </div>
<%
            }
        }
            else{
%>
        <div class="row">
            <div class="col s12 m12">
                Nessun annuncio per te
            </div>
        </div>
        <%
           }
            }else{
        %>
        <div class="row">
            <div class="col s12 m12">
                Nessun annuncio per te
            </div>
        </div>
        <%
            }
        %>
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