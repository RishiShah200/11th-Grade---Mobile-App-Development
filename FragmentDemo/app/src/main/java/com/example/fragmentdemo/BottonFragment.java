package com.example.fragmentdemo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class BottonFragment extends Fragment {

    Button changeButton;
    SendInfo sendInfo;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View fragmentView = inflater.inflate(R.layout.fragment_botton, container, false);
        changeButton = fragmentView.findViewById(R.id.changeButton);

        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendInfo.update("UPDATE");
            }
        });

        return fragmentView;
    }


    public interface SendInfo{
        public void update(String str);
    }

    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        sendInfo = (SendInfo)context;
    }

}
