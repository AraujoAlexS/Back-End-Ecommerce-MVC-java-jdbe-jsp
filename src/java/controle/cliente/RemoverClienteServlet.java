/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.cliente;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.cliente.ClienteModelo;

/**
 *
 * @author alice
 */
public class RemoverClienteServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
       /* entrada */
        String id = request.getParameter("id");
        /* processamento */
        String mensagem = "";
        try {
            ClienteModelo cm = new ClienteModelo();
            cm.remover(Integer.parseInt(id));
            mensagem = "Cliente removido com sucesso";
        } catch (Exception ex) {
            mensagem = ex.getMessage();
        }
        request.setAttribute("mensagem", mensagem);
        /* saída */
        RequestDispatcher dispatcher = request.getRequestDispatcher("InicioServlet");
        dispatcher.forward(request, response);

    }
}
