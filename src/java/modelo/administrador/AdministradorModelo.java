/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.administrador;

import java.util.List;

/**
 *
 * @author alice
 */
public class AdministradorModelo {

    public Administrador obter(int id) throws Exception {
        AdministradorDAO administradorDAO = new AdministradorDAO();
        return administradorDAO.obter(id);
    }

    public Administrador obterPorLogin(String login) throws Exception {
        if (login == null || login.trim().length() == 0) {
            return null;
        }
        AdministradorDAO administradorDAO = new AdministradorDAO();
        return administradorDAO.obterPorLogin(login);
    }

    public boolean identificar(String login, String senha) throws Exception {
        if (login == null || login.trim().length() == 0 || senha == null || senha.trim().length() == 0) {
            return false;
        }
        Administrador administrador = obterPorLogin(login);

        return (administrador.getSenha().equals(senha));
    }

    public void remover(int id) throws Exception {
        AdministradorDAO a = new AdministradorDAO();
        a.remover(id);
    }

    public void atualizar(Administrador administrador, int id) throws Exception {

        if (administrador == null
                || administrador.getNome() == null || administrador.getNome().trim().length() == 0
                || administrador.getLogin() == null || administrador.getLogin().trim().length() == 0
                || administrador.getEmail() == null || administrador.getEmail().trim().length() == 0
                || administrador.getSenha() == null || administrador.getSenha().trim().length() == 0) {

            throw new Exception("Um ou mais campos obrigatórios não foram informado ou contém informações invávidas");
        } else {
            AdministradorDAO administradorDAO = new AdministradorDAO();
            administradorDAO.atualizar(administrador, id);
        }

    }
}
