package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/login")

public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        resp.setContentType("text/html");

        if ("admin".equals(username) && "pass".equals(password)) {
            HttpSession httpSession = req.getSession(true);
            httpSession.setAttribute("username", username);
            httpSession.setAttribute("password", password);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("welcome.html");
            requestDispatcher.forward(req, resp);

        } else {
            PrintWriter writer = resp.getWriter();
            writer.println("Invalid Credentials");
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
            requestDispatcher.include(req, resp);
        }
    }
}
