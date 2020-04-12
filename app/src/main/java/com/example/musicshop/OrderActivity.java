package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    String[] addresses = {"volskyi.dmytro@gmail.com"};
    String subject = "Order from Music Shop";
    String emailText;

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
        emailText = ("Customer name: "+userName + "\n" +
                     "Goods name: "+ goodsForOrder + "\n" +
                  "Quantity: "+ quantityForOrder + "\n" +
                 "Price: "+ price + "\n" +
                 "Order Price: "+ orderPriceForOrder);

        TextView orderTextView = findViewById(R.id.orderTextView);
        orderTextView.setText(emailText);




    }

    public void sendOrderByEmail(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
}
