import java.sql.*;

public class partA {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/CompanyDB";
        String user = "mysql";
        String password = "keshav@sql";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT EmpID, Name, Salary FROM Employee");

            System.out.println("EmpID | Name | Salary");
            System.out.println("----------------------");
            while (rs.next()) {
                int empId = rs.getInt("EmpID");
                String name = rs.getString("Name");
                double salary = rs.getDouble("Salary");
                System.out.println(empId + " | " + name + " | " + salary);
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
