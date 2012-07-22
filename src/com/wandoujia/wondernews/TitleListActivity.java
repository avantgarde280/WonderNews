package com.wandoujia.wondernews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class TitleListActivity extends Activity {
    private ListView titlelist;
    private ArrayAdapter<String> adapter;
    private Source source;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_list);

        titlelist = (ListView) findViewById(R.id.list);
        Intent fromIntent = getIntent();
        source = (Source) fromIntent.getSerializableExtra(Consts.SOURCE);

        String[] titles = source.getTitles();
        adapter = new ArrayAdapter<String>(this, R.layout.list_item, titles);
        titlelist.setAdapter(adapter);
        titlelist.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                    long arg3) {
                Intent webIntent = new Intent(TitleListActivity.this,
                        WebActivity.class);

                webIntent.putExtra(Consts.DATA, source.data[arg2]);
                webIntent.putExtra(Consts.URL, "file:///sdcard/WonderNews/"
                        + source + "/" + source.data[arg2].url);
                startActivity(webIntent);
            }

        });

        getActionBar().setTitle(source.source);
    }
}
