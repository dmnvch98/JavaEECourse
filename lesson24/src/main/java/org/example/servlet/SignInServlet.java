package org.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "signIn", value = "/signIn")
public class SignInServlet extends AbstractUserServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/myapp/view/sign_in.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        if (getUserService().userIsExist(username, password)) {
            req.getSession().setAttribute("isLoggedIn", true);
            getServletContext().getRequestDispatcher("/myapp/allUsers").forward(req, resp);
        } else {
            getServletContext().getRequestDispatcher("/myapp/signIn").forward(req, resp);
        }
    }
}
