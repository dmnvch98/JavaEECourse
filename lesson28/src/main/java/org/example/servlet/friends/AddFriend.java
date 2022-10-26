package org.example.servlet.friends;

import lombok.extern.log4j.Log4j2;
import org.example.model.FriendRequest;
import org.example.model.User;
import org.example.service.FriendRequestService;
import org.example.service.FriendService;

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
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        int friendRequestId = Integer.parseInt(req.getParameter("friendrequestid"));
        FriendRequest friendRequest = friendRequestService.getFriendRequest(friendRequestId);

        log.info(friendRequest.getApproveUser() + " adds " + friendRequest.getRequestUser() + " to friends");

        User firstUser = friendRequest.getApproveUser();
        User secondUser = friendRequest.getRequestUser();
        friendService.addFriend(firstUser, secondUser);
        friendService.addFriend(secondUser, firstUser);
        friendRequestService.deleteRequest(friendRequest);

        resp.sendRedirect(req.getContextPath() + "/getincomingfriendrequests");
    }
}
