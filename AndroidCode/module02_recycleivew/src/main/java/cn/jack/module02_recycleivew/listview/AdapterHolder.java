package cn.jack.module02_recycleivew.listview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jack
 * @time 19-10-21 上午9:19
 * @describe
 *
 *
 * 原文地址： [通用Adapter与ListView滚动时不加载图片的封装](https://www.kymjs.com/code/2015/04/28/01/)
 */
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 万能Adapter，查看KJAdapter
 *
 * @author kymjs(http://www.kymjs.com/)
 */
public class AdapterHolder {
    private final SparseArray<View> mViews;
    private final int mPosition;
    private final View mConvertView;

    private AdapterHolder(ViewGroup parent, int layoutId, int position) {
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(parent.getContext()).inflate(
                layoutId, parent, false);
        // setTag
        mConvertView.setTag(this);
    }

    /**
     * 拿到全部View
     *
     * @return
     */
    public SparseArray<View> getAllView() {
        return mViews;
    }

    /**
     * 拿到一个ViewHolder对象
     *
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static AdapterHolder get(View convertView, ViewGroup parent,
                                    int layoutId, int position) {
        if (convertView == null) {
            return new AdapterHolder(parent, layoutId, position);
        } else {
            return (AdapterHolder) convertView.getTag();
        }
    }

    public View getConvertView() {
        return mConvertView;
    }

    /**
     * 通过控件的Id获取对于的控件，如果没有则加入views
     *
     * @param viewId
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 为TextView设置字符串
     *
     * @param viewId
     * @param text
     * @return
     */
    public AdapterHolder setText(int viewId, CharSequence text) {
        TextView view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param drawableId
     * @return
     */
    public AdapterHolder setImageResource(int viewId, int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);

        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param bm
     * @return
     */
    public AdapterHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }

    /**
     * 为ImageView设置图片
     *
     * @param viewId
     * @param url
     * @return
     */
//    public AdapterHolder setImageByUrl(KJBitmap bitmap, int viewId, String url) {
//        bitmap.display(getView(viewId), url);
//        return this;
//    }

    public int getPosition() {
        return mPosition;
    }

}
