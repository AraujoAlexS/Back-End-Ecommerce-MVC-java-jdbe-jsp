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
public class AtualizarClienteServlet extends HttpServlet {

   
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* entrada */
        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        /* processamento */
        String mensagem = "";
        try {
            Cliente cliente = new Cliente();
            
            cliente.setNome(nome);
            cliente.setEmail(email);
            cliente.setLogin(login);
            cliente.setEndereco(endereco);
            cliente.setSenha(senha);
            ClienteModelo clienteModelo = new ClienteModelo();
            clienteModelo.atualizar(cliente, Integer.parseInt(id));
            mensagem = "Cliente atualizado com sucesso";
        } catch (Exception ex) {
            mensagem = ex.getMessage();
        }
        request.setAttribute("mensagem", mensagem);
        /* saída */
        RequestDispatcher dispatcher = request.getRequestDispatcher("InicioServlet");
        dispatcher.forward(request, response);
    }

}
