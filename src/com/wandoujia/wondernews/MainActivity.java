package com.wandoujia.wondernews;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import org.json.JSONArray;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {
    public static final int BUFFER_SIZE = 4096;
    private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
            case Consts.LOAD_COMPELETE:
                if (coverAdapter != null) {
                    coverAdapter.notifyDataSetChanged();
                }

                break;
            case Consts.REFRESH_COMPLETE:
                refresh();
                if (coverAdapter != null) {
                    coverAdapter.notifyDataSetChanged();
                }
                break;
            default:
                break;
            }
            super.handleMessage(msg);
        }

    };
    private CoverAdapter coverAdapter;
    private GridView gridView;
    private Manifest manifest;
    private ImageCache imageCache;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageCache = ImageCache.getInstance(this);
        gridView = (GridView) findViewById(R.id.cover_grid);
        Runnable refreshFolder = new Runnable() {

            @Override
            public void run() {
                ZipUtils zipUtils = new ZipUtils();
                File source = new File(Environment
                        .getExternalStorageDirectory().getAbsolutePath()
                        + "/WonderNews.zip");
                File target = new File(Environment
                        .getExternalStorageDirectory().getAbsolutePath()
                        + "/WonderNews");

                target.setWritable(true);
                zipUtils.unzipArchive(source, target);
                handler.sendEmptyMessage(Consts.REFRESH_COMPLETE);
            }
        };
        new Thread(refreshFolder).start();

        refresh();

        gridView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
                Logger.d("Main title onClick pos" + arg2);
                Intent listIntent = new Intent(MainActivity.this,
                        TitleListActivity.class);
                listIntent.putExtra(Consts.SOURCE, manifest.sources[arg2]);
                startActivity(listIntent);
            }
        });
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

    private void refresh() {
        File manifestFile = new File(Consts.SDCARD_PATH + "/manifest.json");
        Log.d(Consts.TAG, manifestFile.getName());
        try {
            if (manifestFile != null) {
                DataInputStream dataInput = new DataInputStream(
                        new FileInputStream(manifestFile));
                StringBuilder sb = new StringBuilder();
                String tmp = null;
                while ((tmp = dataInput.readLine()) != null) {
                    sb.append(tmp);
                }
                manifest = Manifest.parseFromJSON(sb.toString());
                if (manifest != null) {
                    gridView.setAdapter(coverAdapter);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (manifest != null) {
            coverAdapter = new CoverAdapter(manifest.sources, this);
        }

    }

}
