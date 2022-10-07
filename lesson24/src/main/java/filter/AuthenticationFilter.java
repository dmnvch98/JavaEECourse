package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

@WebFilter(urlPatterns = "/allUsers")
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

        final String username = (String) session.getAttribute("username");
        final String password = (String) session.getAttribute("password");
        if (nonNull(username) && nonNull(password)) {
            chain.doFilter(request, response);
            res.sendRedirect("/allUsers");
        } else {
            res.sendRedirect("/signIn");
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
