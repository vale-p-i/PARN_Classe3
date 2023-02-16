<%@ page import="matching.service.MatchingService" %>
<%@ page import="matching.service.MatchingServiceInterface" %>
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
  List<Candidatura> list= p.getCandidature();
%>

<div class="container">
  <div class="section-main min">
      <table>
        <thead>
        <tr>
          <th>Nome Azienda</th>
          <th>Ruolo</th>
          <th>Sede</th>
          <th>Descrizione</th>
          <th>Rimuovi</th>
        </tr>
        </thead>
        <tbody>
        <%
          if(list!=null){
            if(list.isEmpty()){
        %>
        <tr><td colspan="5">Nessuna candidatura inviata</td> </tr>
        <%
        }else{
          for(Candidatura c: list){
        %>
          <tr>
            <td><%=c.getAnnuncio().getAzienda().getNome()%></td>
            <td><%=c.getAnnuncio().getRuolo()%></td>
            <td><%=c.getAnnuncio().getSede()%></td>
            <td><%=c.getAnnuncio().getDescrizione()%></td>
            <td><a href="CancellaCandidatura?id_persona=<%=c.getPersona().getId()%>&id_annuncio=<%=c.getAnnuncio().getId()%>"><i class="material-icons red-text">cancel</i></a></td>
          </tr>
        <%
          }
        }
        }
          else{
        %>
          <tr><td colspan="5">Nessuna candidatura inviata</td> </tr>
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