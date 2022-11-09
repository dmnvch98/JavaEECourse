package org.example.servlet.authorization;

import lombok.extern.log4j.Log4j2;
import org.example.facades.AuthorizationFacade;
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
        AuthorizationFacade authorizationFacade = new AuthorizationFacade(userService);
        if (authorizationFacade.signIn(req)) {
            resp.sendRedirect(req.getContextPath() + "/allusers");
        } else {
            resp.sendRedirect(req.getContextPath() + "/signup");
        }
    }
}
