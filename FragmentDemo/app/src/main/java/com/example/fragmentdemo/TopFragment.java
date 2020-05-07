package com.example.fragmentdemo;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class TopFragment extends Fragment {

    Button clicked;
    TextView textView;

    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

       view = inflater.inflate(R.layout.fragment_top, container, false);

       clicked = view.findViewById(R.id.clicked);
       textView = view.findViewById(R.id.top_text);


       clicked.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               textView.setText("Clicked");
           }
       });

       return view;
    }
}
