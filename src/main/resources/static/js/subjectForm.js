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
    $('#updateTeacherForm #subject').change(function(e){
        console.log($(this).val());
        if($(this).val()!=0){
            var callback = function(responseText){
                    subject = JSON.parse(responseText);

                    $('#name').val(subject.name);
                    $('#clas').val(subject.class_id);
                    $('#teacher').val(subject.teacher_id);
            }

            ajax("/subject/getSubject","POST",{"id": $(this).val()},callback)
        }else{
            $('#name').val('');
            $('#clas').val(0);
            $('#teacher').val(0);
        }
    });

    $('#createForm').on('submit',function(event){
            if(!$('#createForm')[0].checkValidity()){
                event.preventDefault();
                event.stopPropagation();
                return false;
            }
            if($('#clas').val() == 0){
                event.preventDefault();
                event.stopPropagation();
                $('#clas')[0].setCustomValidity("Nie wybrałeś klasy");
                $('#clas')[0].reportValidity();
                return false;
            }
            if($('#teacher').val() == 0){
                event.preventDefault();
                event.stopPropagation();
                $('#teacher')[0].setCustomValidity("Nie wybrałeś nauczyciela");
                $('#teacher')[0].reportValidity();
                return false;
            }
            $('#createForm')[0].classList.add('was-validated')
    });

    $('#createForm button').click(function(){
            if($('#clas').val() != 0 ){
                $('#clas')[0].setCustomValidity("");
            }
            if($('#teacher').val() != 0 ){
                $('#teacher')[0].setCustomValidity("");
            }
    });

    $('#updateForm').on('submit',function(event){
            if(!$('#updateForm')[0].checkValidity()){
                event.preventDefault();
                event.stopPropagation();
                return false;
            }
            if($('#clas').val() == 0){
                event.preventDefault();
                event.stopPropagation();
                $('#clas')[0].setCustomValidity("Nie wybrałeś klasy");
                $('#clas')[0].reportValidity();
                return false;
            }
            if($('#teacher').val() == 0){
                event.preventDefault();
                event.stopPropagation();
                $('#teacher')[0].setCustomValidity("Nie wybrałeś nauczyciela");
                $('#teacher')[0].reportValidity();
                return false;
            }
            if($('#subject').val() == 0){
                event.preventDefault();
                event.stopPropagation();
                $('#subject')[0].setCustomValidity("Nie wybrałeś przedmiotu");
                $('#subject')[0].reportValidity();
                return false;
            }

            $('#updateForm')[0].classList.add('was-validated')
    });

    $('#updateForm button').click(function(){
            if($('#clas').val() != 0 ){
                $('#clas')[0].setCustomValidity("");
            }
            if($('#subject').val() != 0 ){
                $('#subject')[0].setCustomValidity("");
            }
            if($('#teacher').val() != 0 ){
                $('#teacher')[0].setCustomValidity("");
            }
    });

    $('#deleteForm').on('submit',function(event){
            if(!$('#deleteForm')[0].checkValidity()){
                event.preventDefault();
                event.stopPropagation();
                return false;
            }
            if($('#subject').val() == 0){
                event.preventDefault();
                event.stopPropagation();
                $('#subject')[0].setCustomValidity("Nie wybrałeś przedmiotu");
                $('#subject')[0].reportValidity();
                return false;
            }
            $('#createForm')[0].classList.add('was-validated')
    });

    $('#deleteForm button').click(function(){
            if($('#subject').val() != 0 ){
                $('#subject')[0].setCustomValidity("");
            }
    });

});
