package cadastroee.servlets;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import cadastroee.controller.ProdutoFacade; // <--- MUDOU AQUI (Tirei o Local)
import cadastroee.model.Produto;

public class ServletProduto extends HttpServlet {

    // Aqui injetamos o EJB para acessar o banco de dados
    @EJB
    private ProdutoFacade facade; // <--- MUDOU AQUI TAMBÉM (Tirei o Local)

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Teste de Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Listagem de Produtos</h1>");
            
            // Aqui usamos o facade para buscar a lista do banco
            List<Produto> listaProdutos = facade.findAll();
            
            out.println("<ul>");
            for (Produto p : listaProdutos) {
                out.println("<li>" + p.getNome() + " - R$ " + p.getPrecoVenda() + "</li>");
            }
            out.println("</ul>");
            
            out.println("</body>");
            out.println("</html>");
        }
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