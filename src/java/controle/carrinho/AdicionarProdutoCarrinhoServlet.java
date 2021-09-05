/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.carrinho;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.carrinho.CarrinhoCompraModelo;

/**
 *
 * @author alice
 */
public class AdicionarProdutoCarrinhoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // entrada
        String id = request.getParameter("id");
        String logado = request.getParameter("logado");
        
        Cookie cookie = CarrinhoCompraModelo.obterCookie(request);
        try {
            String novoValor = CarrinhoCompraModelo.adicionarItem(Integer.parseInt(id), 1, cookie.getValue());
            cookie.setValue(novoValor);
        } catch (Exception ex) {
        }
        // saida
        if (Boolean.parseBoolean(logado)) {
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("ComprarProdutoServlet");
            dispatcher.forward(request, response);
        } else {

            RequestDispatcher dispatcher = request.getRequestDispatcher("InicioServlet");
            dispatcher.forward(request, response);
        }
    }
}
