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
    $('#pageEdit').change(function(e){
        console.log($(this).val());
        if($(this).val()!=0){
            var callback = function(responseText){
                    page = JSON.parse(responseText);

                    $('#Title').val(page.title);
                    console.log(page.hasChild);

                    if(page.hasChild==true){
                        $('#parentPage').attr('disabled','disabled');
                        $('#parentPage').val(0);
                    }else{

                        $('#parentPage').removeAttr('disabled');
                        $('#parentPage').val(page.parent_id);
                    }
            }

            ajax("/page/getPage","POST",{"id": $(this).val()},callback)
        }else{
             $('#parentPage').removeAttr('disabled');
             $('#parentPage').val(0);
             $('#Title').val(null);
        }
    });

});
