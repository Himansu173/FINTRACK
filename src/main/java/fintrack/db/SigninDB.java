package fintrack.db;

import java.sql.ResultSet;

public class SigninDB {
    public SigninDB(String email, String password)
            throws Exception {
        String qry = "SELECT * FROM USERS WHERE EMAIL= '" + email + "' AND PASSWORD= '" + password + "'";
        ResultSet rs = GlobalConnection.stm.executeQuery(qry);
        if (rs.next()) {
            System.out.println(rs.getString("EMAIL") + " user login.");
        } else {
            throw new Exception();
        }
    }
}
