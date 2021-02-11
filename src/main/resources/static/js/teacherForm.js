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
    $('#updateTeacherForm #teacher').change(function(e){
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

     $('#updateTeacherForm').on('submit',function(event){
            if(!$('#updateTeacherForm')[0].checkValidity()){
                event.preventDefault();
                event.stopPropagation();
                return false;
            }
            if($('#teacher').val() == 0){
                event.preventDefault();
                event.stopPropagation();
                $('#teacher')[0].setCustomValidity("Nie wybrałeś nauczyciela");
                $('#teacher')[0].reportValidity();
                return false;
            }
            $('#updateTeacherForm')[0].classList.add('was-validated')
    });

    $('form button').click(function(){
            if($('#teacher').val() != 0 ){
                $('#teacher')[0].setCustomValidity("");
            }
    });

    $('#deletePostForm').on('submit',function(event){
        if(!$('#deletePostForm')[0].checkValidity()){
            event.preventDefault();
            event.stopPropagation();
            return false;
        }
        if($('#teacher').val() == 0){
            event.preventDefault();
            event.stopPropagation();
            $('#teacher')[0].setCustomValidity("Nie wybrałeś nauczyciela");
            $('#teacher')[0].reportValidity();
            return false;
        }

        $('#deletePostForm')[0].classList.add('was-validated')
    })

});
