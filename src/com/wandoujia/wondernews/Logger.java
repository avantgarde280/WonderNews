package com.wandoujia.wondernews;

import android.util.Log;

public class Logger {
    private static final boolean DEBUG = true;
    public static void d(String msg) {
        if(DEBUG) {
            Log.d(Consts.TAG, msg);
        }
    }
}
