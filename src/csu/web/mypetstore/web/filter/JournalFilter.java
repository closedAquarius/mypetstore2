package csu.web.mypetstore.web.filter;

import csu.web.mypetstore.domain.Account;
import csu.web.mypetstore.service.AccountService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "JournalFilter",urlPatterns = {"/itemForm","/categoryForm","/productForm","/signOff"})
public class JournalFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        AccountService accountService = new AccountService();
        Account loginAccount = (Account) session.getAttribute("loginAccount");
        String categoryId = request.getParameter("categoryId");
        String itemId = request.getParameter("itemId");
        String productId=request.getParameter("productId");
        if(categoryId!=null)
            accountService.updateCategoryJournal(loginAccount,categoryId);
        if(itemId!=null)
            accountService.updateItemJournal(loginAccount,itemId);
        if(productId!=null)
            accountService.updateProductJournal(loginAccount,productId);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
