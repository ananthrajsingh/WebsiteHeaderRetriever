package com.example.ananthrajsingh.websiteheaderretriever;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import org.w3c.dom.Document;

import static com.example.ananthrajsingh.websiteheaderretriever.MainActivity.WEBSITE_ADDRESS_EXTRA;

public class HeaderActivity extends AppCompatActivity {

    private String mAddress;
    private ProgressBar mProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header);

        Intent intent = getIntent();
        mAddress = intent.getStringExtra(WEBSITE_ADDRESS_EXTRA);
        final WebView webView = findViewById(R.id.webview);
        mProgressBar = findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.VISIBLE);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        /* If user typed for eg. "fb.com" substring will throw error if this condition isn't checked*/
        if (mAddress.length() > 7) {
            String protocol = mAddress.substring(0, 7);
            if (protocol.equals("https:/") || protocol.equals("http://")) {
                webView.loadUrl(mAddress);
            } else {
                webView.loadUrl("https://" + mAddress);
            }
        }
        else {
            webView.loadUrl("https://" + mAddress);
        }
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(request.getUrl().toString());
                return true;
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                try {
                    webView.loadUrl("javascript:(window.onload = function() { " +
                            "(elem1 = document.getElementsByTagName('body')[0]); elem1.parentNode.removeChild(elem1); " +
//                            "(elem2 = document.getElementById('id2')); elem2.parentNode.removeChild(elem2); " +
                            "})()");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                mProgressBar.setVisibility(View.GONE);

            }
        });

    }
}
