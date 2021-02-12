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
    $('#parent').change(function(e){
        console.log($(this).val());
        if($(this).val()!=0){
            var callback = function(responseText){
                    user = JSON.parse(responseText);

                    $('#name').val(user.name);
                    $('#surname').val(user.surname);
                    $('#login').val(user.login);
                    $('#pass').val(user.pass);
            }

            ajax("/user/getUser","POST",{"id": $(this).val()},callback)
        }else{
            $('#name').val(0);
            $('#surname').val(0);
            $('#login').val(0);
            $('#pass').val(0);
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
            $('#updateForm')[0].classList.add('was-validated')
    });

    $('#deleteForm').on('submit',function(event){
            if(!$('#deleteForm')[0].checkValidity()){
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
            $('#updateForm')[0].classList.add('was-validated')
    });

    $('form button').click(function(){
            if($('#parent').val() != 0 ){
                $('#parent')[0].setCustomValidity("");
            }
    });

});
