/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import compras.Produto;
import java.util.List;

/**
 *
 * @author leonardo
 */
public interface ProdutoDaoInterface {
    List<Produto> obterTodos() throws ComprasDaoException;
    int inserir(Produto produto) throws ComprasDaoException;
    int apagar(int codigo) throws ComprasDaoException;
    Produto buscarNome(String nome) throws ComprasDaoException;
    Produto buscarCod(int codigo) throws ComprasDaoException;
}
