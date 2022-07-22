import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException {

        BufferedImage img = ImageIO.read(Program.class.getResource("it.bmp"));

        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                int color = img.getRGB(x, y);
                if (color == Color.WHITE.getRGB()) {
                    System.out.print(".");
                } else {
                    System.out.print("0");
                }
            }
            System.out.println();
        }

    }
}
