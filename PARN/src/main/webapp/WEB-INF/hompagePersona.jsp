<%@ page import="storage.entity.Annuncio" %>
<%@ page import="storage.entity.Azienda" %>
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
        <li><a href="" class="waves-effect waves-light btn-large white default-color-text">AreaPersonale</a></li>
        <li><a href="" class="waves-effect waves-light btn-large white default-color-text">Curriculum</a></li>
        <li><a href="#!" class="waves-effect waves-light btn-large white default-color-text">Candidature Inviate</a></li>
        <li><a href="#!" class="waves-effect waves-light btn-large red white-text">Logout</a></li>
    </ul>
    <a href="#" data-target="slide-out" class="sidenav-trigger show-on-large"><i class="material-icons white-text">menu</i></a>
    <a id="logo-container" href="index.html" class="brand-logo"><img src="resource/logo.png" width="250" height="80" class="responsive-img"></a>
    </div>
</nav>

<%
    Annuncio acc=new Annuncio(1,new Azienda(2,"intel","mail@mail.it","password","Calabria","Calabia",null,"84128","3245649827","Calabira","delle calabire","11111111", "bella","linko","area",5,null,null,null),true,null,5,"bella desc nfjdsbfjksdlhlflncraaytvuabyvruaesbukrfvhvduivhdfjadhdv hesiuthuiahvueuivt hiesagtvregehufhcsdhfnhtieuvhtihpuah",null,null,null,null,"ruolo",null);
%>

<div class="container">
    <div class="section-main">
        <div class="row">
            <div class="row">
                <div class="col s6 m6">
                    <h4 class="header">Annunci per te</h4>
                </div>
                <div class="input-field col s6 m6">
                    <input placeholder="Ricerca" id="keyword" type="text" class="validate">
                </div>
            </div>
            <div class="col s12 m12">
            <div class="card horizontal min">
                <div class="card-image mrgin">
                    <img width="100" class="activator" height="100" src="resource/ibm.jpeg">
                </div>
                <div class="card-content prova">
                    <span class="card-title activator grey-text text-darken-4">   <%=acc.getAzienda().getNome()%> <i class="material-icons right">more_vert</i></span>
                    <p> <%=acc.getDescrizione()%></p>
                </div>
                <div class="card-reveal">
                    <span class="card-title grey-text text-darken-4"><%=acc.getAzienda().getNome()%><i class="material-icons right">close</i></span>
                    <p>Ruolo:<%=acc.getRuolo()%></p>
                    <p>Sede:<%=acc.getSede()%></p>
                    <p>Preferenze:<%=acc.getPreferenze()%></p>
                    <form >
                        <input type="hidden" value="idpersona">
                        <input type="hidden" value="idannnuncio">
                        <button class="btn waves-effect waves-light default-color" type="submit" name="action">Candidati
                            <i class="material-icons right">send</i>
                        </button>
                    </form>
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