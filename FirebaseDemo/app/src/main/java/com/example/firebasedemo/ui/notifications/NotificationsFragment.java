package com.example.firebasedemo.ui.notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.firebasedemo.R;
import com.example.firebasedemo.ui.detection.DetectionFragment;
import com.example.firebasedemo.ui.detection.Inventory;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    ListView listView;

    List<Inventory> inventoryList;

    DatabaseReference databaseReference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);

        listView = root.findViewById(R.id.inventoryList);

        inventoryList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference("Foods");

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    inventoryList.clear();  //might be this line

                        for(DataSnapshot inventorySnapshot : dataSnapshot.getChildren()) {

                            Inventory inventory = inventorySnapshot.getValue(Inventory.class);
                            inventoryList.add(inventory);

                        }

                        try{
                            InventoryList adapter = new InventoryList(getContext(),R.layout.inventory_layout,inventoryList); //getContext() is null
                            listView.setAdapter(adapter);
                        }catch (Exception e){
                            e.printStackTrace();
                        }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

    }

//    @Override
//    public void onStart() {
//        super.onStart();
//
//        try{
//            databaseReference.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                    inventoryList.clear();
//
//                    for(DataSnapshot inventorySnapshot : dataSnapshot.getChildren()){
//                        Inventory inventory = inventorySnapshot.getValue(Inventory.class);
//
//                        inventoryList.add(inventory);
//                    }
//
//                    InventoryList adapter = new InventoryList(getContext(),R.layout.inventory_layout,inventoryList);
//                    listView.setAdapter(adapter);
//
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                }
//            });
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//
//
//    }
}
