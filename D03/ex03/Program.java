import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Program {

    private static int threadsCount;
    private static final ArrayList<String> urls = new ArrayList<>();
    private static final String URLS = "urls.txt";

    public static void main(String[] args) throws IOException, InterruptedException {

        if (args.length != 1) {
            System.err.println("Wrong number of arguments. --threadsCount=10 ");
            System.exit(-1);
        }

        if (!args[0].matches("--threadsCount=\\d+")) {
            System.err.println("Wrong argument");
            System.exit(-1);
        }

        try {
            threadsCount = Integer.parseInt(args[0].split("=")[1]);
            if (threadsCount == 0) {
                System.err.println("Wrong argument");
                System.exit(-1);
            }
        } catch (NumberFormatException e) {
            System.err.println("Wrong number.");
            e.printStackTrace();
            System.exit(-1);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(URLS))) {

            String line;
            while ((line = reader.readLine()) != null) {
                urls.add(line);
            }

        } catch (IOException e) {

            System.err.println("Something wrong with urls!");
            e.printStackTrace();
            System.exit(-1);

        }

        ExecutorService pool = Executors.newFixedThreadPool(threadsCount);

        for (String url : urls) {
            pool.submit(new Task(url));
        }

        pool.shutdown();
        pool.awaitTermination(Long.MAX_VALUE, TimeUnit.MILLISECONDS);

    }

    private static class Task implements Runnable {

        private static int fileId = 0;

        private final int _fileId;
        private String url;

        public Task(String url) {
            this.url = url;
            _fileId = ++fileId;
        }

        @Override
        public void run() {

            URL website = null;
            try {

                System.out.println("Thread-" + Thread.currentThread().getId() + " start download file number + " + _fileId);
                website = new URL(url);
                ReadableByteChannel rbc = Channels.newChannel(website.openStream());
                File file = new File(String.valueOf(_fileId));
                FileOutputStream fos = new FileOutputStream(file);
                fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
                System.out.println("Thread-" + Thread.currentThread().getId() + " finish download file number + " + _fileId);
                fos.close();

            } catch (Exception e) {
                System.err.println("File " + _fileId + ". No rights. " + website.getFile());
                return ;
            }

        }

    }

}
