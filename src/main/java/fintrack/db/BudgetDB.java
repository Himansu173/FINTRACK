package fintrack.db;

import java.sql.ResultSet;

public class BudgetDB {
    public static void setBudget(String email, String month, int budget) throws Exception {
        String qry = "SELECT * FROM BUDGET WHERE EMAIL='" + email + "' AND MONTH='" + month + "'";
        ResultSet rs = GlobalConnection.stm.executeQuery(qry);
        if (rs.next()) {
            qry = "UPDATE BUDGET SET TOTAL_BUDGET=" + budget + " WHERE EMAIL='" + email + "' AND MONTH='" + month + "'";
            int val = GlobalConnection.stm.executeUpdate(qry);
            if (val > 0) {
                System.out.println("Budget Updated");
            } else {
                throw new Exception();
            }
        } else {
            qry = "INSERT INTO BUDGET VALUES('" + email + "','" + month + "'," + budget + "," + 0 + ")";
            int val = GlobalConnection.stm.executeUpdate(qry);
            if (val > 0) {
                System.out.println("Budget Added");
            } else {
                throw new Exception();
            }
        }
    }

    public static ResultSet getAllBudget(String email) throws Exception {
        ResultSet rs;
        String qry = "SELECT * FROM BUDGET WHERE EMAIL='" + email + "'";
        rs = GlobalConnection.stm.executeQuery(qry);
        return rs;
    }

    public static ResultSet getBudgetOfTheMonth(String email, String month) throws Exception {
        ResultSet rs;
        String qry = "SELECT * FROM BUDGET WHERE EMAIL='" + email + "' AND MONTH='" + month + "' ";
        rs = GlobalConnection.stm.executeQuery(qry);
        return rs;
    }

    public static void updateExpense(String email, String month, int amount) throws Exception {
        String qry = "UPDATE BUDGET SET EXPENSE=EXPENSE+" + amount + ", TOTAL_BUDGET=TOTAL_BUDGET-" + amount
                + " WHERE EMAIL='" + email + "' AND MONTH='" + month + "'";
        int val = GlobalConnection.stm.executeUpdate(qry);
        if (val > 0) {
            System.out.println("Expense updated");
        } else {
            throw new Exception();
        }
    }
}
