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
        <%
        session = request.getSession(false);
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
    <%

    %>
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

<div class="container">
    <div class="section-main ">

        <div class="row">
            <div class="input-field col s12 m12">
                <h5>Modifica dati</h5>
            </div>
        </div>
        <form action="ModificaPersona">
            <%
                if(p!=null){
            %>
            <div class="row">
                <div class="input-field col s12 m4">
                    <input placeholder="Nome" id="nomePersona" value="<%=p.getNome()%>" name="nomePersona" type="text" class="validate" required readonly>
                    <label for="nomePersona">Inserisci il nome</label>
                </div>
                <div class="input-field col s12 m4">
                    <input placeholder="Cognome" id="cognome" name="cognome" value="<%=p.getCognome()%>" type="text" class="validate" required readonly>
                    <label for="cognome">Inserisci il cognome</label>
                </div>
                <div class="input-field col s12 m4">
                    <input placeholder="Telefono" id="telefonoPersona" name="telefonoPersona" value="<%=p.getTelefono()%>" type="text" class="validate" required>
                    <label for="telefonoPersona">Inserisci il telefono</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12 m3">
                    <input placeholder="Codice fiscale" type="text" id="codiceFiscale" name="codiceFiscale" value="<%=p.getCodiceFiscale()%>"  class="validate" required readonly>
                    <label for="codiceFiscale">Codice fiscale</label>
                </div>
                <div class="input-field col s12 m3">
                    <input placeholder="Data" type="date" id="data_n" name="data_n"  value="<%=p.getDataDiNascita()%>" class="validate" required readonly>
                    <label for="data_n">Data di nascita:</label>
                </div>
                <div class="input-field col s12 m3">
                    <input placeholder="Regione" id="regionePersona" name="regionePersona" type="text" value="<%=p.getRegione()%>" class="validate" required>
                    <label for="regionePersona">Inserisci la regione</label>
                </div>
                <div class="input-field col s12 m3">
                    <input placeholder="Provincia" type="text" id="provinciaPersona" name="provinciaPersona"  value="<%=p.getProvincia()%>" class="validate" required>
                    <label for="provinciaPersona">Inserici la provincia</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12 m5">
                    <input placeholder="Via" type="text" id="viaPersona" name="viaPersona"  value="<%=p.getVia()%>" class="validate" required>
                    <label for="viaPersona">Inserici la via</label>
                </div>
                <div class="input-field col s12 m5">
                    <input placeholder="Citta" type="text" id="cittaPersona" name="cittaPersona"  value="<%=p.getCitta()%>" class="validate" required>
                    <label for="cittaPersona">Inserici la citta</label>
                </div>
                <div class="input-field col s12 m2">
                    <input placeholder="CAP" id="capPersona" name="capPersona" type="text" value="<%=p.getCap()%>" class="validate" required>
                    <label for="capPersona">Inserisci il CAP</label>
                </div>
            </div>
            <div class="row">
                <div class="input-field col s12 m4">
                    <input placeholder="Posizione Desiderata" type="text" id="posizione" name="posizione" value="<%=p.getPosizioneDesiderata()%>" class="validate" required>
                    <label for="posizione">Inserici la posizione che si vuole ricoprire</label>
                </div>
                <div class="input-field col s12 m4">
                    <input placeholder="Filtro annunci" type="text" id="filtroMacroarea" name="filtroMacroarea"  value="<%=p.getFiltroMacroarea()%>" class="validate" required>
                    <label for="filtroMacroarea">Inserici preferenza</label>
                </div>
                <div class="input-field col s12 m4">
                    <input placeholder="Mail" id="mailPersona"  value="<%=p.getMail()%>" name="mailPersona" type="email" class="validate" required>
                    <label for="mailPersona">Inserisci la Mail:</label>
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
                            <input class="file-path validate" type="text" id="fotoPersona" name="fotoPersona" value="<%=p.getFoto()%>" placeholder="Foto personale">
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
        <form action="ModificaPasswordUtente">
            <%
                if(p!= null){
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