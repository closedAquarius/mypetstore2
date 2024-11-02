package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Category;
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

public class CategoryFormServlet extends HttpServlet {

    private CatalogService catalogService;
    private static final String CATEGORY_FORM = "/WEB-INF/jsp/catalog/category.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryId = req.getParameter("categoryId");
        catalogService = new CatalogService();
        Category category = catalogService.getCategory(categoryId);
        List<Product> productList = catalogService.getProductListByCategory(categoryId);
        HttpSession session = req.getSession();
        session.setAttribute("category", category);
        session.setAttribute("productList", productList);

        Account account = (Account) session.getAttribute("loginAccount");
        if (account != null)
        {
            JournalDao journalDao = new JournalDaoImpl();
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String currentDate = formatter.format(date);
            String browseCategoryString = "User "+ account.getUsername() + " browsed the product category: "
                    + "<a href=\"categoryForm?categoryId=" + categoryId + "\">" + categoryId + "</a>.";
            journalDao.updateJournal(account.getUsername(), browseCategoryString, currentDate, "#70AD47");
        }

        req.getRequestDispatcher(CATEGORY_FORM).forward(req, resp);
    }
}
