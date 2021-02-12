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

var posts = new Array();
$( document ).ready(function() {
    $('#updateForm #pupil').change(function(e){
        console.log($(this).val());
        if($(this).val()!=0){
            var callback = function(responseText){
                    pupil = JSON.parse(responseText);

                    $('#name').val(pupil.name);
                    $('#surname').val(pupil.surname);
                    $('#login').val(pupil.login);
                    $('#pass').val(pupil.pass);
                    $('#parent').val(pupil.parent_id);
                    $('#class').val(pupil.schoolClass_id);
            }

            ajax("/pupil/getPupil","POST",{"id": $(this).val()},callback)
        }else{
            $('#name').val('');
            $('#surname').val('');
            $('#login').val('');
            $('#pass').val('');
            $('#parent').val(0);
            $('#class').val(0);
        }
    });

    $('#createForm').on('submit',function(event){
            if(!$('#createForm')[0].checkValidity()){
                event.preventDefault();
                event.stopPropagation();
                return false;
            }
            if($('#parent').val() == 0){
                event.preventDefault();
                event.stopPropagation();
                $('#parent')[0].setCustomValidity("Nie wybrałeś rodzica");
                $('#parent')[0].reportValidity();
                return false;
            }
            if($('#class').val() == 0){
                event.preventDefault();
                event.stopPropagation();
                $('#class')[0].setCustomValidity("Nie wybrałeś klasy");
                $('#class')[0].reportValidity();
                return false;
            }
            $('#createForm')[0].classList.add('was-validated')
    });

    $('#createForm button').click(function(){
            if($('#teacher').val() != 0 ){
                $('#teacher')[0].setCustomValidity("");
            }
            if($('#class').val() != 0 ){
                $('#class')[0].setCustomValidity("");
            }
    });

    $('#updateForm').on('submit',function(event){
            if(!$('#updateForm')[0].checkValidity()){
                event.preventDefault();
                event.stopPropagation();
                return false;
            }
            if($('#parent').val() == 0){
                event.preventDefault();
                event.stopPropagation();
                $('#parent')[0].setCustomValidity("Nie wybrałeś rodzica");
                $('#parent')[0].reportValidity();
                return false;
            }
            if($('#class').val() == 0){
                event.preventDefault();
                event.stopPropagation();
                $('#class')[0].setCustomValidity("Nie wybrałeś klasy");
                $('#class')[0].reportValidity();
                return false;
            }
            if($('#pupil').val() == 0){
                event.preventDefault();
                event.stopPropagation();
                $('#pupil')[0].setCustomValidity("Nie wybrałeś ucznia");
                $('#pupil')[0].reportValidity();
                return false;
            }
            $('#updateForm')[0].classList.add('was-validated')
    });

    $('#updateForm button').click(function(){
            if($('#teacher').val() != 0 ){
                $('#teacher')[0].setCustomValidity("");
            }
            if($('#class').val() != 0 ){
                $('#class')[0].setCustomValidity("");
            }
            if($('#pupil').val() != 0 ){
                $('#pupil')[0].setCustomValidity("");
            }
    });
    $('#deleteForm').on('submit',function(event){
           if(!$('#deleteForm')[0].checkValidity()){
               event.preventDefault();
               event.stopPropagation();
               return false;
           }
           if($('#pupil').val() == 0){
               event.preventDefault();
               event.stopPropagation();
               $('#pupil')[0].setCustomValidity("Nie wybrałeś ucznia");
               $('#pupil')[0].reportValidity();
               return false;
           }
           $('#createForm')[0].classList.add('was-validated')
   });

   $('#deleteForm button').click(function(){
           if($('#pupil').val() != 0 ){
               $('#pupil')[0].setCustomValidity("");
           }
   });
});
