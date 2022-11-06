package org.example.servlet.messages;

import lombok.extern.log4j.Log4j2;
import org.example.model.Message;
import org.example.model.User;
import org.example.service.MessageService;
import org.example.service.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Log4j2
@WebServlet("/sendmessage")
public class SendMessageServlet extends HttpServlet {
    private UserService userService;
    private MessageService messageService;

    @Override
    public void init(final ServletConfig config) throws ServletException {
        super.init(config);
        userService = (UserService) config.getServletContext().getAttribute("userService");
        messageService = (MessageService) config.getServletContext().getAttribute("messageService");
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        String recipientUsername = req.getParameter("recipient_user");
        req.getSession().setAttribute("recipient_message_user", recipientUsername);
        User sender = userService.getUser((String) req.getSession().getAttribute("username"));
        User recipient = userService.getUser(recipientUsername);
        List<Message> userDialog = messageService.getUserDialog(sender, recipient);
        req.setAttribute("userDialog", userDialog);
        getServletContext().getRequestDispatcher("/view/send_message.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        User senderUser = userService.getUser((String) req.getSession().getAttribute("username"));
        log.info("Sender: " + senderUser.getUsername());
        String recipientUsername = (String) req.getSession().getAttribute("recipient_message_user");
        User recipientUser = userService.getUser(recipientUsername);
        log.info("Recipient: " + recipientUser);
        Date messageDate = new Date();
        String messageText = req.getParameter("message_text");
        Message message = new Message(senderUser, recipientUser, messageDate, messageText);
        messageService.saveMessage(message);
        resp.sendRedirect(req.getContextPath() + "/sendmessage?recipient_user=" + recipientUsername);
    }
}
