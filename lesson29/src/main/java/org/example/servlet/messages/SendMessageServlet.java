package org.example.servlet.messages;

import lombok.extern.log4j.Log4j2;

import org.example.facades.MessageFacade;
import org.example.service.MessageService;
import org.example.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@Log4j2
@WebServlet("/message")
public class SendMessageServlet extends HttpServlet {
    private UserService userService;
    private MessageService messageService;
    private MessageFacade messageFacade;
    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) config.getServletContext().getAttribute("userService");
        messageService = (MessageService) config.getServletContext().getAttribute("messageService");
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        messageFacade = new MessageFacade(userService, messageService);
        messageFacade.getMessages(req);

        getServletContext().getRequestDispatcher("/view/send_message.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        messageFacade.sendMessage(req);

        resp.sendRedirect(req.getContextPath() + "/message?recipient_user="
                + req.getSession().getAttribute("recipient_message_user"));
    }
}
