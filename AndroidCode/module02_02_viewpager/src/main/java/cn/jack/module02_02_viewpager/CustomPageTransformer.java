package cn.jack.module02_02_viewpager;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

/**
 * @author Jack
 * @time 19-9-27 上午11:42
 * @describe
 *
 *
 *  1.x轴的处理
 *      既然ViewPager里面的View默认是水平排列的,那么只要将每个view的x轴坐标更改为：
 *          view的宽度乘以下标的负数,这样就排列在一起了.为了方便起见,还给view增加了一个透明度.
 *
 *  2.实现卡片在Y方向垂直偏移和卡片大小的缩放操作，就可以实现叠加效果了；
 *
 *  代码如下:
 *
 */
public class CustomPageTransformer implements ViewPager.PageTransformer {

    //todo 设置值
    private int mOffset = 40;

    /**
     *
     * @param page  当前正在滑动的页面
     *
     * @param position 当前滑动状态的表示
     */
    @Override
    public void transformPage(@NonNull View page, float position) {

        //设置透明度
        page.setAlpha(0.5f);

        //设置每个View在中间，即设置相对原位置偏移量
//        page.setTranslationX((-page.getWidth() * position));

        if (position > 0){
            //移动X轴坐标，使得卡片在同一坐标
            page.setTranslationX(-position * page.getWidth());
            //缩放卡片并调整位置
            float scale = (page.getWidth() - mOffset * position) / page.getWidth();
            page.setScaleX(scale);
            page.setScaleY(scale);
            //移动Y轴坐标
            page.setTranslationY(position * mOffset);
        }


    }

}
