package com.wandoujia.wondernews;

import android.os.Environment;

public final class Consts {
    public static final String SDCARD_PATH = Environment
            .getExternalStorageDirectory().getAbsolutePath()
            + "/WonderNews";
    public static final String SOURCE = "source";
    public static final String DATA = "data";
    public static final String TITLE = "title";
    public static final String URL = "url";
    public static final String TAG = "wondernews";
    public static final String MANIFEST = "manifest";
    public static final String ZIP_FILE_PATH = "file:///sdcard/WonderNews.zip";
    
    public static final int LOAD_COMPELETE = 0;
    public static final int REFRESH_COMPLETE = 1;
}
