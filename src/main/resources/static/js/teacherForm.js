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
    $('#teacher').change(function(e){
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

});
