/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle.produto;

import java.io.IOException;
import java.io.PrintWriter;
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
public class InserirProdutoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // entrada
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        double preco = Double.parseDouble(request.getParameter("preco"));
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        String imagem = request.getParameter("imagem");
        String marca = request.getParameter("marca");
        // processamento

        Produto produto = new Produto();

        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setPreco(preco);
        produto.setQuantidade(quantidade);
        produto.setImagem(imagem);
        produto.setMarca(marca);

        ProdutoModelo produtoModelo = new ProdutoModelo();
        boolean sucesso = false;

        try {
            produtoModelo.inserir(produto);
            sucesso = true;

        } catch (Exception ex) {
            sucesso = false;
        }
        // saida
         if(sucesso){
            request.setAttribute("mensagem", "Produto cadastrado com sucesso");
        } else{
            request.setAttribute("mensagem", "Não foi possível cadastrar este produto");
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/produto/novoProduto.jsp");
        dispatcher.forward(request, response);
    }

}
