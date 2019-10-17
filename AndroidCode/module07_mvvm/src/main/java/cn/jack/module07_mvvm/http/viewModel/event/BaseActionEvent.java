package cn.jack.module07_mvvm.http.viewModel.event;

import java.util.Map;

/**
 * created by Jack
 * email:yucrun@163.com
 * date:19-5-13
 * describe:通用的事件
 *
 *  + 用于向 View 层传递 Action 的 Model +
 *  + 在 ViewModel层 通过向 View 层传递不同的消息类型，从而触发View 层 执行相对应更新ui的操作 +
 *
 *  + 注意,在设置值的时候,不要相同 +
 */
public class BaseActionEvent extends BaseEvent {

    public static final int FINISH = 1;

    public static final int BACK_PRESSED = 2;

    public static final int CLOSE_SOFT_KEY = 3;

    public static final int SHOW_LOADING_DIALOG = 4;

    public static final int DISMISS_LOADING_DIALOG = 5;

    public static final int FINISH_WITH_RESULT_OK = 6;

    public static final int OPEN_ACTIVTIY = 7;

    public static final int SHOW_TOAST = 8;

    private String              message;

    /**
     * 其它参数
     */
    private Map<String, Object> params;

    public BaseActionEvent(int action) {
        super(action);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

}
