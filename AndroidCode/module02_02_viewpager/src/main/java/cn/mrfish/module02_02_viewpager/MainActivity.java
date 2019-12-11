package cn.mrfish.module02_02_viewpager;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

/**
 * copy from https://www.imooc.com/article/274387
 *
 * 背景
 * 实现
 *
 *      ViewPager.PageTransformer接口:控制ViewPager中各个页面的偏移显示效果
 *
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
