package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ExaminationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        AccountService accountService = new AccountService();
        Account account = accountService.getAccount(username);
        PrintWriter out = resp.getWriter();
/*        System.out.println(username);
        System.out.println(account);
        System.out.println(account.getUsername());*/
        resp.setContentType("text/plain");
        if(account==null||account.getUsername()==null){
            out.print("");
        }else {
            out.print("true");
        }
    }
}
