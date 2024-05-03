package dao;

import configuration.ConnectionUtil;
import model.Expense;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDao {
    public void addExpense(Expense expense){
        Connection connection= ConnectionUtil.getConnection();
        String insertQuery="insert into expense_manager.expense(id,remark, amount, payment_mode, LocalDateTime) values(?,?,?,?,?)";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1,expense.getId());
            preparedStatement.setString(2,expense.getRemark());
            preparedStatement.setInt(3,expense.getAmount());
            preparedStatement.setString(4,expense.getPayment_mode());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(expense.getTime()));
            preparedStatement.executeUpdate();
            System.out.println("Expense added successfully!!!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateExpense(Expense expense){
        Connection connection=ConnectionUtil.getConnection();
        String updateQuery="Update expense set remark=?,amount=?,payment_mode=?, LocalDateTime=? where id=?";
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(updateQuery);

            preparedStatement.setString(1,expense.getRemark());
            preparedStatement.setInt(2,expense.getAmount());
            preparedStatement.setString(3,expense.getPayment_mode());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(expense.getTime()));
            preparedStatement.setInt(5,expense.getId());
            preparedStatement.executeUpdate();
            System.out.println("Expense updated Sucessfully!!");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteExpense(int id){
        Connection connection=ConnectionUtil.getConnection();
        String deleteQuery="Delete from expense where id=?";
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(deleteQuery);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Expense deleted sucessfully");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public List<Expense> getAllExpenses()  {
        List<Expense> expenseList= new ArrayList<>();
        Connection connection=ConnectionUtil.getConnection();
        String selectquery="select * from expense";

        try {
            Statement statement= connection.createStatement();
            ResultSet resultSet= statement.executeQuery(selectquery);
            while(resultSet.next()){
                Expense expense = Expense.builder()
                        .id(resultSet.getInt("id"))
                        .remark(resultSet.getString("remark"))
                        .amount(resultSet.getInt("amount"))
                        .payment_mode(resultSet.getString("payment_mode"))
                        .time(resultSet.getTimestamp("LocalDateTime").toLocalDateTime())
                        .build();
                expenseList.add(expense);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return expenseList;


    }



}
