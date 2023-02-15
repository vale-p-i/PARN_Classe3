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
    <script>
        <%session=request.getSession();
        Utente u= (Utente) session.getAttribute("utente");
        if (u==null){%>
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
                    <a href="#user" class="mod-a"><img height="100" width="100" class="circle" src="resource/img.png"></a>
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
                        <div class="input-field col s11 m11">
                            <h6>Esperienza:</h6>
                        </div>
                        <div class="right">
                            <form action="EliminaEsperienza">
                            <a type="submit"><i class="material-icons tiny red-text">cancel</i>Elimina</a>
                            </form>
                        </div>
                    </div>
                    <div class="esperienza">
                        <form action="modificaEsperienza">
                            <div class="row">
                                <div class="input-field col s12 m4">
                                    <input placeholder="Nome azienda" id="nomeAziendaEsperienza" name="nomeAziendaEsperienza" value="<%=e.getNomeAzienda()%>" type="text" class="validate" disabled>
                                    <label for="nomeAziendaEsperienza">Inserisci il nome del azienda</label>
                                </div>
                                <div class="input-field col s12 m4">
                                    <input placeholder="Tipo azienda" id="tipoAzienda" name="tipoAzienda" value="<%=e.getTipoAzienda()%>" type="text" class="validate">
                                    <label for="tipoAzienda">Inserisci il tipo di azienda</label>
                                </div>
                                <div class="input-field col s12 m4">
                                    <input placeholder="Tipo impiego" id="tipoImpiego" value="<%=e.getTipoImpiego()%>" name="tipoImpiego" type="text" class="validate" disabled>
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
                    <div class="esperienza">
                        <div class="row">
                            <div class="input-field col s12 m12">
                                <h5>Inserisci nuova esperienza lavorativa</h5>
                            </div>
                        </div>
                        <form action="creaNuovaEsperienza">
                            <div class="row">
                                <div class="input-field col s12 m4">
                                    <input placeholder="Nome azienda" id="nomeAziendaEsperienza" name="nomeAziendaEsperienza"  type="text" class="validate" required>
                                    <label for="nomeAziendaEsperienza">Inserisci il nome del azienda</label>
                                </div>
                                <div class="input-field col s12 m4">
                                    <input placeholder="Tipo azienda" id="tipoAzienda" name="tipoAzienda"  type="text" class="validate" required>
                                    <label for="tipoAzienda">Inserisci il tipo di azienda</label>
                                </div>
                                <div class="input-field col s12 m4">
                                    <input placeholder="Tipo impiego" id="tipoImpiego"  name="tipoImpiego" type="text" class="validate" required>
                                    <label for="tipoImpiego">Inserisci il tipo di impiego svolto</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12 m3">
                                    <input placeholder="Nome Datore" id="nomeDatore" name="nomeDatore" type="text" class="validate" required>
                                    <label for="nomeDatore">Inserisci il nome del datore</label>
                                </div>
                                <div class="input-field col s12 m3">
                                    <input placeholder="Contatto" id="contattoAzienda" name="contattoAzienda"  type="text" class="validate" required>
                                    <label for="contattoAzienda">Inserisci il contatto del referente</label>
                                </div>
                                <div class="input-field col s12 m6">
                                    <input placeholder="Mansioni" id="mansioni" name="mansioni" type="text"  class="validate" required>
                                    <label for="mansioni">Inserisci le mansioni svolte</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12 m6">
                                    <input placeholder=" inizio" type="text" id="data_in_e" name="data_in_e" class="datepicker" required>
                                    <label for="data_in_e">Data di inizio esperienza:</label>
                                </div>
                                <div class="input-field col s12 m6">
                                    <input placeholder="Data fine" type="text" id="data_fin_e" name="data_fin_e"  class="datepicker">
                                    <label for="data_fin_e">Data di inizio esperienza:</label>
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
                            <div class="right">
                                <form action="EliminaLingua">
                                    <a type="submit"><i class="material-icons tiny red-text">cancel</i>Elimina</a>
                                </form>
                            </div>
                        </div>
                        <form action="modificaLingua">
                            <div class="row">
                                <div class="input-field col s12 m6">
                                    <input placeholder="Nome lingua" id="nomeLingua" name="nomeLingua" value="<%=l.getNome()%>" type="text" class="validate" disabled>
                                    <label for="nomeLingua">Inserisci il nome della lingua</label>
                                </div>
                                <div class="input-field col s12 m6">
                                    <select name="livelloLingua" id="livelloLingua">
                                        <option value="" disabled>Scegli il livello</option>
                                        <%
                                            for (String livello: Lingua.LIVELLI){
                                                if(l.getLivello().equals(livello)){
                                        %>
                                        <option selected value="<%=livello%>"><%=livello%></option>
                                        <%
                                                }
                                            else{
                                        %>
                                        <option value="<%=livello%>"><%=livello%></option>
                                        <%
                                            }
                                            }
                                        %>
                                    </select>
                                    <label for="livelloLingua">Inserisci il livello della lingua</label>
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
                    <div class="lingua">
                        <div class="row">
                            <div class="col s12 m12">
                                <h5>Inserisci nuova lingua</h5>
                            </div>
                        </div>
                        <form action="creaLingua">
                            <div class="row">
                                <div class="input-field col s12 m6">
                                    <input placeholder="Nome lingua" id="nomeLingua" name="nomeLingua"  type="text" class="validate" required>
                                    <label for="nomeLingua">Inserisci il nome della lingua</label>
                                </div>
                                <div class="input-field col s12 m6">

                                    <select name="livelloLingua" id="livelloLingua">
                                        <option value="" disabled>Scegli il livello</option>
                                        <%
                                            for (String livello: Lingua.LIVELLI){
                                        %>
                                            <option value="<%=livello%>"><%=livello%></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                    <label for="livelloLingua">Inserisci il livello della lingua</label>
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
                <div class="Istruzioni" id="istruzione">
                    <div class="row">
                        <div class="input-field col s12 m12">
                            <h5>Istruzioni:</h5>
                        </div>
                        <div class="right">
                            <form action="EliminaIstruzione">
                                <a type="submit"><i class="material-icons tiny red-text">cancel</i>Elimina</a>
                            </form>
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
                        <form action="modificaIstruzione" >
                            <div class="row">
                                <div class="input-field col s12 m4">
                                    <input placeholder="Nome Istituto" id="nomeIstituto" value="<%=i.getIstituto()%>" name="nomeIstituto" type="text" class="validate" disabled>
                                    <label for="nomeIstituto">Inserisci il nome del istituto</label>
                                </div>
                                <div class="input-field col s12 m4">
                                    <input placeholder="Tipo Istruttore" id="tipoIstruzione" value="<%=i.getTipo()%>" name="tipoIstruzione" type="text" class="validate" disabled>
                                    <label for="tipoIstruzione">Inserisci il tipo di istruzione</label>
                                </div>
                                <div class="input-field col s12 m4">
                                    <input placeholder="Nome Qualifica" id="nomeQualifica" value="<%=i.getQualifica()%>" name="nomeQualifica" type="text" class="validate">
                                    <label for="nomeQualifica">Inserisci il nome della qualifica</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12 m6">
                                    <input placeholder=" inizio" type="text" id="data_in_i" value="<%=i.getDataInizio()%>" name="data_in_i"  class="datepicker">
                                    <label for="data_in_i">Data di inizio Istruzione:</label>
                                </div>
                                <div class="input-field col s12 m6">
                                    <input placeholder="Data fine" type="text" id="data_fin_i" value="<%=i.getDataFine()%>" name="data_fin_i"  class="datepicker">
                                    <label for="data_fin_i">Data di inizio Istruzione:</label>
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
                <div class="istruzione">
                    <div class="row">
                        <div class="col s12 m12">
                            <h5>Inserisci nuova Istruzione<h4>
                        </div>
                    </div>
                    <form action="creaIstruzione" >
                        <div class="row">
                            <div class="input-field col s12 m4">
                                <input placeholder="Nome Istituto" id="nomeIstituto"  name="nomeIstituto" type="text" class="validate" required>
                                <label for="nomeIstituto">Inserisci il nome del istituto</label>
                            </div>
                            <div class="input-field col s12 m4">
                                <input placeholder="Tipo Istruttore" id="tipoIstruzione" name="tipoIstruzione" type="text" class="validate" required>
                                <label for="tipoIstruzione">Inserisci il tipo di istruzione</label>
                            </div>
                            <div class="input-field col s12 m4">
                                <input placeholder="Nome Qualifica" id="nomeQualifica"  name="nomeQualifica" type="text" class="validate" required>
                                <label for="nomeQualifica">Inserisci il nome della qualifica</label>
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-field col s12 m6">
                                <input placeholder=" inizio" type="text" id="data_in_i"  name="data_in_i"  class="datepicker" required>
                                <label for="data_in_i">Data di inizio Istruzione:</label>
                            </div>
                            <div class="input-field col s12 m6">
                                <input placeholder="Data fine" type="text" id="data_fin_i"  name="data_fin_i"  class="datepicker">
                                <label for="data_fin_i">Data di inizio Istruzione:</label>
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
<script src="js/data.js"></script>

</body>
</html>