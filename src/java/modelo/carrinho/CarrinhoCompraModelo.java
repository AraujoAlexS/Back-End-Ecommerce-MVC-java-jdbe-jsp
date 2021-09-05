/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.carrinho;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import modelo.produto.Produto;
import modelo.produto.ProdutoModelo;

/**
 *
 * @author alice
 */
public class CarrinhoCompraModelo {

    public static final String CARRINHO_COMPRA_CHAVE = "smdecommerce.carrinho";
    private static final String SEPARADOR_PRODUTO = "&";
    private static final String SEPARADOR_CAMPO = ":";

    public static final Cookie obterCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        
        for(int i = 0; cookies != null && i < cookies.length; i++){
            if(cookies[i].getName().equals(CarrinhoCompraModelo.CARRINHO_COMPRA_CHAVE)){
                cookie = cookies[i];
                break;
            }
        }
        return cookie;
    }
    
    public static final List<CarrinhoCompraItem> obterCarrinhoCompra(String valor) throws Exception {
        List<CarrinhoCompraItem> carrinhoItens = new ArrayList<CarrinhoCompraItem>();

        if (valor == null || valor.trim().length() == 0 || !valor.contains(SEPARADOR_CAMPO)) {
            return carrinhoItens;
        }

        ProdutoModelo produtoModelo = new ProdutoModelo();

        if (valor.contains(SEPARADOR_PRODUTO)) {
            String[] produtos = valor.split(SEPARADOR_PRODUTO);
            for (int i = 0; i < produtos.length; i++) {
                String[] item = produtos[i].split(SEPARADOR_CAMPO);

                CarrinhoCompraItem carrinhoCompraItem = new CarrinhoCompraItem();
                Produto produto = produtoModelo.obter(Integer.parseInt(item[0]));
                carrinhoCompraItem.setProduto(produto);
                carrinhoCompraItem.setQuantidade(Integer.parseInt(item[1]));

                carrinhoItens.add(carrinhoCompraItem);
            }
        } else {
            String[] item = valor.split(SEPARADOR_CAMPO);

            CarrinhoCompraItem carrinhoCompraItem = new CarrinhoCompraItem();
            Produto produto = produtoModelo.obter(Integer.parseInt(item[0]));
            carrinhoCompraItem.setProduto(produto);
            carrinhoCompraItem.setQuantidade(Integer.parseInt(item[1]));

            carrinhoItens.add(carrinhoCompraItem);
        }
        return carrinhoItens;
    }

    public static final String adicionarItem(int produtoid, int quantidade, String valor) throws Exception {
        List<CarrinhoCompraItem> carrinhoItens = obterCarrinhoCompra(valor);
        if (carrinhoItens.isEmpty()) {
            return produtoid + SEPARADOR_CAMPO + quantidade;
        }

        boolean adicionou = false;
        String resultado = "";

        for (CarrinhoCompraItem cci : carrinhoItens) {
            if (cci.getProduto().getId() == produtoid) {
                cci.setQuantidade(cci.getQuantidade() + quantidade);
                adicionou = true;
            }
            if (!resultado.isEmpty()) {
                resultado += SEPARADOR_PRODUTO;
            }
            resultado += cci.getProduto().getId() + SEPARADOR_CAMPO + cci.getQuantidade();
        }
        if (!adicionou) {
            resultado += SEPARADOR_PRODUTO + produtoid + SEPARADOR_CAMPO + quantidade;
        }
        return resultado;
    }

    public static final String removerProduto(int produtoid, String valor) throws Exception {
        List<CarrinhoCompraItem> carrinhoItens = obterCarrinhoCompra(valor);

        if (carrinhoItens.isEmpty()) {
            return "";
        }

        String resultado = "";
        for (CarrinhoCompraItem cci : carrinhoItens) {
            if (cci.getProduto().getId() == produtoid) {
                continue;
            }
            if (!resultado.isEmpty()) {
                resultado += SEPARADOR_PRODUTO;
            }
            resultado += cci.getProduto().getId() + SEPARADOR_CAMPO + cci.getQuantidade();
        }
        return resultado;
    }
}