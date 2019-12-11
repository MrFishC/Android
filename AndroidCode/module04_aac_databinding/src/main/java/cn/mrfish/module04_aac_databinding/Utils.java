package cn.mrfish.module04_aac_databinding;

import androidx.databinding.BindingConversion;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Jack
 * @time 19-9-27 上午10:33
 * @describe
 */
public class Utils {

    @BindingConversion
    public static String convertDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

}
