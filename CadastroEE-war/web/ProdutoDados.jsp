<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="cadastroee.model.Produto"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dados do Produto</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body class="container mt-4">
        
        <%
            // Lógica para saber se é Inclusão ou Alteração
            Produto produto = (Produto) request.getAttribute("produto");
            String acao = "incluir"; // Padrão
            String idVal = "";
            String nomeVal = "";
            String qtdVal = "";
            String precoVal = "";
            String titulo = "Inclusão de Produto";
            
            if (produto != null) {
                acao = "alterar";
                titulo = "Alteração de Produto";
                idVal = String.valueOf(produto.getId());
                nomeVal = produto.getNome();
                qtdVal = String.valueOf(produto.getQuantidade());
                precoVal = String.valueOf(produto.getPrecoVenda());
            }
        %>
        
        <h1><%= titulo %></h1>
        
        <form action="ServletProdutoFC" method="POST" class="mt-3">
            
            <input type="hidden" name="acao" value="<%= acao %>">
            
            <% if (produto != null) { %>
                <input type="hidden" name="id" value="<%= idVal %>">
            <% } %>
            
            <div class="mb-3">
                <label class="form-label">Nome:</label>
                <input type="text" name="nome" class="form-control" value="<%= nomeVal %>" required>
            </div>
            
            <div class="mb-3">
                <label class="form-label">Quantidade:</label>
                <input type="number" name="quantidade" class="form-control" value="<%= qtdVal %>" required>
            </div>
            
            <div class="mb-3">
                <label class="form-label">Preço:</label>
                <input type="text" name="preco" class="form-control" value="<%= precoVal %>" required>
                <div class="form-text">Use ponto para decimais (ex: 19.90)</div>
            </div>
            
            <button type="submit" class="btn btn-success">Salvar</button>
            <a href="ServletProdutoFC?acao=listar" class="btn btn-secondary">Cancelar</a>
            
        </form>
            
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    </body>
</html>