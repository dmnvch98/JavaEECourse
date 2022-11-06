package org.example.servlet.friendRequest;

import lombok.extern.log4j.Log4j2;
import org.example.model.FriendRequest;
import org.example.service.FriendRequestService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/removefriendsrequest")
@Log4j2
//Т.к. я не могу сделить один сервлет на создание и удаление Запроса в друзья из за отсутсвия метода delete
// в формах html, я решил сделать два сервлета на создание и удаление запроса.
// Оставил глагол Remove в пути для большей ясности
public class RemoveFriendRequestServlet extends HttpServlet {
    private FriendRequestService friendRequestService;

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init();
        friendRequestService = (FriendRequestService) config
                .getServletContext()
                .getAttribute("friendRequestService");
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        int friendRequestId = Integer.parseInt(req.getParameter("friendrequestid"));
        FriendRequest friendRequest = friendRequestService.getFriendRequest(friendRequestId);

        log.info("Remove friends request. Initiator=[{}], Target=[{}]",
                friendRequest.getRequestUser(), friendRequest.getApproveUser());
        friendRequestService.deleteRequest(friendRequest);

        resp.sendRedirect(req.getContextPath() + "/allusers");
    }
}
