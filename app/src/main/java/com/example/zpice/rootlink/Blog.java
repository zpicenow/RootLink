package com.example.zpice.rootlink;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Blog extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog);

        webView = findViewById(R.id.id_wv_blog);
        webView.loadUrl("http://www.rootlink.cn/blog");
    }
}
