package com.example.warkoprakyat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    WebView webView;
    private String webUrl = "http://192.168.43.32/CoffeCup" +
            "";
    ProgressBar progressBarWeb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.myWebView);
        webView.getSettings().setJavaScriptEnabled(true);
        progressBarWeb = (ProgressBar) findViewById(R.id.progressBar);

        webView.loadUrl(webUrl);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progressBarWeb.setVisibility(View.VISIBLE);
                progressBarWeb.setProgress(newProgress);
                if (newProgress ==100){
                    progressBarWeb.setVisibility(View.GONE);
                }

                super.onProgressChanged(view, newProgress);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()){
            webView.goBack();
        }
        super.onBackPressed();
    }
}