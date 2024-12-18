package csu.web.mypetstore.web.servlet;

import com.alibaba.fastjson.JSON;
import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.CartItem;
import csu.web.mypetstore.domain.Item;
import csu.web.mypetstore.persistence.CartDao;
import csu.web.mypetstore.persistence.ItemDao;
import csu.web.mypetstore.persistence.impl.CartDaoImpl;
import csu.web.mypetstore.persistence.impl.ItemDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UpdateCartItemServlet extends HttpServlet {
    CartDao cartDao = new CartDaoImpl();
    ItemDao itemDao = new ItemDaoImpl();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        Account account = (Account) session.getAttribute("loginAccount");
        Cart cart = (Cart) session.getAttribute("cart");
        String itemId = req.getParameter("itemId");
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(quantity);
        Item item = itemDao.getItem(itemId);
        cartItem.setItem(item);
        try
        {
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
        Cart newCart = cartDao.getCart(account.getUsername());
        session.setAttribute("cart", newCart);
        System.out.println(newCart.getSubTotal());
        //返回更新后的购物车项总价和购物车总价
        PrintWriter out = resp.getWriter();
        Map<String, BigDecimal> totals = new HashMap<>();
        totals.put("cartItemTotal", cartItem.getTotal());
        totals.put("subTotal", newCart.getSubTotal());
        String result = JSON.toJSONString(totals);
        resp.setContentType("text/json");
        out.print(result);
    }
}
