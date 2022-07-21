import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {

    private static final String SIGNATURES = "signatures.txt";
    private static final String RESULT = "result.txt";

    private static List<String> hexDB = new ArrayList<>();
    private static List<String> extDB = new ArrayList<>();

    public static String convertToHex(File file) throws IOException {

        InputStream is = new FileInputStream(file);

        int value = 0;

        StringBuilder sbResult = new StringBuilder();

        while ((value = is.read()) != -1) {
            sbResult.append(String.format("%02X ", value));
        }

        is.close();

        return (sbResult.toString());
    }

    public static void main(String[] args) throws IOException {

        File signatures = null;

        try {
            signatures = new File(SIGNATURES);
        } catch (NullPointerException e) {
            System.err.println("signatures.txt does not exist!");
            e.printStackTrace();
            System.exit(-1);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(signatures))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] arr = line.split(",");
                extDB.add(arr[0]);
                hexDB.add(arr[1].trim());
            }

        } catch (IOException e) {

            System.err.println("Something wrong with signatures!");
            e.printStackTrace();
            System.exit(-1);

        }

        FileOutputStream outputStream = null;

        try {
            outputStream = new FileOutputStream(RESULT);
        } catch (FileNotFoundException e) {
            System.err.println("Can't create result file!");
            e.printStackTrace();
            System.exit(-1);
        }

        Scanner in = new Scanner(System.in);

        String input = in.nextLine();

        while (!input.equals("42")) {

            try {

                boolean processed = false;

                String hexFile = convertToHex(new File(input));

                for (int i = 0; i < hexDB.size(); i++) {

                    String control = hexFile.substring(0, hexDB.get(i).length());

                    if (control.equalsIgnoreCase(hexDB.get(i))) {
                        byte[] buffer = (extDB.get(i) + '\n').getBytes();
                        outputStream.write(buffer, 0, buffer.length);
                        processed = true;
                    }

                }

                if (processed) {
                    System.out.println("PROCESSED");
                } else {
                    System.out.println("UNDEFINED");
                }

            } catch (IOException e) {
                System.out.println("UNDEFINED");
            }

            input = in.nextLine();

        }

        outputStream.close();

    }

}
