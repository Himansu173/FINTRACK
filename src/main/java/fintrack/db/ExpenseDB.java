package fintrack.db;

import java.sql.ResultSet;

public class ExpenseDB {
    public ExpenseDB(String email, String date, int amount, String time, String category, String description)
            throws Exception {
        String qry = "INSERT INTO EXPENSE (EMAIL, EXPENSE_DATE, AMOUNT, TIME, CATEGORY, DESCRIPTION) VALUES ('" + email
                + "', TO_DATE('" + date + "', 'DD-MON-YY'), " + amount + ", '"
                + time + "', '" + category + "', '" + description + "')";
        int rs = GlobalConnection.stm.executeUpdate(qry);
        if (rs > 0) {
            System.out.println("Today expense added");
        } else {
            throw new Exception();
        }
    }

    public static ResultSet returnExpenses(String email, String date)
            throws Exception {
        String qry = "SELECT * FROM EXPENSE WHERE EMAIL = '" + email + "' AND EXPENSE_DATE = TO_DATE('" + date
                + "', 'DD-MON-YY')";
        ResultSet rs = GlobalConnection.stm.executeQuery(qry);
        return rs;
    }

    public static int getTotalExpenseOfTheDay(String email, String date) throws Exception {
        String qry = "SELECT SUM(AMOUNT) AS TOTAL FROM EXPENSE WHERE EMAIL = '" + email
                + "' AND EXPENSE_DATE = TO_DATE('" + date + "', 'DD-MM-YY')";
        ResultSet rs = GlobalConnection.stm.executeQuery(qry);
        if (rs.next()) {
            return rs.getInt("TOTAL");
        }
        return 0;
    }

    public static int getTotalExpense(String email, String startDate, String endDate) throws Exception {
        String qry = "SELECT SUM(AMOUNT) AS total FROM expense WHERE email = '" + email
                + "' AND EXPENSE_DATE <= TO_DATE('" + startDate + "', 'DD-MM-YY') AND EXPENSE_DATE > TO_DATE('"
                + endDate + "', 'DD-MM-YY')";
        ResultSet rs = GlobalConnection.stm.executeQuery(qry);
        if (rs.next()) {
            return rs.getInt("TOTAL");
        }
        return 0;
    }

    public static int getTotalExpenseCategoryWise(String email, String startDate, String endDate, String category)
            throws Exception {
        String qry = "SELECT SUM(AMOUNT) AS total FROM expense WHERE email = '" + email + "' AND EXPENSE_DATE <= TO_DATE('" + startDate + "', 'DD-MM-YY') AND EXPENSE_DATE > TO_DATE('" + endDate + "', 'DD-MM-YY') AND CATEGORY='" + category + "'";
        ResultSet rs = GlobalConnection.stm.executeQuery(qry);
        if (rs.next()) {
            return rs.getInt("TOTAL");
        }
        return 0;
    }
}
