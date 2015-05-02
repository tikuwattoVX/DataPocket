package com.example.datapocket.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by masakisakamoto on 2015/04/29.
 */
public class SplashActivity extends Activity{

    private WebView webView;
    private String script;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        webView = new WebView(this);

        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url)
            {
//                webView.loadUrl(script);
            }

        });

        webView.loadUrl("file:///android_asset/opning.html");

        setContentView(webView);

        Handler handler = new Handler();
        handler.postDelayed(new splashHandler(), 1500);
    }

    class splashHandler implements Runnable {
        public void run() {
            Intent intent = new Intent(getApplication(), GenreTopActivity.class);
            startActivity(intent);
            SplashActivity.this.finish();
        }
    }
}
