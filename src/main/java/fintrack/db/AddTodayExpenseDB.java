package fintrack.db;

import javax.swing.JOptionPane;

public class AddTodayExpenseDB {
    public AddTodayExpenseDB(String email, String date, int amount, String time, String category, String description)
            throws Exception {
                String qry = "INSERT INTO EXPENSE (EMAIL, EXPENSE_DATE, AMOUNT, TIME, CATEGORY, DESCRIPTION) VALUES ('" + email + "', TO_DATE('" + date + "', 'DD-MON-YY'), " + amount + ", '"
                + time + "', '" + category + "', '" + description + "')";
                int rs = GlobalConnection.stm.executeUpdate(qry);
        if (rs > 0) {
            System.out.println("Today expense added");
        } else {
            throw new Exception();
        }
    }
}
