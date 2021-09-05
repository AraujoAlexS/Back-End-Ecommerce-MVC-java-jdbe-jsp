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
import modelo.cliente.Cliente;
import modelo.cliente.ClienteModelo;

/**
 *
 * @author alice
 */
public class FormAtualizarClienteServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* entrada */
        String id = request.getParameter("id");
        /* processamento */
        String mensagem = "";
        try {
            ClienteModelo clienteModelo = new ClienteModelo();
            Cliente cliente = clienteModelo.obter(Integer.parseInt(id));
            request.setAttribute("usuario", cliente);
        } catch (Exception ex) {
            mensagem = ex.getMessage();
            request.setAttribute("mensagem", mensagem);
        }
        /* saída */
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/cliente/atualizarCliente.jsp");
        dispatcher.forward(request, response);
    }

}
