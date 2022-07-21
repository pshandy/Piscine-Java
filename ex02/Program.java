import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Program {

    private static File dir;

    public static void ls() {
        File[] fileArray = dir.listFiles();
        if (fileArray != null) {
            Arrays.sort(fileArray);
            for (File file : fileArray) {
                long bytes = file.length();
                long kilobytes = bytes / 1024;
                System.out.println(file.getName() + " " + String.format("%,d KB", kilobytes));
            }
        }
    }

    public static void cd (String newFolder) {
        File folder = new File(dir + "/" + newFolder);
        if (folder.isDirectory()) {
            try {
                dir = folder.getCanonicalFile();
            } catch (IOException e) {
                dir = folder;
                e.printStackTrace();
            }
            System.out.println(dir);
        } else {
            System.err.println(newFolder + " is not a directory!");
        }
    }

    public static void mv(String what, String where) {

        File file = new File(dir + "/" + what);

        try {
            if (!file.renameTo(new File(dir + "/" + where).getCanonicalFile()))
                System.out.println("Wasn't able to move/rename file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        if (args.length != 1) {
            System.err.println("Wrong number of arguments.");
            System.exit(-1);
        }

        if (!args[0].contains("--current-folder=")) {
            System.err.println("Wrong argument.");
            System.exit(-1);
        }

        dir = new File(args[0].substring(17, args[0].length()));

        if (dir.isDirectory()) {

            System.out.println(dir);
            Scanner in = new Scanner(System.in);

            String input = in.nextLine();
            while (!input.equals("exit")) {

                if (input.equals("ls")) {
                    ls();
                } else if (input.startsWith("cd ")) {
                    String[] cmd = input.split(" ");
                    if (cmd.length != 2) {
                        System.err.println("Wrong parameters to cd.");
                    } else {
                        cd(cmd[1]);
                    }
                } else if (input.startsWith("mv ")) {
                    String[] cmd = input.split(" ");
                    if (cmd.length != 3) {
                        System.err.println("Wrong parameters to mv.");
                    } else {
                        mv(cmd[1], cmd[2]);
                    }
                } else {
                    System.err.println("Unknown command.");
                }

                input = in.nextLine();
            }

        } else {
            System.err.println("Directory does not exists.");
            System.exit(-1);
        }

    }

}
