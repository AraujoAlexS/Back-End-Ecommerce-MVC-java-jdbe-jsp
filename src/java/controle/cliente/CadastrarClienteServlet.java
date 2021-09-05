/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.cliente;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class CadastrarClienteServlet extends HttpServlet {
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    
        //Entrada
        String nome = request.getParameter("nome");
        String endereco = request.getParameter("endereco");
        String email =  request.getParameter("email");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        
        //Processamento
        Cliente cliente = new Cliente();
        cliente.setNome(nome);
        cliente.setEndereco(endereco);
        cliente.setEmail(email);
        cliente.setLogin(login);
        cliente.setSenha(senha);
        
        ClienteModelo clienteModelo = new ClienteModelo();
        boolean sucesso = false;
        try {
            clienteModelo.inserir(cliente);
            sucesso = true;
        } catch (Exception ex) {
            sucesso = false;
        }
        
        //Saída
        if(sucesso){
            request.setAttribute("mensagem", "Cliente cadastrado com sucesso");
        } else{
            request.setAttribute("mensagem", "Não foi possível cadastrar este cliente");
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("novoCliente.jsp");
        dispatcher.forward(request, response);
    }
}
