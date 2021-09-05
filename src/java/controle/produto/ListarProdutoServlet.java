/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.produto;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class ListarProdutoServlet extends HttpServlet {

   
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    
        // entradas
        String nomeProduto = request.getParameter("nome");
        
        
        // processamento
        ProdutoModelo produtoModelo = new ProdutoModelo();
        try {
            // adicionar produtos do modelo a uma lista
            List<Produto> produtoLista = produtoModelo.obterPorNome(nomeProduto);
            request.setAttribute("produtoLista", produtoLista);
        } catch (Exception ex) {
            request.setAttribute("mensagem", ex.getMessage());
        }
        
        // saida
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/produto/listar.jsp");
        dispatcher.forward(request, response);
    }
    
}
