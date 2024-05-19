package fintrack.db;

import javax.swing.JOptionPane;

public class AddFutureExpenseDB {
    public AddFutureExpenseDB(String email, String date, int amount, String category)
            throws Exception {
        String qry = "INSERT INTO FUTURE_EXPENSE (EMAIL, EXPENSE_DATE, AMOUNT, CATEGORY) VALUES ('" + email
                + "', TO_DATE('" + date + "', 'DD-MON-YY'), " + amount + ", '" + category + "')";
        int rs = GlobalConnection.stm.executeUpdate(qry);
        if (rs > 0) {
            System.out.println("future expense added");
        } else {
            throw new Exception();
        }
    }
}
