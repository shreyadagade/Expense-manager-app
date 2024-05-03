package configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static java.lang.Class.forName;

public class ConnectionUtil {
    private static Connection connection;
    static{
        try {
            forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        String url="jdbc:mysql://localhost:3306/expense_manager";
        String username="root";
        String password="root";

        try {
             connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public static Connection getConnection(){
        return  connection;
    }


}
