package com.example.firebasedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button logoutButton;
    TextView userEmail;
    TextView userName;

    FirebaseUser user;

    String name;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logoutButton = findViewById(R.id.logoutButton);
        userEmail = findViewById(R.id.userEmail);
        userName = findViewById(R.id.userName);

        user = FirebaseAuth.getInstance().getCurrentUser();

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity.this, "Successfully Logged Out!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,StartActivity.class));
                finish();
            }
        });

        if(user!=null){
            name = user.getDisplayName();
            email = user.getEmail();
            userName.setText(name);
            userEmail.setText(email);
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("Name",name);
        map.put("Email",email);

        FirebaseDatabase.getInstance().getReference().child("User").updateChildren(map);

    }
}
