/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.compra;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.carrinho.CarrinhoCompraItem;
import modelo.carrinho.CarrinhoCompraModelo;
import modelo.produto.Produto;
import modelo.produto.ProdutoModelo;

public class ComprarProdutoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // manda a  lista de produtos para o perfil do cliente
        ProdutoModelo produtoModelo = new ProdutoModelo();
        try {
            List<Produto> produtoLista = produtoModelo.obterTodosEmEstoque();
            request.setAttribute("produtoLista", produtoLista);
        } catch (Exception ex) {
        }
        // Pega todos os cookies 
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        for (int i = 0; cookies != null && i < cookies.length; i++) {
            if (cookies[i].getName().equals(CarrinhoCompraModelo.CARRINHO_COMPRA_CHAVE)) {
                cookie = cookies[i];
                break;
            }
        }

        if (cookie == null) {
            cookie = new Cookie(CarrinhoCompraModelo.CARRINHO_COMPRA_CHAVE, "");
        }
        cookie.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(cookie);
        // encaminha os cookies
        try {
            List<CarrinhoCompraItem> carrinhoItens = CarrinhoCompraModelo.obterCarrinhoCompra(cookie.getValue());
            if (!carrinhoItens.isEmpty()) {
                request.setAttribute("carrinhoCompraItens", carrinhoItens);
            }
        } catch (Exception ex) {

        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/cliente/compraCliente.jsp");
        dispatcher.forward(request, response);
    }
}
