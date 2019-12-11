package cn.mrfish;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @author Jack
 * @time 19-10-28 下午4:35
 * @describe
 */
public class MyView1 extends View {

    Paint paint=new Paint();

    public MyView1(Context context) {
        this(context,null);
    }

    public MyView1(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    
    protected void onDraw(Canvas canvas) {              //重载onDraw方法
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();

//        paint.setColor(Color.RED);                      //设置画笔颜色
////        paint.setStyle(Paint.Style.FILL);   //设置画笔为空心     如果将这里改为Style.STROKE  这个图中的实线圆柱体就变成了空心的圆柱体
//        paint.setStyle(Paint.Style.FILL);   //设置画笔为空心     如果将这里改为Style.STROKE  这个图中的实线圆柱体就变成了空心的圆柱体
//        canvas.drawColor(Color.WHITE);
////        canvas.drawLine(50, 50, 450, 50, paint);      //绘制直线
//        canvas.drawRect(100, 100, 200, 600, paint);     //绘制矩形
////        canvas.drawRect(300, 100, 400, 600, paint);     //绘制矩形


        Path path = new Path();

        path.moveTo(100,100);
        path.lineTo(50,150);
        path.lineTo(100,150);
        path.lineTo(150,100);



//        path.moveTo(100,100);
//        path.lineTo(50,150);
//        path.lineTo(100,150);
//        path.lineTo(150,100);
        path.close();

        paint.setStrokeWidth(1);
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawPath(path,paint);

    }
}


