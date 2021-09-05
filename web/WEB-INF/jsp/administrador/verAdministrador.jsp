<%-- 
    Document   : ver admin
    Created on : 09/09/2020, 10:59:28
    Author     : alice
--%>
<%@page import="modelo.administrador.Administrador"%>
<%@include file="../cabecalho.jsp" %>

<h3>Cadastro do admin</h3>
<jsp:useBean id="usuario" class="modelo.administrador.Administrador" scope="session" />

<%
  Administrador admin = (Administrador) request.getAttribute("usuario");  
%>


<table>
    <tr>
        <td>Nome:</td>
        <td><jsp:getProperty name="usuario" property="nome" /></td>
    </tr>
    <tr>
        <td>E-mail:</td>
        <td><jsp:getProperty name="usuario" property="email" /></td>
    </tr>
    <tr>
        <td>Login:</td>
        <td><jsp:getProperty name="usuario" property="login" /></td>
    </tr>
    
</table>
<div>
    <a href="FormAtualizarAdministrador?id=<jsp:getProperty name="usuario" property="id"/>">Alterar Cadastro</a>
    <span> | </span>
    <a href="RemoverAdministradorServlet?id=<jsp:getProperty name="usuario" property="id"/>">Excluir Cadastro</a>
</div>
<%@include file="../rodape.jsp" %>