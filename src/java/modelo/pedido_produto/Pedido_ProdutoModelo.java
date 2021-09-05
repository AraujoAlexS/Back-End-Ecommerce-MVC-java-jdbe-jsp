/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pedido_produto;

import java.util.List;
import modelo.pedido.Pedido;
import modelo.pedido.PedidoModelo;
import modelo.produto.Produto;
import modelo.produto.ProdutoModelo;

/**
 *
 * @author alice
 */
public class Pedido_ProdutoModelo {

    public List<Pedido_Produto> obterTodos() throws Exception {
        Pedido_ProdutoDAO ppDAO = new Pedido_ProdutoDAO();
        return ppDAO.obterTodos();
    }

    public void inserir(int pedidoId, int produtoId, int quantidade) throws Exception {
        if (pedidoId != 0 && produtoId != 0 && quantidade != 0) {
                        
            Pedido_ProdutoDAO ppDAO = new Pedido_ProdutoDAO();
            ppDAO.inserir(pedidoId, produtoId, quantidade);
        } else {
            System.out.println("Pedido ou Produto não existente");
        
        }

    }

    public void atualizar(Pedido_Produto pp, int id) throws Exception {
        Pedido_ProdutoDAO ppDAO = new Pedido_ProdutoDAO();
        ppDAO.atualizar(pp, id);
    }

    public void remover(int produtoId, int pedidoId) throws Exception {
        Pedido_ProdutoDAO pp = new Pedido_ProdutoDAO();
        pp.remover(produtoId, pedidoId);
    }

}
