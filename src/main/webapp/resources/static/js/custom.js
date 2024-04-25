let BASE_URL = location.protocol + "//" + location.host;
let REMOVE_URL = "/user/remove/";
let USER_TABLE_URL = "/user/user-table";

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