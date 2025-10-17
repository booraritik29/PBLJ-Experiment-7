import java.sql.*;
import java.util.Scanner;

public class partB{
    private static final String URL = "jdbc:mysql://localhost:3306/CompanyDB";
    private static final String USER = "mysql";
    private static final String PASSWORD = "keshav@sql";
    private static Connection con;

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            con.setAutoCommit(false);
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.println("\n1-Create 2-Read 3-Update 4-Delete 5-Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1: createProduct(sc); break;
                    case 2: readProducts(); break;
                    case 3: updateProduct(sc); break;
                    case 4: deleteProduct(sc); break;
                    case 5: System.exit(0);
                    default: System.out.println("Invalid choice!"); break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createProduct(Scanner sc) throws SQLException {
        System.out.print("Product Name: "); String name = sc.next();
        System.out.print("Price: "); double price = sc.nextDouble();
        System.out.print("Quantity: "); int qty = sc.nextInt();
        String sql = "INSERT INTO Product(ProductName, Price, Quantity) VALUES (?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, name);
        ps.setDouble(2, price);
        ps.setInt(3, qty);
        ps.executeUpdate();
        con.commit();
        System.out.println("Product inserted successfully.");
    }

    private static void readProducts() throws SQLException {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Product");
        System.out.println("ID | Name | Price | Quantity");
        System.out.println("----------------------------");
        while (rs.next()) {
            System.out.println(rs.getInt("ProductID") + " | " +
                               rs.getString("ProductName") + " | " +
                               rs.getDouble("Price") + " | " +
                               rs.getInt("Quantity"));
        }
    }

    private static void updateProduct(Scanner sc) throws SQLException {
        System.out.print("Enter ProductID to update: "); int id = sc.nextInt();
        System.out.print("New Price: "); double price = sc.nextDouble();
        System.out.print("New Quantity: "); int qty = sc.nextInt();
        String sql = "UPDATE Product SET Price=?, Quantity=? WHERE ProductID=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setDouble(1, price);
        ps.setInt(2, qty);
        ps.setInt(3, id);
        ps.executeUpdate();
        con.commit();
        System.out.println("Product updated successfully.");
    }

    private static void deleteProduct(Scanner sc) throws SQLException {
        System.out.print("Enter ProductID to delete: "); int id = sc.nextInt();
        String sql = "DELETE FROM Product WHERE ProductID=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
        con.commit();
        System.out.println("Product deleted successfully.");
    }
}
