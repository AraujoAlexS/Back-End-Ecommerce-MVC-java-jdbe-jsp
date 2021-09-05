/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.produto;

import java.util.List;

/**
 *
 * @author alice
 */
public class ProdutoModelo {

    public Produto obter(int id) throws Exception {
        ProdutoDAO p = new ProdutoDAO();
        return p.obter(id);
    }

    public List<Produto> obterTodos() throws Exception {
        ProdutoDAO p = new ProdutoDAO();
        return p.obterTodos();
    }

    public List<Produto> obterTodosEmEstoque() throws Exception {
        ProdutoDAO p = new ProdutoDAO();
        return p.obterTodosEmEstoque();
    }

    public List<Produto> obterPorNome(String nome) throws Exception {
        if (nome == null) {
            nome = "";
        }
        ProdutoDAO p = new ProdutoDAO();
        return p.obterPorNome(nome);
    }

    public void inserir(Produto produto) throws Exception {
        if (produto == null
                || produto.getNome() == null || produto.getNome().trim().length() == 0
                || produto.getDescricao() == null || produto.getDescricao().trim().length() == 0
                || produto.getPreco() <= 0 || produto.getQuantidade() <= 0
                || produto.getImagem() == null || produto.getImagem().trim().length() == 0) {

            throw new Exception("Um ou mais campos obrigatórios não foram informado ou contém informações invávidas");
        }
        ProdutoDAO produtoDAO = new ProdutoDAO();
        produtoDAO.inserir(produto);
    }

    public void atualizar(Produto produto, int id) throws Exception {

        if (produto == null
                || produto.getNome() == null || produto.getNome().trim().length() == 0
                || produto.getDescricao() == null || produto.getDescricao().trim().length() == 0
                || produto.getPreco() < 0
                || produto.getQuantidade() < 0
                || produto.getImagem() == null || produto.getImagem().trim().length() == 0
                || produto.getMarca() == null || produto.getMarca().trim().length() == 0) {

            throw new Exception("Um ou mais campos obrigatórios não foram informado ou contém informações invávidas");
        } else {

            ProdutoDAO p = new ProdutoDAO();
            p.atualizar(produto, id);
        }
    }

    public void remover(int id) throws Exception {
        ProdutoDAO p = new ProdutoDAO();
        p.remover(id);
    }
}
