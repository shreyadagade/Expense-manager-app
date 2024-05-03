package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Expense;
import service.ExpenseService;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/expense")

public class ExpenseController extends HttpServlet {
    ExpenseService expenseService = new ExpenseService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Expense> expenseList = expenseService.getAllExpenses();
        resp.setContentType("Text/html");
        PrintWriter writer = resp.getWriter();
        writer.println("<table border: 10px solid black><tr><th>ID</th> <th>Remark</th> <th>Amount</th> <th>Payment Mode</th> <th>Time</th></tr>");
        for (Expense expense : expenseList) {
            writer.println("<tr>");
            writer.println("<td>" + expense.getId() + "</td>");
            writer.println("<td>" + expense.getRemark() + "</td>");
            writer.println("<td>" + expense.getAmount() + "</td>");
            writer.println("<td>" + expense.getPayment_mode() + "</td>");
            writer.println("<td>" + expense.getTime() + "</td>");
            writer.println("</tr>");
        }
        writer.println("</table>");
    }


   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String remark = req.getParameter("remark");
        int amount = Integer.parseInt(req.getParameter("amount"));
        String payment_mode = req.getParameter("payment_mode");
        LocalDateTime time = LocalDateTime.parse(req.getParameter("time"));

        Expense expense = Expense.builder()
                .id(id)
                .remark(remark)
                .amount(amount)
                .payment_mode(payment_mode)
                .time(time)

                .build();

        expenseService.addExpense(expense);
        PrintWriter writer = resp.getWriter();
        writer.println("Expense Added Successfully");

    }

    @Override

    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id= Integer.parseInt(req.getParameter("id"));
        expenseService.deleteExpense(id);
        PrintWriter writer=resp.getWriter();
        writer.println("Expense Deleted  Sucessfully");

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String remark = req.getParameter("remark");
        int amount = Integer.parseInt(req.getParameter("amount"));
        String payment_mode = req.getParameter("payment_mode");
        LocalDateTime time = LocalDateTime.parse(req.getParameter("date"));

        Expense expense = Expense.builder()
                .id(id)
                .remark(remark)
                .amount(amount)
                .payment_mode(payment_mode)
                .time(time)

                .build();

        expenseService.updateExpense(expense);
        PrintWriter writer = resp.getWriter();
        writer.println("Expense Updated Successfully");
    }
}

