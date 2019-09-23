package cn.jack.module02_recycleivew.cardlist;

import android.os.SystemClock;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.view.MotionEventCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @author Jack
 * @time 19-9-23 上午9:51
 * @describe    总体来说，CardLayoutManager 主要就是为 Item View 布局，然后根据 position 做相对应的偏差。
 *
 * copy from http://www.sohu.com/a/129609921_608959
 *
 * 步骤
 *      1.创建 CardLayoutManager 并继承自 RecyclerView.LayoutManager;
 *      2.实现 generateDefaultLayoutParams() 方法：
 *      3.重点,onLayoutChildren方法：用来实现 Item View 布局；
 *
 */
public class CardLayoutManager extends RecyclerView.LayoutManager{

    private static final String TAG = "CARD ";
    private RecyclerView mRecyclerView;
    private ItemTouchHelper mItemTouchHelper;
    private OnLoadMoreListener mOnLoadMoreListener;


    //实现 generateDefaultLayoutParams() 方法
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    public CardLayoutManager(@NonNull RecyclerView recyclerView, @NonNull ItemTouchHelper itemTouchHelper,OnLoadMoreListener onLoadMoreListener) {
        this.mRecyclerView = checkIsNull(recyclerView);
        this.mItemTouchHelper = checkIsNull(itemTouchHelper);
        this.mOnLoadMoreListener = onLoadMoreListener;
    }

    private <T> T checkIsNull(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        return t;
    }

    /**
     * 实现 Item View 布局
     * @param recycler
     * @param state
     */
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {

        //        super.onLayoutChildren(recycler, state);

        if (getItemCount() == 0) {//没有Item，界面空着吧
            detachAndScrapAttachedViews(recycler);
            return;
        }

        if (getChildCount() == 0 && state.isPreLayout()) {  //state.isPreLayout()是支持动画的
            return;
        }

        //3.1.  先移除所有view
        removeAllViews();

        //3.2.  在布局之前，将所有的子 View 先 Detach 掉,放入到 Scrap 缓存中
        detachAndScrapAttachedViews(recycler);

        int itemCount = getItemCount();

        // 在这里,我们默认配置 CardConfig.DEFAULT_SHOW_ITEM = 3。即在屏幕上显示的卡片数为3

        // 当数据源个数大于最大显示数时
        if (itemCount > CardConfig.DEFAULT_SHOW_ITEM) {

            // 把数据源倒着循环，这样，第0个数据就在屏幕最上面了            多层叠一张
            for (int position = CardConfig.DEFAULT_SHOW_ITEM; position >= 0; position--) {

                final View view = recycler.getViewForPosition(position);

                // 将 Item View 加入到 RecyclerView 中
                addView(view);
                // 测量 Item View
                measureChildWithMargins(view, 0, 0);

                // getDecoratedMeasuredWidth(view) 可以得到 Item View 的宽度
                // 所以 widthSpace 就是除了 Item View 剩余的值
                int widthSpace = getWidth() - getDecoratedMeasuredWidth(view);

                // 同理
                int heightSpace = getHeight() - getDecoratedMeasuredHeight(view);

                // 将 Item View 放入 RecyclerView 中布局
                // 在这里默认布局是放在 RecyclerView 中心
                layoutDecoratedWithMargins(view, widthSpace / 2, heightSpace / 2,
                        widthSpace / 2 + getDecoratedMeasuredWidth(view),
                        heightSpace / 2 + getDecoratedMeasuredHeight(view));

                // 其实屏幕上有四张卡片，但是我们把第三张和第四张卡片重叠在一起，这样看上去就只有三张
                // 第CardConfig.DEFAULT_SHOW_ITEM + 1张卡片主要是为了保持动画的连贯性
                System.out.println("position " + position);

                if (position == CardConfig.DEFAULT_SHOW_ITEM) {
                    // 按照一定的规则缩放，并且偏移Y轴。
                    // CardConfig.DEFAULT_SCALE 默认为0.1f，CardConfig.DEFAULT_TRANSLATE_Y 默认为14
                    view.setScaleX(1 - (position - 1) * CardConfig.DEFAULT_SCALE);
                    view.setScaleY(1 - (position - 1) * CardConfig.DEFAULT_SCALE);
//                    view.setTranslationY((position - 1) * view.getMeasuredHeight() / CardConfig.DEFAULT_TRANSLATE_Y);     //从下往上层叠
                    view.setTranslationY(-(position - 1) * view.getMeasuredHeight() / CardConfig.DEFAULT_TRANSLATE_Y);      //从上往下层叠
                }else if (position > 0) {
                    view.setScaleX(1 - position * CardConfig.DEFAULT_SCALE);
                    view.setScaleY(1 - position * CardConfig.DEFAULT_SCALE);
//                    view.setTranslationY(position * view.getMeasuredHeight() / CardConfig.DEFAULT_TRANSLATE_Y);    //从下往上层叠
                    view.setTranslationY(- position * view.getMeasuredHeight() / CardConfig.DEFAULT_TRANSLATE_Y);    //从上往下层叠
                }else {
                    // 设置 mTouchListener 的意义就在于我们想让处于顶层的卡片是可以随意滑动的
                    // 而第二层、第三层等等的卡片是禁止滑动的
                    view.setOnTouchListener(mOnTouchListener);
                }

            }

        }else {

            if(itemCount == CardConfig.DEFAULT_SHOW_ITEM){
                System.out.println("数据源触发验证");
                mOnLoadMoreListener.loadMoreData();
            }

            // 当数据源个数小于或等于最大显示数时，和上面的代码差不多
            for (int position = itemCount - 1; position >= 0; position--) {

                final View view = recycler.getViewForPosition(position);
                addView(view);

                measureChildWithMargins(view, 0, 0);

                int widthSpace = getWidth() - getDecoratedMeasuredWidth(view);
                int heightSpace = getHeight() - getDecoratedMeasuredHeight(view);

                layoutDecoratedWithMargins(view, widthSpace / 2, heightSpace / 2,
                        widthSpace / 2 + getDecoratedMeasuredWidth(view),
                        heightSpace / 2 + getDecoratedMeasuredHeight(view));

                if (position > 0) {
                    view.setScaleX(1 - position * CardConfig.DEFAULT_SCALE);
                    view.setScaleY(1 - position * CardConfig.DEFAULT_SCALE);
//                    view.setTranslationY(position * view.getMeasuredHeight() / CardConfig.DEFAULT_TRANSLATE_Y);       //从下往上层叠
                    view.setTranslationY(-position * view.getMeasuredHeight() / CardConfig.DEFAULT_TRANSLATE_Y);        //从上往下层叠
                }else {
                    view.setOnTouchListener(mOnTouchListener);
                    Log.i(TAG, " : " + SystemClock.currentThreadTimeMillis());
                }

            }
        }
    }

    private View.OnTouchListener mOnTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            RecyclerView.ViewHolder childViewHolder = mRecyclerView.getChildViewHolder(v);
            // 把触摸事件交给 mItemTouchHelper，让其处理卡片滑动事件
            if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                mItemTouchHelper.startSwipe(childViewHolder);
            }
            return false;
        }
    };

}
