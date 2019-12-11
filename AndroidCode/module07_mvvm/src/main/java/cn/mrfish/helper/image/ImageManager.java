package cn.mrfish.helper.image;

/**
 * @author Jack
 * @time 19-12-10 下午8:34
 * @describe 图片加载器管理类
 */
public class ImageManager {

    private ILoader mImageLoader;

    /**
     * 使用静态内部类的方式实现单例
     */
    public static ImageManager getInstance() {
        return ImageManager.Holder.INSTANCE;
    }

    private static class Holder {
        private static final ImageManager INSTANCE = new ImageManager();
    }

    private ImageManager() {

    }

    /**
     * 初始化图片加载框架
     * @param imageLoader 指定的图片加载框架
     */
    public void init( ILoader imageLoader){
        this.mImageLoader = imageLoader;
    }

    /**
     * 供给外部调用
     * @return mImageLoader
     */
    public ILoader getImageLoader() {
        if(mImageLoader == null){
            throw new RuntimeException("ImageManager's init function must be call frist!");
        }
        return mImageLoader;
    }

}
