package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterFormServlet extends HttpServlet {

    private static final String SIGN_ON_FORM = "/WEB-INF/jsp/account/register.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(SIGN_ON_FORM).forward(req, resp);
    }
}
