package http;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class HttpImageStatusCli {
    private static final Logger LOG = LogManager.getLogger(HttpImageStatusCli.class);

    public void askStatus() {
        try (Scanner scanner = new Scanner(System.in)) {
            LOG.info("Enter HTTP status code: ");
            String input = scanner.nextLine();

            try {
                int statusCode = Integer.parseInt(input);
                HttpStatusImageDownloader httpStatusImageDownloader = new HttpStatusImageDownloader();
                httpStatusImageDownloader.downloadStatusImage(statusCode);
            } catch (NumberFormatException e) {
                LOG.error("Please enter a valid number");
            }
        }
    }
}
