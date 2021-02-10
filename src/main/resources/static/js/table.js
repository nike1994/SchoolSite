function randomString(length) {
   var result           = '';
   var characters       = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789';
   var charactersLength = characters.length;
   for ( var i = 0; i < length; i++ ) {
      result += characters.charAt(Math.floor(Math.random() * charactersLength));
   }
   return result;
}

function ajax(url,type,object,callback){
    console.log(object);
   var xmlhttp = new XMLHttpRequest();
    xmlhttp.open(type, url);
    xmlhttp.setRequestHeader("Content-Type", "application/json");
    xmlhttp.onreadystatechange = function () {
        try {
            if (xmlhttp.readyState === 4) {
                  console.log(xmlhttp.responseText);
                  callback(xmlhttp.responseText);
            }
        } catch (e) {
            alert(e.toString());
        }
    }
    xmlhttp.send(JSON.stringify(object));

}


var table;
var activeDropmenuLink;

$( document ).ready(function() {

  table = new Tabulator("#school_register", {
   	data:tableDB.tableData, // z serwera dane do tabeli
    layout:"fitDataFill", //fit columns to width of table (optional)
    reactiveData:true,
    dataTree:true,
    dataTreeStartExpanded:true,
    columns:tableDB.tableConfiguration // z serwera definicja kolumn
  });

  var columns=[];

  $( "#addColumn" ).click(function() {
    table.addColumn({title:"wpisz opis", field:"Ocena"+randomString(5), editableTitle:true, editor:"number", editorParams:{
      min:0,
      max:6,
      step:1,
      elementAttributes:{
        maxlength:"1",
      },
      mask:"9",

    },validator:"max:6"}, false, "ocena")
      .then(function(column){
          columns.push(column);
      })
      .catch(function(error){
          console.log(error);
      });
  });

  $( "#deleteColumn" ).click(function() {
      table.deleteColumn(columns[columns.length-1].getField());
      columns[columns.length-1].delete();
      columns.pop();
  });

  $('#saveColumn').click(function(){
    var data =[];
    var subject = $("div.dropdown-menu a.dropdown-item.active").attr("id"); //aktywny przedmiot
    console.log(columns.length);
    for(let i=0; i<columns.length;i++){
        console.log(columns[i].getField());
        var cells = columns[i].getCells();
        console.log(cells.length);
        for(let j=0; j<cells.length;j++){
            data.push({
                    grade:cells[j].getValue(),
                    pupil_id: cells[j].getRow().getData().id,
                    description: columns[i].getDefinition().title,
                    subject_id: subject
                    });
        }
    }
    console.log("tabela");
    console.log(data);

    var callback = function(responseText){
        if(responseText == "ok"){
            console.log("ok");
            //uniemożliwienie edycji zapisanych kolumn
            for(let i=0; i<columns.length;i++){
                columns[i].updateDefinition({editableTitle:false, editor:null, editorParams:null});
            }
            columns=[];
        }else{
            $('#modalBoxTitle').html("Błąd !!");
            $('#modalBoxBody').html("Wystąpił błąd podczas zapisu");
            $('#modalBox').modal('show');
        }
    };
    ajax("/register/saveGrade","POST",data,callback)
  });


   $('div.dropdown-menu .dropdown-item').click(function(){
        if(!$(this).hasClass('active')){
            activeDropmenuLink=this;

            var callback = function(responseText){
                if(responseText == "error"){

                    $('#modalBoxTitle').html("Błąd !!");
                    $('#modalBoxBody').html("Wystąpił błąd podczas wczytywania danych z bazy");
                    $('#modalBox').modal('show');

                }else{

                    $('div.dropdown-menu .dropdown-item.active').removeClass('active');
                    $(activeDropmenuLink).addClass('active');
                    $('.table-content>h2').html($(activeDropmenuLink).text());

                    var tableFromServer = JSON.parse(responseText);

                    columns=[];
                    table = new Tabulator("#school_register", {
                        data:tableFromServer.tableData,
                        layout:"fitDataFill",
                        reactiveData:true,
                        dataTree:true,
                        dataTreeStartExpanded:true,
                        columns:tableFromServer.tableConfiguration
                    });
                }
            }
            ajax("/register/getTableByID","POST",{id:$(this).attr("id")},callback)
        }
   });


});
