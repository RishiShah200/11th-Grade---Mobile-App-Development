package com.example.firebasedemo.ui.notifications;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.firebasedemo.R;
import com.example.firebasedemo.ui.detection.Inventory;

import java.util.List;

public class InventoryList extends ArrayAdapter<Inventory> {

    List<Inventory> list;
    Context parentContext;
    int xmlResource;

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
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = (LayoutInflater)parentContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);  //sets layout on the screeen
        View view = layoutInflater.inflate(R.layout.inventory_layout,null,true);

        TextView nameOfProduct = view.findViewById(R.id.nameOfProduct);
        TextView expirationDate = view.findViewById(R.id.expirationDate);
        ImageView productImage = view.findViewById(R.id.productImage);
        TextView totalQuantity = view.findViewById(R.id.totalQuantity);

        Inventory inventory = list.get(position);

        nameOfProduct.setText(inventory.getName());
        expirationDate.setText(inventory.getDateOfExpiration());
        totalQuantity.setText("Quantity: " + inventory.getTotalQuantity());


        return view;
    }
}
