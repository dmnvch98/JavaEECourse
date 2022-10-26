package org.example.servlet;

import lombok.extern.log4j.Log4j2;
import org.example.model.User;
import org.example.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/myfriends")
@Log4j2
public class MyFriendsServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) config
                .getServletContext()
                .getAttribute("userService");
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        String username = (String) req.getSession().getAttribute("username");
        User user = userService.getUser(username);
        List<User> userFriendsUsernames = userService.getUserFriends(user.getId());
        log.info(username + " friends: " + userFriendsUsernames);
        req.setAttribute("userFriends", userFriendsUsernames);
        getServletContext().getRequestDispatcher("/view/my_friends.jsp").forward(req, resp);
    }

}
