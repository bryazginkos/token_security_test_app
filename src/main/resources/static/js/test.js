/**
 * Created by Константин on 06.04.2016.
 */
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

function getToken() {
    $.ajax({
        url: "/api/1.0/token",
        type: 'POST',
        data: JSON.stringify({
            login : $('#loginField').val(),
            password : $('#passField').val()
        }),
        contentType: "application/json",
        success: function(token) {
            $('#tokenField').val(token);
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            $('#tokenField').val("");
            alert(errorThrown);
        }
    });
}

function showProducts() {
    $.ajax({
        url: "/api/1.0/products/",
        type: 'GET',
        contentType: "application/json",
        success: function(data) {
            $('#productsDiv').html(JSON.stringify(data));
        }
    });
}

function addProduct() {
    $.ajax({
        url: "/api/1.0/products",
        type: 'POST',
        headers: {
            "X_ACCESS_TOKEN" : $('#tokenField').val()
        },
        data: JSON.stringify({
            name : $('#productField').val(),
            price : $('#priceField').val()
        }),
        contentType: "application/json",
        success: function(data) {
            alert("ok " + JSON.stringify(data));
            $('#productField').val("");
            $('#priceField').val("");
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(errorThrown);
        }
    });
}

function editProduct() {
    $.ajax({
        url: "/api/1.0/products/" + $('#editProductIdField').val() + "/",
        type: 'PUT',
        headers: {
            "X_ACCESS_TOKEN" : $('#tokenField').val()
        },
        data: JSON.stringify({
            name : $('#editProductField').val(),
            price : $('#editPriceField').val()
        }),
        contentType: "application/json",
        success: function(data) {
            alert("ok " + JSON.stringify(data));
            $('#editProductField').val("");
            $('#editPriceField').val("");
            $('#editProductIdField').val("");
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(errorThrown);
        }
    });
}
