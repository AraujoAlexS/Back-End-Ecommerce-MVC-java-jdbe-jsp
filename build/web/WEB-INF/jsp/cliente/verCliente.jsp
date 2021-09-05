<%-- 
    Document   : ver cliente
    Created on : 09/09/2020, 10:59:28
    Author     : alice
--%>

<%@include file="../cabecalho.jsp" %>

<h3>Cadastro do cliente</h3>
<jsp:useBean id="usuario" class="modelo.cliente.Cliente" scope="session" />

<%
    Cliente cliente = (Cliente) request.getAttribute("usuario");
%>

<table>
    <tr>
        <td>Nome:</td>
        <td><jsp:getProperty name="usuario" property="nome" /></td>
    </tr>
    <tr>
        <td>Endereço:</td>
        <td><jsp:getProperty name="usuario" property="endereco" /></td>
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
            <a href="FormAtualizarClienteServlet?id=<jsp:getProperty name="usuario" property="id" />">Alterar Cadastro</a>
            <span> | </span>
            <a href="RemoverClienteServlet?id=<jsp:getProperty name="usuario" property="id" />">Excluir Cadastro</a>
        </div>
<%@include file="../rodape.jsp" %>