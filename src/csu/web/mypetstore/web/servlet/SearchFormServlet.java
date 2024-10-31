package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Product;
import csu.web.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class SearchFormServlet extends HttpServlet {

    private static final String SEARCH_FORM = "/WEB-INF/jsp/catalog/searchProducts.jsp";
    private static final String MAIN_FORM = "/WEB-INF/jsp/catalog/main.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        if (keyword != null) {
            CatalogService catalogService = new CatalogService();
            List<Product> products = catalogService.searchProductList(keyword);
            HttpSession session = req.getSession();
            session.setAttribute("productList", products);
            req.getRequestDispatcher(SEARCH_FORM).forward(req, resp);
        }else {
            req.getRequestDispatcher(MAIN_FORM).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
