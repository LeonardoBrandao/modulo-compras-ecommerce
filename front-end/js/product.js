$(window).ready(function () {
    insertProductInfos(getCookie('product'));

    // actions botoes

});


// monta a pagina de single de produto
function insertProductInfos(product) {
    product = JSON.parse(product);
    console.log(product);
    $('.product-single img').attr('src', product.imagem);
    $('.product-single .titulo').text(product.nome);
    $('.product-single .desc').text(product.descricao);
    $('.product-single .preco span').text(product.preco);
}