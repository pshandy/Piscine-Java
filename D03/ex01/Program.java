public class Program {

    private static int count;

    private int status = 0;

    public void writeEgg() {

        synchronized (this) {
          if (status != 0) {
              try {
                  wait();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
          status = 1;
          System.out.println("Egg");
          notify();
        }

    }

    public void writeHen() {

        synchronized (this) {
          if (status != 1) {
              try {
                  wait();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
          }
          status = 0;
          System.out.println("Hen");
          notify();
        }

    }

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

        Program lock = new Program();

        Thread tr1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < count; i++) {
                    lock.writeEgg();
                }
            }
        });

        Thread tr2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < count; i++) {
                    lock.writeHen();
                }
            }
        });

        tr1.start();
        tr2.start();


    }
}
