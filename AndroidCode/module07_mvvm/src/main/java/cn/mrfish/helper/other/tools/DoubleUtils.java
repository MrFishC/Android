package cn.mrfish.helper.other.tools;

/**
 * created by Jack
 * email:yucrun@163.com
 * date:19-6-6
 * describe:
 */
public class DoubleUtils {
    /**
     * Prevent continuous click, jump two pages                             非最佳方案
     */
    private static long lastClickTime;
    private final static long TIME = 800;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < TIME) {
            return true;
        }
        lastClickTime = time;
        return false;
    }


}
