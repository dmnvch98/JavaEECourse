package org.example.servlet.friendRequest;

import org.example.model.FriendRequest;
import org.example.service.FriendRequestService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/incomingfriendrequests")
public class GetIncomingFriendRequestsServlet extends HttpServlet {
    private FriendRequestService friendRequestService;

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        friendRequestService = (FriendRequestService) config
                .getServletContext()
                .getAttribute("friendRequestService");
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        String currentUsername = (String) req.getSession().getAttribute("username");
        List<FriendRequest> incomingFriendRequests = friendRequestService.getIncomingFriendRequests(currentUsername);
        req.setAttribute("incomingFriendRequests", incomingFriendRequests);
        getServletContext().getRequestDispatcher("/view/incoming_friend_requests.jsp").forward(req, resp);
    }

}
