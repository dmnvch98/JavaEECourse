package org.example.servlet.friends;

import org.example.model.Friends;
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
import java.util.List;

@WebServlet("/removefriend")
public class RemoveFriendServlet extends HttpServlet {
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
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        User userFriend = userService.getUser(req.getParameter("user_friend"));
        User currentUser = userService.getUser((String) req.getSession().getAttribute("username"));
        List<Friends> friendsRecords = friendService.getFriendsRecords(currentUser, userFriend);
        friendService.removeFriend(friendsRecords.get(0));
        friendService.removeFriend(friendsRecords.get(1));
        resp.sendRedirect(req.getContextPath() + "/myfriends");
    }
}
