package cn.mrfish;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * @author Jack
 * @time 19-10-28 下午4:16
 * @describe
 */
public class TestView extends AppCompatImageView {

    private int mOffSet = 100;

    public TestView(Context context) {
        this(context, null);
    }

    public TestView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TestView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();

        Drawable d = getResources().getDrawable(R.mipmap.timg);

        d.setBounds(0, 0, width, height);


        mOffSet = width/2;

        canvas.save();

        //一个平行四边形
        Path path = new Path();
        path.moveTo(mOffSet, 0);
        path.lineTo(0, height);
        path.lineTo(width - mOffSet, height);
        path.lineTo(width, 0);
        canvas.clipPath(path);

        //将图像画在canvas上
        d.draw(canvas);
        canvas.restore();

    }

}