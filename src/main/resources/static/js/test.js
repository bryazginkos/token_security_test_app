/**
 * Created by Константин on 06.04.2016.
 */
function registerAdmin() {
    $.ajax({
        url: "/api/1.0/register",
        type: 'POST',
        data: JSON.stringify({
            login : "kostya",
            password : "123"
        }),
        contentType: "application/json",
        success: function(data) {
            alert(JSON.stringify(data))
        }
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

function deleteProduct() {
    $.ajax({
        url: "/api/1.0/products/" + $('#deleteProductIdField').val() + "/",
        type: 'DELETE',
        headers: {
            "X_ACCESS_TOKEN" : $('#tokenField').val()
        },
        contentType: "application/json",
        success: function(data) {
            alert("deleted");
            $('#deleteProductIdField').val("");
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(errorThrown);
        }
    });
}

function addProductToBasket() {
    $.ajax({
        url: "/api/1.0/basket/" + $('#addBasketProductIdField').val() + "/",
        type: 'POST',
        contentType: "application/json",
        success: function(data) {
            alert('added to basket ' + JSON.stringify(data));
            $('#addBasketProductIdField').val("");
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(errorThrown);
        }
    });
}

function saveBasket() {
    $.ajax({
        url: "/api/1.0/orders/",
        type: 'POST',
        contentType: "application/json",
        data: "+7 999 123 12 12",
        success: function(data) {
            alert('basket is saved ' + JSON.stringify(data));
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            alert(errorThrown);
        }
    });
}

function getOrders() {
    $.ajax({
        url: "/api/1.0/orders?begin=" + $('#fromDateField').val() + "&end=" + $('#toDateField').val(),
        type: 'GET',
        contentType: "application/json",
        headers: {
            "X_ACCESS_TOKEN" : $('#tokenField').val()
        },
        success: function(data) {
            $('#ordersDiv').html(JSON.stringify(data));
        },
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            $('#ordersDiv').val("");
            alert(errorThrown);
        }
    });
}
