/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.categoria;

import java.util.List;

/**
 *
 * @author alice
 */
public class CategoriaModelo {
    public Categoria obter(int id) throws Exception {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        return categoriaDAO.obter(id);
    }
    
    public List<Categoria> obterTodos() throws Exception {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        return categoriaDAO.obterTodos();
    }
    
    public void inserir(Categoria categoria) throws Exception {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        categoriaDAO.inserir(categoria);
    }
    
    public void atualizar(Categoria categoria, int id) throws Exception {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        categoriaDAO.atualizar(categoria, id);
    }
    
    public void remover(int id) throws Exception {
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        categoriaDAO.remover(id);
    }   
}
