package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Order;
import csu.web.mypetstore.service.AccountService;
import csu.web.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListOrderFormServlet extends HttpServlet {

    private static final String LIST_ORDER_FORM = "/WEB-INF/jsp/order/listOrder.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Account account = (Account) req.getSession().getAttribute("loginAccount");
        OrderService orderService = new OrderService();
        List<Order> orderList=new ArrayList<Order>();
        orderList=orderService.getOrdersByUsername(account.getUsername());
        req.setAttribute("orderList",orderList);
        req.getRequestDispatcher(LIST_ORDER_FORM).forward(req, resp);
    }
}
