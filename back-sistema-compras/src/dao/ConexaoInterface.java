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

public interface ConexaoInterface {
    Connection getConnection() throws ConexaoException;
    void close() throws ConexaoException;
}