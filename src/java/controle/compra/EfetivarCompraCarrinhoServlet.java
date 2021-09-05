/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.compra;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.carrinho.CarrinhoCompraItem;
import modelo.carrinho.CarrinhoCompraModelo;
import modelo.pedido.Pedido;
import modelo.pedido.PedidoModelo;
import modelo.pedido_produto.Pedido_ProdutoModelo;
import modelo.produto.Produto;
import modelo.produto.ProdutoModelo;

/**
 *
 * @author alice
 */
public class EfetivarCompraCarrinhoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // Entrada
        String mensagem = "";
        int id = Integer.parseInt(request.getParameter("id"));

        //  CRIAÇÃO DE CLASSES NECESSÁRIAS
        ProdutoModelo produtoModelo = new ProdutoModelo();
        Produto produto = null;
        // Criando um pedido no banco de dados para acomodar a ação de compras
        // data do pedido
        long millis = System.currentTimeMillis();
        Date data = new Date(millis);

        // instância do pedido        
        Pedido pedido = new Pedido();
        pedido.setCliente_id(id);
        pedido.setData(data);
        // inserir o objeto na tabela pedido
        PedidoModelo pedidoModelo = new PedidoModelo();
        try {
            pedidoModelo.inserir(pedido);
        } catch (Exception ex) {
            
        }
        // Pega todos os cookies 
        Cookie cookie = CarrinhoCompraModelo.obterCookie(request);
       
        // Transforma os cookies em uma lista de produtos e os insere no banco de dados
        try {
            List<CarrinhoCompraItem> carrinhoItens = CarrinhoCompraModelo.obterCarrinhoCompra(cookie.getValue());
            if(!carrinhoItens.isEmpty()){
                for(CarrinhoCompraItem cci :carrinhoItens){
                    Pedido_ProdutoModelo ppm = new Pedido_ProdutoModelo();
                    // inserindo na tabela pedido_produto o pedido realizado pelo cliente neste exato momento
                    ppm.inserir(pedidoModelo.obterPorClienteEData(id, data).getId(), cci.getProduto().getId(), cci.getQuantidade());
                    // alterando o registro do produto comprado para que sua quantidade seja subtraída no BD
                    produto = produtoModelo.obter(cci.getProduto().getId());
                    produto.setQuantidade((produto.getQuantidade() - cci.getQuantidade()));
                    produtoModelo.atualizar(produto, produto.getId());
                }
            } else {
                mensagem = "Nenhum item no carrinho";
            }
        } catch (Exception ex) {
            mensagem = "Erro ao realizar a compra";
        }
        mensagem = "Compra efetivada com sucesso";
        request.setAttribute("mensagem", mensagem);
               
        RequestDispatcher dispatcher = request.getRequestDispatcher("ComprarProdutoServlet");
        dispatcher.forward(request, response);
    }
}
