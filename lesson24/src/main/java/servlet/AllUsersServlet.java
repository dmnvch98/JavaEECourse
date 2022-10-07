package servlet;

import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "allUsers", value = "/allUsers")
public class AllUsersServlet extends AbstractUserServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> users;
        if (req.getParameter("search") != null) {
            users = getUserService().filterUsers(req.getParameter("search"));
        } else {
            users = getUserService().getAllUsers();
        }
        req.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/view/all_users.jsp").forward(req, resp);
    }
}
