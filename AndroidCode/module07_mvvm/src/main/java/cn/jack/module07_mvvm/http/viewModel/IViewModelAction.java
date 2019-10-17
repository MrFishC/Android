package cn.jack.module07_mvvm.http.viewModel;

import android.os.Bundle;
import androidx.lifecycle.MutableLiveData;
import cn.jack.module07_mvvm.http.viewModel.event.BaseActionEvent;

/**
 * @author Jack
 * @time 19-10-16 下午5:35
 * @describe 抽取成接口比较好
 *
 * BaseViewModel的实现类直接调用方法即可
 */
public interface IViewModelAction {

    /**
     * 关闭界面
     */
    void finish();

    /**
     * 返回上一层
     */
    void onBackPressed();

    /**
     * 关闭软件盘
     */
    void closeSoftKey();

    /**
     * 展示对话框
     */
    void showDialog();

    /**
     * 展示对话框
     */
    void showDialog(String message);

    /**
     * 关闭对话框
     */
    void dismissDialog();

    /**
     * 打开界面
     */
    void openActivity(Class<?> clz);

    /**
     * 吐司
     */
    void showToast(String message);

    /**
     * 打开界面
     */
    void openActivity(Class<?> clz, Bundle bundle);

    MutableLiveData<BaseActionEvent> getActionLiveData();

}
