package org.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;


@WebFilter(urlPatterns = "/allusers")
public class AuthenticationFilter implements Filter {

    private static Set<String> ALLOWED_PATHS;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ALLOWED_PATHS = Set.of("", "/signin", "/signup");
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;
        final HttpSession session = req.getSession();

        final boolean isLoggedIn = (session != null && session.getAttribute("isLoggedIn") != null);
        String path = req
                .getRequestURI()
                .substring(req.getContextPath().length())
                .replaceAll("/+$", "");
        boolean allowedPath = ALLOWED_PATHS.contains(path);

        if (isLoggedIn || allowedPath) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(req.getContextPath() + "/view/sign_in.jsp");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
