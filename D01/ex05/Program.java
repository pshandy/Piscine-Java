import java.util.Scanner;

public class Program {

    private static Integer getChoice(Scanner in) {
        Integer choice = 0;
        do {
            while (!in.hasNextInt()) {
                System.err.println("Number must be Integer!");
                in.next();
            }
            choice = in.nextInt();
            if (!(choice >= 1 && choice <= 7)) {
                System.err.println("Number must be between 1 and 7!");
            }
        } while (!(choice >= 1 && choice <= 7));
        return (choice);
    }

    public static void main(String[] args) {

        if (args.length > 1) {
            System.out.println("Wrong number of arguments!");
            System.exit(-1);
        }

        Menu menu = null;
        if (args.length == 0) {
            menu = new Menu("default");
        } else {
            if (args[0].equals("--profile=dev")) {
                menu = new Menu("dev");
            } else {
                System.out.println("Wrong argument!");
                System.exit(-1);
            }
        }


        Scanner in = new Scanner(System.in);

        Integer choice = -1;
        while (choice != 7) {

            menu.showMenu();

            choice = getChoice(in);

            if (choice == 1) {
                menu.addUser(in);
            } else if (choice == 2) {
                menu.viewBalance(in);
            } else if (choice == 3) {
                menu.addTransfer(in);
            } else if (choice == 4) {
                menu.viewHistory(in);
            } else if (choice == 5) {
                menu.removeTransfer(in);
            } else if (choice == 6) {
                menu.checkTransfer();
            }

            menu.showFooter();

        }
    }
}
