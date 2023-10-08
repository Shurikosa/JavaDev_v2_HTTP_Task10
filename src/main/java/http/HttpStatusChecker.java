package http;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

public class HttpStatusChecker {
    private static final Logger LOG = LogManager.getLogger(HttpStatusChecker.class);
    private static final String BASE_URL = "https://http.cat/";

    public String getStatusImage(int code){
        if(code < 100 || code > 599){
            LOG.error("Invalid status code: " + code);
            throw new IllegalArgumentException("Invalid status code");
        }
        String imageUrl = BASE_URL + code + ".jpg";
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(imageUrl)
                .build();
        try ( Response response = client.newCall(request).execute()){
            if (response.code() == 404) {
                LOG.error("Images for code " + code + " do not exist");
            }
        } catch (IOException e) {
            LOG.error("Error making the request", e.getMessage());
        }
        return imageUrl;
    }
}
