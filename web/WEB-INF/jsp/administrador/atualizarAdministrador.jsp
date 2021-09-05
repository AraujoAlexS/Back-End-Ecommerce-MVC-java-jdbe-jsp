
<%@page import="modelo.administrador.Administrador"%>
<%@page import="java.util.List"%>

<%@include file="../cabecalho.jsp" %>

<h3>Atualização de Produto</h3>
<jsp:useBean id="usuario" class="modelo.administrador.Administrador" scope="session" />


<form action="AtualizarAdministradorServlet" method="post">
    <div><input type="hidden" name="id" value="<jsp:getProperty name="usuario" property="id" />" /></div>
    <div>Nome:</div>
    <div><input type="text" name="nome" value="<jsp:getProperty name="usuario" property="nome" />" /></div>
    <div>Login:</div>
    <div><input type="text" name="login" value="<jsp:getProperty name="usuario" property="login" />" /></div>
    <div>Email:</div>
    <div><input type="text" name="email" value="<jsp:getProperty name="usuario" property="email" />" /></div>
    <div>Senha:</div>
    <div><input type="text" name="senha" value="<jsp:getProperty name="usuario" property="senha" />" /></div>

    
    <div><input type="submit" value="Atualizar" /></div>
</form>
<%    String mensagem = (String) request.getAttribute("mensagem");
    if (mensagem != null) {
%>
<div><%= mensagem%></div>
<%
    }
%>
<a href="ListarProdutoServlet">Voltar</a>

<%@include file="../rodape.jsp" %>
