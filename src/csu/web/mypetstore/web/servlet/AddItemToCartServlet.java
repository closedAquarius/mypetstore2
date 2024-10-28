package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddItemToCartServlet extends HttpServlet {

    private static final String CART_FORM = "/WEB-INF/jsp/cart/cart.jsp";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String isAdd=session.getAttribute("isAdd").toString();
        String workingItemId = req.getParameter("workingItemId");
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        if(isAdd.equals("true")) {
            if (cart.containsItemId(workingItemId)) {
                cart.incrementQuantityByItemId(workingItemId);
                //包含则增加数量
            } else {
                CatalogService catalogService = new CatalogService();
                // isInStock is a "real-time" property that must be updated
                // every time an item is added to the cart, even if other
                // item details are cached.
                boolean isInStock = catalogService.isItemInStock(workingItemId);
                Item item = catalogService.getItem(workingItemId);
                cart.addItem(item, isInStock);
            }
        }
        session.setAttribute("isAdd", "false");
        session.setAttribute("cart", cart);
        req.getRequestDispatcher(CART_FORM).forward(req, resp);
    }
}