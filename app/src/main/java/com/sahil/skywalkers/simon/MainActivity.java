package com.sahil.skywalkers.simon;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sahil.skywalkers.simon.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtBack,level;

    Button redBlock,blueBlock,greenBlock,yellowBlock;
    List<Integer> game = new ArrayList<Integer>();
    final Handler handler = new Handler();
    int hardness = 3;
    int levels = 1;
    int userTouchValue;
    int si;
    public SoundPool sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
    boolean touchON = false;
    int eachStepValue = 0;
    int score = 0 ;
    TextView txtScore,howtoplay;
    int stepToTaken = 0 ;
    Intent intent = null, chooser = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FullScreencall();
        txtBack = findViewById(R.id.bBack);
        level = findViewById(R.id.level);
        howtoplay = findViewById(R.id.textView67);

        howtoplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.youtube.com/watch?v=LhbHlAOhVcM"));
                chooser = Intent.createChooser(intent, "Open using...");
                if(intent.resolveActivity(getPackageManager()) != null ){
                    startActivity(chooser);
                }
            }
        });

        hardness = getIntent().getIntExtra("level",3);

        redBlock = findViewById(R.id.red);
        blueBlock = findViewById(R.id.blue);
        greenBlock = findViewById(R.id.green);
        yellowBlock = findViewById(R.id.yellow);
        txtScore = findViewById(R.id.score);

        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                playGame();
            }
        };
        handler.postDelayed(runnable,1500);






    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
    }

    private void setRandom(){

        level.setText("Level - "+ levels);
        levels++;
        Random ran = new Random();
        int newNumber = ran.nextInt(4) +1;
         si =  game.size() ;

        if(si == 0){
            game.add(0,newNumber);
        }
        else if(si == 1){
            game.add(1,newNumber);
        }
        else {
            game.add(si,newNumber);
        }


    }

    private void playGame(){
        setRandom();
        touchON = false;
        for(int i = 0 ; i <= (game.size() - 1) ;i++){
            click(i);
        }

    }

    public void click(final int click_index) {
        //Function that clicks one place randomally on the view



        final Runnable r = new Runnable() {
            public void run() {
                if (game.get(click_index) == 1) {
                    playSound(R.id.red);
                    xorMyColor(redBlock,1);
                    stepToTaken+=1;
                } else if (game.get(click_index) == 2) {
                    playSound(R.id.blue);
                    xorMyColor(blueBlock,2);
                    stepToTaken+=1;
                } else if (game.get(click_index) == 3) {
                    playSound(R.id.green);
                    xorMyColor(greenBlock,3);
                    stepToTaken+=1;
                } else {
                    playSound(R.id.yellow);
                    xorMyColor(yellowBlock,4);
                    stepToTaken+=1;
                }

                if(game.size() == stepToTaken){
                    touchON = true;
                    stepToTaken = 0;
                }
            }
        };




        handler.postDelayed(r, (2000 - 500 * hardness) * click_index);

    }
    private void xorMyColor(final Button b,int i) {
        //function that changes the background color and get it back after 500 milliseconds

           final int value = i;

        if(value == 1){
            b.setBackgroundColor(getResources().getColor(R.color.redOn));
        }
        else if(value == 2){
            b.setBackgroundColor(getResources().getColor(R.color.blueOn));
        }
        else if(value == 3){
            b.setBackgroundColor(getResources().getColor(R.color.greenOn));
        }
        else{
            b.setBackgroundColor(getResources().getColor(R.color.yellowOn));
        }

        final Runnable r = new Runnable() {
            public void run() {
                if(value == 1){
                    b.setBackgroundColor(getResources().getColor(R.color.redDim));
                }
                else if(value == 2){
                    b.setBackgroundColor(getResources().getColor(R.color.blueDim));
                }
                else if(value == 3){
                    b.setBackgroundColor(getResources().getColor(R.color.greenDim));
                }
                else{
                    b.setBackgroundColor(getResources().getColor(R.color.yellowDim));
                }



            }
        };
        handler.postDelayed(r, 300);
    }
    private void playSound(int id) {
        //function that play sound according to sound ID
        int audioRes = 0;
        switch (id) {
            case R.id.red:
                audioRes = R.raw.doo;
                break;
            case R.id.blue:
                audioRes = R.raw.re;
                break;
            case R.id.yellow:
                audioRes = R.raw.mi;
                break;
            case R.id.green:
                audioRes = R.raw.fa;
                break;
        }
        MediaPlayer p = MediaPlayer.create(this, audioRes);
        p.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
        p.start();
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

    public void clear(){
        levels = 1;
        si = 0;
        game.clear();
        eachStepValue = 0 ;
        userTouchValue = 0 ;
        score = 0;
        txtScore.setText("0");

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                playGame();
            }
        };
        handler.postDelayed(runnable,2000);

    }






    public void TouchOn(View view) {
        try {
            if(touchON){
                touchON = false;
                switch (view.getId()) {
                    case R.id.red:
                        playSound(R.id.red);
                        xorMyColor(redBlock,1);
                        userTouchValue = 1;
                        break;

                    case R.id.blue:
                        playSound(R.id.blue);
                        xorMyColor(blueBlock,2);
                        userTouchValue = 2;
                        break;


                    case R.id.green:
                        playSound(R.id.green);
                        xorMyColor(greenBlock,3);
                        userTouchValue = 3;
                        break;

                    case R.id.yellow:
                        playSound(R.id.yellow);
                        xorMyColor(yellowBlock,4);
                        userTouchValue = 4 ;
                        break; }

                if(game.get(eachStepValue) != userTouchValue){
                    level.setText("Game is Over");
                    gameOver();

                    return;
                }

                eachStepValue++;
                updateScore();
                if((si +1) == eachStepValue){
                    eachStepValue = 0 ;

                    final Runnable r = new Runnable() {
                        public void run() {
                            playGame();
                        }
                    };
                    handler.postDelayed(r, 2000 - 500 * hardness);

                }
                touchON = true;
            }
        }catch (Exception e){
            Toast.makeText(this, "Please touch slowly!", Toast.LENGTH_SHORT).show();
        }

    }

    private void updateScore(){
        score = score+10;
        txtScore.setText(score+"");
    }

    private void gameOver(){
        Bundle bundle=new Bundle();
        bundle.putString("scc", score+"");
        FragmentManager fragmentManager = (this).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment prev = fragmentManager.findFragmentByTag("dialog");
        if (prev != null) {
            fragmentTransaction.remove(prev);
        }
        fragmentTransaction.addToBackStack(null);
        DialogFragment dialogFragment = new GameOver();
        dialogFragment.setArguments(bundle);
        dialogFragment.setCancelable(false);
        dialogFragment.show(fragmentTransaction, "dialog");








    }



}
