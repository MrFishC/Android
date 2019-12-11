package cn.mrfish.helper.image;

import android.content.Context;
import android.widget.ImageView;
import androidx.annotation.DrawableRes;

/**
 * @author Jack
 * @time 19-12-10 下午8:33
 * @describe 图片加载框架隔离
 * 定义加载图片的方法[规范]
 */
public interface ILoader {

    void loadImageNetDefault(Context context, String url, ImageView imageView);

    // -------  网络图片   -------
    void loadImageNet(Context context, String urlId, ImageView imgeview);
    void loadICirclemageNet(Context context, String urlId, ImageView imgeview);

    void loadImageNet(Context context, String urlId, ImageView imgeview, int loadingResId, int loadErrorResId);
    void loadICirclemageNet(Context context, String urlId, ImageView imgeview, int loadingResId, int loadErrorResId);

//    void loadImageNetById(Context context, String urlId, ImageView imgeview);
//    void loadCircleImageNetById(Context context, String urlId, ImageView imgeview);
//    void loadImageNetById(Context context, String urlId, ImageView imgeview, int loadingResId, int loadErrorResId);
//    void loadCircleImageNetById(Context context, String urlId, ImageView imgeview, int loadingResId, int loadErrorResId);

    // -------  本地图片   -------
    void showImageLocalByDrawableRes(Context context, @DrawableRes int resourceId, ImageView imgeview);
    void showCircleImageLocalByDrawableRes(Context context, @DrawableRes int resourceId, ImageView imgeview);
    void showImageLocalByDrawableRes(Context context, @DrawableRes int resourceId, ImageView imgeview, int loadingResId, int loadErrorResId);
    void showCircleImageLocalByDrawableRes(Context context, @DrawableRes int resourceId, ImageView imgeview, int loadingResId, int loadErrorResId);

    void showImageLocalByPath(Context context, String path, ImageView imgeview);
    void showCircleImageLocalByPath(Context context, String path, ImageView imgeview);
    void showImageLocalByPath(Context context, String path, ImageView imgeview, int loadingResId, int loadErrorResId);
    void showCircleImageLocalByPath(Context context, String path, ImageView imgeview, int loadingResId, int loadErrorResId);

}
