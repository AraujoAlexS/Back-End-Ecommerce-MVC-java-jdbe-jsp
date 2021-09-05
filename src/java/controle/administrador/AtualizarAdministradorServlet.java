/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.administrador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.administrador.Administrador;
import modelo.administrador.AdministradorModelo;

/**
 *
 * @author alice
 */
public class AtualizarAdministradorServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* entrada */
        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        /* processamento */
        String mensagem = "";
        try {
            Administrador admin = new Administrador();
            
            admin.setNome(nome);
            admin.setEmail(email);
            admin.setLogin(login);
            admin.setSenha(senha);
            AdministradorModelo adminModelo = new AdministradorModelo();
            adminModelo.atualizar(admin, Integer.parseInt(id));
            mensagem = "Administrador atualizado com sucesso";
        } catch (Exception ex) {
            mensagem = ex.getMessage();
        }
        request.setAttribute("mensagem", mensagem);
        /* saída */
        RequestDispatcher dispatcher = request.getRequestDispatcher("InicioServlet");
        dispatcher.forward(request, response);
    }
}
