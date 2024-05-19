package fintrack.db;

import java.io.EOFException;
import java.sql.ResultSet;

public class ProfileDB {
    public static String[] getDetails(String email) throws Exception {
        String qry = "SELECT * FROM USERS WHERE EMAIL = '" + email + "'";
        ResultSet rs = GlobalConnection.stm.executeQuery(qry);
        String data[] = new String[5];
        if (rs.next()) {
            data[0] = new String(rs.getString("name"));
            data[1] = new String("" + rs.getInt("phone_number"));
            data[2] = new String(rs.getString("profession"));
            data[3] = new String("" + rs.getInt("age"));
            data[4] = new String(rs.getString("gender"));
        } else {
            throw new EOFException();
        }
        return data;
    }

    public static void changePassword(String email, String oldPass, String newPass) throws Exception {
        String qry = "UPDATE USERS SET PASSWORD = '" + newPass + "' WHERE EMAIL = '" + email + "' AND PASSWORD = '"
                + oldPass + "'";
        System.out.println(qry);
        int rs = GlobalConnection.stm.executeUpdate(qry);
        if (rs > 0) {
            System.out.println("password updated.");
        } else {
            throw new EOFException();
        }
    }

    public static void editProfile(String email, String name, int Phone, String profession, int age) throws Exception {
        String qry = "UPDATE USERS SET NAME = '" + name + "', PHONE=" + Phone + ", PROFESSION='" + profession
                + "', AGE=" + age + " WHERE EMAIL = '" + email + "'";
        System.out.println(qry);
        int rs = GlobalConnection.stm.executeUpdate(qry);
        if (rs > 0) {
            System.out.println("profile eddited.");
        } else {
            throw new EOFException();
        }
    }

    public static void setBudget(String email, String month, int budget) throws Exception {
        String qry = "SELECT * FROM BUDGET WHERE EMAIL='" + email + "' AND MONTH='" + month + "'";
        ResultSet rs = GlobalConnection.stm.executeQuery(qry);
        if (rs.next()) {
            qry = "UPDATE BUDGET SET TOTAL_BUDGET=" + budget + " WHERE EMAIL='" + email + "' AND MONTH='" + month + "'";
            int val = GlobalConnection.stm.executeUpdate(qry);
            if (val > 0) {
                System.out.println("budget updated.");
            } else {
                throw new Exception();
            }
        } else {
            qry = "INSERT INTO BUDGET VALUES('" + email + "', '" + month + "', " + budget + "," + 0 + ")";
            int val = GlobalConnection.stm.executeUpdate(qry);
            if (val > 0) {
                System.out.println("budget inserted.");
            } else {
                throw new EOFException();
            }
        }
    }
}
