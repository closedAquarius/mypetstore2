package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Journal;
import csu.web.mypetstore.domain.Order;
import csu.web.mypetstore.persistence.JournalDao;
import csu.web.mypetstore.persistence.impl.JournalDaoImpl;
import csu.web.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JournalFormServlet extends HttpServlet
{
    private static final String JOURNAL_FORM = "/WEB-INF/jsp/journal/journal.jsp";

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Account account = (Account) request.getSession().getAttribute("loginAccount");

        JournalDao journalDao = new JournalDaoImpl();
        List<Journal> journals = journalDao.getAllJournals(account.getUsername());
        Collections.reverse(journals);

        request.setAttribute("journals",journals);
        request.getRequestDispatcher(JOURNAL_FORM).forward(request, response);
    }
}
