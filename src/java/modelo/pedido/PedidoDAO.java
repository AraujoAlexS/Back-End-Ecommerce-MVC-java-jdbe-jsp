/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pedido;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.cliente.Cliente;
import modelo.dao.DAO;

/**
 *
 * @author alice
 */
public class PedidoDAO extends DAO{
 
    public void inserir(Pedido pedido) throws Exception {
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO pedido (cliente_id, data) VALUES (?, ?)");
        preparedStatement.setInt(1, pedido.getCliente_id());
        preparedStatement.setDate(2, pedido.getData());
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (resultado != 1) {
            throw new Exception("Não foi possível inserir este pedido");
        }        
    }
    
    public void atualizar(Pedido pedido, int id) throws Exception {
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE pedido SET cliente_id = ?, data = ? WHERE id = ?");
        preparedStatement.setInt(1, pedido.getCliente_id());
        preparedStatement.setDate(2, pedido.getData());        
        preparedStatement.setInt(3, id);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (resultado != 1) {
            throw new Exception("Não foi possível atualizar este pedido");
        } 
    }
    
    public void remover(int id) throws Exception {
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM pedido WHERE id = ?");
        preparedStatement.setInt(1, id);
        int resultado = preparedStatement.executeUpdate();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (resultado != 1) {
            throw new Exception("Não foi possível remover este pedido");
        } 
    }
    
    public Pedido obter(int id) throws Exception {
        Pedido pedido = null;
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, cliente_id, data FROM pedido WHERE id = ?");
        preparedStatement.setInt(1, id);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            pedido = new Pedido();
            pedido.setId(resultSet.getInt("id"));
            pedido.setCliente_id(resultSet.getInt("cliente_id"));
            pedido.setData(resultSet.getDate("data"));
        }
        resultSet.close();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (pedido == null) {
            throw new Exception("Não foi possível obter esta categoria");
        }
        
        return pedido;
    }
    public Pedido obterPorClienteEData(int clienteid, Date data) throws Exception {
        Pedido pedido = null;
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, cliente_id, data FROM pedido WHERE cliente_id = ? AND data = ?");
        preparedStatement.setInt(1, clienteid);
        preparedStatement.setDate(2, data);
        
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            pedido = new Pedido();
            pedido.setId(resultSet.getInt("id"));
            pedido.setCliente_id(resultSet.getInt("cliente_id"));
            pedido.setData(resultSet.getDate("data"));
        }
        resultSet.close();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (pedido == null) {
            throw new Exception("Não foi possível obter esta categoria");
        }
        
        return pedido;
    }
    public java.util.List<Pedido> obterTodos() throws Exception {
        java.util.List<Pedido> pedidos = new ArrayList<Pedido>();
        Connection connection = obterConexao();
        
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT id, cliente_id, data FROM pedido");
        
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Pedido pedido = new Pedido();
            pedido.setId(resultSet.getInt("id"));
            pedido.setCliente_id(resultSet.getInt("cliente_id"));
            pedido.setData(resultSet.getDate("data"));
      
            pedidos.add(pedido);
        }
        resultSet.close();
        preparedStatement.close();
        
        fecharConnection(connection);
        
        if (pedidos.isEmpty()) {
            throw new Exception("Não foi possível obter nenhum pedido");
        }
        
        return pedidos;
    }
}