package org.example.servlet.friendrequest.friends;

import lombok.extern.log4j.Log4j2;
import org.example.facades.AddFriendFacade;
import org.example.service.FriendRequestService;
import org.example.service.FriendService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/friend")
@Log4j2
public class AddFriend extends HttpServlet {

    private FriendService friendService;

    private FriendRequestService friendRequestService;

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init();
        friendService = (FriendService) config
                .getServletContext()
                .getAttribute("friendService");
        friendRequestService = (FriendRequestService) config
                .getServletContext()
                .getAttribute("friendRequestService");
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        new AddFriendFacade(friendService, friendRequestService).addFriend(req);
        resp.sendRedirect(req.getContextPath() + "/incomingfriendrequests");
    }
}
