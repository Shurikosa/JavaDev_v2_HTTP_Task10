package http;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;


public class HttpStatusImageDownloader {
    private static final Logger LOG = LogManager.getLogger(HttpStatusImageDownloader.class);
    private static final OkHttpClient HTTP_CLIENT = new OkHttpClient();
    public void downloadStatusImage(int code){
        LOG.info("get HTTP status");
        HttpStatusChecker checker = new HttpStatusChecker();
        String imageUrl = checker.getStatusImage(code);
        LOG.info("create request");
        Request request = new Request.Builder()
                .url(imageUrl)
                .build();
        try(Response response = HTTP_CLIENT.newCall(request).execute()){
            if(response.isSuccessful()){
                try(InputStream inputStream = response.body().byteStream()){
                    saveImageToFile(inputStream, code);
                }
            }else {
                LOG.error("Image upload error for status code " + code +
                        ". Response code: " + response.code());
            }
        }catch (IOException e){
            LOG.error("Error while downloading image", e.getMessage());
        }
    }

    private void saveImageToFile(InputStream inputStream, int code) throws IOException{
        try(FileOutputStream outputStream = new FileOutputStream(code + ".jpg")){
            byte[] buffer = new byte[1024];
            int bytesRead;
            while((bytesRead = inputStream.read(buffer)) != -1){
                outputStream.write(buffer,0,bytesRead);
            }
            LOG.info("The image for the status code: " + code + " has been uploaded and saved.");
        }
    }
}
