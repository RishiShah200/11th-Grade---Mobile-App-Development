package com.example.jsondemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.id_text);

        JSONObject schoolInfo = new JSONObject();
        try{
            schoolInfo.put("name","Rishi Shah");
            schoolInfo.put("grade",12);
            schoolInfo.put("id",12345);
        }catch(JSONException e){
            textView.setText("ERROR");
        }

        Log.d("TAG",schoolInfo.toString());

        JSONObject compsciClass = new JSONObject();
        try{
            compsciClass.put("grade","A");
            compsciClass.put("raw score",94);
            compsciClass.put("course name","Computer Science");
            schoolInfo.put("Computer Science",compsciClass);
        }catch (JSONException e){

        }

        JSONObject bioClass = new JSONObject();
        try{
            bioClass.put("grade","B-");
            bioClass.put("raw score",96);
            bioClass.put("course name","Biology Class");
            schoolInfo.put("Biology Class",bioClass);
        }catch (JSONException e){

        }

        try{

            JSONObject findCourse;
            findCourse = schoolInfo.getJSONObject("Computer Science");
            textView.setText(findCourse.getString("grade"));

        }catch (JSONException e){
            
        }

        Log.d("TAG",schoolInfo.toString());
    }
}
