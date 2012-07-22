package com.wandoujia.wondernews;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.LinkedHashMap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.widget.ImageView;

public class ImageCache {
    private static ImageCache instance = null;
    private Context context;
    private LinkedHashMap<String, SoftReference<Bitmap>> map;
    private Handler handler;

    private ImageCache() {
        map = new LinkedHashMap<String, SoftReference<Bitmap>>();
    }

    public static ImageCache getInstance(Context context) {
        if (instance == null) {
            instance = new ImageCache();
        }
        instance.assignContext(context);
        return instance;
    }

    public Bitmap getImage(String path) {
        SoftReference<Bitmap> softBitmap = map.get(path);
        if (softBitmap != null) {
            Bitmap bitmap = softBitmap.get();
            if (softBitmap != null) {
                return bitmap;
            }
        }
        new LazyImageLoad().execute(path);
        return null;
    }

    private void assignContext(Context context) {
        this.context = context;
    }

    public void assignHandler(Handler handler) {
        this.handler = handler;
    }

    private class LazyImageLoad extends AsyncTask<String, Void, Void> {
        @Override
        protected Void doInBackground(String... params) {
            File file = new File(Environment.getExternalStorageDirectory(),"WonderNews/baidu_app_files/vol21d.jpg");
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath()
                    .toString());
            if (bitmap != null) {
                SoftReference<Bitmap> softBitmap = new SoftReference<Bitmap>(
                        bitmap);
                synchronized (map) {
                    map.put(params[0], softBitmap);
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            handler.sendEmptyMessage(Consts.LOAD_COMPELETE);
            super.onPostExecute(result);
        }

    }
}
