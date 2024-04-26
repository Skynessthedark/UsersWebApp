let BASE_URL = location.protocol + "//" + location.host;
let REMOVE_URL = "/user/remove/";
let USER_TABLE_URL = "/user/user-table";
let USER_CHECK_URL = "/user/check-user-exists?username=";

$(document).ready(function (){
    $('#birthDate').datepicker({format: 'dd-mm-yyyy'});

    $('#myform').submit(function() {
        checkUser();
    });
});

function removeUser(userId) {
    $.ajax({
        method: "POST",
        url: BASE_URL + REMOVE_URL + userId,
        contentType: 'application/json',
        cache: false,
        async: false,
        success: function (response) {
            alert(response.message);
            getUserTable();
        },
        error: function (result) {
            console.log(result.message);
        }
    });
    return false;
}

function getUserTable() {
    $.ajax({
        method: "GET",
        url: BASE_URL + USER_TABLE_URL,
        success: function (response) {
            $('#user-table').html(response);
        },
        error: function (result) {
            console.log(result.message);
        }
    });
    return false;
}

function checkUsername(username) {
    let userExists = false;
    $.ajax({
        method: "POST",
        url: BASE_URL + USER_CHECK_URL + username,
        cache: false,
        async: false,
        success: function (response) {
            if(response.status === true){
                alert("Girilen Kullanıcı Adı ile bir kullanıcı bulunmaktadır.");
                userExists = true;
            }
        },
        error: function (result) {
            console.log(result.message);
        }
    });
    return userExists;
}