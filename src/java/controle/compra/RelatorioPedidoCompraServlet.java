/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.compra;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.cliente.Cliente;
import modelo.cliente.ClienteModelo;
import modelo.compra.RelatorioCompra;
import modelo.pedido.Pedido;
import modelo.pedido.PedidoModelo;
import modelo.pedido_produto.Pedido_Produto;
import modelo.pedido_produto.Pedido_ProdutoModelo;
import modelo.produto.Produto;
import modelo.produto.ProdutoModelo;

/**
 *
 * @author alice
 */
public class RelatorioPedidoCompraServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String mensagem = "";
        // Pseudo-código: Para cada tupla na tabela pedido_produto, crie uma instância da classe RelatorioCompra
        // e adicione nela o cliente que realizou o pedido, o produto comprado e o pedido. Depois
        // crie uma lista de todos os RelatorioCompra e envie para a página determinada

        //
        List<RelatorioCompra> relatorioCompras = new ArrayList<RelatorioCompra>();
        try {

            // Recupera todos os pedidos registrados em pedido_produto
            Pedido_ProdutoModelo pedidoProdutoModelo = new Pedido_ProdutoModelo();
            List<Pedido_Produto> listaPedidos_Produtos = pedidoProdutoModelo.obterTodos();

            // cria as instâcias necessárias para preencher a classe RelatórioCompra
            ProdutoModelo produtoModelo = new ProdutoModelo();
            Produto produto = null;

            PedidoModelo pedidoModelo = new PedidoModelo();
            Pedido pedido = null;

            ClienteModelo clienteModelo = new ClienteModelo();
            Cliente cliente = null;

            for (Pedido_Produto pp : listaPedidos_Produtos) {
                RelatorioCompra rc = new RelatorioCompra();
                //
                produto = produtoModelo.obter(pp.getProduto_id());
                produto.setQuantidade(pp.getProduto_qtd());

                pedido = pedidoModelo.obter(pp.getPedido_id());

                cliente = clienteModelo.obter(pedido.getCliente_id());

                rc.setCliente(cliente);
                rc.setPedido(pedido);
                rc.setProduto(produto);

                relatorioCompras.add(rc);
            }
        } catch (Exception ex) {
        }
        if (relatorioCompras != null) {
            for (RelatorioCompra rc : relatorioCompras) {
                System.out.println(rc.getCliente().getNome());
                System.out.println(rc.getProduto().getNome());
                System.out.println(rc.getPedido().getData());
            }
        } else {
            System.out.println("deu erro mermão ta null o bagulho");
        }
        request.setAttribute("relatorio", relatorioCompras);

        mensagem = "Relatorio obtido com sucesso";
        request.setAttribute("mensagem", mensagem);

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/administrador/relatorio.jsp");
        dispatcher.forward(request, response);
    }
}
