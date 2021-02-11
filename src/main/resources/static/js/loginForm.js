$( document ).ready(function() {

    $('#login-form').submit(function(event){
        if(!form.checkValidity()){
            event.preventDefault();
            event.stopPropagation();
        }
        form.classList.add('was-validated')
    });
});