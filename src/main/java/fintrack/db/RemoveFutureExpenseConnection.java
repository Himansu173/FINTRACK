package fintrack.db;

public class RemoveFutureExpenseConnection {
    public RemoveFutureExpenseConnection(String email, String date, int amount, String category)
            throws Exception {
        String qry = "DELETE FROM FUTURE_EXPENSE WHERE EMAIL= '" + email + "' AND  EXPENSE_DATE= TO_DATE('" + date
                + "', 'DD-MON-YY') AND AMOUNT= " + amount + " AND CATEGORY= '" + category + "' ";
                System.out.println(qry);
        int rs = GlobalConnection.stm.executeUpdate(qry);
        if (rs > 0) {
            System.out.println(rs + " row(s) affected.");
        } else {
            throw new Exception();
        }
    }
}