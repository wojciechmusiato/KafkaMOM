import Users.Role;

import java.util.Scanner;

public class ShopAppClientGui {
    public Role role;

    public ShopAppClientGui(Role role) {
        this.role = role;
    }

    public void startClient() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type '/help' for help anytime");
        String input;
        while (scanner.hasNext()) {
            input = scanner.next();
            if (role.equals(Role.CUSTOMER)) {
                customerInterface(scanner);
            } else if (role.equals(Role.SUPPLIER)) {
                supplierInterface(scanner);
            } else {
                ownerInterface(scanner);
            }

        }
    }

    public void customerInterface(Scanner scanner) {

    }

    public void ownerInterface(Scanner scanner) {

    }

    public void supplierInterface(Scanner scanner) {

    }
}
