function hashCode(str) {
  var hash = 0, i, chr;
  for (i = 0; i < str.length; i++) {
    chr   = str.charCodeAt(i);
    hash  = ((hash << 5) - hash) + chr;
    hash |= 0; // Convert to 32bit integer
  }
  return hash;
}

$( document ).ready(function() {
    console.log(hashPass);
    $('#updatePostForm.needs-validation').on('submit',function(event){
        if(!$('#updatePostForm')[0].checkValidity()){
            event.preventDefault();
            event.stopPropagation();
            return false;
        }
        if($('#pass').val() != $('#pass2').val()){
            event.preventDefault();
            event.stopPropagation();
            $('#pass2')[0].setCustomValidity("Hasła nie są takie same");
            $('#pass2')[0].reportValidity();
            return false;
        }else if(hashCode($('#pass').val()) == hashPass){ // hashPass hash z serwera
            event.preventDefault();
            event.stopPropagation();
            $('#pass')[0].setCustomValidity("Hasło jest takie samo jak stare");
            $('#pass')[0].reportValidity();
            return false;
        }
    });

    $('form button').click(function(){
            if($('#pass').val() == $('#pass2').val()){
                $('#pass2')[0].setCustomValidity("");
            }
            if(hashCode($('#pass').val()) != hashPass){
                $('#pass')[0].setCustomValidity("");
            }
    })
});



