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
import modelo.produto.ProdutoModelo;

/**
 *
 * @author alice
 */
public class RemoverProdutoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        int id = Integer.parseInt(request.getParameter("id"));
        
        
        ProdutoModelo produtoModelo = new ProdutoModelo();
        boolean sucesso = false;

        try {
            produtoModelo.remover(id);
            sucesso = true;

        } catch (Exception ex) {
            sucesso = false;
        }
        // saida
         if(sucesso){
            request.setAttribute("mensagem", "Produto removido com sucesso");
        } else{
            request.setAttribute("mensagem", "Não foi possível remover este produto");
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("ListarProdutoServlet");
        dispatcher.forward(request, response);
        
    }
}
