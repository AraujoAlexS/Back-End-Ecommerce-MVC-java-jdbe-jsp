/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.produto;

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
public class ProdutoDAO extends DAO{
    
    public void inserir(Produto produto) throws Exception {
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO produto (nome, descricao, preco, quantidade, imagem, marca) VALUES (?, ?, ?, ?, ?, ?)");
        preparedStatement.setString(1, produto.getNome());
        preparedStatement.setString(2, produto.getDescricao());
        preparedStatement.setDouble(3, produto.getPreco());
        preparedStatement.setInt(4, produto.getQuantidade());
        preparedStatement.setString(5, produto.getImagem());
        preparedStatement.setString(6, produto.getMarca());
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (resultado != 1) {
            throw new Exception("Não foi possível inserir este cliente");
        }        
    }
    
    public void atualizar(Produto produto, int id) throws Exception {
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE produto SET nome = ?, descricao = ?, preco = ?, quantidade = ?, imagem = ?, marca = ? WHERE id = ?");
        
        preparedStatement.setString(1, produto.getNome());
        preparedStatement.setString(2, produto.getDescricao());
        preparedStatement.setDouble(3, produto.getPreco());
        preparedStatement.setInt(4, produto.getQuantidade());
        preparedStatement.setString(5, produto.getImagem());
        preparedStatement.setString(6, produto.getMarca());
        
        preparedStatement.setInt(7, id);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (resultado != 1) {
            throw new Exception("Não foi possível atualizar este produto");
        } 
    }
    
    
    public void remover(int id) throws Exception {
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM produto WHERE id = ?");
        preparedStatement.setInt(1, id);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (resultado != 1) {
            throw new Exception("Não foi possível remover este cliente");
        } 
    }
    
    public Produto obter(int id) throws Exception {
        Produto produto = null;
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, nome, descricao, preco, quantidade, imagem, marca FROM produto WHERE id = ?");
        preparedStatement.setInt(1, id);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            produto = new Produto();
            produto.setId(resultSet.getInt("id"));
            produto.setNome(resultSet.getString("nome"));
            produto.setDescricao(resultSet.getString("descricao"));
            produto.setPreco(resultSet.getDouble("preco"));
            produto.setQuantidade(resultSet.getInt("quantidade"));
            produto.setImagem(resultSet.getString("imagem"));
            produto.setMarca(resultSet.getString("marca"));
        }
        resultSet.close();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (produto == null) {
            throw new Exception("Não foi possível obter este produto");
        }
        
        return produto;
    }
    
    public List<Produto> obterPorNome(String nome) throws Exception {
        List<Produto> produtos = new ArrayList<Produto>();
        Connection connection = obterConexao();

        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, nome, descricao, preco, quantidade, imagem, marca FROM produto WHERE upper(descricao) LIKE upper(?)");
        preparedStatement.setString(1, "%" + nome + "%");

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Produto produto = new Produto();
            produto.setId(resultSet.getInt("id"));
            produto.setNome(resultSet.getString("nome"));
            produto.setDescricao(resultSet.getString("descricao"));
            produto.setPreco(resultSet.getDouble("preco"));
            produto.setQuantidade(resultSet.getInt("quantidade"));
            produto.setImagem(resultSet.getString("imagem"));
            produto.setMarca(resultSet.getString("marca"));

            produtos.add(produto);
        }
        resultSet.close();
        preparedStatement.close();

        fecharConnection(connection);

        if (produtos.isEmpty()) {
            throw new Exception("Não foi possível obter um produto");
        }

        return produtos;
    }
    
    public List<Produto> obterTodos() throws Exception {
        List<Produto> produtos = new ArrayList<Produto>();
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, nome, descricao, preco, quantidade, imagem, marca FROM produto");
        
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Produto produto = new Produto();
            
            produto.setId(resultSet.getInt("id"));
            produto.setNome(resultSet.getString("nome"));
            produto.setDescricao(resultSet.getString("descricao"));
            produto.setPreco(resultSet.getDouble("preco"));
            produto.setQuantidade(resultSet.getInt("quantidade"));
            produto.setImagem(resultSet.getString("imagem"));
            produto.setMarca(resultSet.getString("marca"));
            
            produtos.add(produto);
        }
        resultSet.close();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (produtos.isEmpty()) {
            throw new Exception("Não foi possível obter um cliente");
        }
        
        return produtos;
    }
    public List<Produto> obterTodosEmEstoque() throws Exception {
        List<Produto> produtos = new ArrayList<Produto>();
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, nome, descricao, preco, quantidade, imagem, marca FROM produto WHERE quantidade > 0");
        
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Produto produto = new Produto();
            
            produto.setId(resultSet.getInt("id"));
            produto.setNome(resultSet.getString("nome"));
            produto.setDescricao(resultSet.getString("descricao"));
            produto.setPreco(resultSet.getDouble("preco"));
            produto.setQuantidade(resultSet.getInt("quantidade"));
            produto.setImagem(resultSet.getString("imagem"));
            produto.setMarca(resultSet.getString("marca"));
            
            produtos.add(produto);
        }
        resultSet.close();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (produtos.isEmpty()) {
            throw new Exception("Não foi possível obter um cliente");
        }
        
        return produtos;
    }
}
