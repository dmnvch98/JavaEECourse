package org.example.servlet.authorization;

import at.favre.lib.crypto.bcrypt.BCrypt.Hasher;
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

@WebServlet(name = "signup", value = "/signup")
@Log4j2
public class SignUpServlet extends HttpServlet {
    private UserService userService;

    private Hasher hasher;

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) config
                .getServletContext()
                .getAttribute("userService");
        hasher = (Hasher) config
                .getServletContext()
                .getAttribute("hasher");
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        resp.sendRedirect(req.getContextPath() + "/view/sign_up.jsp");
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        AuthorizationFacade authorizationFacade = new AuthorizationFacade(userService);
        if (authorizationFacade.signUp(req, hasher)) {
            resp.sendRedirect(req.getContextPath() + "/allusers");
        } else {
            resp.sendRedirect(req.getContextPath() + "/signup");
        }
    }
}
