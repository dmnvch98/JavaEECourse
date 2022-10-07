package servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "signUp", value = "/signUp")
public class SignUpServlet extends AbstractUserServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/view/sign_up.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if (!(username.isEmpty() && password.isEmpty())) {
            getUserService().signUp(username, password);
            req.getSession().setAttribute("username", username);
            req.getSession().setAttribute("password", password);
            resp.sendRedirect("/allUsers");
        } else {
            resp.sendRedirect("/view/sign_up.jsp");
        }
    }
}
