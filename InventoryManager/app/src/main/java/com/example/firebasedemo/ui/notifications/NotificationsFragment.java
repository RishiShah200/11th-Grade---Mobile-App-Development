package com.example.firebasedemo.ui.notifications;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.firebasedemo.AlertReceiver;
import com.example.firebasedemo.LoginActivity;
import com.example.firebasedemo.R;
import com.example.firebasedemo.ui.detection.Inventory;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class NotificationsFragment extends Fragment{

    ListView listView;

    List<Inventory> inventoryList;

    DatabaseReference databaseReference;

    FloatingActionButton shareButton;

    String shareMessage = "";

    Button logoutButton;
    TextView userEmail;
    TextView userName;

    FirebaseUser user;

    String name = "";
    String email = "";

    GoogleApiClient mGoogleApiClient;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        logoutButton = root.findViewById(R.id.logoutButton);
        userEmail = root.findViewById(R.id.userEmail);
        userName = root.findViewById(R.id.userName);

        user = FirebaseAuth.getInstance().getCurrentUser();

        listView = root.findViewById(R.id.inventoryList);

        databaseReference = FirebaseDatabase.getInstance().getReference("Foods");

        inventoryList = new ArrayList<>();

        shareButton = root.findViewById(R.id.shareButton);

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

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Share via...");
                startActivity(Intent.createChooser(intent, "Share"));
            }
        });



        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        mGoogleApiClient.connect();
        super.onStart();
    }

    private void startAlarm(Calendar c){
        try{
            AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(getContext(), AlertReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(),1,intent,0);

            if (c.before(Calendar.getInstance())) {
                c.add(Calendar.DATE, 1);
            }

            alarmManager.setExact(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pendingIntent);
            Log.d("REACHED",c.getTimeInMillis()+"");
        }catch (Exception e){
            //e.printStackTrace();
        }
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);



            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    inventoryList.clear();  //might be this line
                    shareMessage = "";

                        for(DataSnapshot inventorySnapshot : dataSnapshot.getChildren()) {

                            Inventory inventory = inventorySnapshot.getValue(Inventory.class);
                            Calendar calendar = Calendar.getInstance();
                            calendar.set(Calendar.YEAR,inventory.getYear());
                            calendar.set(Calendar.MONTH,inventory.getMonth());
                            calendar.set(Calendar.DAY_OF_MONTH,inventory.getDayOfMonth());
                            calendar.set(Calendar.HOUR_OF_DAY,20);
                            calendar.set(Calendar.MINUTE,19);
                            Log.d("TESTING",calendar.get(Calendar.MINUTE)+"");
                            //long timeUntil = calendar.getTimeInMillis() - System.currentTimeMillis();
                            //Log.d("TAG",calendar.getTimeInMillis()+"");
                            try{
                                startAlarm(calendar);
                            }catch(Exception e){
                                //e.printStackTrace();
                            }

                            shareMessage += inventory.toString() + "\n";
                            inventoryList.add(inventory);

                        }

                        try{
                            InventoryList adapter = new InventoryList(getContext(),R.layout.inventory_layout,inventoryList); //getContext() is null
                            listView.setAdapter(adapter);
                        }catch (Exception e){
                            //e.printStackTrace();
                        }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

    }


}
