package csu.web.mypetstore.web.servlet;

import com.alibaba.fastjson.JSON;
import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Order;
import csu.web.mypetstore.domain.UserAddress;
import csu.web.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ChangeAddressServlet extends HttpServlet {
    AccountService accountService = new AccountService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String addressId = req.getParameter("addressId");
        String isDelete = req.getParameter("isDelete");
        HttpSession session = req.getSession();
        Account loginAccount =(Account)session.getAttribute("loginAccount");
        if (isDelete.equals("choose")) {
            UserAddress address = accountService.getUserAddressByAddressId(loginAccount.getUsername(), addressId);
            Order order=(Order) session.getAttribute("order");
            order.setShipToFirstName(address.getFirstName());
            order.setShipToLastName(address.getLastName());
            order.setShipAddress1(address.getAddress1());
            order.setShipAddress2(address.getAddress2());
            order.setShipCity(address.getCity());
            order.setShipState(address.getState());
            order.setShipZip(address.getZip());
            order.setShipCountry(address.getCountry());
            resp.setContentType("text/json");
            session.setAttribute("order", order);
            String result = JSON.toJSONString(address);
            PrintWriter out = resp.getWriter();
            out.print(result);
        }else if (isDelete.equals("delete")) {
            accountService.deleteUserAddress(loginAccount.getUsername(), addressId);
            resp.setContentType("text/plain");
            PrintWriter out = resp.getWriter();
            out.print("delete success");
        } else if (isDelete.equals("getDetails")) {
            UserAddress address =accountService.getUserAddressByAddressId(loginAccount.getUsername(), addressId);
            resp.setContentType("text/json");
            String result = JSON.toJSONString(address);
            System.out.println(result);
            PrintWriter out = resp.getWriter();
            out.print(result);
        } else if (isDelete.equals("setMain")) {
            accountService.upadteUserMainAddress(loginAccount.getUsername(), addressId);
            resp.setContentType("text/plain");
            PrintWriter out = resp.getWriter();
            out.print("set main success");
        }

    }
}
