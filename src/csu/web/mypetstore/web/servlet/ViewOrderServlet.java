package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Order;
import csu.web.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewOrderServlet extends HttpServlet {

    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/viewOrder.jsp";
    private String orderId;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        orderId = req.getParameter("orderId");
        System.out.println("orderId: " + orderId);
        HttpSession session = req.getSession();
        OrderService orderService = new OrderService();
        Order order = orderService.getOrder(Integer.parseInt(orderId));
        session.setAttribute("order", order);
        req.getRequestDispatcher(VIEW_ORDER).forward(req, resp);
    }
}
