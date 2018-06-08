package com.devannexe.makaut;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.URLUtil;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.google.firebase.crash.FirebaseCrash.log;
import static com.google.firebase.crash.FirebaseCrash.report;

public class wv_activity extends AppCompatActivity {
    //Declaring Variables
    ProgressBar progress;
    AdView adView4;
    private WebView brow;


    //Button go;
    //SearchView urledit;
    //ListView listView;

    @SuppressLint("SetJavaScriptEnabled")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wv_activity);
//        report(new Exception("EPFO:Crash Log"));
        //I'm also creating a log message, which we'll look at in more detail later//
//        log("wv_activity started");
        progress = findViewById(R.id.progressBar);
        brow = findViewById(R.id.wv_brow);
        adView4 = findViewById(R.id.adView4);
        MobileAds.initialize(this,
                getString(R.string.admob_app_id));
        //mAdView = findViewById(R.id.adView);
        AdRequest adreq = new AdRequest.Builder().build();
        adView4.loadAd(adreq);

        brow.setWebViewClient(new ourViewClient());
        brow.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                progress.setProgressBackgroundTintMode(PorterDuff.Mode.DARKEN);
                progress.setProgress(newProgress);
                if (newProgress == 100) {
                    progress.setVisibility(View.GONE);
                } else if (newProgress < 1) {
                    progress.setVisibility(View.GONE);
                } else {
                    progress.setVisibility(View.VISIBLE);
                }
            }
        });
        WebSettings webSettings = brow.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.getCacheMode();
        webSettings.getCursiveFontFamily();
        webSettings.getDatabaseEnabled();
        webSettings.getJavaScriptCanOpenWindowsAutomatically();
        webSettings.getMediaPlaybackRequiresUserGesture();
        webSettings.setGeolocationEnabled(true);
        webSettings.supportMultipleWindows();
        webSettings.getAllowContentAccess();
        webSettings.getAllowFileAccessFromFileURLs();
        webSettings.getDisplayZoomControls();
        webSettings.getDomStorageEnabled();
        webSettings.getFantasyFontFamily();
        webSettings.getLoadsImagesAutomatically();
        webSettings.setDisplayZoomControls(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.getAllowFileAccessFromFileURLs();
        webSettings.getAllowContentAccess();
        webSettings.getAllowUniversalAccessFromFileURLs();
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        webSettings.setAllowFileAccess(true);
        webSettings.getJavaScriptCanOpenWindowsAutomatically();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.getDatabaseEnabled();
        webSettings.getCacheMode();

        String url = getIntent().getStringExtra("url");
        brow.clearCache(true);
        brow.clearFormData();
        brow.loadUrl(url);

        brow.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimeType,long contentLength) {
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
                request.setMimeType(mimeType);
                String cookies = CookieManager.getInstance().getCookie(url);
                request.addRequestHeader("cookie", cookies);
                request.addRequestHeader("User-Agent", userAgent);
                request.setDescription("Downloading file...");
                request.setTitle(URLUtil.guessFileName(url, contentDisposition, mimeType));
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalFilesDir(wv_activity.this, Environment.DIRECTORY_DOWNLOADS, ".pdf");
                DownloadManager dm = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                assert dm != null;
                dm.enqueue(request);
                Toast.makeText(getApplicationContext(), "Downloading File", Toast.LENGTH_LONG).show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(wv_activity.this, activity_hub.class);
        startActivity(intent);
        finish();
    }
}
