/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.cliente;

import java.util.List;

/**
 *
 * @author alice
 */
public class ClienteModelo {
    
    public Cliente obterPorLogin(String login) throws Exception{
        if (login == null || login.trim().length() == 0){
            return null;
        }
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.obterPorLogin(login);
    }
    
    public boolean identificar(String login, String senha) throws Exception{
        if (login == null || login.trim().length() == 0 || senha == null || senha.trim().length() == 0){
            return false;
        }
        Cliente cliente = obterPorLogin(login);
        
        return (cliente.getSenha().equals(senha));
    }
    
     public void inserir(Cliente cliente) throws Exception {
        if(cliente == null ||
           cliente.getNome() == null || cliente.getNome().trim().length() == 0 ||
           cliente.getEndereco() == null || cliente.getEndereco().trim().length() == 0 ||
           cliente.getEmail() == null || cliente.getEmail().trim().length() == 0 ||
           cliente.getLogin() == null || cliente.getLogin().trim().length() == 0 ||
           cliente.getSenha() == null || cliente.getSenha().trim().length() == 0){
           
           throw new Exception("Um ou mais campos obrigatórios não foram informado");
        }
        
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.inserir(cliente);
    }
     
    public Cliente obter(int id) throws Exception {
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.obter(id);
    }
    
    public void remover(int id) throws Exception {
        if(id <= 0){
             throw new Exception("Um ou mais campos obrigatórios não foram informado");
        }
        ClienteDAO c = new ClienteDAO();
        c.remover(id);
    }
    
    public void atualizar(Cliente cliente, int id) throws Exception {
        if(cliente == null || id == 0 ||
           cliente.getNome() == null || cliente.getNome().trim().length() == 0 ||
           cliente.getEndereco() == null || cliente.getEndereco().trim().length() == 0 ||
           cliente.getEmail() == null || cliente.getEmail().trim().length() == 0 ||
           cliente.getLogin() == null || cliente.getLogin().trim().length() == 0 ||
           cliente.getSenha() == null || cliente.getSenha().trim().length() == 0){
           
           throw new Exception("Um ou mais campos obrigatórios não foram informado");
        }
        ClienteDAO clienteDAO = new ClienteDAO();
        clienteDAO.atualizar(cliente, id);
    }
}
