package com.fanyafeng.testijkplayer.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.content.ContextCompat;

/**
 * Created by fanyafeng on 2015/12/25,0025.
 */
public class PermissionControl {
    public static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE=0;
    /**
     * 以下的util在activity中调用
     */
    public static boolean isGetPermissionFor(Context context, String permission) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }

    /**
     * 以下的util在fragment中调用
     * 但是如果是fragment嵌套fragment需要注意
     */


}
