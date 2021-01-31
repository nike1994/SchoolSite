var table;

$( document ).ready(function() {
  //define some sample data
   var tabledata = [
   	{id:1, name:"Oli Bob", age:"12", col:"red", dob:""},
   	{id:2, name:"Mary May", age:"1", col:"blue", dob:"14/05/1982"},
   	{id:3, name:"Christine Lobowski", age:"42", col:"green", dob:"22/05/1982"},
   	{id:4, name:"Brendon Philips", age:"125", col:"orange", dob:"01/08/1980"},
   	{id:5, name:"Margret Marmajuke", age:"16", col:"yellow", dob:"31/01/1999"},
   ];

  //create Tabulator on DOM element with id "example-table"
  table = new Tabulator("#school_register", {
   	data:tabledata, //assign data to table
    layout:"fitDataFill", //fit columns to width of table (optional)
    movableColumns:true,
   	columns:[ //Define Table Columns
  	 	{title:"Name", field:"name", width:150},
  	 	{title:"Age", field:"age", hozAlign:"left", formatter:"progress"},
  	 	{title:"Favourite Color", field:"col"},
  	 	{title:"Date Of Birth", field:"dob", sorter:"date", hozAlign:"center"},
   	],
    rowFormatter:function(row){
        if(row.getData().age < 20){
            row.getElement().classList.add("table-primary"); //mark rows with age greater than or equal to 18 as successful;
        }
    },
   	rowClick:function(e, row){ //trigger an alert message when the row is clicked
   		alert("Row " + row.getData().id + " Clicked!!!!");
   	},
  });

  var columns=[];

  $( "#addColumn" ).click(function() {
    table.addColumn({title:"Ocena", field:"Ocena", editableTitle:true, editor:"number", editorParams:{
      min:0,
      max:6,
      step:1,
      elementAttributes:{
        maxlength:"1", //set the maximum character length of the input element to 10 characters
      },
      mask:"9",

    },validator:"max:6"}, false, "ocena")
      .then(function(column){
          //column - the component for the newly created column

          //run code after column has been added
          columns.push(column);
      })
      .catch(function(error){
          //handle error adding column
          console.log(error);
      });
  });

  $( "#deleteColumn" ).click(function() {
      columns[0].delete();
      columns.shift();
  });



});
