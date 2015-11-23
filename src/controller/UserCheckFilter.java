package controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.Client;
import domain.User;
 
/**
 * Servlet Filter implementation class UserCheckFilter
 */
public class UserCheckFilter implements Filter {
    private String LOGIN_ACTION_URI;
     
    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        LOGIN_ACTION_URI = fConfig.getInitParameter("loginActionURI");
    }
 
    /**
     * @see Filter#destroy()
     */
    public void destroy() {
    }
 
    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
         
        if ((user == null || user instanceof Client) && !LOGIN_ACTION_URI.equals(req.getRequestURI())){
            /*RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
            rd.forward(request, response);*/
        	HttpServletResponse r = (HttpServletResponse) response;
        	r.sendRedirect("../login.jsp");
            return;
        }
         
        chain.doFilter(request, response);
    }
}