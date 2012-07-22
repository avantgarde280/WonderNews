package com.wandoujia.wondernews;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CoverAdapter extends BaseAdapter {
    private Source[] sources;
    private Context context;
    private LayoutInflater inflater;

    public CoverAdapter(Source[] sources, Context context) {
        this.sources = sources;
        this.context = context;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return sources.length;
    }

    @Override
    public Object getItem(int position) {
        return sources.length;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            view = inflater.inflate(R.layout.grid_item, parent, false);
        } else {
            view = convertView;
        }
        bindView(position, view);
        return view;
    }

    private void bindView(int position, View view) {
        TextView titleView = (TextView) view.findViewById(R.id.title);
        ImageView coverView = (ImageView) view.findViewById(R.id.cover);

        titleView.setText(sources[position].source);
        Bitmap bitmap = ImageCache.getInstance(context).getImage(
                "file:///sdcard/WonderNews/baidu/baidu_app_files/vol17d.jpg");
        if (bitmap != null) {
            coverView.setImageBitmap(bitmap);
        }
    }
}
