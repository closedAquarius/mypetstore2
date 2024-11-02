package csu.web.mypetstore.web.servlet;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.domain.Product;
import csu.web.mypetstore.persistence.JournalDao;
import csu.web.mypetstore.persistence.impl.JournalDaoImpl;
import csu.web.mypetstore.service.AccountService;
import csu.web.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SignOnServlet extends HttpServlet {

    private static final String SIGN_ON_FORM = "/WEB-INF/jsp/account/signon.jsp";
    private static final String MAIN_FORM = "/WEB-INF/jsp/catalog/main.jsp";

    private String username;
    private String password;

    private String msg;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.username = req.getParameter("username");
        this.password = req.getParameter("password");
        //校验用户输入的正确性
        if(!validate()){
            req.setAttribute("signOnMsg", msg);
            req.getRequestDispatcher(SIGN_ON_FORM).forward(req, resp);
        }else {
            AccountService accountService=new AccountService();
            Account loginAccount = accountService.getAccount(username, password);
            if(loginAccount == null){
                this.msg = "用户名或密码错误";
                req.getRequestDispatcher(SIGN_ON_FORM).forward(req, resp);
            } else if (!judgeCaptcha(req)) {
                req.setAttribute("signOnMsg", msg);
                req.getRequestDispatcher(SIGN_ON_FORM).forward(req, resp);
            } else {
                loginAccount.setPassword(null);
                HttpSession session = req.getSession();
                session.setAttribute("loginAccount", loginAccount);
                if (loginAccount.isListOption()) {
                    CatalogService catalogService = new CatalogService();
                    List<Product> myList = catalogService.getProductListByCategory(loginAccount.getFavouriteCategoryId());
                    session.setAttribute("myList", myList);
                }
                JournalDao journalDao = new JournalDaoImpl();
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                String currentDate = formatter.format(date);
                String loginInString = "User "+ username + " logged in.";
                journalDao.updateJournal(username, loginInString, currentDate, "#4472C4");
                req.getRequestDispatcher(MAIN_FORM).forward(req, resp);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    private boolean validate(){
        if (this.username == null || this.username.equals("")) {
            this.msg="用户名不能为空";
            return false;
        }
        if (this.password == null || this.password.equals("")) {
            this.msg="密码不能为空";
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
            this.msg="请输入验证码";
            return false;
        }
        else if(!captcha.equals(captchaInput)){
            this.msg="验证码不正确";
            return false;
        }
        return true;
    }
}
