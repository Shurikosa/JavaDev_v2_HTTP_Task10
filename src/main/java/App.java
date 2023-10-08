import http.HttpImageStatusCli;
import http.HttpStatusChecker;
import http.HttpStatusImageDownloader;

public class App {

    public static void main(String[] args) {
        HttpImageStatusCli httpImageStatusCli = new HttpImageStatusCli();
        httpImageStatusCli.askStatus();

    }
}
