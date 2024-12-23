package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Cart;
import csu.web.mypetstore.domain.Order;
import csu.web.mypetstore.domain.UserAddress;
import csu.web.mypetstore.persistence.CartDao;
import csu.web.mypetstore.persistence.JournalDao;
import csu.web.mypetstore.persistence.impl.CartDaoImpl;
import csu.web.mypetstore.persistence.impl.JournalDaoImpl;
import csu.web.mypetstore.service.AccountService;
import csu.web.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewOrderServlet extends HttpServlet {
    private static final String SHIP_ADDRESS = "/WEB-INF/jsp/order/shipAddress.jsp";
    private static final String CONFIRM_ORDER = "/WEB-INF/jsp/order/confirmOrder.jsp";
    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/viewOrder.jsp";

    OrderService orderService = new OrderService();
    CartDao cartDao = new CartDaoImpl();
    AccountService accountService = new AccountService();
    private Account account;
    private Cart cart;
    private Order order;
    private boolean shippingAddressRequired;
    private boolean newOrderFormSubmited;
    private boolean shipAddressSubmitted;
    private boolean confirmed;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException, IOException {
        HttpSession session = request.getSession();
        account = (Account) session.getAttribute("loginAccount");
        cart = (Cart)session.getAttribute("cart");
        order = (Order)session.getAttribute("order");
        //newOrderFormSubmited = (boolean)session.getAttribute("newOrderFormSubmited");


        shippingAddressRequired = request.getParameter("shippingAddressRequired") != null;
        System.out.println("shippingAddressRequired: " + shippingAddressRequired);
        shipAddressSubmitted = "true".equals(request.getParameter("shipAddressSubmitted"));
        System.out.println("shipAddressSubmitted: " + shipAddressSubmitted);
        confirmed = "true".equals(request.getParameter("confirmed"));
        System.out.println("confirmed: " + confirmed);

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
            UserAddress userAddress = new UserAddress();
            order.setShipAddress1(request.getParameter("order.shipAddress1"));
            order.setShipAddress2(request.getParameter("order.shipAddress2"));
            order.setShipCity(request.getParameter("order.shipCity"));
            order.setShipCountry(request.getParameter("order.shipCountry"));
            order.setShipState(request.getParameter("order.shipState"));
            order.setShipToFirstName(request.getParameter("order.shipToFirstName"));
            order.setShipToLastName(request.getParameter("order.shipToLastName"));
            order.setShipZip(request.getParameter("order.shipZip"));
            userAddress.setUsername(account.getUsername());
            userAddress.setFirstName(request.getParameter("order.shipToFirstName"));
            userAddress.setLastName(request.getParameter("order.shipToLastName"));
            userAddress.setZip(request.getParameter("order.shipZip"));
            userAddress.setAddress1(request.getParameter("order.shipAddress1"));
            userAddress.setAddress2(request.getParameter("order.shipAddress2"));
            userAddress.setCity(request.getParameter("order.shipCity"));
            userAddress.setCountry(request.getParameter("order.shipCountry"));
            userAddress.setState(request.getParameter("order.shipState"));
            userAddress.setStatus("OK");
            accountService.insertUserAddress(userAddress);
            session.setAttribute("order",order);

            request.getRequestDispatcher(CONFIRM_ORDER).forward(request,response);
        }
        else if(confirmed)
        {
            session.setAttribute("confirmed",confirmed);

            orderService.insertOrder(order);
            session.setAttribute("cart",null);
            cartDao.deleteCart(account.getUsername());

            JournalDao journalDao = new JournalDaoImpl();
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String currentDate = formatter.format(date);
            String addOrderString = "User "+ account.getUsername() + " added a new order "
                    + "<a href=\"viewOrder?orderId=" + order.getOrderId() + "\">"
                    + order.getOrderId() + "</a>.";
            journalDao.updateJournal(account.getUsername(), addOrderString, currentDate, "#ED7D31");

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
