package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.CartItem;
import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.persistence.CartDao;
import csu.web.mypetstore.persistence.JournalDao;
import csu.web.mypetstore.persistence.impl.CartDaoImpl;
import csu.web.mypetstore.persistence.impl.JournalDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class RemoveCartItemServlet extends HttpServlet {
    CartDao cartDao = new CartDaoImpl();

    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";
    private static final String ERROR_FORM = "/WEB-INF/jsp/common/error.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession session = req.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        String workingItemId=req.getParameter("workingItemId");
        Account account = (Account) session.getAttribute("loginAccount");
        Cart currentCart = cartDao.getCart(account.getUsername());
        CartItem cartItem = (CartItem) currentCart.getItemMap().get(workingItemId);

        if(cartItem==null)
        {
            session.setAttribute("errorMsg", "Attempted to remove null CartItem from Cart.");
            req.getRequestDispatcher(ERROR_FORM).forward(req, resp);
        }
        else
        {
            cartDao.deleteItem(account.getUsername(),cartItem);

            Cart newCart = cartDao.getCart(account.getUsername());
            session.setAttribute("cart", newCart);

            JournalDao journalDao = new JournalDaoImpl();
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String currentDate = formatter.format(date);
            String deleteItemString = "User "+ account.getUsername() + " deleted product "
                    + "<a href=\"productForm?productId=" + workingItemId + "\">" + workingItemId + "<\\a>"
                    + " from the <a href=\"cartForm\">cart</a>.";
            journalDao.updateJournal(account.getUsername(), deleteItemString, currentDate, "#BF9000");

            req.getRequestDispatcher(CART_FORM).forward(req, resp);
        }
    }
}
