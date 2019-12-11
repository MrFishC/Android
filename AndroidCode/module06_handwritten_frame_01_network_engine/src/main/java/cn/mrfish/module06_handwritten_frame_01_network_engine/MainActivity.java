package cn.mrfish.module06_handwritten_frame_01_network_engine;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * learn from 曾辉老师  https://www.jianshu.com/p/7fdfbe556194
 *
 *
 * 曾辉老师使用的使用封装的方式;
 *
 * 也有其它的实现方式，比如代理，但不推荐；
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
