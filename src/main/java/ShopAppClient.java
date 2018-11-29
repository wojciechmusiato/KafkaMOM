import Users.*;

public class ShopAppClient {
    public static void main(String[] args) {
        Role role = null;
        if (args.length==0) {
            System.out.println("Run the app with one of these commands:");
            System.out.println("<consumer, admin, owner, supplier>");
        } else if (args[0].equalsIgnoreCase("customer")) {
            System.out.println("I'm a customer");
            role = Role.CUSTOMER;
        } else if (args[0].equalsIgnoreCase("owner")) {
            System.out.println("I'm an owner");
            role = Role.OWNER;
        } else if (args[0].equalsIgnoreCase("supplier")) {
            System.out.println("I'm a supplier");
            role = Role.SUPPLIER;
        }  else {
            System.out.println("Run the app with one of these commands:");
            System.out.println("<consumer, admin, owner, supplier>");
        }
        ShopAppClientGui sacg = new ShopAppClientGui(role);
        sacg.startClient();

    }
}
