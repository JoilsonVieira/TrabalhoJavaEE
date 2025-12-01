package cadastroee.servlets;

import jakarta.ejb.EJB;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import cadastroee.controller.ProdutoFacade;
import cadastroee.model.Produto;

public class ServletProdutoFC extends HttpServlet {

    @EJB
    private ProdutoFacade facade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // 1. Captura qual ação o usuário quer fazer (listar, incluir, excluir...)
        String acao = request.getParameter("acao");
        
        // Se não tiver ação, o padrão é listar
        if (acao == null) {
            acao = "listar";
        }

        // Variável para saber para qual página (JSP) vamos enviar depois
        String destino = "ProdutoLista.jsp";

        try {
            switch (acao) {
                case "listar":
                    List<Produto> lista = facade.findAll();
                    request.setAttribute("lista", lista);
                    destino = "ProdutoLista.jsp";
                    break;

                case "formIncluir":
                    // Apenas manda para a tela vazia de cadastro
                    destino = "ProdutoDados.jsp";
                    break;

                case "incluir":
                    // Cria um produto novo com os dados da tela
                    Produto produtoNovo = new Produto();
                    produtoNovo.setNome(request.getParameter("nome"));
                    produtoNovo.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
                    produtoNovo.setPrecoVenda(Float.parseFloat(request.getParameter("preco")));
                    
                    facade.create(produtoNovo);
                    
                    // Recarrega a lista para mostrar o novo
                    request.setAttribute("lista", facade.findAll());
                    destino = "ProdutoLista.jsp";
                    break;
                    
                case "formAlterar":
                    // Busca o produto pelo ID para preencher a tela antes de editar
                    Integer idAlterar = Integer.parseInt(request.getParameter("id"));
                    Produto produtoExistente = facade.find(idAlterar);
                    request.setAttribute("produto", produtoExistente);
                    destino = "ProdutoDados.jsp";
                    break;

                case "alterar":
                    // Atualiza o produto existente
                    Integer idParaEditar = Integer.parseInt(request.getParameter("id"));
                    Produto produtoEditado = facade.find(idParaEditar);
                    produtoEditado.setNome(request.getParameter("nome"));
                    produtoEditado.setQuantidade(Integer.parseInt(request.getParameter("quantidade")));
                    produtoEditado.setPrecoVenda(Float.parseFloat(request.getParameter("preco")));
                    
                    facade.edit(produtoEditado);
                    
                    request.setAttribute("lista", facade.findAll());
                    destino = "ProdutoLista.jsp";
                    break;

                case "excluir":
                    // Remove o produto pelo ID
                    Integer idExcluir = Integer.parseInt(request.getParameter("id"));
                    Produto produtoExcluir = facade.find(idExcluir);
                    facade.remove(produtoExcluir);
                    
                    request.setAttribute("lista", facade.findAll());
                    destino = "ProdutoLista.jsp";
                    break;
            }
        } catch (Exception e) {
            // Se der erro, apenas volta para a lista (pode melhorar depois)
            request.setAttribute("lista", facade.findAll());
            destino = "ProdutoLista.jsp";
        }

        // Encaminha para a página correta (JSP)
        RequestDispatcher rd = request.getRequestDispatcher(destino);
        rd.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}