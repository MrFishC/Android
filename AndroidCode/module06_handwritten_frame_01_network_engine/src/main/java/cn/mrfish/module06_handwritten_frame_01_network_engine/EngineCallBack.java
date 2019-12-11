package cn.mrfish.module06_handwritten_frame_01_network_engine;

import android.content.Context;

import java.util.Map;

/**
 * Email 240336124@qq.com
 * Created by Darren on 2017/3/4.
 * Version 1.0
 * Description:
 */
public interface EngineCallBack {
    // 开始执行，在执行之前会回掉的方法
    public void onPreExecute(Context context, Map<String, Object> params);

    // 错误
    public void onError(Exception e);

    // 成功  返回对象会出问题   成功  data对象{"",""}  失败  data:""
    public void onSuccess(String result);


    // 默认的

    public final EngineCallBack DEFUALT_CALL_BACK = new EngineCallBack() {
        @Override
        public void onPreExecute(Context context, Map<String, Object> params) {

        }

        @Override
        public void onError(Exception e) {

        }

        @Override
        public void onSuccess(String result) {

        }
    };
}
