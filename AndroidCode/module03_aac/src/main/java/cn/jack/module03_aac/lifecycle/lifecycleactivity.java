package cn.jack.module03_aac.lifecycle;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import cn.jack.module03_aac.R;

/**
 * @author Jack
 * @time 19-9-25 下午12:21
 * @describe
 *
 * 推荐－程序亦非猿（船长）－的文章：
 *
 *      https://mp.weixin.qq.com/s?__biz=MzIxNDE1NjQ2Mw==&mid=2649872556&idx=1&sn=6734027d9ca7e4db67fdbad657f7c862&scene=19#wechat_redirect
 *
 * 问题：
 *      Lifecycle 利用了 Fragment(ReportFragment) 来实现监听生命周期;
 *
 *
 */
public class lifecycleactivity extends AppCompatActivity{

    private static final String TAG = "lifecycleactivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testLifecycle();
    }

    private void testLifecycle() {
        getLifecycle().addObserver(new LifecycleObserver() {

            @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
            void onResume(){
                Log.d(TAG, "LifecycleObserver onResume() called");
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

}
