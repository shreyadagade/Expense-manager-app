package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ExpenseService;

import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/delete")

public class ExpenseDeleteController  extends HttpServlet {
    ExpenseService expenseService = new ExpenseService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException,NumberFormatException {
        int id = Integer.parseInt(req.getParameter("id"));
        expenseService.deleteExpense(id);
        PrintWriter writer=resp.getWriter();
        writer.println("Expense Deleted Successfully");

    }
}
