package cn.mrfish.module02_recycleivew.cardlist;

/**
 * @author Jack
 * @time 19-9-23 上午10:12
 * @describe copy from https://github.com/yuqirong/CardSwipeLayout/blob/master/lib/src/main/java/me/yuqirong/cardswipelayout/CardConfig.java
 */
public class CardConfig {
    /**
     * 显示可见的卡片数量
     */
    public static final int DEFAULT_SHOW_ITEM = 3;
    /**
     * 默认缩放的比例
     */
    public static final float DEFAULT_SCALE = 0.1f;
    /**
     * 卡片Y轴偏移量时按照14等分计算         这里的值
     */
    public static final int DEFAULT_TRANSLATE_Y = 10;
    /**
     * 卡片滑动时默认倾斜的角度
     */
    public static final float DEFAULT_ROTATE_DEGREE = 15f;
    /**
     * 卡片滑动时不偏左也不偏右
     */
//    public static final int SWIPING_NONE = 1;
    public static final int SWIPING_NONE = -1;

//    /**
//     * 卡片向左滑动时
//     */
//    public static final int SWIPING_LEFT = 1 << 2;
    /**
     * 卡片向上滑动时
     */
    public static final int SWIPING_UP = 1 << 2;

//    /**
//     * 卡片向右滑动时
//     */
//    public static final int SWIPING_RIGHT = 1 << 3;

    /**
     * 卡片向下滑动时
     */
    public static final int SWIPING_DOWN = 1 << 3;

//    /**
//     * 卡片从左边滑出
//     */
//    public static final int SWIPED_LEFT = 1;

    /**
     * 卡片从上方滑出
     */
    public static final int SWIPED_UP = 1;

//    /**
//     * 卡片从右边滑出
//     */
//    public static final int SWIPED_RIGHT = 1 << 2;

    /**
     * 卡片从下方滑出
     */
    public static final int SWIPED_DOWN = 1 << 1;

}
