
<%@page import="modelo.cliente.Cliente"%>
<%@page import="java.util.List"%>

<%@include file="../cabecalho.jsp" %>

<h3>Atualização de Produto</h3>
<jsp:useBean id="usuario" class="modelo.cliente.Cliente" scope="session" />


<form action="AtualizarClienteServlet" method="post">
    <input type="hidden" name="id" value="<jsp:getProperty name="usuario" property="id" />" />
    <div>Nome:</div>
    <div><input type="text" name="nome" value="<jsp:getProperty name="usuario" property="nome" />" /></div>
    <div>Endereco:</div>
    <div><input type="text" name="endereco" value="<jsp:getProperty name="usuario" property="endereco" />" /></div>
    <div>Login:</div>
    <div><input type="text" name="login" value="<jsp:getProperty name="usuario" property="login" />" /></div>
    <div>Email:</div>
    <div><input type="text" name="email" value="<jsp:getProperty name="usuario" property="email" />" /></div>
    <div>Senha</div>
    <div><input type="text" name="senha" value="<jsp:getProperty name="usuario" property="senha" />" /></div>
    
    <div><input type="submit" value="Atualizar" /></div>
</form>
<%
String mensagem = (String) request.getAttribute("mensagem");
if (mensagem != null) {
%>
<div><%= mensagem %></div>
<%
}
%>
<a href="VerClienteServlet?id<jsp:getProperty name="usuario" property="id" />">Voltar</a>

<%@include file="../rodape.jsp" %>
