$(window).ready(function () {
    cart.forEach(function (item) {
        var tr = '<tr>' +
                    '<td>' + item.cod + '</td>' +
                    '<td>' + item.nome + '</td>' +
                    '<td>' + item.preco + '</td>' +
                    '<td>' + item.quant + '</td>' +
                '</tr>';
        $('#cart-table').find('tbody').append(tr);
    });
});