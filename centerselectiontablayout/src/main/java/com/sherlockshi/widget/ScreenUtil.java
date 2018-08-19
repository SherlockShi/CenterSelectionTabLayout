package com.sherlockshi.widget;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.WindowManager;

/**
 * Author:      SherlockShi
 * Email:       sherlock_shi@163.com
 * Date:        2018-08-19 16:39
 * Description:
 */
public class ScreenUtil {

    /**
     * Return the width of screen, in pixel.
     *
     * @return the width of screen, in pixel
     * @param context
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        if (wm == null) {
            return context.getResources().getDisplayMetrics().widthPixels;
        }
        Point point = new Point();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            wm.getDefaultDisplay().getRealSize(point);
        } else {
            wm.getDefaultDisplay().getSize(point);
        }
        return point.x;
    }

    /**
     * Value of dp to value of px.
     *
     * @param dpValue The value of dp.
     * @return value of px
     */
    public static int dp2px(Context context, final float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
