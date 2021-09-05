/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.produto;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.produto.Produto;
import modelo.produto.ProdutoModelo;

/**
 *
 * @author alice
 */
public class MostrarProdutoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // entrada
        int id = Integer.parseInt(request.getParameter("id"));
        // processamento
        ProdutoModelo produtoModelo = new ProdutoModelo();
        Produto produto = null;
        boolean sucesso = false;

        try {
            produto = produtoModelo.obter(id);
            sucesso = true;

        } catch (Exception ex) {
            sucesso = false;
        }
        // saida
        if (sucesso) {
            request.setAttribute("produto", produto);
        } else {
            request.setAttribute("mensagem", "Não foi possível remover este produto");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/produto/atualizarProduto.jsp");
        dispatcher.forward(request, response);

    }

}
