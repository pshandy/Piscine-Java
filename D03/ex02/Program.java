import java.util.Arrays;
import java.util.Random;

public class Program {

    private static int arraySize;
    private static int threadsCount;
    private static int chunk;

    public static void sum(int[] array, int[] results, int section) {

        int start = section * chunk;

        int end = Math.min((section + 1) * chunk, arraySize);

        for (int i = start; i < end; i++) {
            results[section] += array[i];
        }

        System.out.println("Thread " + Thread.currentThread().getId() + ": " +
                "from " + start + " to " + (end - 1) + " sum is " + results[section]);

    }

    public static void main(String[] args) throws InterruptedException {

        if (args.length != 2) {
            System.err.println("Wrong number of arguments");
            System.exit(-1);
        }

        if (!args[0].matches("--arraySize=\\d+")
                || !args[1].matches("--threadsCount=\\d+")) {
            System.err.println("Wrong argument");
            System.exit(-1);
        }

        try {
            arraySize = Integer.parseInt(args[0].split("=")[1]);
            threadsCount = Integer.parseInt(args[1].split("=")[1]);
            chunk = (int) Math.ceil((double)arraySize / threadsCount);
        } catch (NumberFormatException e) {
            System.err.println("Wrong number.");
            e.printStackTrace();
            System.exit(-1);
        }

        for (int i = 0; i < threadsCount; i++) {
            if (i * chunk > arraySize) {
                threadsCount = i;
                break ;
            }
        }

        Random rand = new Random();
        int[] array = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            array[i] = rand.nextInt(10) - 5;
        }

        int defSum = 0;
        for (int i = 0; i < arraySize; i++) {
            defSum += array[i];
        }
        System.out.println("Sum: " + defSum);

        int[] results = new int[threadsCount];
        Thread[] threads = new Thread[threadsCount];

        for (int i = 0; i < threadsCount; i++) {
            int finalI = i;
            threads[i] = new Thread(() -> sum(array, results, finalI));
            threads[i].start();
        }

        for (int i = 0; i < threadsCount; i++) {
            threads[i].join();
        }

        int result = Arrays.stream(results).sum();
        System.out.println("Sum by threads: " + result);

    }
}
