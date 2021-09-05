<%-- 
    Document   : principal
    Created on : 29/08/2020, 10:59:28
    Author     : alice
--%>

<%@page import="modelo.carrinho.CarrinhoCompraItem"%>
<%@page import="modelo.produto.Produto"%>
<%@page import="java.util.List"%>
<%@include file="../cabecalho.jsp" %>

<h3>Página principal do cliente</h3>

<div><a href="VerClienteServlet">Meu Cadastro</a></div>
<div><a href="ComprarProdutoServlet">Ver produtos</a></div>

<hr/>
<h3>Produtos Disponíveis</h3>
<%            /* Recupera a lista de produtos que foi gerada e encaminhada pelo InicioServlet */
    List<Produto> produtoLista = (List<Produto>) request.getAttribute("produtoLista");
    /* Se a lista tiver produtos a serem exibidos */
    if (produtoLista != null && !produtoLista.isEmpty()) {
%>
<table border="1">
    <tr>
        <td>Nome</td>
        <td>Descrição</td>
        <td>Preço</td>
        <td>Quantidade</td>
        <td>Imagem</td>
        <td>Marca</td>
        <td>&nbsp;</td>
    </tr>
    <%
        boolean logado = true;
        String login = "";
        String senha = "";
        String perfil = "cliente";
        if (session.getAttribute("usuario") instanceof Cliente) {
            Cliente cliente = (Cliente) session.getAttribute("usuario");
            login = cliente.getLogin();
            senha = cliente.getSenha();
        }
        /* Percorre a lista de produtos e faz a exibição, de cada produto, na página */
        for (int i = 0; i < produtoLista.size(); i++) {
            Produto p = produtoLista.get(i);
    %>
    <tr>
        <td><%= p.getNome()%></td>
        <td><%= p.getDescricao()%></td>
        <td><%= p.getPreco()%></td>
        <td><%= p.getQuantidade()%></td>
        <td><%= p.getImagem()%></td>
        <td><%= p.getMarca()%></td>
        <td><a href="AdicionarProdutoCarrinhoServlet?id=<%= p.getId()%>&logado=<%= logado%>&senha=<%= senha%>&login=<%= login%>&perfil=<%=perfil%>">Adicionar ao carrinho</a></td>
    </tr>
    <%
        }
    %>
</table>
<%
} else {
%>
<div>Não existem produtos a serem exibidos</div>
<%
    }
%>
<hr/>
<h3>Carrinho de compras</h3>
<%%>
<%
    List<CarrinhoCompraItem> carrinhoCompraItens = (List<CarrinhoCompraItem>) request.getAttribute("carrinhoCompraItens");
    if (carrinhoCompraItens == null || carrinhoCompraItens.isEmpty()) {

%>
<div><span>Carrinho de compras vazio</span></div>
<% } else { %>
<table border="1">
    <tr>
        <td>Nome</td>
        <td>Descrição</td>
        <td>Preço</td>
        <td>Quantidade</td>
        <td>Imagem</td>
        <td>Marca</td>
        <td>&nbsp;</td>
    </tr>

    <%
        /* Percorre a lista de produtos no carrinho e faz a exibição, de cada produto, na página */
        double total = 0;
        for (CarrinhoCompraItem cci : carrinhoCompraItens) {
            total += cci.getProduto().getPreco() * cci.getQuantidade();
    %>
    <tr>
        <td><%= cci.getProduto().getNome()%></td>
        <td><%= cci.getProduto().getDescricao()%></td>
        <td><%= cci.getProduto().getPreco()%></td>
        <td><%= cci.getQuantidade()%></td>
        <td><%= cci.getProduto().getImagem()%></td>
        <td><%= cci.getProduto().getMarca()%></td>
        <td><a href="RemoverProdutoCarrinhoServlet?id=<%= cci.getProduto().getId()%>">Remover do carrinho</a></td>
    </tr>
    <%
        }
    %>
</table>
<div><span>Total R$: <%= total%> reais</span></div>
<%}%>


<%@include file="../rodape.jsp" %>