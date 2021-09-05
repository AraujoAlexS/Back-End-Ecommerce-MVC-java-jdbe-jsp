
<%@page import="modelo.produto.Produto"%>
<%@page import="java.util.List"%>

<%@include file="../cabecalho.jsp" %>

<h3>Atualização de Produto</h3>

<%
    Produto p = (Produto) request.getAttribute("produto");
%>

<form action="AtualizarProdutoServlet" method="post">
    <input type="text" name="id" value="<%= p.getId() %>" />
    <div>Nome:</div>
    <div><input type="text" name="nome" value="<%= p.getNome() %>" /></div>
    <div>Descrição:</div>
    <div><input type="text" name="descricao" value="<%= p.getDescricao() %>" /></div>
    <div>Preço:</div>
    <div><input type="text" name="preco" value="<%= p.getPreco()%>" /></div>
    <div>Quantidade:</div>
    <div><input type="text" name="quantidade" value="<%= p.getQuantidade()%>" /></div>
    <div>Imagem:</div>
    <div><input type="text" name="imagem" value="<%= p.getImagem()%>" /></div>
    <div>Marca:</div>
    <div><input type="text" name="marca" value="<%= p.getMarca()%>" /></div>
    
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
<a href="ListarProdutoServlet">Voltar</a>

<%@include file="../rodape.jsp" %>
