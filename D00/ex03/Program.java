import java.util.Scanner;

public class Program {

    private static void errorExit() {
        System.err.println("IllegalArgument");
        System.exit(-1);
    }

    private static int getMinGrade(Scanner in) {

        int min = 10;

        for (int i = 0; i < 5; i++) {
            int number = in.nextInt();
            if (number < 1 || number > 9) {
                errorExit();
            }
            min = number < min ? number : min;
        }

        if (!in.nextLine().equals("")) {
            errorExit();
        }

        return (min);
    }

    public static long pack(int number, int week) {
        long power = 1;
        for (int i = 1; i < week; i++) {
            power *= 10;
        }
        return (number * power);
    }

    public static int unpack(long packed, int week) {
        for (int i = 1; i < week; i++) {
            packed /= 10;
        }
        return ((int)(packed % 10));
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);

        int week = 1;
        long packed = 0;
        String input = in.nextLine();

        while (week <= 18 && !input.equals("42")) {
            if (!input.equals("Week " + week)) {
                errorExit();
            }
            packed += pack(getMinGrade(in), week);
            input = in.nextLine();
            week++;
        }

        for (int i = 1; i < week; i++) {
            System.out.print("Week " + i + " ");
            int minGrade = unpack(packed, i);
            for (int j = 0; j < minGrade; j++) {
                System.out.print("=");
            }
            System.out.println(">");
        }

    }

}
