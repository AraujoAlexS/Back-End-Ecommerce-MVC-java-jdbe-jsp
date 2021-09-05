/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.categoria_produto;

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
public class Categoria_ProdutoDAO extends DAO{
    
    
    public void inserir(int categoria, int produto) throws Exception {
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO categoria_produto (categoria_id, produto_id) VALUES (?, ?)");
        preparedStatement.setInt(1, categoria);
        preparedStatement.setInt(2, produto);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (resultado != 1) {
            throw new Exception("Não foi possível inserir esta relação");
        }        
    }
    
    public void atualizar(Categoria_Produto cp, int id) throws Exception {
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE categoria_produto SET  categoria_id = ? WHERE produto_id = ?");
        preparedStatement.setInt(1, cp.getCategoria_id());        
        preparedStatement.setInt(2, id);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (resultado != 1) {
            throw new Exception("Não foi possível atualizar esta categoria");
        } 
    }
    
    public void remover(int pid, int cid) throws Exception {
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM categoria_produto WHERE produto_id = ? AND categoria_id = ? ");
        preparedStatement.setInt(1, pid);
        preparedStatement.setInt(2, cid);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (resultado != 1) {
            throw new Exception("Não foi possível remover esta tupla de categoria_produto");
        } 
    }
    
    public Categoria_Produto obter(int produto_id) throws Exception {
        Categoria_Produto cp = null;
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT produto_id, categoria_id FROM categoria_produto WHERE produto_id = ?");
        preparedStatement.setInt(1, produto_id);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            cp = new Categoria_Produto();
            cp.setProduto_id(resultSet.getInt("produto_id"));
            cp.setCategoria_id(resultSet.getInt("categoria_id"));
        }
        resultSet.close();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (cp == null) {
            throw new Exception("Não foi possível obter esta categoria");
        }
        
        return cp;
    }
    
    public List<Categoria_Produto> obterTodos() throws Exception {
        List<Categoria_Produto> cps = new ArrayList<Categoria_Produto>();
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT produto_id, categoria_id FROM categoria_produto");
        
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Categoria_Produto cp = new Categoria_Produto();
            cp.setProduto_id(resultSet.getInt("produto_id"));
            cp.setCategoria_id(resultSet.getInt("categoria_id"));
            
            cps.add(cp);
        }
        resultSet.close();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (cps.isEmpty()) {
            throw new Exception("Não foi possível obter nenhuma tupla nesta tabela");
        }
        
        return cps;
    }
    
    
    public List<Categoria_Produto> obterProdutosPorCategoria(int categoria_id) throws Exception {
        List<Categoria_Produto> cps = new ArrayList<Categoria_Produto>();
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT produto_id FROM categoria_produto WHERE categoria_id = ?");
        preparedStatement.setInt(1, categoria_id);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Categoria_Produto cp = new Categoria_Produto();
            cp.setProduto_id(resultSet.getInt("produto_id"));
            cp.setCategoria_id(resultSet.getInt("categoria_id"));
            
            cps.add(cp);
        }
        resultSet.close();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (cps.isEmpty()) {
            throw new Exception("Não foi possível obter nenhuma tupla nesta tabela");
        }
        
        return cps;
    }

}
