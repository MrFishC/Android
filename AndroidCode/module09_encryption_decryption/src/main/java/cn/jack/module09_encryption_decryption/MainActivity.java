package cn.jack.module09_encryption_decryption;

import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.tv);

        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("http://192.168.1.206:1111/pj/sso/getKeyCode")
                    .build();
            Call call = okHttpClient.newCall(request);
            //1.异步请求，通过接口回调告知用户 http 的异步执行结果
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println(e.getMessage());
                }
                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {

                        String resultJson = response.body().string();

                        System.out.println("==========================================");
                        System.out.println(resultJson);
                        System.out.println("==========================================");

                        JSONObject jsonObject = JSONObject.parseObject(resultJson);
                        JSONObject data =  jsonObject.getJSONObject("data");

                        System.out.println(data);
                        System.out.println("==========================================");

                        String  AES_IV =  data.getString("AES_IV");
                        String  AES_KEY =  data.getString("AES_KEY");
                        String  RSA_PUBLIC_KEY =  data.getString("RSA_PUBLIC_KEY");

                        System.out.println(AES_IV);
                        System.out.println("==========================================");
                        System.out.println(AES_KEY);
                        System.out.println("==========================================");
                        System.out.println(RSA_PUBLIC_KEY);
                        System.out.println("==========================================");

//                        String passwd = "helloworld";
                        String passwd = "1234qwer";

                        String passwds = AesUtils.getInstance(AES_KEY,AES_IV).encrypt(passwd);
//                        String passwds = AesUtils.getInstance("D60faTBiXnq1lu4Y","nX5BSQ0sN113RtB2").encrypt(passwd);

                        System.out.println("加密之后:"+passwds);
                        System.out.println("==========================================");



                        try {
                            RsaEncrypt.getInstance(new String(Base64.decode(RSA_PUBLIC_KEY,Base64.DEFAULT)));

                            byte[] str = RsaEncrypt.RSAEncode(passwds.getBytes());

//                            String string = Base64.encodeToString(str,Base64.DEFAULT);
                            String string = new String(Base64.encode(str,Base64.DEFAULT));

                            System.out.println("加密之后 string :" + string);
                            System.out.println("==========================================");

//                            textView.setText(string);

                            postData(okHttpClient,string);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();

            Log.d("onCreate123 ", " : " + e.getMessage());

        }

    }

    private void postData(OkHttpClient okHttpClient, String string) {

        TestBean testBean = new TestBean();
        testBean.setUserName("admin");
        testBean.setPassword(string);

        String json = JSON.toJSONString(testBean);

        System.out.println("--------------------------------- ---------------" + json);

        //请求body
        RequestBody body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);

        //请求组合创建
        Request request = new Request.Builder()
//                .url("http://192.168.1.164:8081/region-manager/pj/sso/login")
                .url("http://192.168.1.206:1111/pj/sso/login")
                .post(body)
                .build();

        //发起请求
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("e" + e.getMessage());
            }
            @Override
            public void onResponse(Call call, Response response){

                System.out.println("----------------" + response);
                System.out.println("------------------------------------------------");

                try {
                    System.out.println("----------------" + response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }

}
