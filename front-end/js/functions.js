$(window).ready(function () {
    get_all_products();

    $('#search-form button').click(function (e) {
        e.preventDefault();

        var url = 'http://localhost:7000/compras/produto/' + $('#search-form input').val().toLowerCase();
        $.get(url, function (item) {
            var grid = $('#product-grid');
            grid.empty();
            node = create_product_node(item);
            grid.append(node);

        })



    });

    $('#product-grid').on('click', '.btn-comprar', function () {
        console.log('teste');
        var prod_nome = $(this).parent().find('.titulo').text();
        var prod_preco = $(this).parent().find('.preco span').text();
        var prod_img = $(this).parent().parent().find('img').attr('src');
        var prod_desc = $(this).parent().find('.desc').text();
        var prod_est = $(this).parent().find('.est').text();


        var prod_to_add = {nome: prod_nome, preco: prod_preco, descricao: prod_desc, imagem: prod_img, estoque: prod_est};
        document.cookie = "product=" + JSON.stringify(prod_to_add);

        window.location = "/single-produto.html";
    });
});


function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0) === ' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) === 0) {
            return c.substring(name.length, c.length);
        }
    }
    return "";
}


function get_all_products() {
    var url = 'http://localhost:7000/compras/produtos';

    $.get(url, function (data) {
        console.log(data);
        var grid = $('#product-grid');
        data.forEach(function (item) {
            var node = '';

            node += create_product_node(item);
            grid.append(node);
        });
    });
}

function create_product_node (item) {
    return '<div class="product-card row mt-2">' +
        '       <div class="col-4">' +
        '           <img src="' + item.imagem + '" alt="" class="img-fluid">' +
        '           </div>' +
        '                <div class="col-8">' +
        '                    <h3 class="titulo">' + item.nome + '</h3>' +
        '                    <p class="desc">' + item.descricao + '</p>' +
        '                    <h5 class="preco">R$ <span>' + item.preco + ',00</span></h5>' +
        '                    <span class="est">' + item.status + '</span>' +
        '                    <a href="#" class="btn btn-primary btn-comprar">Comprar</a>' +
        '                </div>' +
        '            </div>';
}