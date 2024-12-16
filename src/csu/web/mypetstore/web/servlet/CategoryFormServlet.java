package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Category;
import csu.web.mypetstore.domain.Product;
import csu.web.mypetstore.service.AccountService;
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
    private AccountService accountService;
    private static final String CATEGORY_FORM = "/WEB-INF/jsp/catalog/category.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryId = req.getParameter("categoryId");
        catalogService = new CatalogService();
        accountService = new AccountService();
        Category category = catalogService.getCategory(categoryId);
        List<Product> productList = catalogService.getProductListByCategory(categoryId);
        HttpSession session = req.getSession();
        //修改用户的喜爱
        Account loginAccount = (Account) session.getAttribute("loginAccount");
        if(loginAccount!=null) {
            accountService.updateProfileFavcategory(categoryId, loginAccount);
            Account loginAccount1 = accountService.getAccount(loginAccount.getUsername());
            session.setAttribute("loginAccount", loginAccount1);
            List<Product> myList = catalogService.getProductListByCategory(loginAccount1.getFavouriteCategoryId());
            session.setAttribute("myList", myList);
        }
        session.setAttribute("category", category);
        session.setAttribute("productList", productList);


        req.getRequestDispatcher(CATEGORY_FORM).forward(req, resp);
    }
}
