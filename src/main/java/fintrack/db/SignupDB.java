package fintrack.db;

public class SignupDB {
    public SignupDB(String email, String name, int phone, String profession, int age, String gender, String password)
            throws Exception {
        String qry = "INSERT INTO USERS VALUES('" + email + "','" + name + "'," + phone + ",'" + profession + "'," + age + ",'" + gender + "','" + password + "')";
        int rs = GlobalConnection.stm.executeUpdate(qry);
        if (rs > 0) {
            System.out.println("new user created.");
        } else {
            throw new Exception();
        }
    }
}
