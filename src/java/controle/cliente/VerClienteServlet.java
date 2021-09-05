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
import javax.servlet.http.HttpSession;
import modelo.cliente.Cliente;


/**
 *
 * @author alice
 */
public class VerClienteServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession sessao = request.getSession();
        if (sessao.getAttribute("usuario") != null && sessao.getAttribute("usuario") instanceof Cliente) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/cliente/verCliente.jsp");
            dispatcher.forward(request, response);
            System.out.println("fim do despacho");
        } else {
            request.setAttribute("mensagem", "Você não pode ter acesso a este conteúdo privado");
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }

    }
}