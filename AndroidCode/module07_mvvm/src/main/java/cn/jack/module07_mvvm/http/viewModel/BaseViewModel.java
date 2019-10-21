package cn.jack.module07_mvvm.http.viewModel;

import android.os.Bundle;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.HashMap;
import java.util.Map;
import cn.jack.module07_mvvm.http.other.constant.ParameterField;
import cn.jack.module07_mvvm.http.model.BaseModel;
import cn.jack.module07_mvvm.http.other.tools.DoubleUtils;
import cn.jack.module07_mvvm.http.viewModel.event.BaseActionEvent;
import io.reactivex.disposables.Disposable;

/**
 * @author Jack
 * @time 19-10-16 下午5:36
 * @describe
 *
 * 作用：
 *      管理ui的数据
 *      组件间的通讯
 *
 */
public class BaseViewModel<M extends BaseModel> extends ViewModel implements IViewModelAction,ILifecycleCallback {

    private MutableLiveData<BaseActionEvent> mActionLiveData;

    /**
     * ViewModel层持有Model层引用,Model的具体实现类为自定义的xxxRepository(数据仓库类)
     */
    protected M mModel;

    public BaseViewModel(M model) {
        mActionLiveData = new MutableLiveData<>();
        this.mModel = model;
    }

    /**
     * 调用BaseModel中的addSubscribe方法,处理rxjava的内存泄露         xxxRepository实现BaseModel后即可调用addSubscribe方法
     * @param disposable
     */
    protected void addSubscribe(Disposable disposable) {
        if (mModel == null) {
            throw new NullPointerException("Create ViewModel with ViewModelFactory with Model");
        }
        mModel.addSubscribe(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        //viewmodle层执行onCleared方法时,执行BaseModel的onCleared方法,即取消所有异步任务
        if (mModel != null) {
            mModel.onCleared();
        }
    }

    /**
     * =============================================== 生命周期方法 start =================================================
     */

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    /**
     * =============================================== 生命周期方法 end  =================================================
     */

    /**
     * =============================================== 公共部分,v层通过观察vm层,自行决定是否需要调用v层的方法 start  =================================================
     */

    @Override
    public void finish() {
        mActionLiveData.setValue(new BaseActionEvent(BaseActionEvent.FINISH));
    }

    @Override
    public void onBackPressed() {
        mActionLiveData.setValue(new BaseActionEvent(BaseActionEvent.BACK_PRESSED));
    }

    @Override
    public void closeSoftKey() {
        mActionLiveData.setValue(new BaseActionEvent(BaseActionEvent.CLOSE_SOFT_KEY));
    }

    @Override
    public void showDialog() {
        showDialog(null);
    }

    @Override
    public void showDialog(String message) {
        mActionLiveData.setValue(new BaseActionEvent(BaseActionEvent.SHOW_LOADING_DIALOG));
    }

    @Override
    public void dismissDialog() {
        mActionLiveData.setValue(new BaseActionEvent(BaseActionEvent.DISMISS_LOADING_DIALOG));
    }

    @Override
    public void openActivity(Class<?> clz) {
        openActivity(clz,null);
    }

    @Override
    public void openActivity(Class<?> clz, Bundle bundle) {

        if(!DoubleUtils.isFastDoubleClick()){
            Map<String, Object> params = new HashMap<>();
            params.put(ParameterField.CLASS, clz);

            if (bundle != null) {
                params.put(ParameterField.BUNDLE, bundle);
            }

            BaseActionEvent baseActionEvent = new BaseActionEvent(BaseActionEvent.OPEN_ACTIVTIY);
            baseActionEvent.setParams(params);
            mActionLiveData.setValue(baseActionEvent);
        }

    }

    @Override
    public void showToast(String message) {
        BaseActionEvent baseActionEvent = new BaseActionEvent(BaseActionEvent.SHOW_TOAST);
        baseActionEvent.setMessage(message);
        mActionLiveData.setValue(baseActionEvent);
    }

    /**
     * =============================================== 公共部分,v层通过观察vm层,自行决定是否需要调用v层的方法 end =================================================
     */

    @Override
    public MutableLiveData<BaseActionEvent> getActionLiveData() {

        if(mActionLiveData == null){
            mActionLiveData = new MutableLiveData<>();
        }

        return mActionLiveData;
    }

}
