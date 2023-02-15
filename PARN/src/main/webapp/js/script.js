let counterEsperienza = 1;
let counterLingua = 1;
let counterIstruzione = 1;
function addEsperienza(){
    counterEsperienza++;
    let firstRow = document.createElement('div');
    firstRow.setAttribute('class', 'row');
    firstRow.innerHTML = "                <div class=\"input-field col s12 m4\">\n" +
        "                                    <input placeholder=\"Nome azienda\" id=\"nomeAziendaEsperienza"+ counterEsperienza +"\" name=\"nomeAziendaEsperienza"+ counterEsperienza +"\" type=\"text\" class=\"validate\">\n" +
        "                                    <label for=\"nomeAziendaEsperienza"+ counterEsperienza +"\">Inserisci il nome del azienda</label>\n" +
        "                                </div>\n" +
        "                                <div class=\"input-field col s12 m4\">\n" +
        "                                    <input placeholder=\"Tipo azienda\" id=\"tipoAzienda" + counterEsperienza + "\" name=\"tipoAzienda"+ counterEsperienza +"\" type=\"text\" class=\"validate\">\n" +
        "                                    <label for=\"tipoAzienda"+ counterEsperienza +"\">Inserisci il tipo di azienda</label>\n" +
        "                                </div>\n" +
        "                                <div class=\"input-field col s12 m4\">\n" +
        "                                    <input placeholder=\"Tipo impiego\" id=\"tipoImpiego" + counterEsperienza + "\" name=\"tipoImpiego"+ counterEsperienza +"\" type=\"text\" class=\"validate\">\n" +
        "                                    <label for=\"tipoImpiego"+ counterEsperienza +"\">Inserisci il tipo di impiego svolto</label>\n" +
        "                                </div>\n";
    document.getElementById("esperienzaLavorativa").appendChild(firstRow);
    let secondRow = document.createElement('div');
    secondRow.setAttribute('class', 'row');
    secondRow.innerHTML = "              <div class=\"input-field col s12 m3\">\n" +
        "                                    <input placeholder=\"Nome Datore\" id=\"nomeDatore" + counterEsperienza + "\" name=\"nomeDatore"+ counterEsperienza +"\" type=\"text\" class=\"validate\">\n" +
        "                                    <label for=\"nomeDatore"+ counterEsperienza +"\">Inserisci il nome del datore</label>\n" +
        "                                </div>\n" +
        "                                <div class=\"input-field col s12 m3\">\n" +
        "                                    <input placeholder=\"Contatto\" id=\"contattoAzienda" + counterEsperienza + "\" name=\"contattoAzienda"+ counterEsperienza +"\" type=\"text\" class=\"validate\">\n" +
        "                                    <label for=\"contattoAzienda"+ counterEsperienza +"\">Inserisci il contatto del referente</label>\n" +
        "                                </div>\n" +
        "                                <div class=\"input-field col s12 m6\">\n" +
        "                                    <input placeholder=\"Mansioni\" id=\"mansioni" + counterEsperienza + "\" name=\"mansioni"+ counterEsperienza +"\" type=\"text\" class=\"validate\">\n" +
        "                                    <label for=\"mansioni"+ counterEsperienza +"\">Inserisci le mansioni svolte</label>\n" +
        "                                </div>\n";
    document.getElementById("esperienzaLavorativa").appendChild(secondRow);
    let thirdRow = document.createElement('div');
    thirdRow.setAttribute('class', 'row');
    thirdRow.innerHTML = "               <div class=\"input-field col s12 m6\">\n" +
        "                                    <input placeholder=\" inizio\" type=\"text\" id=\"data_in_e" + counterEsperienza + "\" name=\"data_in_e"+ counterEsperienza +"\"  class=\"datepicker\">\n" +
        "                                    <label for=\"data_in_e"+ counterEsperienza +"\">Data di inizio esperienza:</label> \n" +
        "                                </div>\n" +
        "                                <div class=\"input-field col s12 m6\">\n" +
        "                                    <input placeholder=\"Data fine\" type=\"text\" id=\"data_fin_e" + counterEsperienza + "\" name=\"data_fin_e"+ counterEsperienza +"\"  class=\"datepicker\">\n" +
        "                                    <label for=\"data_fin_e"+ counterEsperienza +"\">Data di inizio esperienza:</label> \n" +
        "                                </div>\n";
    document.getElementById("esperienzaLavorativa").appendChild(thirdRow);
}

function addLingua(){
    counterLingua++;
    let firstRow = document.createElement('div');
    firstRow.setAttribute('class', 'row');
    firstRow.innerHTML = "                <div class=\"input-field col s12 m6\">\n" +
        "                                    <input placeholder=\"Nome lingua\" id=\"nomeLingua" + counterLingua + "\" name=\"nomeLingua" + counterLingua + "\" type=\"text\" class=\"validate\">\n" +
        "                                    <label for=\"nomeLingua" + counterLingua + "\">Inserisci il nome della lingua</label>\n" +
        "                                </div>\n" +
        "                                <div class=\"input-field col s12 m6\">\n" +
        "                                    <select name=\"livelloLingua" + counterLingua + "\" id=\"libelloLingua" + counterLingua + "\">" +
        "                                       <option value=\"\" disabled>Scegli il livello</option>" +
        "                                       <option value=\"Madrelingua\">Madrelingua</option>" +
        "                                       <option value=\"Avanzato\">Avanzato</option>" +
        "                                       <option value=\"Medio\">Mediio</option>" +
        "                                       <option value=\"Sufficiente\">Sufficiente</option>" +
        "                                       <option value=\"Insufficiente\">Insufficiente</option>" +
        "                                    </select>" +
        "                                    <input placeholder=\"Livello Lingua\" id=\"livelloLingua" + counterLingua + "\" name=\"livelloLingua" + counterLingua + "\" type=\"text\" class=\"validate\">\n" +
        "                                    <label for=\"livelloLingua" + counterLingua + "\">Inserisci il livello della lingua</label>\n" +
        "                                </div> \n";

    document.getElementById("lingua").appendChild(firstRow);
}

function addIstruzione(){
    counterIstruzione++;
    let firstRow = document.createElement('div');
    firstRow.setAttribute('class', 'row');
    firstRow.innerHTML = "               <div class=\"input-field col s12 m4\">\n" +
        "                                    <input placeholder=\"Nome Istituto\" id=\"nomeIstituto" + counterIstruzione + "\" name=\"nomeIstituto" + counterIstruzione + "\" type=\"text\" class=\"validate\">\n" +
        "                                    <label for=\"nomeIstituto" + counterIstruzione + "\">Inserisci il nome del istituto</label>\n" +
        "                                </div><div class=\"input-field col s12 m4\">\n" +
        "                                    <input placeholder=\"Tipo Istruttore\" id=\"tipoIstruzione" + counterIstruzione + "\" name=\"tipoIstruzione" + counterIstruzione + "\" type=\"text\" class=\"validate\">\n" +
        "                                    <label for=\"tipoIstruzione" + counterIstruzione + "\">Inserisci il tipo di istruzione</label>\n" +
        "                                </div><div class=\"input-field col s12 m4\">\n" +
        "                                    <input placeholder=\"Nome Qualifica\" id=\"nomeQualifica" + counterIstruzione + "\" name=\"nomeQualifica" + counterIstruzione + "\" type=\"text\" class=\"validate\">\n" +
        "                                    <label for=\"nomeQualifica" + counterIstruzione + "\">Inserisci il nome della qualifica</label>\n" +
        "                                </div>\n";
    document.getElementById("istruzione").appendChild(firstRow);
    let secondRow = document.createElement('div');
    secondRow.setAttribute('class', 'row');
    secondRow.innerHTML = "              <div class=\"input-field col s12 m6\">\n" +
        "                                    <input placeholder=\" inizio\" type=\"text\" id=\"data_in_i" + counterIstruzione + "\" name=\"data_in_i" + counterIstruzione + "\"  class=\"datepicker\">\n" +
        "                                    <label for=\"data_in_i" + counterIstruzione + "\">Data di inizio Istruzione:</label> \n" +
        "                                </div>\n" +
        "                                <div class=\"input-field col s12 m6\">\n" +
        "                                    <input placeholder=\"Data fine\" type=\"text\" id=\"data_fin_i" + counterIstruzione + "\" name=\"data_fin_i" + counterIstruzione + "\"  class=\"datepicker\">\n" +
        "                                    <label for=\"data_fin_i" + counterIstruzione + "\">Data di inizio Istruzione:</label> \n" +
        "                                </div>\n";
    document.getElementById("istruzione").appendChild(secondRow);
}