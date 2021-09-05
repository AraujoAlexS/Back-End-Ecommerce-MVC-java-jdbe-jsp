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
import modelo.produto.Produto;
import modelo.produto.ProdutoModelo;

/**
 *
 * @author alice
 */
public class AtualizarProdutoServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* entrada */
        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        String descricao = request.getParameter("descricao");
        String preco = request.getParameter("preco");
        String quantidade = request.getParameter("quantidade");
        String imagem = request.getParameter("imagem");
        String marca = request.getParameter("marca");
        
        /* processamento */
        String mensagem = "";
        try {
            Produto produto = new Produto();
            produto.setNome(nome);
            produto.setDescricao(descricao);
            produto.setPreco(Double.parseDouble(preco));
            produto.setQuantidade(Integer.parseInt(quantidade));
            produto.setImagem(imagem);
            produto.setMarca(marca);
            ProdutoModelo produtoModelo = new ProdutoModelo();
            produtoModelo.atualizar(produto, Integer.parseInt(id));
            mensagem = "Produto atualizado com sucesso";
        } catch (Exception ex) {
            mensagem = ex.getMessage();
        }
        request.setAttribute("mensagem", mensagem);
        /* saída */
        RequestDispatcher dispatcher = request.getRequestDispatcher("ListarProdutoServlet");
        dispatcher.forward(request, response);
    }

}
