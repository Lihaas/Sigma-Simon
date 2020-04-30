package com.sahil.skywalkers.simon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.sahil.skywalkers.simon.R;

public class aboutUS extends AppCompatActivity {

    Intent intent = null, chooser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_u_s);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    public void linkedin(View view) {

        intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.linkedin.com/in/sahil-verma-b7698215a/"));
        chooser = Intent.createChooser(intent, "Open using...");
        if(intent.resolveActivity(getPackageManager()) != null ){
            startActivity(chooser);
        }

    }

    public void instagram(View view) {
        Uri uri = Uri.parse("https://www.instagram.com/sahilverma9992");
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        likeIng.setPackage("com.instagram.android");

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://www.instagram.com/sahilverma9992")));
        }
    }

    public void youtube(View view) {
        intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.youtube.com/channel/UCZjs3yZcifkpSOvnP2l-iww/"));
        chooser = Intent.createChooser(intent, "Open using...");
        if(intent.resolveActivity(getPackageManager()) != null ){
            startActivity(chooser);
        }
    }

    public void Instashivam(View view) {
        intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.instagram.com/shivam_a07/"));
        chooser = Intent.createChooser(intent, "Open using...");
        if(intent.resolveActivity(getPackageManager()) != null ){
            startActivity(chooser);
        }
    }

    public void Linkdinshivam(View view) {
        intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.linkedin.com/in/budhi-parkash-9371b7191/"));
        chooser = Intent.createChooser(intent, "Open using...");
        if(intent.resolveActivity(getPackageManager()) != null ){
            startActivity(chooser);
        }
    }
}
