package org.example.servlet;

import org.apache.logging.log4j.Logger;
import org.example.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet(name = "signup", value = "/signup")
public class SignUpServlet extends HttpServlet {
    private UserService userService;
    private Logger logger;

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) config
                .getServletContext()
                .getAttribute("userService");
        logger = (Logger) config.getServletContext().getAttribute("logger");
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        resp.sendRedirect(req.getContextPath() + "/view/sign_up.jsp");
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        logger.info("Attempt to signup with username: " + username);
        if (!(username.isEmpty() && password.isEmpty())) {
            try {
                userService.save(username, password);
                req.getSession().setAttribute("isLoggedIn", true);
                resp.sendRedirect(req.getContextPath() + "/allusers");
            } catch (IOException e) {
                logger.error(e);
                resp.sendRedirect(req.getContextPath() + "/signup");
            }
        }
    }
}
