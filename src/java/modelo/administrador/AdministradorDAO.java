/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.administrador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.dao.DAO;

/**
 *
 * @author alice
 */
public class AdministradorDAO extends DAO{
    
    
    public void inserir(Administrador ad) throws Exception {
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO administrador (nome, email, senha, login) VALUES (?, ?, ?, ?)");
        preparedStatement.setString(1, ad.getNome());
        preparedStatement.setString(2, ad.getEmail());
        preparedStatement.setString(3, ad.getSenha());
        preparedStatement.setString(4, ad.getLogin());
        
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (resultado != 1) {
            throw new Exception("Não foi possível inserir este administrador");
        }        
    }
    
    public void atualizar(Administrador ad, int id) throws Exception {
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE administrador SET nome = ?, email = ?, senha = ?, login = ? WHERE id = ?");
        preparedStatement.setString(1, ad.getNome());
        preparedStatement.setString(2, ad.getEmail());
        preparedStatement.setString(3, ad.getSenha());
        preparedStatement.setString(4, ad.getLogin());
        preparedStatement.setInt(5, id);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (resultado != 1) {
            throw new Exception("Não foi possível atualizar este administrador");
        } 
    }
    
    public void remover(int id) throws Exception {
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM administrador WHERE id = ?");
        preparedStatement.setInt(1, id);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (resultado != 1) {
            throw new Exception("Não foi possível remover este administrador");
        } 
    }
    
    public Administrador obter(int id) throws Exception {
        Administrador admim = null;
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, nome, login, email, senha FROM administrador WHERE id = ?");
        preparedStatement.setInt(1, id);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            admim = new Administrador();
            admim.setId(resultSet.getInt("id"));
            admim.setNome(resultSet.getString("nome"));
            admim.setEmail(resultSet.getString("email"));
            admim.setSenha(resultSet.getString("senha"));
            admim.setLogin(resultSet.getString("login"));
        }
        resultSet.close();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (admim == null) {
            throw new Exception("Não foi possível obter este administrador");
        }
        
        return admim;
    }
    
    public Administrador obterPorLogin(String login) throws Exception {
        Administrador administrador = null;
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, nome, email, login, senha FROM administrador WHERE login = ?");
        preparedStatement.setString(1, login);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            administrador = new Administrador();
            administrador.setId(resultSet.getInt("id"));
            administrador.setNome(resultSet.getString("nome"));
            administrador.setEmail(resultSet.getString("email"));
            administrador.setLogin(resultSet.getString("login"));
            administrador.setSenha(resultSet.getString("senha"));
        }
        resultSet.close();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (administrador == null) {
            throw new Exception("Não foi possível obter este administrador");
        }
        
        return administrador;
    }
    
    public List<Administrador> obterTodos() throws Exception {
        List<Administrador> administradores = new ArrayList<Administrador>();
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, nome, email, login, senha FROM administrador");
        
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Administrador admim = new Administrador();
            admim.setId(resultSet.getInt("id"));
            admim.setNome(resultSet.getString("nome"));
            admim.setEmail(resultSet.getString("email"));
            admim.setLogin(resultSet.getString("login"));
            admim.setSenha(resultSet.getString("senha"));
            
            administradores.add(admim);
        }
        resultSet.close();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (administradores.isEmpty()) {
            throw new Exception("Não foi possível obter um cliente");
        }
        
        return administradores;
    }
}
