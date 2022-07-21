import java.util.Scanner;

public class Program {

    public static int[] sort(int[] array) {

        int i;
        int[] positions = new int[10];

        for (i = 0; i < array.length - 1 && i < 10; ++i) {

            int minPos = i;

            for (int j = i + 1; j < array.length; ++j) {
                if (array[j] != 0 && array[j] > array[minPos]) {
                    minPos = j;
                }
            }

            if (minPos != i)
                positions[i] = minPos;
            else
                positions[i] = -42;

            int saveValue = array[minPos];
            array[minPos] = array[i];
            array[i] = saveValue;

        }

        return (positions);

    }

    public static void main(String[] args) {

        char[] charArray = new Scanner(System.in).nextLine().toCharArray();

        int[] occurrences = new int[65535];
        for (char c : charArray) {
            occurrences[c]++;
        }

        int len = 0;
        int[] topTen = sort(occurrences);
        for (int i = 0; i < 10; i++) {
            if (topTen[i] == -42)
                break;
            len++;
        }

        int max = occurrences[0];
        for (int i = 11; i > 0 ; i--) {
            for (int j = 0; j < len; j++) {
                if (i <= ((((double) occurrences[j] / (double) max) * 10))) {
                    System.out.print("#" + " ");
                }
                else if (i <= ((((double) occurrences[j] / (double) max) * 10) + 1)) {
                    System.out.print(occurrences[j] + " ");
                }
            }
            System.out.println();
        }

        for (int i = 0; i < len; i++) {
            System.out.print((char)topTen[i] + " ");
        }

    }
}
