package org.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter(urlPatterns = "/allusers")
public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;
        final HttpSession session = req.getSession();

        final Boolean isLoggedIn = (Boolean) session.getAttribute("isLoggedIn");
        if (nonNull(isLoggedIn)) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect("view/sign_in.jsp");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
