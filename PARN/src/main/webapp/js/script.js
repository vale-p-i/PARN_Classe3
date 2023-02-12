function addEsperienza(){
    let fields = document.getElementById("esperienzaLavorativa").getElementsByClassName("row");
    for(let i = 0; i < 3; i++){
        let newElem = document.createElement('div');
        newElem.setAttribute('class', 'row');
        newElem.innerHTML = fields[i].innerHTML;
        document.getElementById("esperienzaLavorativa").appendChild(newElem);
    }
}

function addLingua(){
    let fields = document.getElementById("lingua").getElementsByClassName("row");
    let newElem = document.createElement('div');
    newElem.setAttribute('class', 'row');
    newElem.innerHTML = fields[0].innerHTML;
    document.getElementById("lingua").appendChild(newElem);
}

function addIstruzione(){
    let fields = document.getElementById("istruzione").getElementsByClassName("row");
    for(let i = 0; i < 2; i++){
        let newElem = document.createElement('div');
        newElem.setAttribute('class', 'row');
        newElem.innerHTML = fields[i].innerHTML;
        document.getElementById("istruzione").appendChild(newElem);
    }
}