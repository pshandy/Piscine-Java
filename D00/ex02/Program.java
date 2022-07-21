import java.util.Scanner;

public class Program {

    private static int getSum(int number) {

        int sum = 0;

        while (number != 0) {
            sum += number % 10;
            number = number / 10;
        }

        return (sum);
    }

    private static boolean isPrime(int number) {

        if (number <= 1)
            return (false);

        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return (false);
            }
        }

        return (true);
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int number;

        int counter = 0;

        do {
            number = in.nextInt();
            if (isPrime(getSum(number))) {
                counter++;
            }
        } while (number != 42);

        System.out.println("Count of coffee - request - " + counter);
    }
}
