package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        this.setTitle("Your Order");

        Intent receivedOrderIntent = getIntent();
        String userName = receivedOrderIntent.getStringExtra("userNameForOrder");
        String goodsForOrder = receivedOrderIntent.getStringExtra("goodsForOrder");
        int quantityForOrder = receivedOrderIntent.getIntExtra("quantityForOrder", 0);
        double orderPriceForOrder = receivedOrderIntent.getDoubleExtra("orderPriceForOrder", 0.0);
        double price = receivedOrderIntent.getDoubleExtra("pricePerOne", 0.0);

        TextView orderTextView = findViewById(R.id.orderTextView);
        orderTextView.setText("Customer name: "+userName + "\n"+
                "Goods name: "+ goodsForOrder + "\n"+
                "Quantity: "+ quantityForOrder + "\n"+
                "Price: "+ price + "\n"+
                "Order Price: "+ orderPriceForOrder);




    }
}
