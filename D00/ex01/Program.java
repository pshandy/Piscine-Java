import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        int number = new Scanner(System.in).nextInt();

        if (number <= 1) {
            System.err.println("theIllegalArgument");
            System.exit(-1);
        }

        int steps = 1;

        boolean isPrime = true;

        for (int i = 2; i * i <= number; i++) {
            steps++;
            if (number % i == 0) {
                isPrime = false;
                break ;
            }
        }

        System.out.println(isPrime + " " + steps);
    }
}
