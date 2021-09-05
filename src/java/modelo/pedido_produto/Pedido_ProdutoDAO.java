/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pedido_produto;

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
public class Pedido_ProdutoDAO extends DAO{
    
    public void inserir(int pedido, int produto, int quantidade) throws Exception {
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO pedido_produto (pedido_id, produto_id, produto_qtd) VALUES (?,?, ?)");
        preparedStatement.setInt(1, pedido);
        preparedStatement.setInt(2, produto);
        preparedStatement.setInt(3, quantidade);
        
        
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (resultado != 1) {
            throw new Exception("Não foi possível inserir esta relação");
        }        
    }
    public void atualizar(Pedido_Produto pp, int pedidoId) throws Exception {
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE pedido_produto SET  pedido_id = ?, produto_id = ?, produto_qtd = ? WHERE pedido_id = ?");
        preparedStatement.setInt(1, pp.getPedido_id());        
        preparedStatement.setInt(2, pp.getProduto_id());
        preparedStatement.setInt(3, pp.getProduto_qtd());
        preparedStatement.setInt(4, pedidoId);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (resultado != 1) {
            throw new Exception("Não foi possível atualizar esta tupla");
        } 
    }
    
    public void remover(int prodId, int pedId) throws Exception {
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM pedido_produto WHERE produto_id = ? AND pedido_id = ? ");
        preparedStatement.setInt(1, prodId);
        preparedStatement.setInt(2, pedId);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (resultado != 1) {
            throw new Exception("Não foi possível remover esta tupla");
        } 
    }
    
    public List<Pedido_Produto> obterTodos() throws Exception {
        List<Pedido_Produto> pps = new ArrayList<Pedido_Produto>();
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT produto_id, pedido_id, produto_qtd FROM pedido_produto");
        
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Pedido_Produto pp = new Pedido_Produto();
            pp.setProduto_id(resultSet.getInt("produto_id"));
            pp.setPedido_id(resultSet.getInt("pedido_id"));
            pp.setProduto_qtd(resultSet.getInt("produto_qtd"));
            
            pps.add(pp);
        }
        resultSet.close();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (pps.isEmpty()) {
            throw new Exception("Não foi possível obter nenhuma tupla nesta tabela");
        }
        
        return pps;
    }
   
}
