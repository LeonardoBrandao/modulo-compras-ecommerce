################################## CRIAÇÃO DA TABELA PRODUTOS ##################################
CREATE TABLE produtos (
    codigo BIGINT NOT NULL,
    nome VARCHAR(900),
    modelo VARCHAR(900),
    descricao VARCHAR(900),
    status VARCHAR(900),
    imagem VARCHAR(900),
    preco DECIMAL(8,2),
    PRIMARY KEY (codigo)
);


### INSERT PRODUTOS ###
INSERT INTO produtos VALUES (123, 'Mouse Steel Series', 'Raw Series', 'Mouse gamer', 'Em estoque',
 'http://d2fvaoynuecth8.cloudfront.net/assets/01237/produtos/707/mouses-senseirawrubber-3.jpg', 299.00);

