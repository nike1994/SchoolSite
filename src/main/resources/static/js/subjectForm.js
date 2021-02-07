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
    $('#subject').change(function(e){
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

});
