/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.pedido_produto;

/**
 *
 * @author alice
 */
public class Pedido_Produto {
    private int produto_id;
    private int pedido_id;
    private int produto_qtd;

    public int getProduto_id() {
        return produto_id;
    }

    public void setProduto_id(int produto_id) {
        this.produto_id = produto_id;
    }

    public int getPedido_id() {
        return pedido_id;
    }

    public void setPedido_id(int pedido_id) {
        this.pedido_id = pedido_id;
    }

    public int getProduto_qtd() {
        return produto_qtd;
    }

    public void setProduto_qtd(int produto_qtd) {
        this.produto_qtd = produto_qtd;
    }
    
}
