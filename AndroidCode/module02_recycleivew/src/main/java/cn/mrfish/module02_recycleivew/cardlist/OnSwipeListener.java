package cn.mrfish.module02_recycleivew.cardlist;

import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Jack
 * @time 19-9-23 上午10:25
 * @describe    用于监听卡片滑动事件
 */

public interface OnSwipeListener<T> {

    /**
     * 卡片还在滑动时回调
     *
     * @param viewHolder 该滑动卡片的viewHolder
     * @param ratio 滑动进度的比例
     * @param direction 卡片滑出的方向，
     *                       CardConfig.SWIPING_UP 为上方滑出；
     *                       CardConfig.SSWIPING_DOWN 为下方滑出；
     *                       CardConfig.SWIPING_LEFT 为左边滑出，
     *                       CardConfig.SWIPING_RIGHT 为右边滑出
     *                       CardConfig.SWIPING_NONE 为不偏左也不偏右
     */

    void onSwiping(RecyclerView.ViewHolder viewHolder, float ratio, int direction);

    /**
     * 卡片完全滑出时回调
     *
     * @param viewHolder 该滑出卡片的viewHolder
     * @param t 该滑出卡片的数据
     * @param direction 卡片滑出的方向，
     *                  CardConfig.SWIPED_UP 为上方滑出；
     *                  CardConfig.SWIPED_DOWN 为下方滑出；
     *                  CardConfig.SWIPED_LEFT 为左边滑出，
     *                  CardConfig.SWIPED_RIGHT 为右边滑出
     */
    void onSwiped(RecyclerView.ViewHolder viewHolder, T t, int direction);

    /**
     * 所有的卡片全部滑出时回调
     */
    void onSwipedClear();
}
