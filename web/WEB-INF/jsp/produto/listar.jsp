<%-- 
    Document   : listar
    Created on : 12/09/2020, 21:26:19
    Author     : alice
--%>

<%@page import="modelo.produto.Produto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../cabecalho.jsp" %>

<h3>Cadastro de Produtos</h3>

<form action="ListarProdutoServlet" method="post">
    <div>Nome:</div>
    <div><input type="text" name="nome" /></div>
    <div><input type="submit" value="Consultar" /></div>
</form>
<%
    String mensagem = (String) request.getAttribute("mensagem");
    if (mensagem != null) {
%>
<div><%= mensagem%></div>
<%
    }
%>
<%
    List<Produto> produtoLista = (List<Produto>) request.getAttribute("produtoLista");
    if (produtoLista != null && !produtoLista.isEmpty()) {
%>
<table>
    <tr>
        <td>Id</td>
        <td>Nome</td>
        <td>Descrição</td>
        <td>Preço</td>
        <td>Quantidade</td>
        <td>Imagem</td>
        <td>Marca</td>
        <td>&nbsp;</td>
    </tr>
    <% for (Produto p : produtoLista) {%>
    <tr>
        <td><%= p.getId()%></td>
        <td><%= p.getNome()%></td>
        <td><%= p.getDescricao()%></td>
        <td><%= p.getPreco()%></td>
        <td><%= p.getQuantidade()%></td>
        <td><%= p.getImagem()%></td>
        <td><%= p.getMarca()%></td>
        <td><a href="MostrarProdutoServlet?id=<%= p.getId()%>">Atualizar</a><span> | </span><a href="RemoverProdutoServlet?id=<%= p.getId()%>">Remover</a></td>
    </tr>
    <% } %>
</table>
<%
    }
%>
<a href="NovoProdutoServlet">Cadastrar um Novo Produto</a>
<%@include file="../rodape.jsp" %>