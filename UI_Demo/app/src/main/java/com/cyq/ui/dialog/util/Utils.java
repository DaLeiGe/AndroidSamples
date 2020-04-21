package com.cyq.ui.dialog.util;

import android.content.Context;

/**
 * @author : ChenYangQi
 * date   : 2020/4/21 14:19
 * desc   :
 */
public class Utils {

    /**
     * dp 转 px
     *
     * @param dpValue
     * @param context
     * @return
     */
    public static int dip2px(float dpValue, Context context) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
