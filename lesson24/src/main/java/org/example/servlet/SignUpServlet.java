package org.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signUp")
public class SignUpServlet extends AbstractUserServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/myapp/view/sign_up.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (!(username.isEmpty() && password.isEmpty())) {
            getUserService().signUp(username, password);
            req.getSession().setAttribute("isLoggedIn", true);
            getServletContext().getRequestDispatcher("/allUsers").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/myapp/signUp").forward(req, resp);
        }
    }
}
