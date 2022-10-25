package org.example.servlet.authorization;

import lombok.extern.log4j.Log4j2;
import org.example.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "signIn", value = "/signin")
@Log4j2
public class SignInServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) config
                .getServletContext()
                .getAttribute("userService");
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        resp.sendRedirect(req.getContextPath() + "/view/sign_in.jsp");
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        log.info("Attempt to login with username: " + username);
        if (userService.isExist(username, password)) {
            log.info("success");
            req.getSession().setAttribute("isLoggedIn", true);
            req.getSession().setAttribute("username", username);
            req.getSession().setAttribute("username", username);
            resp.sendRedirect(req.getContextPath() + "/allusers");
        } else {
            log.info("failed");
            resp.sendRedirect(req.getContextPath() + "/signup");
        }
    }
}
