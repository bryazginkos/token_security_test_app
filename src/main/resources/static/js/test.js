/**
 * Created by Константин on 06.04.2016.
 */

var mytoken;

function registerAdmin(userName, userPass, callback) {
    $.ajax({
        url: "/api/1.0/register",
        type: 'POST',
        data: JSON.stringify({
            login : userName,
            password : userPass
        }),
        contentType: "application/json",
        success: callback
    });
}

function getToken(userName, userPass, callback) {
    $.ajax({
        url: "/api/1.0/token",
        type: 'POST',
        data: JSON.stringify({
            login : userName,
            password : userPass
        }),
        contentType: "application/json",
        success: function(token) {
            mytoken = token;
            callback();
        }
    });
}

function addProduct(name, price, callback) {
    $.ajax({
        url: "/api/1.0/products",
        type: 'POST',
        headers: {
            "X_ACCESS_TOKEN" : mytoken
        },
        data: JSON.stringify({
            name : name,
            price : price
        }),
        contentType: "application/json",
        success: callback
    });
}

function base() {
    getToken("kostya", "123", function() {
        addProduct("PC", 12.34, function() {
            alert('hi');
        })
    });
}
