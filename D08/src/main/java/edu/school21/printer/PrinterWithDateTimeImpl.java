package edu.school21.printer;

import edu.school21.renderer.Renderer;

import java.time.LocalDateTime;

public class PrinterWithDateTimeImpl implements Printer {

    private LocalDateTime localDateTime;
    private Renderer renderer;

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
        localDateTime = LocalDateTime.now();
    }

    public void print(String message) {
        renderer.print(localDateTime + " " + message);
    }
}
