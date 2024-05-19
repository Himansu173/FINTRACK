package fintrack.db;

import java.sql.*;

import javax.swing.*;

public class GlobalConnection {
    public static Connection con;
    public static Statement stm;

    public GlobalConnection() {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "system";
        String password = "1234";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(url, username, password);
            stm = con.createStatement();
            new CreateTableConnection();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Some error occure. please reopen the application.",
                    "Server Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void closeConnection() {
        try {
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
