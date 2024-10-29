package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.Order;
import csu.web.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class NewOrderServlet extends HttpServlet {
    private static final String SHIP_ADDRESS = "/WEB-INF/jsp/order/shipAddress.jsp";
    private static final String CONFIRM_ORDER = "/WEB-INF/jsp/order/confirmOrder.jsp";
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/viewOrder.jsp";

    OrderService orderService = new OrderService();
    private Account account;
    private Cart cart;
    private Order order;
    private boolean shippingAddressRequired;
    private boolean newOrderFormSubmited;
    private boolean shipAddressSubmitted;
    private boolean confirmed;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException, IOException {
        HttpSession session = request.getSession();
        account = (Account)session.getAttribute("account");
        cart = (Cart)session.getAttribute("cart");
        order = (Order)session.getAttribute("order");
        //newOrderFormSubmited = (boolean)session.getAttribute("newOrderFormSubmited");


        shippingAddressRequired = request.getParameter("shippingAddressRequired") != null;
        shipAddressSubmitted = "true".equals(request.getParameter("shipAddressSubmitted"));
        confirmed = "true".equals(request.getParameter("confirmed"));

        confirmed = request.getParameter("confirmed") != null;

        if(shippingAddressRequired)
        {
            session.setAttribute("shippingAddressRequired",shippingAddressRequired);
            shippingAddressRequired = false;
            request.getRequestDispatcher(SHIP_ADDRESS).forward(request,response);
        }
        else if(shipAddressSubmitted)
        {
            shipAddressSubmitted = false;
            order.setShipAddress1(request.getParameter("order.shipToFirstName"));
            order.setShipAddress2(request.getParameter("order.shipToFirstName"));
            order.setShipCity(request.getParameter("order.shipCity"));
            order.setShipCountry(request.getParameter("order.shipCountry"));
            order.setShipState(request.getParameter("order.shipState"));
            order.setShipToFirstName(request.getParameter("order.shipAddress1"));
            order.setShipToLastName(request.getParameter("order.shipAddress2"));
            order.setShipZip(request.getParameter("order.shipZip"));
            session.setAttribute("order",order);

            request.getRequestDispatcher(CONFIRM_ORDER).forward(request,response);
        }
        else if(confirmed)
        {
            session.setAttribute("confirmed",confirmed);

            orderService.insertOrder(order);
            System.out.println(order.getOrderId());
            System.out.println(order.getShipAddress1());
            System.out.println(order.getShipAddress2());
            System.out.println(order.getShipCity());
            session.setAttribute("cart",null);
            request.getRequestDispatcher(VIEW_ORDER).forward(request,response);
        }
        else
        {
            newOrderFormSubmited = false;
            order.setCardType(request.getParameter("order.cardType"));
            order.setCreditCard(request.getParameter("order.creditCard"));
            order.setExpiryDate(request.getParameter("order.expiryDate"));
            //order.setStatus(request.getParameter("Deal"));
            order.setBillAddress1(request.getParameter("order.billAddress1"));
            order.setBillAddress2(request.getParameter("order.billAddress1"));
            order.setBillCity(request.getParameter("order.billCity"));
            order.setBillCountry(request.getParameter("order.billCountry"));
            order.setBillState(request.getParameter("order.billState"));
            order.setBillToFirstName(request.getParameter("order.billToFirstName"));
            order.setBillToLastName(request.getParameter("order.billToLastName"));
            order.setBillZip(request.getParameter("order.billZip"));
            session.setAttribute("order",order);

            request.getRequestDispatcher(CONFIRM_ORDER).forward(request,response);
        }
    }

}
