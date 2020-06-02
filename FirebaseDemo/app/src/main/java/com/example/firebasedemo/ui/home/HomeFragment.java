package com.example.firebasedemo.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.firebasedemo.LoginActivity;
import com.example.firebasedemo.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class HomeFragment extends Fragment {

    Button logoutButton;
    TextView userEmail;
    TextView userName;

    FirebaseUser user;

    String name = "";
    String email = "";

    GoogleApiClient mGoogleApiClient;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_home, container, false);


        logoutButton = root.findViewById(R.id.logoutButton);
        userEmail = root.findViewById(R.id.userEmail);
        userName = root.findViewById(R.id.userName);

        user = FirebaseAuth.getInstance().getCurrentUser();

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                        new ResultCallback<Status>() {
                            @Override
                            public void onResult(Status status) {
                                Toast.makeText(getActivity().getApplicationContext(),"Logged Out",Toast.LENGTH_SHORT).show();
                                Intent i=new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                                startActivity(i);
                            }
                        });


                FirebaseAuth.getInstance().signOut();
                Toast.makeText(getContext(), "Successfully Logged Out!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(),LoginActivity.class));
                getActivity().finish();
            }
        });

        name = getActivity().getIntent().getStringExtra("name");
        email = getActivity().getIntent().getStringExtra("email");
        userName.setText(name);
        userEmail.setText(email);



        HashMap<String, Object> map = new HashMap<>();
        map.put("Name",name);
        map.put("Email",email);

        FirebaseDatabase.getInstance().getReference().child("User").updateChildren(map);

        return root;
    }

    @Override
    public void onStart() {

        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();
        super.onStart();
    }



}
