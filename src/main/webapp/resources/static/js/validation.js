$(document).ready(function (){

    $.validator.addMethod("validUsername", function (username){
        let userId = $('#userId').val();
        return checkUser(userId, username);
    }, "Bu kullanıcı zaten var.");

    $.validator.addMethod("validPassword", function (value){
        let userId = $('#userId').val();
        return checkPassword(userId, value);
    }, "Lütfen Şifre Alanını Doldurun.");

    $('#login-form').validate({
        rules: {
            username: {
                required: true,
                minlength: 5
            },
            password: {
                required: true,
                minlength: 5
            }
        },
        errorPlacement: function(error, element) {
            error.insertAfter($(element).parent());
        }
    });

    $('#user-form').validate({
        rules: {
            name: {
                required: true,
                minlength: 2
            },
            surname: {
                required: true,
                minlength: 2
            },
            username: {
                required: true,
                minlength: 5,
                validUsername: true
            },
            password: {
                required: true,
                minlength: 5,
            },
            phone: {
                required: true,
                phone: true
            },
            email: {
                required: true,
                email: true
            },
            birthDate: {
                required: true
            }
        },
        messages: {
            name: "Adınızı giriniz.",
            surname: "Soyadınızı Giriniz.",
            username: {
                required: "Kullanıcı Adınızı Giriniz.",
                validUsername: "Bu kullanıcı zaten var."
            },
            password: "Şifrenizi Giriniz.",
            phone: "Telefon Numaranızı Giriniz.",
            email: "E-Posta Adresinizi Giriniz.",
            birthDate: "Doğum Tarihinizi Giriniz."
        },
        errorPlacement: function(error, element) {
            if (element.attr("name") === "birthDate") {
                error.insertAfter($(element).parent());
            } else {
                error.insertAfter(element);
            }
        },
        submitHandler: function(form) {
            form.submit();
        }
    });

});

function checkUser(userId, username) {
    if(userId === null || userId.trim() === ''){
        return !checkUsername(username);
    }
    return true;
}