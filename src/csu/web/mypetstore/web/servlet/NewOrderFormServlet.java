package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.Order;
import csu.web.mypetstore.domain.UserAddress;
import csu.web.mypetstore.persistence.CartDao;
import csu.web.mypetstore.persistence.impl.CartDaoImpl;
import csu.web.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class NewOrderFormServlet extends HttpServlet {

    private static final String NEW_ORDER_FORM = "/WEB-INF/jsp/order/newOrder.jsp";
    CartDao cartDao=new CartDaoImpl();
    AccountService accountService=new AccountService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Account loginAccount = (Account)session.getAttribute("loginAccount");
        Cart cart = (Cart)session.getAttribute("cart");
        System.out.println(cart);

        if(loginAccount == null)
        {
            resp.sendRedirect("signOnForm");
        }
        else
        {
            Order order = new Order();
            order.initOrder(loginAccount,cart);
            session.setAttribute("order",order);
            List<UserAddress> allAddress= accountService.getUserOKAddressByUsername(loginAccount.getUsername());
            session.setAttribute("addresses",allAddress);
            req.getRequestDispatcher(NEW_ORDER_FORM).forward(req, resp);
        }
    }
}
