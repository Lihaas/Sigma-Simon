package com.sahil.skywalkers.simon;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sahil.skywalkers.simon.R;
import com.bumptech.glide.Glide;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class GameOver extends DialogFragment {

    public GameOver() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_game_over, container, false);
        String  strtext  =getArguments().getString("scc");
        TextView score = v.findViewById(R.id.score2);

        Button back = v.findViewById(R.id.backs);
        Button play = v.findViewById(R.id.againplay);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
                getActivity().recreate();

            }
        });

        ImageView imageView = v.findViewById(R.id.immg);
        score.setText("Score - "+strtext);
        Glide.with(this).asGif().load(R.raw.over).into(imageView);

        return v;
    }
}
