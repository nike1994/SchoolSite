function ajax(url,type,object,callback){
    console.log(object);
   var xmlhttp = new XMLHttpRequest();   // new HttpRequest instance
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
    //xmlhttp.onreadystatechange = callback;
    xmlhttp.send(JSON.stringify(object));

}

var posts = new Array();
$( document ).ready(function() {
    $('#page').change(function(e){
        console.log($(this).val());
        if($(this).val()!=0){
//            $.ajax({
//                    url:"/post/getPostsByPage",
//                    method:"POST",
//                    data:{"id":$(this).val()},
//                    dataType: "json",
//                    contentType: "application/json",
//             }).done(function(result) {
//                 console.log(result)
//             });
// z jakiegoś powodu nie działa jquery ajax
            $('#post').empty();
            var callback = function(responseText){
                    posts = JSON.parse(responseText);
                    $('#post').append($('<option>', {
                        value: "0",
                        text: "wybierz post"
                     }))
                    posts.forEach(function(entry) {
                               $('#post').append($('<option>', {
                                  value: entry.id,
                                  text: entry.title
                              }));
                           });
            }
            ajax("/post/getPostsByPage","POST",{"id": $(this).val()},callback)
        }
    });

    $('#post').change(function(){
         console.log($(this).val())
        if(posts.length>0 && ($(this).val() != 0)){

            var post;

            for(var i =0; i<posts.length;i++){
                if(posts[i].id == $(this).val()){
                    post = posts[i];
                }
            }

            $('#Title').val(post.title);
            $('#textPost').summernote('code', post.content);
        }
    });
});
