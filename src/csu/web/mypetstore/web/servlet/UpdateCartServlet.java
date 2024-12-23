package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.CartItem;
import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.persistence.CartDao;
import csu.web.mypetstore.persistence.CategoryDao;
import csu.web.mypetstore.persistence.ItemDao;
import csu.web.mypetstore.persistence.impl.CartDaoImpl;
import csu.web.mypetstore.persistence.impl.CategoryDaoImpl;
import csu.web.mypetstore.persistence.impl.ItemDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Iterator;

public class UpdateCartServlet extends HttpServlet {
    CartDao cartDao = new CartDaoImpl();

    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("loginAccount");
        Cart cart = (Cart) session.getAttribute("cart");
        Iterator<CartItem> cartItems = cart.getCartItems();
        while (cartItems.hasNext())
        {
            CartItem cartItem = (CartItem) cartItems.next();
            String itemId = cartItem.getItem().getItemId();
            try
            {
                String quantityString = req.getParameter(itemId);
                int quantity = Integer.parseInt(quantityString);

                cartItem.setQuantity(quantity);
                if (quantity < 1)
                {
                    cartDao.deleteItem(account.getUsername(),cartItem);
                }
                else
                {
                    cartDao.updateCart(account.getUsername(),cartItem);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }

        Cart newCart = cartDao.getCart(account.getUsername());
        session.setAttribute("cart", newCart);
        req.getRequestDispatcher(CART_FORM).forward(req, resp);
    }
}
