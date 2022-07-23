package edu.school21.printer.app;

import edu.school21.printer.logic.Printer;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

import java.io.File;
import java.io.IOException;

@Parameters(separators = "=")
public class Program {

    @Parameter(names = {"--white"})
    private static String whiteChar;
    @Parameter(names = {"--black"})
    private static String blackChar;

    private static final String IMAGE_PATH = "/resources/image.bmp";

    public static void main(String ... argv) {

        Program main = new Program();
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(argv);

        Printer logic = new Printer(whiteChar, blackChar);

        try {
            logic.printBmp(new File(Program.class.getResource(IMAGE_PATH).getFile()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}