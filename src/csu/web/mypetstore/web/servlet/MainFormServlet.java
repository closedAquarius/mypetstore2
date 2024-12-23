package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.service.AccountService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MainFormServlet extends HttpServlet {
    private static final String MAIN_FORM = "/WEB-INF/jsp/catalog/main.jsp";
    AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        session.setAttribute("allCategories",accountService.getAllFavcategory());
        session.setAttribute("languages",accountService.getLanguages());
        session.setAttribute("categories",accountService.getCategories());
        req.getRequestDispatcher(MAIN_FORM).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
