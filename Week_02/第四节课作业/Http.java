import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author zhurui
 * @Date 2021/1/19 6:47 下午
 * @Version 1.0
 */
public class Http {

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        Request request = new Request.Builder().url("http://localhost:8888/api/hello").method("GET", null)
            .addHeader("invokeOrigin", "\"1123\"").build();
        Response response = client.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
