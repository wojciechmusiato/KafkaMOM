package Shop;

import java.sql.*;

public class ShopAppController {
    private Connection c;

    public ShopAppController() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost/db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "root");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mainLoop() {
        ShopConsumer shipmentsConsumer = new ShopConsumer("shipments");
        shipmentsConsumer.run();
        ShopConsumer purchaseConsumer = new ShopConsumer("purchases");
        purchaseConsumer.run();
        ShopProducer myreplyProducer = new ShopProducer("myreply");
        ShopProducer reorderProducer = new ShopProducer("reorder");
    }


    public boolean checkIfEnoughItems(String item, int quantity){
        Statement stmt;
        ResultSet rs;
        try {
            stmt = c.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*)FROM items " +
                    "where name = '"+item+"' and quantity > "+quantity+";");
            rs.next();
            if (rs.getInt(1) == 0) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean updatePrice(String item, int price) {
        PreparedStatement stmt;

        try {
            stmt = c.prepareStatement("update items set price = " + price +
                    " where '" + item + "' = name");
            if (stmt.executeUpdate() == 0) {
                System.out.println("Nothing inserted...WTF");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean updateQuantity(String item, int quantity) {
        PreparedStatement stmt;

        try {
            stmt = c.prepareStatement("update items set quantity = " + quantity +
                    " where '" + item + "' = name");
            if (stmt.executeUpdate() == 0) {
                System.out.println("Nothing inserted...WTF");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

}
