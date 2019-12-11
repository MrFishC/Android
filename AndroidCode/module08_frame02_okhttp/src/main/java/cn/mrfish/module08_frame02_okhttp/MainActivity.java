package cn.mrfish.module08_frame02_okhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





    }

    private void get() throws IOException{
//            OkHttpClient client = new OkHttpClient();
//            Request request = new Request.Builder()
//                    .url("")
//                    .build();
//
//            Response response = client.newCall(request).execute();
//            return response.body().string();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://www.baidu.com")
                .build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                System.out.println("成功");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
