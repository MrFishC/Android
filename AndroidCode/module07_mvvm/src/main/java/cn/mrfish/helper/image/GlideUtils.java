package cn.mrfish.helper.image;

import android.content.Context;
import android.widget.ImageView;
import androidx.annotation.DrawableRes;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

/**
 * @author Jack
 * @time 19-12-10 下午8:33
 * @describe glide工具类
 */

public class GlideUtils {

    private int mLoadingResId;

    private int mLoadErrorResId;

    /**
     * 使用静态内部类的方式实现单例
     */
    public static GlideUtils getInstance() {
        return GlideUtils.Holder.INSTANCE;
    }

    private static class Holder {
        private static final GlideUtils INSTANCE = new GlideUtils();
    }

    private GlideUtils() {

    }

    public void initDefaultResource(int loadingResId,int loadErrorResId){
        this.mLoadingResId = loadingResId;
        this.mLoadErrorResId = loadErrorResId;
    }

    /**
     *      加载图片(默认)
     *      不设置属性
     * @param context 上下文
     * @param url   图片网络链接
     * @param imageView 目标控件
     */
    public void loadImageNetDefault(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).into(imageView);
    }

    /**
     *
     * @param context 上下文
     * @param url   图片地址
     * @param imgeview 目标控件
     */
    public  void loadImageNet(Context context,String url,ImageView imgeview) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(mLoadingResId)
                .placeholder(mLoadErrorResId)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imgeview);
    }

    /**
     * 圆图
     * @param context 上下文
     * @param url   图片地址
     * @param imgeview 目标控件
     */
    public void loadICirclemageNet(Context context,String url,ImageView imgeview) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .circleCrop()
                .error(mLoadingResId)
                .placeholder(mLoadErrorResId)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imgeview);
    }

    /**
     *
     * @param context 上下文
     * @param url   图片地址
     * @param imgeview 目标控件
     */
    public  void loadImageNet(Context context,String url,ImageView imgeview,int loadingResId,int loadErrorResId) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(loadingResId)
                .placeholder(loadErrorResId)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imgeview);
    }

    /**
     * 圆图
     * @param context 上下文
     * @param url   图片地址
     * @param imgeview 目标控件
     */
    public void loadICirclemageNet(Context context,String url,ImageView imgeview,int loadingResId,int loadErrorResId) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .circleCrop()
                .error(loadingResId)
                .placeholder(loadErrorResId)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context)
                .load(url)
                .apply(options)
                .into(imgeview);
    }

    /**
     * 需要使用则自行拓展
     * @param context 上下文
     * @param urlId   服务器图片id，需要拼接图片地址前缀
     * @param imgeview 目标控件
     */
//    public void loadImageNetById(Context context,String urlId,ImageView imgeview) {
//        RequestOptions options = new RequestOptions()
//                .centerCrop()
//                .error(mLoadingResId)
//                .placeholder(mLoadErrorResId)
//                .dontAnimate()
//                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
//        Glide.with(context)
//                .load("" + urlId)
//                .apply(options)
//                .into(imgeview);
//    }

    /**
     *
     * @param context       上下文
     * @param resourceId drawable资源
     * @param imgeview      目标控件
     */
    public void showImageLocalByDrawableRes(Context context, @DrawableRes int resourceId, ImageView imgeview) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(mLoadingResId)
                .placeholder(mLoadErrorResId)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context)
                .load(resourceId)
                .apply(options)
                .into(imgeview);
    }

    /**
     *
     * @param context       上下文
     * @param resourceId drawable资源
     * @param imgeview      目标控件
     */
    public  void showCircleImageLocalByDrawableRes(Context context, @DrawableRes int resourceId,
                                     ImageView imgeview) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .circleCrop()
                .error(mLoadingResId)
                .placeholder(mLoadErrorResId)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context)
                .load(resourceId)
                .apply(options)
                .into(imgeview);
    }

    /**
     * @param context       上下文
     * @param resourceId drawable资源
     * @param imgeview      目标控件
     */
    public void showImageLocalByDrawableRes(Context context, @DrawableRes int resourceId, ImageView imgeview,int loadingResId,int loadErrorResId) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(loadingResId)
                .placeholder(loadErrorResId)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context)
                .load(resourceId)
                .apply(options)
                .into(imgeview);
    }

    /**
     * @param context       上下文
     * @param resourceId drawable资源
     * @param imgeview      目标控件
     */
    public  void showCircleImageLocalByDrawableRes(Context context, @DrawableRes int resourceId, ImageView imgeview,int loadingResId,int loadErrorResId) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .circleCrop()
                .error(loadingResId)
                .placeholder(loadErrorResId)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context)
                .load(resourceId)
                .apply(options)
                .into(imgeview);
    }


    /**
     * 显示图片Imageview,GridImageAdapter中使用
     *
     * @param context       上下文
     * @param path          图片文件路径
     * @param imgeview      iv控件
     */
    public  void showImageLocalByPath(Context context, String path, ImageView imgeview) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(mLoadingResId)
                .placeholder(mLoadErrorResId)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context)
                .load(path)
                .apply(options)
                .into(imgeview);
    }

    /**
     * 显示图片Imageview,GridImageAdapter中使用
     *
     * @param context       上下文
     * @param path             图片文件路径
     * @param imgeview    iv控件
     */
    public void showCircleImageLocalByPath(Context context, String path, ImageView imgeview) {

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .circleCrop()
                .error(mLoadingResId)
                .placeholder(mLoadErrorResId)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context)
                .load(path)
                .apply(options)
                .into(imgeview);
    }

    /**
     * 显示图片Imageview,GridImageAdapter中使用
     *
     * @param context       上下文
     * @param path          图片文件路径
     * @param imgeview      iv控件
     */
    public  void showImageLocalByPath(Context context, String path, ImageView imgeview,int loadingResId,int loadErrorResId) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .error(loadingResId)
                .placeholder(loadErrorResId)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context)
                .load(path)
                .apply(options)
                .into(imgeview);
    }

    /**
     * 显示图片Imageview,GridImageAdapter中使用
     *
     * @param context       上下文
     * @param path             图片文件路径
     * @param imgeview    iv控件
     */
    public void showCircleImageLocalByPath(Context context, String path, ImageView imgeview,int loadingResId,int loadErrorResId) {

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .circleCrop()
                .error(loadingResId)
                .placeholder(loadErrorResId)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context)
                .load(path)
                .apply(options)
                .into(imgeview);
    }

//    /**
//     * Load cust round circle image.
//     *
//     * @param context   the context
//     * @param url       the url
//     * @param imageView the image view
//     * @param type      the type
//     */
//    /*
//     *加载圆角图片-指定任意部分圆角（图片上、下、左、右四个角度任意定义）
//     */
//    public  void loadCustRoundCircleImage(Context context, String url, ImageView imageView,
//                                          RoundedCornersTransformation.CornerType type) {
//        RequestOptions options = new RequestOptions()
//                .centerCrop()
//                .error(mLoadingResId)
//                .placeholder(mLoadErrorResId)
//                .bitmapTransform(new RoundedCornersTransformation(45, 0, type))
//                .diskCacheStrategy(DiskCacheStrategy.ALL);
//
//        Glide.with(context).load(url).apply(options).into(imageView);
//    }

//    /**
//     * Load blur image.
//     *
//     * @param context   the context
//     * @param url       the url
//     * @param imageView the image view
//     * @param blur      the blur
//     */
//    /*
//     *加载模糊图片（自定义透明度）
//     */
//    public static void loadBlurImage(Context context, String url, ImageView imageView, int blur) {
//        RequestOptions options = new RequestOptions()
//                .centerCrop()
//              //  .placeholder(R.color.white)
//              //  .error(R.color.white)
//                //.priority(Priority.HIGH)
//                .bitmapTransform(new BlurTransformation(blur))
//                .diskCacheStrategy(DiskCacheStrategy.ALL);
//        Glide.with(context).load(url).apply(options).into(imageView);
//    }

//    /**
//     * 加载第四秒的帧数作为封面
//     *  url就是视频的地址
//     */
//    public  void loadCover(Context context, String url,ImageView imageView) {
//
//        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        Glide.with(context)
//                .setDefaultRequestOptions(
//                        new RequestOptions()
//                                .frame(1000000)
//                                .centerCrop()
//                        //        .error(R.mipmap.ic_zhanwei_banner)//可以忽略
//                              //  .placeholder(R.mipmap.ppppp)//可以忽略
//                )
//                .load(url)
//                .into(imageView);
//    }

}
