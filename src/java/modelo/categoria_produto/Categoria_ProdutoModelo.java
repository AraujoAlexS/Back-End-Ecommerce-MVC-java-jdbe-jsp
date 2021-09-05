/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.categoria_produto;

import java.util.List;
import modelo.categoria.Categoria;
import modelo.categoria.CategoriaModelo;
import modelo.produto.Produto;
import modelo.produto.ProdutoModelo;

/**
 *
 * @author alice
 */
public class Categoria_ProdutoModelo {

    public Categoria_Produto obter(int id) throws Exception {
        Categoria_ProdutoDAO cpDAO = new Categoria_ProdutoDAO();
        return cpDAO.obter(id);
    }

    public List<Categoria_Produto> obterTodos() throws Exception {
        Categoria_ProdutoDAO cpDAO = new Categoria_ProdutoDAO();
        return cpDAO.obterTodos();
    }

    public List<Categoria_Produto> obterProdutosPorCategoria(int categoriaId) throws Exception {
        Categoria_ProdutoDAO cpDAO = new Categoria_ProdutoDAO();
        return cpDAO.obterProdutosPorCategoria(categoriaId);
    }

    public void inserir(int categoriaId, int produtoId) throws Exception {
        if (categoriaId != 0 && produtoId != 0) {

            //checagem se o pedido foi cadastrado no banco de dados
            CategoriaModelo pedidoModelo = new CategoriaModelo();
            Categoria categoria = pedidoModelo.obter(categoriaId);

            //checagem se o produto foi cadastrado no banco de dados
            ProdutoModelo produtoModelo = new ProdutoModelo();
            Produto produto = produtoModelo.obter(produtoId);

            Categoria_ProdutoDAO cpDAO = new Categoria_ProdutoDAO();
            cpDAO.inserir(categoria.getId(), produto.getId());
        } else {
            System.out.println("Categoria ou Produto não existente");
        }
    }

    public void atualizarCategoria(Categoria_Produto cp, int id) throws Exception {
        Categoria_ProdutoDAO cpDAO = new Categoria_ProdutoDAO();
        cpDAO.atualizar(cp, id);
    }

    public void remover(int produtoId, int categoriaId) throws Exception {
        Categoria_ProdutoDAO cp = new Categoria_ProdutoDAO();
        cp.remover(produtoId, categoriaId);
    }

}
