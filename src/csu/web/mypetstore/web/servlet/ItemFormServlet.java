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

public class ItemFormServlet extends HttpServlet {

    private CatalogService catalogService;
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

        Account account = (Account) session.getAttribute("loginAccount");
        if (account != null)
        {
            JournalDao journalDao = new JournalDaoImpl();
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String currentDate = formatter.format(date);
            String browseItemString = "User "+ account.getUsername() + " browsed the item: "
                    + "<a href=\"itemForm?itemId=" + itemId + "\">" + itemId + "</a>.";
            journalDao.updateJournal(account.getUsername(), browseItemString, currentDate, "#70AD47");
        }

        req.getRequestDispatcher(ITEM_FORM).forward(req, resp);

    }
}
