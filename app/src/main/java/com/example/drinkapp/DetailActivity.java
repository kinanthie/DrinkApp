package com.example.drinkapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.drinkapp.Model.DataModel;


public class DetailActivity extends AppCompatActivity {

    WebView webView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_drink);



        mProgressBar = findViewById(R.id.progress_bar);
        mProgressBar.setMax(100);
        mProgressBar.setProgress(0);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        DataModel dataModel = (DataModel) bundle.getSerializable("dataModel");

        // setting judul bar
        setTitle(dataModel.getJudul());

        //tampil data konten
        webView = findViewById(R.id.webView);
        webView.loadUrl("file:///android_assets/" + dataModel.getKonten());
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String newUrl) {

                return true;
            }

            @Override
            public void onPageStarted(WebView view, String urlStart, Bitmap favicon) {
                mProgressBar.setVisibility(View.VISIBLE);

            }

            @Override
            public void onPageFinished(WebView view, String urlPage) {
                mProgressBar.setVisibility(View.GONE);

            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
