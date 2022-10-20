package org.example.servlet;

import org.example.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "signIn", value = "/signin")
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
    @SuppressWarnings("PMD.UnusedAssignment")
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        resp.sendRedirect(req.getContextPath() + "/view/sign_in.jsp");
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (userService.isExist(username, password)) {
            req.getSession().setAttribute(req.getContextPath() + "isLoggedIn", true);
        }

        resp.sendRedirect(req.getContextPath() + "/allusers");
    }
}
