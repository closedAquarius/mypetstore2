package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.CartItem;
import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.persistence.CartDao;
import csu.web.mypetstore.persistence.impl.CartDaoImpl;
import csu.web.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddItemToCartServlet extends HttpServlet {

    CartDao cartDao = new CartDaoImpl();
    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String isAdd=session.getAttribute("isAdd").toString();
        String workingItemId = req.getParameter("workingItemId");
        Account account = (Account) session.getAttribute("loginAccount");
        if(account == null)
        {
            resp.sendRedirect("signOnForm");
        }
        else
        {
            Cart currentCart = cartDao.getCart(account.getUsername());
            CartItem cartItem = (CartItem) currentCart.getItemMap().get(workingItemId);

            if(isAdd.equals("true"))
            {
                //购物车中没有该商品，addItem
                if(cartItem==null)
                {
                    CatalogService catalogService = new CatalogService();
                    boolean isInStock = catalogService.isItemInStock(workingItemId);
                    Item item = catalogService.getItem(workingItemId);
                    CartItem newCartItem = new CartItem();
                    newCartItem.setItem(item);
                    newCartItem.setQuantity(1);
                    newCartItem.setInStock(isInStock);
                    cartDao.addItem(account.getUsername(),newCartItem);
                }
                //购物车中有该商品，updateItem
                else
                {
                    cartItem.setQuantity(cartItem.getQuantity() + 1);
                    cartDao.updateCart(account.getUsername(),cartItem);
                }
            }
            session.setAttribute("isAdd", "false");

            Cart cart = cartDao.getCart(account.getUsername());
            session.setAttribute("cart", cart);

            req.getRequestDispatcher(CART_FORM).forward(req, resp);
        }

    }
}
