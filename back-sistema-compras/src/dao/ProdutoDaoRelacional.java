package dao;

import compras.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDaoRelacional implements ProdutoDaoInterface {
    
    private PreparedStatement stmObterTodos;
    private PreparedStatement stmInserir;
    private PreparedStatement stmApagar;
//    private PreparedStatement stmAtualizar;
    private PreparedStatement stmBuscarCod;
    private PreparedStatement stmBuscarNome;

    public ProdutoDaoRelacional(ConexaoInterface conexao) throws ConexaoException, ComprasDaoException {
        Connection connection = conexao.getConnection();
        String sql = "";
        try {
            sql = "SELECT * FROM produtos";
            stmObterTodos = connection.prepareStatement(sql);
            sql = "DELETE FROM produtos WHERE codigo=?";
            stmApagar = connection.prepareStatement(sql);
            sql = "SELECT * FROM produtos WHERE codigo=?";
            stmBuscarCod = connection.prepareStatement(sql);
            sql = "SELECT * FROM produtos WHERE lower(nome) LIKE ?";
            stmBuscarNome = connection.prepareStatement(sql);
        } catch (SQLException ex) {
            throw new ComprasDaoException("Erro ao preparar sentença SQL: " + sql);
        }
    }
    
//    @Override
//    public int atualizar(Conta conta) throws ComprasDaoException {
//        int ret = -1;
//        try {
//            stmAtualizar.setBigDecimal(1, conta.getSaldo());
//            stmAtualizar.setLong(2, conta.getNumero());
//            ret = stmAtualizar.executeUpdate();
//        } catch(SQLException ex) {
//            throw new ComprasDaoException("Erro ao atualizar!");
//        }
//        return ret;
//    }
    
    @Override
    public int apagar(int codigo) throws ComprasDaoException {
        int ret = -1;
        try {
            stmApagar.setInt(1, codigo);
            ret = stmApagar.executeUpdate();
        } catch (SQLException ex) {
            throw new ComprasDaoException("Erro ao apagar!");
        }
        return ret;
    }
    
    @Override
    public int inserir(Produto produto) throws ComprasDaoException {
        int ret = -1;
        try {
            stmInserir.setString(1, produto.getNome());
            stmInserir.setInt(2, produto.getCodigo());
            stmInserir.setString(3, produto.getDescricao());
            stmInserir.setString(4, produto.getModelo());
            stmInserir.setString(5, produto.getStatus());
            stmInserir.setDouble(6, produto.getPreco());
            stmInserir.setString(6, produto.getImagem());
            ret = stmInserir.executeUpdate();
        } catch (SQLException ex) {
            throw new ComprasDaoException("Erro na operação de inserir novo produto!");
        }
        return ret;
    }
    
    @Override
    public List<Produto> obterTodos() throws ComprasDaoException {
        List<Produto> produtos = new ArrayList<>();
        try {
            ResultSet resultados = stmObterTodos.executeQuery();
            while (resultados.next()) {
                Produto p = new Produto(resultados.getString("nome"),
                        resultados.getInt("codigo"),
                        resultados.getString("descricao"),
                        resultados.getString("modelo"),
                        resultados.getString("status"),
                        resultados.getInt("preco"),
                        resultados.getString("imagem"));
                produtos.add(p);
            }
        } catch (SQLException ex) {
            throw new ComprasDaoException("Erro ao executar consulta de todos os produtos");
        }
        return produtos;
    }

    @Override
    public Produto buscarNome(String nome) throws ComprasDaoException {
        Produto p = null;
        try {
            stmBuscarNome.setString(1,"%" +  nome + "%");
            ResultSet resultados = stmBuscarNome.executeQuery();
            if (resultados.next()) {
                p = new Produto(resultados.getString("nome"),
                        resultados.getInt("codigo"),
                        resultados.getString("descricao"),
                        resultados.getString("modelo"),
                        resultados.getString("status"),
                        resultados.getInt("preco"),
                        resultados.getString("imagem"));
              
            }
        } catch (SQLException ex) {
            throw new ComprasDaoException("Erro ao executar busca de produto pelo nome");
        }
        return p;
    }
    
    @Override
    public Produto buscarCod(int codigo) throws ComprasDaoException {
        Produto p = null;
        try {
            stmBuscarCod.setInt(1, codigo);
            ResultSet resultados = stmBuscarCod.executeQuery();
            if (resultados.next()) {
              p = new Produto(resultados.getString("nome"),
                        resultados.getInt("codigo"),
                        resultados.getString("descricao"),
                        resultados.getString("modelo"),
                        resultados.getString("status"),
                        resultados.getInt("preco"),
                        resultados.getString("imagem"));
            }
        } catch (SQLException ex) {
            throw new ComprasDaoException("Erro ao executar busca de produto pelo codigo");
        }
        return p;
    }   
    
    public static void main(String[] args) throws ConexaoException, ComprasDaoException, SQLException {
        ConexaoInterface conexao = new ConexaoJavaDb("jdbc:derby://localhost:1527/sistema_compras", "org.apache.derby.jdbc.ClientDriver", "app", "app");
        Connection connection = conexao.getConnection();
        String sql = "INSERT INTO produtos VALUES (126, 'Fonte Corsair 450W', 'Corsair', 'Fonte Corsair 450W 80 Plus Bronze, confiabilidade e seguranca para seu PC.', 'Em estoque'," +
" 'https://images1.kabum.com.br/produtos/fotos/91021/91021_index_gg.jpg', 249.00)";
//        String sql = "DELETE FROM produtos WHERE codigo=124";
        PreparedStatement stmInserir = connection.prepareStatement(sql);
        stmInserir.executeUpdate();
   }
    
}