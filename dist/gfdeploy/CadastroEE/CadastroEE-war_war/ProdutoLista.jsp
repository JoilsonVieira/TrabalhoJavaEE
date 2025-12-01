<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="cadastroee.model.Produto"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Produtos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="container mt-4">
        
        <h1>Lista de Produtos</h1>
        
        <a href="ServletProdutoFC?acao=formIncluir" class="btn btn-primary mb-3">Novo Produto</a>
        
        <table class="table table-striped table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Quantidade</th>
                    <th>Preço</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <%
                    // Código Java para recuperar a lista enviada pelo Servlet
                    List<Produto> lista = (List<Produto>) request.getAttribute("lista");
                    
                    if (lista != null) {
                        for (Produto p : lista) {
                %>
                <tr>
                    <td><%= p.getId() %></td>
                    <td><%= p.getNome() %></td>
                    <td><%= p.getQuantidade() %></td>
                    <td>R$ <%= p.getPrecoVenda() %></td>
                    <td>
                        <a href="ServletProdutoFC?acao=formAlterar&id=<%= p.getId() %>" class="btn btn-warning btn-sm">Alterar</a>
                        <a href="ServletProdutoFC?acao=excluir&id=<%= p.getId() %>" class="btn btn-danger btn-sm" onclick="return confirm('Tem certeza que deseja excluir?');">Excluir</a>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
        
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>