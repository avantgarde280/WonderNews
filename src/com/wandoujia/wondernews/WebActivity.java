package com.wandoujia.wondernews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class WebActivity extends Activity {
    private WebView webview;
    private Data data;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web);

        webview = (WebView) findViewById(R.id.webview);
        webview.getSettings().setJavaScriptEnabled(true);

        Intent intent = getIntent();
        data = (Data) intent.getSerializableExtra(Consts.DATA);
        url = intent.getStringExtra(Consts.URL);
        getActionBar().setTitle(data.title);
        webview.loadUrl(url);
    }
}
