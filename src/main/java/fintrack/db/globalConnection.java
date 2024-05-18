package fintrack.db;

import java.sql.*;

public class globalConnection {
    public Connection con;

    public globalConnection() {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String username = "system";
        String password = "1234";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Connection getConnection() {
        return con;
    }

    public void closeConnection() {
        try {
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
