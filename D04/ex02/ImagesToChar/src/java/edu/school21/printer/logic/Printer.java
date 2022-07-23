package edu.school21.printer.logic;

import com.diogonunes.jcolor.Ansi;
import com.diogonunes.jcolor.Attribute;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Printer {

    private String whiteChar;
    private String blackChar;

    public Printer(String whiteChar, String blackChar) {
        this.whiteChar = whiteChar;
        this.blackChar = blackChar;
    }

    public void printBmp(File file) throws IOException {

        BufferedImage img = ImageIO.read(file);

        for (int y = 0; y < img.getHeight(); y++) {

            for (int x = 0; x < img.getWidth(); x++) {

                int color = img.getRGB(x, y);

                if (color == Color.WHITE.getRGB()) {
                    System.out.print(Ansi.colorize(" ", resolveColor(whiteChar)));
                } else {
                    System.out.print(Ansi.colorize(" ", resolveColor(blackChar)));
                }

            }

            System.out.println();

        }

    }

    private Attribute resolveColor(String input) {

        switch (input) {

            case "RED":
                return Attribute.RED_BACK();
            case "GREEN":
                return Attribute.GREEN_BACK();
            case "BLUE":
                return Attribute.BLUE_BACK();
            case "BLACK":
                return Attribute.BLACK_BACK();
            case "WHITE":
                return Attribute.WHITE_BACK();

            default:
                return Attribute.NONE();
        }

    }



}