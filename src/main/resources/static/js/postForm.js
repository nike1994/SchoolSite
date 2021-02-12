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
    xmlhttp.send(JSON.stringify(object));

}

var posts = new Array();
$( document ).ready(function() {
    $('#page').change(function(e){
        console.log($(this).val());
        if($(this).val()!=0){
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

    $('#createPostForm').on('submit',function(event){
            if(!$('#createPostForm')[0].checkValidity()){
                event.preventDefault();
                event.stopPropagation();
                return false;
            }
            if($('#pageParent').val() == 0){
                event.preventDefault();
                event.stopPropagation();
                $('#pageParent')[0].setCustomValidity("Nie wybrałeś strony");
                $('#pageParent')[0].reportValidity();
                return false;
            }
            $('#createPostForm')[0].classList.add('was-validated')
    });

    $('#createPostForm button').click(function(){
            if($('#teacher').val() != 0 ){
                $('#teacher')[0].setCustomValidity("");
            }
    });

    $('#updatePostForm').on('submit',function(event){
            if(!$('#updatePostForm')[0].checkValidity()){
                event.preventDefault();
                event.stopPropagation();
                return false;
            }
            if($('#page').val() == 0){
                event.preventDefault();
                event.stopPropagation();
                $('#page')[0].setCustomValidity("Nie wybrałeś strony");
                $('#page')[0].reportValidity();
                return false;
            }
            if($('#post').val() == 0){
                event.preventDefault();
                event.stopPropagation();
                $('#post')[0].setCustomValidity("Nie wybrałeś postu");
                $('#post')[0].reportValidity();
                return false;
            }
            $('#createPostForm')[0].classList.add('was-validated')
    });

    $('#updatePostForm button').click(function(){
            if($('#page').val() != 0 ){
                $('#page')[0].setCustomValidity("");
            }
            if($('#post').val() != 0 ){
                $('#post')[0].setCustomValidity("");
            }
    });

    $('#deletePostForm').on('submit',function(event){
            if(!$('#deletePostForm')[0].checkValidity()){
                event.preventDefault();
                event.stopPropagation();
                return false;
            }
            if($('#page').val() == 0){
                event.preventDefault();
                event.stopPropagation();
                $('#page')[0].setCustomValidity("Nie wybrałeś strony");
                $('#page')[0].reportValidity();
                return false;
            }
            if($('#post').val() == 0){
                event.preventDefault();
                event.stopPropagation();
                $('#post')[0].setCustomValidity("Nie wybrałeś postu");
                $('#post')[0].reportValidity();
                return false;
            }
            $('#createPostForm')[0].classList.add('was-validated')
    });

    $('#deletePostForm button').click(function(){
            if($('#page').val() != 0 ){
                $('#page')[0].setCustomValidity("");
            }
            if($('#post').val() != 0 ){
                $('#post')[0].setCustomValidity("");
            }
    });
});
