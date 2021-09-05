/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.administrador.Administrador;
import modelo.administrador.AdministradorModelo;
import modelo.carrinho.CarrinhoCompraItem;
import modelo.carrinho.CarrinhoCompraModelo;
import modelo.cliente.Cliente;
import modelo.cliente.ClienteModelo;
import modelo.produto.Produto;
import modelo.produto.ProdutoModelo;

/**
 *
 * @author alice
 */
public class LoginServlet extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        //Entrada
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String perfil = request.getParameter("perfil");
        //Processamento

        boolean sucesso = false;

        if (perfil != null && perfil.equals("cliente")) {

            ClienteModelo clienteModelo = new ClienteModelo();
            Cliente cliente = null;

            try {
                // -- checando se os valores são válidos
                sucesso = clienteModelo.identificar(login, senha);

                // --- Iniciando objeto com um cliente válido no BD ---
                if (sucesso) {
                    cliente = clienteModelo.obterPorLogin(login);
                }

                // --- Criando a sessão e jogando o cliente nela ---
                HttpSession session = request.getSession(true);
                session.setAttribute("usuario", cliente);

            } catch (Exception ex) {
                sucesso = false;
            }

            //Saída
            if (sucesso) {
                
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/cliente/principal.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("mensagem", "Login ou senha inválidos");
                RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
            }

        } else {
            if (perfil != null && perfil.equals("administrador")) {
                AdministradorModelo administradorModelo = new AdministradorModelo();
                Administrador administrador = null;

                try {
                    // -- checando se os valores são válidos
                    sucesso = administradorModelo.identificar(login, senha);

                    // --- Iniciando objeto com um administrador válido no BD ---
                    if (sucesso) {
                        administrador = administradorModelo.obterPorLogin(login);
                    }

                    // --- Criando a sessão e jogando o administrador nela ---
                    HttpSession session = request.getSession(true);
                    session.setAttribute("usuario", administrador);

                } catch (Exception ex) {
                    sucesso = false;
                }

                //Saída
                if (sucesso) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/administrador/principal.jsp");
                    dispatcher.forward(request, response);
                } else {
                    request.setAttribute("mensagem", "Login ou senha inválidos");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("InicioServlet");
                    dispatcher.forward(request, response);
                }
            } else {
                request.setAttribute("mensagem", "Login, senha ou perfil inválido");
                RequestDispatcher dispatcher = request.getRequestDispatcher("InicioServlet");
                dispatcher.forward(request, response);

            }
        }
    }
}
