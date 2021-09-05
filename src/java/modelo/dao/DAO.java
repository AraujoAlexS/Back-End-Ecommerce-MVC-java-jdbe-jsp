/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import modelo.config.Config;

/**
 *
 * @author alice
 */
public class DAO {
    protected Connection obterConexao() throws Exception {
        try {
            Class.forName(Config.JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(Config.JDBC_URL, Config.JDBC_USUARIO, Config.JDBC_SENHA);
            return connection;
        } catch (ClassNotFoundException ex) {
            throw new Exception("Driver JDBC não encontrado");
        } catch (SQLException ex) {
            throw new Exception("Não foi possível obter um conexão com o SGBD");
        }
    }
    
    protected void fecharConnection(Connection connection) throws Exception {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
