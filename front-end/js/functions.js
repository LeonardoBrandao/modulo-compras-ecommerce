$(window).ready(function () {

    // definindo o carrinho de compras. 

    getCookie('cart') ? cart = JSON.parse(getCookie('cart')) : cart = [];

    updateCartBadge(cart);

    $('.btn-comprar').click(function () {
        var prod_id = parseInt($(this).attr('data-id'));
        var prod_nome = $(this).parent().find('.titulo').text();
        var prod_preco = $(this).parent().find('.preco span').text();

        var obj_found = false;

        cart.forEach(function (obj, idx) {
            if(obj.cod === prod_id) {
                obj_found = true;
                obj_idx = idx
            }
        });
        if(obj_found){
            cart[obj_idx].quant += 1;
        } else {
            var prod_to_add = {cod: prod_id, nome: prod_nome, preco: prod_preco, quant: 1};
            cart.push(prod_to_add);
        }
        swal(
            'Produto adicionado ao carrinho!',
            '',
            'success'
        );

        document.cookie = "cart=" + JSON.stringify(cart);

        updateCartBadge(cart);

    });
    
});


function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) == ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}

function updateCartBadge(cart) {
    var num_itens = null;
    cart.forEach(function (item) {
        num_itens += item.quant;
    });
    $('.badge-info').text(num_itens);
}

// function get_all_products() {
//     var url = '';
//
//     $.get(url, function (data) {
//         console.log(data);
//     });
// }