/**
 * Created by simon on 20/03/2017.
 */
//using jquery.form.js
function uploadJqueryForm() {
    $('#result').html('');
    $("#form2").ajaxForm({
        success: function (data) {
            //$('#result').text(data+" uploaded by Jquery Form plugin!");
            $('#result').html(data);
        },
        dataType: "text"
    }).submit();
}

//---------------------------------------------------------
//using FormData() object
function uploadFormData() {
    $('#result').html('');
    var oMyForm = new FormData();
    oMyForm.append("file", file2.files[0]);

    $.ajax({
        url: 'http://localhost:8080/count/upload',
        data: oMyForm,
        dataType: 'text',
        processData: false,
        contentType: false,
        type: 'POST',
        success: function (data) {
            //  $('#result').html(data+ " uploaded by FormData!");
            $('#result').html(data);
        }
    });
}