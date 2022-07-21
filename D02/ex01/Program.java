import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Program {

    public static String getFileContent(String path) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader(path));
        StringBuilder sb = new StringBuilder();
        char[] buffer = new char[10];
        while (reader.read(buffer) != -1) {
            sb.append(buffer);
            buffer = new char[10];
        }
        reader.close();
        return (sb.toString());

    }

    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.err.println("Two files needed!");
            System.exit(-1);
        }

        String[] textA = null;
        String[] textB = null;

        try {
            textA = getFileContent(args[0]).trim().split("\\P{L}+");
            textB = getFileContent(args[1]).trim().split("\\P{L}+");
        } catch (FileNotFoundException e) {
            System.err.println("Bad file.");
            System.exit(-1);
        }

        Map<String, Integer> dict = new HashMap<>();

        for (String s : textA) {
            dict.put(s, 0);
        }

        for (String s : textB) {
            dict.put(s, 0);
        }

        BufferedWriter writer = new BufferedWriter(new FileWriter("dictionary.txt"));
        String[] keys = dict.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        for (String value : keys) {
            writer.write(value + "\n");
        }
        writer.close();

        Map<String, Integer> mapA = new HashMap<>(dict);
        Map<String, Integer> mapB = new HashMap<>(dict);

        for (String s : textA) {
            mapA.merge(s, 1, Integer::sum);
        }

        for (String s : textB) {
            mapB.merge(s, 1, Integer::sum);
        }

        Integer[] vectorA = mapA.values().toArray(new Integer[0]);
        Integer[] vectorB = mapB.values().toArray(new Integer[0]);

        int numerator = 0;
        for (int i = 0; i < vectorA.length; i++) {
            numerator += vectorA[i] * vectorB[i];
        }

        double subDenominator1 = 0;
        double subDenominator2 = 0;
        for (int i = 0; i < vectorA.length; i++) {
            subDenominator1 += vectorA[i] * vectorA[i];
            subDenominator2 += vectorB[i] * vectorB[i];
        }
        double denominator = Math.sqrt(subDenominator1) * Math.sqrt(subDenominator2);

        double similarity = numerator / denominator;
        System.out.println("Similarity = " + String.format("%.2f", similarity));

    }
}
