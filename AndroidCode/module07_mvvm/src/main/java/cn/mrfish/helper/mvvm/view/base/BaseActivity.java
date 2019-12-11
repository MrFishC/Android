package cn.mrfish.helper.mvvm.view.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import java.util.Map;
import cn.mrfish.helper.other.constant.ParameterField;
import cn.mrfish.helper.other.tools.DoubleUtils;
import cn.mrfish.helper.mvvm.viewModel.BaseViewModel;
import cn.mrfish.helper.mvvm.viewModel.event.BaseActionEvent;

/**
 * @author Jack
 * @time 19-10-16 下午5:47
 * @describe 消息的接收方之一的基类 BaseActivity
 *
 */
public abstract class BaseActivity<VM extends BaseViewModel> extends AppCompatActivity implements IBaseView{

    protected VM mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewModel = initViewModel(this,setViewModel());

        registorUIChangeLiveDataCallBack();

    }

    protected abstract Class<VM> setViewModel();

    /**
     * 初始化vm,由子类实现
     */
    protected VM initViewModel(@NonNull FragmentActivity activity, @NonNull Class<VM> viewModel){
       return mViewModel = ViewModelProviders.of(activity).get(viewModel);
    }

    /**
     * 注册ViewModel与View的契约UI回调事件,即vm驱动v层更新ui
     */
    private void registorUIChangeLiveDataCallBack() {

        mViewModel.getActionLiveData().observe(this, new Observer<BaseActionEvent>() {
            @Override
            public void onChanged(BaseActionEvent baseActionEvent) {
                if (baseActionEvent != null) {
                    switch (baseActionEvent.getAction()) {

                        case BaseActionEvent.FINISH:
                                finish();
                            break;

                        case BaseActionEvent.BACK_PRESSED:

                            break;

                        case BaseActionEvent.CLOSE_SOFT_KEY:
                                closeInputMethod();
                            break;

                        case BaseActionEvent.SHOW_LOADING_DIALOG:
                                showMDialog(baseActionEvent.getMessage());
                            break;

                        case BaseActionEvent.DISMISS_LOADING_DIALOG:
                                dismissMDialog();
                            break;
                        case BaseActionEvent.FINISH_WITH_RESULT_OK:

                            break;

                        case BaseActionEvent.OPEN_ACTIVTIY:
                            Map<String, Object> params = baseActionEvent.getParams();
                            Class<?> clz = (Class<?>) params.get(ParameterField.CLASS);
                            Bundle bundle = (Bundle) params.get(ParameterField.BUNDLE);
                            openActivity(clz, bundle);
                            break;
                        case BaseActionEvent.SHOW_TOAST:

                            break;

                    }
                }
            }
        });

    }

    /**
     * 收起键盘
     */
    protected void closeInputMethod() {
        // 收起键盘
        View view = getWindow().peekDecorView();// 用于判断虚拟软键盘是否是显示的
        if (view != null) {
            InputMethodManager inputmanger = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    public void openActivity(Class<?> clz, Bundle bundle) {
        if(!DoubleUtils.isFastDoubleClick()){
            Intent intent = new Intent(this, clz);
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            startActivity(intent);
        }

    }

    /**
     * 关闭对话框
     */
    protected void dismissMDialog() {

    }

    /**
     * 加载对话框
     * @param title 对话框提示内容
     */
    protected void showMDialog(String title) {

    }

}
