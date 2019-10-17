package cn.jack.module07_mvvm.http.viewModel.event;

/**
 * created by Jack
 * email:yucrun@163.com
 * date:19-5-13
 * describe:消息类型的 Event类
 *
 *  + ViewModel 与 LiveData 都是 Android Jetpack 架构组件之一 +
 *
 *  由于是 消息驱动 ,单独定义一个消息类型的类,更能体现是以 消息为驱动 的结构
 *
 */
public class BaseEvent {
    private int action;

    public BaseEvent(int action) {
        this.action = action;
    }

    public int getAction() {
        return action;
    }
}
