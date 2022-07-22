public class Program {

    private static int count;

    public static void main(String[] args) throws InterruptedException {

        if (args.length != 1) {
            System.err.println("Wrong number of arguments. --count=10 ");
            System.exit(-1);
        }

        if (!args[0].matches("--count=\\d+")) {
            System.err.println("Wrong argument");
            System.exit(-1);
        }

        try {
            count = Integer.parseInt(args[0].split("=")[1]);
        } catch (NumberFormatException e) {
            System.err.println("Wrong number.");
            e.printStackTrace();
            System.exit(-1);
        }

        Thread tr1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < count; i++) {
                    System.out.println("Hen");
                }
            }
        });

        Thread tr2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < count; i++) {
                    System.out.println("Egg");
                }
            }
        });

        tr1.start();
        tr2.start();

        tr1.join();
        tr2.join();

        for (int i = 0; i < count; i++) {
            System.out.println("Human");
        }

    }
}
