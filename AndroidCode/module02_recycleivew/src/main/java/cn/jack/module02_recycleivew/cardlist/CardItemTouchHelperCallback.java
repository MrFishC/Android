package cn.jack.module02_recycleivew.cardlist;

import android.graphics.Canvas;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/**
 * @author Jack
 * @time 19-9-23 上午10:30
 * @describe todo 上滑下滑还存在bug
 */
public class CardItemTouchHelperCallback<T> extends ItemTouchHelper.Callback{

    private final RecyclerView.Adapter adapter;
    private       List<T>              dataList;
    private       OnSwipeListener<T>   mListener;

    public CardItemTouchHelperCallback(@NonNull RecyclerView.Adapter adapter, @NonNull List<T> dataList, OnSwipeListener<T> listener) {
        this.adapter = checkIsNull(adapter);
        this.dataList = checkIsNull(dataList);
        this.mListener = listener;
    }

    private <T> T checkIsNull(T t) {
        if (t == null) {
            throw new NullPointerException();
        }
        return t;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {

        //        System.out.println("getMovementFlags");
        //        System.out.println("getItemCount " + recyclerView.getAdapter().getItemCount());

        //        if (recyclerView.getAdapter() != null) {

        //item数量
        //int childCount = recyclerView.getAdapter().getItemCount();

        //item数量为1的情况下,不设置滑动效果                      更改为循环加载
        //            if(childCount  == 1){
        //                return 0;
        //            }

        //1.对于 swipeFlags 只关心上下两个方向：

        int dragFlags = 0;
        int swipeFlags = 0;

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof CardLayoutManager) {
            //            swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;

            //设置item滑动的方向
            swipeFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            return makeMovementFlags(dragFlags, swipeFlags);
        }
        //        }

        return 0;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        //3.因为在上面我们对于 dragFlags 配置的是 0 ，
        //所以在 onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) 中直接返回 false 即可
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        //4.移除之前设置的 onTouchListener, 否则触摸滑动会乱了
        viewHolder.itemView.setOnTouchListener(null);

        //4.1.删除相对应的数据
        int layoutPosition = viewHolder.getLayoutPosition();

        System.out.println("layoutPosition " + layoutPosition);

        T itemBean = dataList.get(layoutPosition);
//        T itemBean = dataList.remove(layoutPosition);
//        adapter.notifyDataSetChanged();

        //4.2.卡片滑出后回调 OnSwipeListener 监听器
        if (mListener != null) {
            //            mListener.onSwiped(viewHolder, remove, direction == ItemTouchHelper.LEFT ? CardConfig.SWIPED_LEFT : CardConfig.SWIPED_RIGHT);
            mListener.onSwiped(viewHolder, itemBean, direction == ItemTouchHelper.UP ? CardConfig.SWIPED_UP : CardConfig.SWIPED_DOWN);

            //4.3.当没有数据时回调 OnSwipeListener 监听器
            if (adapter.getItemCount() == 0) {
                mListener.onSwipedClear();
            }
        }

    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        //2.为了防止第二层和第三层卡片也能滑动，因此我们需要设置 isItemViewSwipeEnabled() 返回 false 。
        return false;
    }

    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        View itemView = viewHolder.itemView;
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            // 得到滑动的阀值
            //            float ratio = dX / getThreshold(recyclerView, viewHolder);
            float ratio = dY / getThreshold(recyclerView, viewHolder);

            // ratio 最大为 1 或 -1
            if (ratio > 1) {
                ratio = 1;
            } else if (ratio < -1) {
                ratio = -1;
            }

            // 默认最大的旋转角度为 15 度                      上下滑动不设置旋转角度
            //            itemView.setRotation(ratio * CardConfig.DEFAULT_ROTATE_DEGREE);
            int childCount = recyclerView.getChildCount();

            System.out.println("数据源" + childCount);

            // 当数据源个数大于最大显示数时
            if (childCount > CardConfig.DEFAULT_SHOW_ITEM) {

                System.out.println("数据源" + "大于");

                for (int position = 1; position < childCount - 1; position++) {
                    int index = childCount - position - 1;
                    View view = recyclerView.getChildAt(position);

                    // 和之前 onLayoutChildren 是一个意思，不过是做相反的动画
                    view.setScaleX(1 - index * CardConfig.DEFAULT_SCALE + Math.abs(ratio) * CardConfig.DEFAULT_SCALE);
                    view.setScaleY(1 - index * CardConfig.DEFAULT_SCALE + Math.abs(ratio) * CardConfig.DEFAULT_SCALE);

                    //从下往上层叠,滑动过程中,item的内部的item缩放动画
                    //                    view.setTranslationY((index - Math.abs(ratio)) * itemView.getMeasuredHeight() / CardConfig.DEFAULT_TRANSLATE_Y);

                    //从上往下层叠,滑动过程中,item的内部的item缩放动画
                    view.setTranslationY(-(index - Math.abs(ratio)) * itemView.getMeasuredHeight() / CardConfig.DEFAULT_TRANSLATE_Y);

                }

            }else {

                System.out.println("数据源" + "小于");

                // 当数据源个数小于或等于最大显示数时
                for (int position = 0; position < childCount - 1; position++) {
                    int index = childCount - position - 1;
                    View view = recyclerView.getChildAt(position);
                    view.setScaleX(1 - index * CardConfig.DEFAULT_SCALE + Math.abs(ratio) * CardConfig.DEFAULT_SCALE);
                    view.setScaleY(1 - index * CardConfig.DEFAULT_SCALE + Math.abs(ratio) * CardConfig.DEFAULT_SCALE);

                    //从下往上层叠,滑动过程中,item的内部的item缩放动画
                    //                    view.setTranslationY((index - Math.abs(ratio)) * itemView.getMeasuredHeight() / CardConfig.DEFAULT_TRANSLATE_Y);

                    //从上往下层叠,滑动过程中,item的内部的item缩放动画
                    view.setTranslationY(-(index - Math.abs(ratio)) * itemView.getMeasuredHeight() / CardConfig.DEFAULT_TRANSLATE_Y);

                }
            }

            System.out.println("childCount" + childCount);

            // 回调监听器
            if (mListener != null) {

                if (ratio != 0) {
                    mListener.onSwiping(viewHolder, ratio, ratio < 0 ? CardConfig.SWIPING_UP : CardConfig.SWIPING_DOWN);
                } else {
                    mListener.onSwiping(viewHolder, ratio, CardConfig.SWIPING_NONE);
                }

            }

        }
    }

    //发现还是有问题，第一层的卡片滑出去之后第二层的就莫名其妙地偏了。这正是因为 Item View 重用机制“捣鬼”。
    //所以我们应该在 clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) 方法中重置一下
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setRotation(0f);
    }

    private float getThreshold(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {

        //若item若是左右滑动,则以宽为基准
        //        return recyclerView.getWidth() * getSwipeThreshold(viewHolder);

        //item若是上下滑动,则以高为基准
        return recyclerView.getHeight() * getSwipeThreshold(viewHolder);

    }

}

