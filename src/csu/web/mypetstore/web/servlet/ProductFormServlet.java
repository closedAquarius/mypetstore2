package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.domain.Product;
import csu.web.mypetstore.persistence.JournalDao;
import csu.web.mypetstore.persistence.impl.JournalDaoImpl;
import csu.web.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProductFormServlet extends HttpServlet {

    private CatalogService catalogService;
    private static final String PRODUCT_FORM = "/WEB-INF/jsp/catalog/product.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId=req.getParameter("productId");
        catalogService = new CatalogService();
        Product product = catalogService.getProduct(productId);
        List<Item> itemList = catalogService.getItemListByProduct(productId);
        HttpSession session = req.getSession();
        session.setAttribute("isAdd","true");
        session.setAttribute("product", product);
        session.setAttribute("itemList", itemList);

      /*  Account account = (Account) session.getAttribute("loginAccount");
        if (account != null)
        {
            JournalDao journalDao = new JournalDaoImpl();
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String currentDate = formatter.format(date);
            String browseItemString = "User "+ account.getUsername() + " browsed the product: "
                    + "<a href=\"productForm?productId=" + productId + "\">" + productId + "</a>.";
            journalDao.updateJournal(account.getUsername(), browseItemString, currentDate, "#70AD47");
        }*/

        req.getRequestDispatcher(PRODUCT_FORM).forward(req, resp);
    }
}
