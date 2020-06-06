package com.example.firebasedemo.ui.notifications;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.firebasedemo.R;
import com.example.firebasedemo.ui.detection.Inventory;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryList extends ArrayAdapter<Inventory> {

    List<Inventory> list;
    Context parentContext;
    int xmlResource;

    URL url;
    URLConnection connection;
    InputStream stream;
    BufferedReader reader;
    InputStreamReader streamReader;
    String text;        //used for the 5 day forecast
    String info = "";

    JSONObject imageData;
    String actualURL;

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

    //@SuppressLint("StaticFieldLeak")
    @SuppressLint("StaticFieldLeak")
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater)parentContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);  //sets layout on the screeen
        View view = layoutInflater.inflate(R.layout.inventory_layout,null,true);

        TextView nameOfProduct = view.findViewById(R.id.nameOfProduct);
        TextView expirationDate = view.findViewById(R.id.expirationDate);
        final ImageView productImage = view.findViewById(R.id.productImage);
        TextView totalQuantity = view.findViewById(R.id.totalQuantity);
        ImageView removeFromList = view.findViewById(R.id.removeFromList);

        ImageView buttonAdd = view.findViewById(R.id.buttonAdd);
        ImageView buttonSubtract = view.findViewById(R.id.buttonSubtract);


        final Inventory inventory = list.get(position);

        new AsyncTask<Void,Void,Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                try {        //for the 5 day forecast
                    Log.d("SEARCHING","SEARCHING");
                    url = new URL("https://www.googleapis.com/customsearch/v1?q=%22" + inventory.getName() + "%22&key=AIzaSyDifREwA82Bv_RM7LkDEFKjK7INwrrwL9Y&num=5&searchType=image&cx=008840029476306506173:4zyg9ds1eoq");   //5 day
                    connection = url.openConnection();
                    stream = connection.getInputStream();
                    streamReader = new InputStreamReader(stream);
                    reader = new BufferedReader(streamReader);
                    info = "";
                    while ((text = reader.readLine()) != null) {
                        info += text;
                    }
                    imageData = new JSONObject(info);
                    Log.d("REACHED",imageData+"");
                }catch(Exception e){
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                try{
                    actualURL = imageData.getJSONArray("items").getJSONObject(0).getJSONObject("image").getString("thumbnailLink");
                    Log.d("REALURL",actualURL);
                    Picasso.get().load(actualURL).into(productImage);
                }catch(Exception e){
                    e.printStackTrace();
                }

            }
        }.execute();
        //Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/" + inventory.getName().toLowerCase() + ".jpg").into(productImage);


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
                if(inventory.getTotalQuantity() <= 1){
                    Toast.makeText(parentContext, "Cannot have fewer than one item in the list!", Toast.LENGTH_SHORT).show();
                }else{
                    inventory.setQuantity(inventory.getTotalQuantity() - 1);
                    databaseReference.child(list.get(position).getName()).setValue(inventory);
                }
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
