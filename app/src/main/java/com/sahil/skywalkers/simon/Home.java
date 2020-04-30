package com.sahil.skywalkers.simon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sahil.skywalkers.simon.R;

public class Home extends AppCompatActivity {

    Boolean volme = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Button start = findViewById(R.id.button);
        Button hiscore = findViewById(R.id.button2);
        Button developer = findViewById(R.id.button3);
        Button aboutus = findViewById(R.id.button4);
        final TextView volume = findViewById(R.id.textView2);
        FullScreencall();
        startService(new Intent(Home.this, SoundService.class));

        hiscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Home.this, "Coming Soon...", Toast.LENGTH_SHORT).show();
            }
        });

        volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(volme){
                    volume.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.volumeoff,0,0);
                    volme = false;
                    stopService(new Intent(Home.this, SoundService.class));
                }
                else {

                    startService(new Intent(Home.this, SoundService.class));
                    volume.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.volume,0,0);
                    volme = true;
                }
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                volume.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.volumeoff,0,0);
                stopService(new Intent(Home.this, SoundService.class));
                volme =false;
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Fragment prev = fragmentManager.findFragmentByTag("dialog");
                if (prev != null) {
                    fragmentTransaction.remove(prev);
                }
                fragmentTransaction.addToBackStack(null);
                DialogFragment dialogFragment = new GameLevel();
                dialogFragment.show(fragmentTransaction, "dialog");

            }
        });


        developer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDev();

            }
        });
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDev();

            }
        });

    }


    private void openDev(){
        startActivity(new Intent(Home.this,aboutUS.class));
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
    }

    public void FullScreencall() {
        if(Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if(Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        FullScreencall();
    }
}
