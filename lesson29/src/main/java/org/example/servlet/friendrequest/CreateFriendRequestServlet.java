package org.example.servlet.friendrequest;

import lombok.extern.log4j.Log4j2;
import org.example.model.User;
import org.example.service.FriendRequestService;
import org.example.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet("/friendrequest")
public class CreateFriendRequestServlet extends HttpServlet {
    private FriendRequestService friendRequestService;
    private UserService userService;

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init();
        friendRequestService = (FriendRequestService) config
                .getServletContext()
                .getAttribute("friendRequestService");
        userService = (UserService) config
                .getServletContext()
                .getAttribute("userService");
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        String requestUsername = req.getParameter("requestUsername");
        String approveUsername = req.getParameter("approveUsername");
        User requestUser = userService.getUser(requestUsername);
        User approveUser = userService.getUser(approveUsername);

        log.info("Create friend request. Initiator=[{}], Target=[{}]", requestUser, approveUser);
        friendRequestService.createRequest(requestUser, approveUser);
        resp.sendRedirect(req.getContextPath() + "/allusers");
    }
}
