package com.wandoujia.wondernews;

import java.util.ArrayList;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CoverAdapter extends BaseAdapter {
    private ArrayList<String> titles;
    private ArrayList<String> covers;
    private Context context;
    private LayoutInflater inflater;

    public CoverAdapter(ArrayList<String> titles, ArrayList<String> covers,
            Context context) {
        this.titles = titles;
        this.covers = covers;
        this.context = context;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Override
    public Object getItem(int position) {
        return titles.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position / 2;
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

        titleView.setText(titles.get(position));
        Bitmap bitmap = ImageCache.getInstance(context).getImage(
                covers.get(position));
        if(bitmap != null) {
            coverView.setImageBitmap(bitmap);
        }
    }
}
