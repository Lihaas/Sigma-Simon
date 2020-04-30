package com.sahil.skywalkers.simon;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.sahil.skywalkers.simon.R;
import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameLevel extends DialogFragment {

    public GameLevel() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_game_level, container, false);

        Button esy = v.findViewById(R.id.Easy);
        Button mid = v.findViewById(R.id.mid);
        Button master = v.findViewById(R.id.master);
        ImageView imageView = v.findViewById(R.id.immgs);


        Glide.with(this).asGif().load(R.raw.over).into(imageView);
        esy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = 1;
                openGame(value);
            }
        });
        mid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = 2;
                openGame(value);
            }
        });
        master.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int value = 3;
                openGame(value);
            }
        });




        return v;
    }

    private void openGame(int v){
        MediaPlayer p = MediaPlayer.create(getActivity(), R.raw.buttion);
        p.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
        p.start();

        Intent i = new Intent(getActivity(),MainActivity.class);
        i.putExtra("level",v);
        startActivity(i);
       getActivity().overridePendingTransition(R.anim.slide_out_bottom, R.anim.slide_in_bottom);
    }
}
