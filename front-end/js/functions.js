$(window).ready(function () {


    $('.btn-comprar').click(function () {
        var prod_id = parseInt($(this).attr('data-id'));
        var prod_nome = $(this).parent().find('.titulo').text();
        var prod_preco = $(this).parent().find('.preco span').text();
        var prod_img = $(this).parent().parent().find('img').attr('src');
        var prod_desc = $(this).parent().find('.desc').text();


        var prod_to_add = {cod: prod_id, nome: prod_nome, preco: prod_preco, descricao: prod_desc, imagem: prod_img};
        document.cookie = "product=" + JSON.stringify(prod_to_add);
        console.log(JSON.parse(getCookie('product')));
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


// function get_all_products() {
//     var url = '';
//
//     $.get(url, function (data) {
//         console.log(data);
//     });
// }