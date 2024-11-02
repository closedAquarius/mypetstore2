package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.domain.Product;
import csu.web.mypetstore.service.AccountService;
import csu.web.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ItemFormServlet extends HttpServlet {

    private CatalogService catalogService;
    private AccountService accountService;
    private static final String ITEM_FORM = "/WEB-INF/jsp/catalog/item.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String itemId = req.getParameter("itemId");
        catalogService = new CatalogService();
        Item item = catalogService.getItem(itemId);
        Product product=item.getProduct();

        HttpSession session = req.getSession();
        //req.setAttribute("item", item);
        session.setAttribute("isAdd","true");
        session.setAttribute("item", item);
        session.setAttribute("product", product);
        req.getRequestDispatcher(ITEM_FORM).forward(req, resp);

    }
}
