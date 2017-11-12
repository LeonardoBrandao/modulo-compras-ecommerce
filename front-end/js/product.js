$(window).ready(function () {
    insertProductInfos(getCookie('product'));

    // actions botoes
    $('.btn').click(function (e) {
        e.preventDefault();

        if($('#rua').val() === '' || $('#numero').val() === '' || $('#complemento').val() === '' || $('#bairro').val() === '' || $('#cidade').val() === '') {
            swal(
                'Oops...',
                'Preencha o endereço corretamente!',
                'error'
            );
            return false;
        }

        var end = $('#rua').val() + ', ' + $('#numero').val() + ' - ' + $('#complemento').val() + ', ' + $('#bairro').val() + ', ' + $('#cidade').val();

        swal({
            title: 'Quase lá!',
            text: "Seu produto será entrege no endereço: " + end,
            type: 'info',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            cancelButtonText: 'Cancelar',
            confirmButtonText: 'Confirmar compra!'
        }).then(function () {
            window.location = '/compra-finalizada.html';
        })
    });


});


// monta a pagina de single de produto
function insertProductInfos(product) {
    product = JSON.parse(product);
    $('.product-single img').attr('src', product.imagem);
    $('.product-single .titulo').text(product.nome);
    $('.product-single .desc').text(product.descricao);
    $('.product-single .preco span').text(product.preco);
    $('.product-single .estoque').text(product.estoque);
}