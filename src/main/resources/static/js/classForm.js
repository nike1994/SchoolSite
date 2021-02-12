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

$( document ).ready(function() {
    $('#year').datepicker( {
        container:'.form-group',
        format: " yyyy",
        viewMode: "years",
        minViewMode: "years",
        startDate: new Date('2020')
    });

    $('#classEdit').change(function(e){
            console.log($(this).val());
            if($(this).val()!=0){

                var callback = function(responseText){
                        var clas = JSON.parse(responseText);

                        $('#name').val(clas.name);
                        $('#year').val(clas.year);
                }

                ajax("/class/getClass","POST",{"id": $(this).val()},callback)
            }else{
                $('#name').val('');
                $('#year').val('');
            }
        });

        $('form').on('submit',function(event){
                if(!$('form')[0].checkValidity()){
                    event.preventDefault();
                    event.stopPropagation();
                    return false;
                }
                if($('select').val() == 0){
                    event.preventDefault();
                    event.stopPropagation();
                    $('select')[0].setCustomValidity("Nie wybrałeś klasy");
                    $('select')[0].reportValidity();
                    return false;
                }
                $('form')[0].classList.add('was-validated')
        });

        $('form button').click(function(){
                if($('select').val() != 0 ){
                    $('select')[0].setCustomValidity("");
                }
        });
});
