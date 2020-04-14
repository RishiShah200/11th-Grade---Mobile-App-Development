package com.example.writingdata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    String dataToWrite;
    String file;

    String writtenData;

    TextView writtenText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataToWrite = "Hello! This is a test!!";
        file = "C:\\Users\\rishi\\Documents\\SBHS\\11th\\Mobile App Development\\11th-Grade---Mobile-App-Development\\WritingData\\data.txt";

        writtenText = findViewById(R.id.writtenText);


        try{
            OutputStreamWriter writer = new OutputStreamWriter(openFileOutput(file, Context.MODE_PRIVATE));
            writer.write(dataToWrite);
            writer.close();
        }catch(Exception e){

        }

        try{
            BufferedReader reader = new BufferedReader (new InputStreamReader(openFileInput(file)));
            String text = reader.readLine();
            while(text!=null){
                writtenData += text;
                text = reader.readLine();
            }
        }catch(Exception e){

        }

        writtenText.setText(writtenData);


    }
}
