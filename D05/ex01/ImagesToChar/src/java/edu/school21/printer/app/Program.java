package edu.school21.printer.app;

import edu.school21.printer.logic.Printer;

import java.io.File;
import java.io.IOException;

public class Program {

    private static final String IMAGE_PATH = "/resources/image.bmp";

    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("Wrong number of parameters! (Expected 2)");
            System.exit(-1);
        }

        if (args[0].length() != 1 || args[1].length() != 1) {
            System.err.println("Wrong arguments! (Expected char char path)");
            System.exit(-1);
        }

        char whiteChar = args[0].charAt(0);
        char blackChar = args[1].charAt(0);

        Printer logic = new Printer(whiteChar, blackChar);
        try {
            logic.printBmp(new File(Program.class.getResource(IMAGE_PATH).getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}