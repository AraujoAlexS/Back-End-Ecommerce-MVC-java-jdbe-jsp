/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.categoria;

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
public class CategoriaDAO extends DAO{
    
    public void inserir(Categoria categoria) throws Exception {
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO categoria (nome, descricao) VALUES (?, ?)");
        preparedStatement.setString(1, categoria.getNome());
        preparedStatement.setString(2, categoria.getDescricao());
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (resultado != 1) {
            throw new Exception("Não foi possível inserir esta categoria");
        }        
    }
    
    public void atualizar(Categoria categoria, int id) throws Exception {
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE categoria SET nome = ?, descricao = ? WHERE id = ?");
        preparedStatement.setString(1, categoria.getNome());
        preparedStatement.setString(2, categoria.getDescricao());        
        preparedStatement.setInt(3, id);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (resultado != 1) {
            throw new Exception("Não foi possível atualizar esta categoria");
        } 
    }
    
    public void remover(int id) throws Exception {
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM categoria WHERE id = ?");
        preparedStatement.setInt(1, id);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (resultado != 1) {
            throw new Exception("Não foi possível remover esta categoria");
        } 
    }
    
    public Categoria obter(int id) throws Exception {
        Categoria categoria = null;
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, nome, descricao FROM categoria WHERE id = ?");
        preparedStatement.setInt(1, id);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            categoria = new Categoria();
            categoria.setId(resultSet.getInt("id"));
            categoria.setNome(resultSet.getString("nome"));
            categoria.setDescricao(resultSet.getString("descricao"));
        }
        resultSet.close();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (categoria == null) {
            throw new Exception("Não foi possível obter esta categoria");
        }
        
        return categoria;
    }
    
    public List<Categoria> obterTodos() throws Exception {
        List<Categoria> categorias = new ArrayList<Categoria>();
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, nome, descricao FROM categoria");
        
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Categoria categoria = new Categoria();
            categoria.setId(resultSet.getInt("id"));
            categoria.setNome(resultSet.getString("nome"));
            categoria.setDescricao(resultSet.getString("descricao"));
      
            categorias.add(categoria);
        }
        resultSet.close();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (categorias.isEmpty()) {
            throw new Exception("Não foi possível obter nenhuma categoria");
        }
        
        return categorias;
    }
}
