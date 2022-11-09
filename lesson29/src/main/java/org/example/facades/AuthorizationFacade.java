package org.example.facades;

import at.favre.lib.crypto.bcrypt.BCrypt.Hasher;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

@Log4j2
@RequiredArgsConstructor
public class AuthorizationFacade {
    private final UserService userService;

    public boolean signIn(final HttpServletRequest req) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        log.info("Attempt to login with username: " + username);
        if (userService.verifyUser(username, password)) {
            log.info("success");
            setSessionAttributes(req, username);
            return true;
        } else {
            log.info("failed");
            return false;
        }
    }

    public boolean signUp(final HttpServletRequest req, final Hasher hasher) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        log.info("Attempt to signup with username: " + username);
        if (!(username.isEmpty() && password.isEmpty())) {
            try {
                int hashCost = 12;
                String hashedPassword = hasher.hashToString(hashCost, password.toCharArray());
                userService.save(username, hashedPassword, "USER", new Date());
                setSessionAttributes(req, username);
                log.info("success");
                return true;
            } catch (IOException e) {
                log.error(e);
            }
        }
        return false;
    }

    private void setSessionAttributes(final HttpServletRequest req, final String username) {
        req.getSession().setAttribute("isLoggedIn", true);
        req.getSession().setAttribute("username", username);
    }
}
