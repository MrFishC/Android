package cn.mrfish.helper.image;

import android.content.Context;
import android.widget.ImageView;

import cn.mrfish.helper.R;

/**
 * @author Jack
 * @time 19-12-10 下午9:30
 * @describe glide管理类
 *
 */
public class GlideManager implements ILoader {

    private GlideManager() {

    }

    private GlideManager(int loadingResId,int loadErrorResId) {
        //初始化GlideTool
        GlideUtils.getInstance().initDefaultResource(loadingResId,loadErrorResId);
    }

    /**
     * 使用Builder设计模式完成GlideManager的创建
     */
    public static class Builder {

        private Context mContext;

        /**
         * 占位置图
         */
        private int mLoadingResId;

        /**
         * 图片加载错误时显示的图片
         */
        private int mLoadErrorResId;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setLoadingResId(int loadingResId) {
            mLoadingResId = loadingResId;
            return this;
        }

        public Builder setLoadErrorResId(int loadErrorResId) {
            mLoadErrorResId = loadErrorResId;
            return this;
        }

        public GlideManager create(){

            if(mLoadingResId == 0){
                mLoadingResId = R.color.colorPrimary;
            }

            if(mLoadErrorResId == 0){
                mLoadErrorResId = R.color.colorAccent;
            }

            return  new GlideManager(mLoadingResId,mLoadErrorResId);
        }

    }

    //加载图片的方法
    @Override
    public void loadImageNetDefault(Context context, String url, ImageView imageView) {
        GlideUtils.getInstance().loadImageNetDefault(context,url,imageView);
    }

    @Override
    public void loadImageNet(Context context, String url, ImageView imgeview) {
        GlideUtils.getInstance().loadImageNet(context,url,imgeview);
    }

    @Override
    public void loadICirclemageNet(Context context, String url, ImageView imgeview) {
        GlideUtils.getInstance().loadICirclemageNet(context,url,imgeview);
    }

    @Override
    public void loadImageNet(Context context, String url, ImageView imgeview, int loadingResId, int loadErrorResId) {
        GlideUtils.getInstance().loadImageNet(context,url,imgeview,loadingResId,loadErrorResId);
    }

    @Override
    public void loadICirclemageNet(Context context, String url, ImageView imgeview, int loadingResId, int loadErrorResId) {
        GlideUtils.getInstance().loadICirclemageNet(context,url,imgeview,loadingResId,loadErrorResId);
    }

//    @Override
//    public void loadImageNetById(Context context, String urlId, ImageView imgeview) {
//        GlideUtils.getInstance().loadImageNetById(context,urlId,imgeview);
//    }
//
//    @Override
//    public void loadCircleImageNetById(Context context, String urlId, ImageView imgeview) {
//        GlideUtils.getInstance().loadCircleImageNetById(context,urlId,imgeview);
//    }
//
//    @Override
//    public void loadImageNetById(Context context, String urlId, ImageView imgeview, int loadingResId, int loadErrorResId) {
//        GlideUtils.getInstance().loadImageNetById(context,urlId,imgeview,loadingResId,loadErrorResId);
//    }
//
//    @Override
//    public void loadCircleImageNetById(Context context, String urlId, ImageView imgeview, int loadingResId, int loadErrorResId) {
//        GlideUtils.getInstance().loadCircleImageNetById(context,urlId,imgeview,loadingResId,loadErrorResId);
//    }

    @Override
    public void showImageLocalByDrawableRes(Context context, int resourceId, ImageView imgeview) {
        GlideUtils.getInstance().showImageLocalByDrawableRes(context,resourceId,imgeview);
    }

    @Override
    public void showCircleImageLocalByDrawableRes(Context context, int resourceId, ImageView imgeview) {
        GlideUtils.getInstance().showCircleImageLocalByDrawableRes(context,resourceId,imgeview);
    }

    @Override
    public void showImageLocalByDrawableRes(Context context, int resourceId, ImageView imgeview, int loadingResId, int loadErrorResId) {
        GlideUtils.getInstance().showImageLocalByDrawableRes(context,resourceId,imgeview,loadingResId,loadErrorResId);
    }

    @Override
    public void showCircleImageLocalByDrawableRes(Context context, int resourceId, ImageView imgeview, int loadingResId, int loadErrorResId) {
        GlideUtils.getInstance().showCircleImageLocalByDrawableRes(context,resourceId,imgeview,loadingResId,loadErrorResId);
    }

    @Override
    public void showImageLocalByPath(Context context, String path, ImageView imgeview) {
        GlideUtils.getInstance().showImageLocalByPath(context,path,imgeview);
    }

    @Override
    public void showCircleImageLocalByPath(Context context, String path, ImageView imgeview) {
        GlideUtils.getInstance().showCircleImageLocalByPath(context,path,imgeview);
    }

    @Override
    public void showImageLocalByPath(Context context, String path, ImageView imgeview, int loadingResId, int loadErrorResId) {
        GlideUtils.getInstance().showImageLocalByPath(context,path,imgeview,loadingResId,loadErrorResId);
    }

    @Override
    public void showCircleImageLocalByPath(Context context, String path, ImageView imgeview, int loadingResId, int loadErrorResId) {
        GlideUtils.getInstance().showCircleImageLocalByPath(context,path,imgeview,loadingResId,loadErrorResId);
    }

}
