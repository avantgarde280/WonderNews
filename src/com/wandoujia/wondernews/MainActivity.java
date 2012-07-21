package com.wandoujia.wondernews;

import java.io.File;
import java.util.ArrayList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {
    private Handler handler;
    private CoverAdapter coverAdapter;
    private GridView gridView;
    private ArrayList<String> titles;
    private ArrayList<String> covers;
    private ImageCache imageCache;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageCache = ImageCache.getInstance(this);
        gridView = (GridView) findViewById(R.id.cover_grid);

        covers = new ArrayList<String>();
        for (int i = 0; i < 7; i++) {
            covers.add("file:///sdcard/WonderNews/baidu_app_files/vol21d.jpg");
        }

        File file = new File("file:///sdcard/WonderNews/baidu_app_files/vol21d.jpg");
        
        titles = new ArrayList<String>();
        for (int i = 0; i < 7; i++) {
            titles.add("Title " + i);
        }
        coverAdapter = new CoverAdapter(titles, covers, this);
        gridView.setAdapter(coverAdapter);

        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                case ImageCache.LOAD_COMPELETE:
                    coverAdapter.notifyDataSetChanged();
                    break;

                default:
                    break;
                }
                super.handleMessage(msg);
            }

        };
    }

    @Override
    protected void onResume() {
        imageCache.assignHandler(handler);
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

}
