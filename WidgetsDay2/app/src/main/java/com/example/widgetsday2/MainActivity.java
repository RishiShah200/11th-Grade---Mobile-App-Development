package com.example.widgetsday2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    RadioGroup radioGroup;
    TextView textView;
    ImageView leftimage;
    ImageView darthvader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroup = findViewById(R.id.id_radiogroup);
        textView = findViewById(R.id.textView);
        leftimage = findViewById(R.id.id_leftimage);
        darthvader = findViewById(R.id.id_darthvader);

        leftimage.setImageResource(R.drawable.vader);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.A)
                    textView.setText("Selection A");
                if(checkedId == R.id.B)
                    textView.setText("Selection B");
                if(checkedId == R.id.C)
                    textView.setText("Selection C");

                Toast myToast = Toast.makeText(MainActivity.this, "C is the best",Toast.LENGTH_SHORT);
                myToast.show();
            }
        });
    }
}
