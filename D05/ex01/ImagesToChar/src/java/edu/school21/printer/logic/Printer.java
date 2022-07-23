package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Printer {

    private char whiteChar;
    private char blackChar;

    public Printer(char whiteChar, char blackChar) {
        this.whiteChar = whiteChar;
        this.blackChar = blackChar;
    }

    public void printBmp(File file) throws IOException {

        BufferedImage img = ImageIO.read(file);

        for (int y = 0; y < img.getHeight(); y++) {

            for (int x = 0; x < img.getWidth(); x++) {

                int color = img.getRGB(x, y);

                if (color == Color.WHITE.getRGB()) {
                    System.out.print(whiteChar);
                } else {
                    System.out.print(blackChar);
                }

            }

            System.out.println();

        }

    }

}