package com.example.fragmentdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity implements BottonFragment.SendInfo {

    Button replaceButton;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;

    TopFragment topFragment;
    BottonFragment bottonFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        replaceButton = findViewById(R.id.button);

        fragmentManager = getSupportFragmentManager();

        //Begin first transaction
        fragmentTransaction = fragmentManager.beginTransaction();

        //Create bottom fragment and add to layout on bottom of XML
        bottonFragment = new BottonFragment();
        fragmentTransaction.add(R.id.id_bottom,bottonFragment);

        //commit the transaction (end)
        fragmentTransaction.commit();

        replaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragmentTransaction = fragmentManager.beginTransaction();
                topFragment = new TopFragment();
                bottonFragment = new BottonFragment();
                if(fragmentManager.findFragmentById(R.id.id_top) instanceof TopFragment){
                    fragmentTransaction.replace(R.id.id_bottom,topFragment);
                    fragmentTransaction.replace(R.id.id_top,bottonFragment);
                }else{
                    fragmentTransaction.replace(R.id.id_bottom,bottonFragment);
                    fragmentTransaction.replace(R.id.id_top,topFragment);
                }

                fragmentTransaction.commit();
            }
        });


    }

    @Override
    public void update(String str) {
        replaceButton.setText(str);
    }
}
