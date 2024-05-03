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
@WebServlet("/update")

public class ExpenseUpdateController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExpenseService expenseService = new ExpenseService();
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

        expenseService.updateExpense(expense);

        PrintWriter writer = resp.getWriter();
        writer.println("Expense Updated Successfully");


    }
}
