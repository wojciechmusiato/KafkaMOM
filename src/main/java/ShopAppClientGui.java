import Customer.CustomerProducer;
import Users.Role;

import java.sql.*;
import java.util.Scanner;

public class ShopAppClientGui {
    public static boolean AUTO = false;
    private CustomerProducer cp;
    public Role role;
    private Connection c;

    public ShopAppClientGui(Role role) {
        this.role = role;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type '/help' for help anytime");
        String input;
        while (scanner.hasNext()) {
            input = scanner.next();
            if (role.equals(Role.CUSTOMER)) {
                customerInterface(scanner, input);
            } else if (role.equals(Role.SUPPLIER)) {
                supplierInterface(scanner);
            } else {
                ownerInterface(scanner);
            }

        }
    }

    public void customerInterface(Scanner scanner, String input) {
        cp = new CustomerProducer();
        switch (input) {
            case "get":
                purchaseItems(scanner);
                break;
            default:
                System.out.println("Wrong command");
                break;
        }
    }

    private void purchaseItems(Scanner scanner) {
        getAllItems();
        System.out.println("enter item name:");
        String item = scanner.nextLine();
        if (!checkIfExists(item)) {
            System.out.println("wrong item");
            return;
        }
        System.out.println("enter quantity:");
        if (!scanner.hasNextInt()) {
            System.out.println("wrong shit");
            return;
        }
        int quantity = scanner.nextInt();
        String price_quantity = getRecord(item);
        price_quantity += " " + quantity;
        cp.purchase(item, price_quantity);

    }

    public void ownerInterface(Scanner scanner) {

    }

    public void supplierInterface(Scanner scanner) {

    }

    public void getAllItems() {
        Statement stmt;
        ResultSet rs = null;
        String format = "%-20s%-20s%-20s%n";
        System.out.printf(format, "name", "price", "quantity");
        try {
            stmt = c.createStatement();
            rs = stmt.executeQuery("SELECT * FROM items");
            while (rs.next()) {
                String name = rs.getString(1);
                int price = rs.getInt(2);
                int quantity = rs.getInt(3);
                System.out.printf(format, name, price, quantity);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return;
    }

    public boolean checkIfExists(String item) {
        Statement stmt;
        ResultSet rs;
        try {
            stmt = c.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*)FROM items " +
                    "where name = '" + item + "'");
            rs.next();
            if (rs.getInt(1) == 0) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public String getRecord(String item) {
        Statement stmt;
        ResultSet rs = null;
        String record = "";
        try {
            stmt = c.createStatement();
            rs = stmt.executeQuery("SELECT * FROM items where name = '" + item + "'");
            while (rs.next()) {
                record = Integer.toString(rs.getInt(2));
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return record;
    }
}
