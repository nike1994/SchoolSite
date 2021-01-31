
$( document ).ready(function() {
  $('#textPost').summernote({
      placeholder: 'pisz tutaj ...',
      toolbar: [
        ['style', ['style']],
        ['font', ['bold', 'underline', 'clear']],
        ['fontname', ['fontname']],
        ['color', ['color']],
        ['para', ['ul', 'ol', 'paragraph']],
        ['table', ['table']],
        ['insert', ['link', 'picture', 'video']],
        ['view', ['codeview']],
      ],
      height: 400
  });
});
