package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.persistence.JournalDao;
import csu.web.mypetstore.persistence.impl.JournalDaoImpl;
import csu.web.mypetstore.service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        } else if (!judgeCaptcha(req)) {
            req.setAttribute("registerMsg", registerMsg);
            req.getRequestDispatcher(SIGN_ON_FORM).forward(req, resp);
        } else {
            JournalDao journalDao = new JournalDaoImpl();
            Date date = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String currentDate = formatter.format(date);
            String registerString = "User "+ username + " completed registration.";
            journalDao.updateJournal(username, registerString, currentDate, "#C00000");
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

    private boolean judgeCaptcha(HttpServletRequest req){
        HttpSession session = req.getSession();
        String captcha = (String) session.getAttribute("captcha");
        String captchaInput= (String) req.getParameter("captchaInput");
        if(captchaInput==null || captchaInput.equals("")){
            this.registerMsg="请输入验证码";
            return false;
        }
        else if(!captcha.equals(captchaInput)){
            this.registerMsg="验证码不正确";
            return false;
        }
        return true;
    }
}
