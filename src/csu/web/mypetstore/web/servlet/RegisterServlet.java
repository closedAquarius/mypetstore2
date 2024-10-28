package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    private static final String SIGN_ON_FORM = "/WEB-INF/jsp/account/signon.jsp";
    private static final String REGISTER_FORM = "/WEB-INF/jsp/account/register.jsp";


    private Account registerAccount = new Account();

    String registerMsg = null;
    private String username;
    private String password;
    private String repeatPassword;
    private String email;
    private String firstName;
    private String lastName;
    private String status;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phone;
    private String favouriteCategoryId;
    private String languagePreference;
    private boolean listOption;
    private boolean bannerOption;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        username = req.getParameter("username");
        password = req.getParameter("password");
        repeatPassword = req.getParameter("repeatPassword");
        email = req.getParameter("email");
        firstName = req.getParameter("firstName");
        lastName = req.getParameter("lastName");
        status = req.getParameter("status");
        address1 = req.getParameter("address1");
        address2 = req.getParameter("address2");
        city = req.getParameter("city");
        state = req.getParameter("state");
        zip = req.getParameter("zip");
        country = req.getParameter("country");
        phone = req.getParameter("phone");
        favouriteCategoryId = req.getParameter("favouriteCategoryId");
        languagePreference = req.getParameter("languagePreference");
        listOption = Boolean.parseBoolean(req.getParameter("listOption"));
        bannerOption = Boolean.parseBoolean(req.getParameter("bannerOption"));
        if(!validate()){
            req.setAttribute("registerMsg", registerMsg);
            req.getRequestDispatcher(REGISTER_FORM).forward(req, resp);
        }
        else {
            AccountService accountService = new AccountService();
            registerAccount.setUsername(username);
            registerAccount.setPassword(password);
            registerAccount.setFirstName(firstName);
            registerAccount.setLastName(lastName);
            registerAccount.setEmail(email);
            registerAccount.setStatus(status);
            registerAccount.setAddress1(address1);
            registerAccount.setAddress2(address2);
            registerAccount.setCity(city);
            registerAccount.setState(state);
            registerAccount.setZip(zip);
            registerAccount.setCountry(country);
            registerAccount.setPhone(phone);
            registerAccount.setFavouriteCategoryId(favouriteCategoryId);
            registerAccount.setLanguagePreference(languagePreference);
            registerAccount.setListOption(listOption);
            registerAccount.setBannerOption(bannerOption);
            accountService.insertAccount(registerAccount);
            req.getRequestDispatcher(SIGN_ON_FORM).forward(req, resp);
        }
    }

    private boolean validate(){
        if (this.username == null || this.username.equals("")) {
            this.registerMsg="用户名不能为空";
            return false;
        }
        if (this.password == null || this.password.equals("")) {
            this.registerMsg="密码不能为空";
            return false;
        }
        if(!this.password.equals(this.repeatPassword)){
            System.out.println(password+"  "+repeatPassword);
            this.registerMsg="两次密码不一致";
            return false;
        }
        //其他校验
        return true;
    }
}