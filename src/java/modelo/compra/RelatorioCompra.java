/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.compra;

import modelo.cliente.Cliente;
import modelo.pedido.Pedido;
import modelo.produto.Produto;

/**
 *
 * @author alice
 */
public class RelatorioCompra {
    private Cliente cliente;
    private Pedido pedido;
    private Produto produto;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }    
    
}
