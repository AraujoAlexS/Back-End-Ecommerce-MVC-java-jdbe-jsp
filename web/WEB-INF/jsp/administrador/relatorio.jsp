<%@page import="modelo.compra.RelatorioCompra"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../cabecalho.jsp" %>

<h3>Relatório de compras</h3>

<%    String mensagem = (String) request.getAttribute("mensagem");
    if (mensagem != null) {
%>
<div><%= mensagem%></div>
<%
    }
%>
<%
    List<RelatorioCompra> relatorioCompras = (List<RelatorioCompra>) request.getAttribute("relatorio");
    if (relatorioCompras != null && !relatorioCompras.isEmpty()) {
%>
<table border="1">
    <tr>
        <td>Nome do Cliente</td>
        <td>Produto</td>
        <td>Preço</td>
        <td>Quantidade</td>
        <td>Data de compra</td>
        
    </tr>
    <% for (RelatorioCompra rc : relatorioCompras) {%>
    <tr>
        <td><%= rc.getCliente().getNome()%></td>
        <td><%= rc.getProduto().getNome()%></td>
        <td><%= rc.getProduto().getPreco()%></td>
        <td><%= rc.getProduto().getQuantidade()%></td>
        <td><%= rc.getPedido().getData()%></td>
    </tr>
    <% } %>
</table>
<%
    }
%>
<%@include file="../rodape.jsp" %>