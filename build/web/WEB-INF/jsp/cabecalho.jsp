<%-- 
    Document   : cabecalho
    Created on : 29/08/2020, 18:32:39
    Author     : alice
--%>

<%@page import="modelo.administrador.Administrador"%>
<%@page import="modelo.cliente.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SMD ecommerce</title>
    </head>
    <body>
        <%
            String usuarioNome = "";
            if (session.getAttribute("usuario") instanceof Cliente) {
                Cliente cliente = (Cliente) session.getAttribute("usuario");
                usuarioNome = cliente.getNome();
            } else {
                Administrador administrador = (Administrador) session.getAttribute("usuario");
                usuarioNome = administrador.getNome();

            }

        %>
        <h1>Olá, <%= usuarioNome%> !</h1>
        <a href="LogoutServlet">Sair</a>
        <hr/>
