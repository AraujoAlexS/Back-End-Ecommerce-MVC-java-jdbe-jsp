/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pedido;

import java.sql.Date;
import java.util.List;
import modelo.cliente.Cliente;
import modelo.cliente.ClienteModelo;

/**
 *
 * @author alice
 */
public class PedidoModelo {

    public Pedido obter(int id) throws Exception {
        PedidoDAO pedidoDAO = new PedidoDAO();
        return pedidoDAO.obter(id);
    }
    public Pedido obterPorClienteEData(int clienteId, Date data) throws Exception{
        PedidoDAO pedidoDAO = new PedidoDAO();
        return pedidoDAO.obterPorClienteEData(clienteId, data);
    }
    public List<Pedido> obterTodos() throws Exception {
        PedidoDAO pedidoDAO = new PedidoDAO();
        return pedidoDAO.obterTodos();
    }

    public void inserir(Pedido pedido) throws Exception {
        if (pedido != null) {

            PedidoDAO pedidoDAO = new PedidoDAO();
            pedidoDAO.inserir(pedido);
        }
    }

    public void atualizar(Pedido pedido, int id) throws Exception {
        PedidoDAO pedidoDAO = new PedidoDAO();
        pedidoDAO.atualizar(pedido, id);
    }

    public void remover(int id) throws Exception {
        PedidoDAO pedidoDAO = new PedidoDAO();
        pedidoDAO.remover(id);
    }
}
