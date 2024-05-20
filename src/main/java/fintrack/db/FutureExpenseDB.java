package fintrack.db;

import java.sql.ResultSet;

import javax.swing.JOptionPane;

public class FutureExpenseDB {
    public FutureExpenseDB(String email, String date, int amount, String category)
            throws Exception {
        String qry = "INSERT INTO FUTURE_EXPENSE (EMAIL, EXPENSE_DATE, AMOUNT, CATEGORY) VALUES ('" + email
                + "', '" + date + "', " + amount + ", '" + category + "')";
        int rs = GlobalConnection.stm.executeUpdate(qry);
        if (rs > 0) {
            System.out.println("future expense added");
        } else {
            throw new Exception();
        }
    }

    public static ResultSet returnFutureExpense(String email)
            throws Exception {
        String qry = "SELECT * FROM FUTURE_EXPENSE WHERE EMAIL = '" + email + "'";
        ResultSet rs = GlobalConnection.stm.executeQuery(qry);
        return rs;
    }

    public static void RemoveFutureExpense(String email, String date, int amount, String category)
            throws Exception {
        String qry = "DELETE FROM FUTURE_EXPENSE WHERE EMAIL= '" + email + "' AND  EXPENSE_DATE= TO_DATE('" + date
                + "', 'DD-MON-YY') AND AMOUNT= " + amount + " AND CATEGORY= '" + category + "' ";
        int rs = GlobalConnection.stm.executeUpdate(qry);
        if (rs > 0) {
            System.out.println(rs + " row(s) affected.");
        } else {
            throw new Exception();
        }
    }
}
