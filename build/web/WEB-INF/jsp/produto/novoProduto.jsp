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

<form action="InserirProdutoServlet" method="post">
    <div>Nome</div>
    <div><input type="text" name="nome" /></div>
    <div>Descrição:</div>
    <div><input type="text" name="descricao" /></div>
    <div>Preço:</div>
    <div><input type="text" name="preco" /></div>
    <div>Quantidade:</div>
    <div><input type="text" name="quantidade" /></div>
    <div>Imagem:</div>
    <div><input type="text" name="imagem" /></div>
    <div>Marca:</div>
    <div><input type="text" name="marca" /></div>
    <div><input type="submit" value="inserir" /></div>
</form>
<%
    String mensagem = (String) request.getAttribute("mensagem");
    if (mensagem != null) {
%>
<div><%= mensagem%></div>
<%
    }
%>
<a href="ListarProdutoServlet">Voltar</a>

<%@include file="../rodape.jsp" %>