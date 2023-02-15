document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.datepicker');
    var instances = M.Datepicker.init(elems, {
        format:'yyyy-mm-dd',
        showClearBtn:true,
        conteiner:'html',
        maxDate:new Date(),
        i18n:{
            clear:'Elimina',
            done:'Si',
            today:'Oggi',
            months:['Gennaio','Febbraio','Marzo','Aprile','Maggio','Giugno','Luglio','Agosto','Settembre','Ottobre','Novembre','Dicembre'],
            monthsShort:['Gen','Feb','Mar','Apr','Mag','Giu','Lug','Ago','Set','Ott','Nov','Dic'],
            weekdaysFull:['Domenica','Lunedi','Martedi','Mercoldi','Giovedi','Venerdi','Sabato'],
            weekdaysShort:['Dom','Lun','Mar','Mer','Gio','Ven','Sab'],
            weekdaysAbbrev:['D','L','M','M','G','V','S']
        }
    });
});

// Or with jQuery

$(document).ready(function(){
    $('.datepicker').datepicker();
});