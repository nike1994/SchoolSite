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

var post_id = null;
$( document ).ready(function() {

    $('button.comment-create').click(function(){
        $('#modalBoxComment').modal('show');
        post_id=$(this).parent().parent().attr("id");
        console.log(post_id);
    });

    $('#modalBoxComment .closeModal').click(function(){
        $('#modalBoxComment').modal('hide');
    });

    $('#modalBoxComment .saveModalBoxComment').click(function(){
        $('#modalBoxComment').modal('hide');
        var callback = function(responseText){
            if(responseText == "ok"){
                window.location.reload(true);
            }else{
                $('#modalBoxTitle').html("Błąd !!");
                $('#modalBoxBody').html("Wystąpił błąd podczas zapisu komentarza");
                $('#modalBox').modal('show');
            }
        }
        var data = {
                    id: post_id,
                    content: $('#comment').val()
                    };
        ajax("/post/createComment","POST",data,callback);
    });


});
