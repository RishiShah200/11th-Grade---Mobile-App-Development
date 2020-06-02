package com.example.firebasedemo.ui.notifications;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.firebasedemo.R;
import com.example.firebasedemo.ui.detection.Inventory;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InventoryList extends ArrayAdapter<Inventory> {

    List<Inventory> list;
    Context parentContext;
    int xmlResource;

    DatabaseReference databaseReference;

    public InventoryList(@NonNull Context context, int resource, @NonNull List<Inventory> objects) {
        super(context, resource, objects);
        try{
            list = objects;
            parentContext = context;
            xmlResource = resource;
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater)parentContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);  //sets layout on the screeen
        View view = layoutInflater.inflate(R.layout.inventory_layout,null,true);

        TextView nameOfProduct = view.findViewById(R.id.nameOfProduct);
        TextView expirationDate = view.findViewById(R.id.expirationDate);
        ImageView productImage = view.findViewById(R.id.productImage);
        TextView totalQuantity = view.findViewById(R.id.totalQuantity);
        ImageView removeFromList = view.findViewById(R.id.removeFromList);

        ImageView buttonAdd = view.findViewById(R.id.buttonAdd);
        ImageView buttonSubtract = view.findViewById(R.id.buttonSubtract);


        final Inventory inventory = list.get(position);

        nameOfProduct.setText(inventory.getName());
        expirationDate.setText(inventory.getDateOfExpiration());
        totalQuantity.setText("Quantity: " + inventory.getTotalQuantity());

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inventory.setQuantity(inventory.getTotalQuantity() + 1);
                databaseReference.child(list.get(position).getName()).setValue(inventory);
            }
        });

        buttonSubtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inventory.setQuantity(inventory.getTotalQuantity() - 1);
                databaseReference.child(list.get(position).getName()).setValue(inventory);
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("Foods");



        removeFromList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference.orderByChild("name").equalTo(list.get(position).getName()).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot child: dataSnapshot.getChildren()){
                            child.getRef().setValue(null);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                list.remove(position);
                notifyDataSetChanged();

            }
        });


        return view;
    }
}
