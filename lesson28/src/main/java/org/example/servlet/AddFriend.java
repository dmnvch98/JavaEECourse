package org.example.servlet;

import lombok.extern.log4j.Log4j2;
import org.example.model.User;
import org.example.service.FriendService;
import org.example.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addfriend")
@Log4j2
public class AddFriend extends HttpServlet {

    private FriendService friendService;
    private UserService userService;

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init();
        friendService = (FriendService) config
                .getServletContext()
                .getAttribute("friendService");
        userService = (UserService) config
                .getServletContext()
                .getAttribute("userService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String firstUsername = req.getParameter("firstUsername");
        String secondUsername = req.getParameter("secondUsername");

        log.info(firstUsername + " adds " + secondUsername + " to friends");
        User firstUser = userService.getUser(firstUsername);
        User secondUser = userService.getUser(secondUsername);
        friendService.addFriend(firstUser, secondUser);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }


}
