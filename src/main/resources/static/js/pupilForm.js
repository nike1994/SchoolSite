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
    $('#pupil').change(function(e){
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

});
