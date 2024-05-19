package fintrack.db;

import java.sql.*;

public class CreateTableDB {

    public CreateTableDB() throws Exception {
        String sql = "SELECT table_name FROM all_tables WHERE table_name = ?";
        PreparedStatement preparedStatement = GlobalConnection.con.prepareStatement(sql);
        preparedStatement.setString(1, "USERS");

        // Execute the query
        ResultSet resultSet = preparedStatement.executeQuery();

        // Check if the table exists
        if (!resultSet.next()) { // Table does not exist
            String createUserTableString = "CREATE TABLE USERS(EMAIL VARCHAR2(100) PRIMARY KEY, NAME VARCHAR2(70), PHONE NUMBER(13), PROFESSION VARCHAR2(20), AGE NUMBER(3), GENDER VARCHAR2(6),PASSWORD VARCHAR2(25) NOT NULL)";
            String createExpenseTableString = "CREATE TABLE EXPENSE(EMAIL VARCHAR2(100), EXPENSE_DATE DATE, AMOUNT NUMBER(20), TIME VARCHAR2(10), CATEGORY VARCHAR2(30), DESCRIPTION VARCHAR2(300), PRIMARY KEY(EMAIL,EXPENSE_DATE,TIME), CONSTRAINT fk_expense_email FOREIGN KEY (EMAIL) REFERENCES USERS(EMAIL))";
            String createFutureExpenseTableString = "CREATE TABLE FUTURE_EXPENSE(EMAIL VARCHAR2(100),EXPENSE_DATE DATE, AMOUNT NUMBER(20), CATEGORY VARCHAR2(30), PRIMARY KEY(EMAIL,EXPENSE_DATE,CATEGORY,AMOUNT), CONSTRAINT fk_future_expense_email FOREIGN KEY (EMAIL) REFERENCES USERS(EMAIL))";
            String createBudgetTableString = "CREATE TABLE BUDGET(EMAIL VARCHAR2(100), MONTH VARCHAR2(8), TOTAL_BUDGET NUMBER(20),EXPENSE NUMBER(20), PRIMARY KEY(EMAIL,MONTH), CONSTRAINT fk_budget_email FOREIGN KEY (EMAIL) REFERENCES USERS(EMAIL))";

            GlobalConnection.stm.executeUpdate(createUserTableString);
            GlobalConnection.stm.executeUpdate(createExpenseTableString);
            GlobalConnection.stm.executeUpdate(createFutureExpenseTableString);
            GlobalConnection.stm.executeUpdate(createBudgetTableString);
        }
    }
}
