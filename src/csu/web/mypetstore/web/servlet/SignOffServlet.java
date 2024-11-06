package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.persistence.JournalDao;
import csu.web.mypetstore.persistence.impl.JournalDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SignOffServlet extends HttpServlet {
    private static final String MAIN_FORM = "/WEB-INF/jsp/catalog/main.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("loginAccount");
        JournalDao journalDao = new JournalDaoImpl();
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String currentDate = formatter.format(date);
        String loginOutString = "User "+ account.getUsername() + " logged out.";
        journalDao.updateJournal(account.getUsername(), loginOutString, currentDate, "#4472C4");

        session.invalidate();
        req.getRequestDispatcher(MAIN_FORM).forward(req, resp);
    }

}
