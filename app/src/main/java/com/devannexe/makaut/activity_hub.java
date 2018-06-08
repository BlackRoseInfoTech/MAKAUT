package com.devannexe.makaut;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.appinvite.AppInviteInvitation;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crash.FirebaseCrash;

import static com.google.firebase.crash.FirebaseCrash.log;
import static com.google.firebase.crash.FirebaseCrash.report;

public class activity_hub extends AppCompatActivity {
    Button a, b, c, d, e, f, g, h, s;
    AdView adview1, adview2, adview3;
//    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);
//        report(new Exception("EPFO:Crash Log"));
//        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        //I'm also creating a log message, which we'll look at in more detail later//
//        log("Portal_Activity started");
        MobileAds.initialize(this,
                getString(R.string.admob_app_id));
        adview1 = findViewById(R.id.adView1);
        adview2 = findViewById(R.id.adView2);
        adview3 = findViewById(R.id.adView3);
        AdRequest adreq1 = new AdRequest.Builder().build();
        AdRequest adreq2 = new AdRequest.Builder().build();
        AdRequest adreq3 = new AdRequest.Builder().build();
        adview1.loadAd(adreq1);
        adview2.loadAd(adreq2);
        adview3.loadAd(adreq3);
//        Bundle bundle = new Bundle();
        //bundle.putString(FirebaseAnalytics.Param.ITEM_ID, id);
        //bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, name);
//        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "image");
//        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
        a = findViewById(R.id.item1);
        b = findViewById(R.id.item2);
        c = findViewById(R.id.item3);
        d = findViewById(R.id.item4);
        e = findViewById(R.id.item5);
        f = findViewById(R.id.item6);
        g = findViewById(R.id.item7);
        h = findViewById(R.id.item8);
    }
//
    public void onClick(View v) {
        Intent browserIntent = new Intent(getApplicationContext(), wv_activity.class);
        switch (v.getId()) {
            case R.id.item1:
                browserIntent.putExtra("url", getString(R.string.a_ADMIT_CARD));
                startActivity(browserIntent);
                break;
//
            case R.id.item2:
                browserIntent.putExtra("url", getString(R.string.b_EXAM_ROUTINE));
                startActivity(browserIntent);
                break;
//
            case R.id.item3:
                browserIntent.putExtra("url", getString(R.string.c_EXAM_FORM));
                startActivity(browserIntent);
                break;
//
            case R.id.item4:
                browserIntent.putExtra("url", getString(R.string.d_RESULTS));
                startActivity(browserIntent);
                break;
//
            case R.id.item5:
                browserIntent.putExtra("url", getString(R.string.e_PPR_PPS_FORM));
                startActivity(browserIntent);
                break;
//
            case R.id.item6:
                browserIntent.putExtra("url", getString(R.string.f_MEMBERS_AREA));
                startActivity(browserIntent);
                break;
//
            case R.id.item7:
                browserIntent.putExtra("url", getString(R.string.g_LETTERS_AND_NOTICES));
                startActivity(browserIntent);
                break;
//
            case R.id.item8:
                browserIntent.putExtra("url", getString(R.string.h_APPLICATION_AND_NOTICE));
                startActivity(browserIntent);
                break;
//
        }
    }
}