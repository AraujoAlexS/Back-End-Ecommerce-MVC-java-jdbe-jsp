<%-- 
    Document   : novoCliente
    Created on : 29/08/2020, 09:49:45
    Author     : alice
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SMD ecommerce</title>
    </head>
    <body>
        <h1>Cadastro de novo cliente</h1>
        <form action="CadastrarClienteServlet" method="post">
            <div>Nome: </div>
            <div><input type="text" name="nome"></div>
            <div>Endereço: </div>
            <div><input type="text" name="endereco"></div>
            <div>Email: </div>
            <div><input type="text" name="email"></div>
            <div>Login: </div>
            <div><input type="text" name="login"></div>
            <div>Senha</div>
            <div><input type="password" name="senha"></div>
            <div><input type="submit" value="cadastre-se"></div>
        </form>
        <a href="InicioServlet">Fazer login</a>
        <%
        if(request.getAttribute("mensagem") != null){        
        %>
        <div><%= request.getAttribute("mensagem") %></div>
        <%
        }
        %>
    </body>
</html>
