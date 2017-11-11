/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author LRB
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoJavaDb implements ConexaoInterface {
    private String url;
    private String usuario;
    private String senha;
    private String driverJdbc;
    private Connection connection;

    public ConexaoJavaDb() {
    }

    public ConexaoJavaDb(String url, String driverJdbc, String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
        this.url = url;
        this.driverJdbc = driverJdbc;
    }

    @Override
    public Connection getConnection() throws ConexaoException {
        if (connection == null) {
            try {
                Class.forName(driverJdbc);
                connection = DriverManager.getConnection(url, usuario, senha);
            } catch (ClassNotFoundException ex) {
                throw new ConexaoException("Driver JDBC não encontrado!") ;   
            } catch (SQLException ex) {
                throw new ConexaoException("Falha ao abrir conexão!");
            }
        }
        return connection;
    }

    @Override
    public void close() throws ConexaoException {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ex) {
                throw new ConexaoException("Falha ao fechar a conexão!");
            }
        }
    }
    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getDriverJdbc() {
        return driverJdbc;
    }

    public void setDriverJdbc(String driverJdbc) {
        this.driverJdbc = driverJdbc;
    }
    
}

