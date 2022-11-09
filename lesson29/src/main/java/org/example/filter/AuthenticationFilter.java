package org.example.filter;

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;


@WebFilter(urlPatterns = "/*")
@Log4j2
public class AuthenticationFilter implements Filter {

    private Set<String> allowedPaths;

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        allowedPaths = Set.of("/signin", "/signup", "/sign_in.jsp", "/sign_up.jsp");
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
            throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpSession session = req.getSession();

        final boolean isLoggedIn = session != null && session.getAttribute("isLoggedIn") != null;
        log.info("Trying to access to " + req.getRequestURI());
        boolean allowedPath = allowedPaths.stream()
                .anyMatch(path -> req.getRequestURI()
                        .endsWith(path));

        if (!isLoggedIn && !allowedPath) {
            request.getRequestDispatcher("/view/sign_in.jsp").forward(request, response);
            return;
        }
        chain.doFilter(request, response);
    }

}
