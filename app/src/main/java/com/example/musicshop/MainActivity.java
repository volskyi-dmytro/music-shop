package com.example.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    int q = 0;
    Spinner spinner;
    ArrayList spinnerItemList;
    ArrayAdapter spinnerAdapter;
    HashMap musicGoods;
    String goodsName;
    double price;
    EditText userNameEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createSpinner();
        userNameEditText = findViewById(R.id.nameEditText);
        createMap();




    }

    void createSpinner(){
        spinner = findViewById(R.id.itemSpinner);
        spinner.setOnItemSelectedListener(this);


        spinnerItemList = new ArrayList();
        spinnerItemList.add("guitar");
        spinnerItemList.add("drums");
        spinnerItemList.add("keyboard");
        spinnerAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItemList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

    }

    void createMap(){
        musicGoods = new HashMap();
        musicGoods.put("guitar", 1000.0);
        musicGoods.put("drums", 1500.0);
        musicGoods.put("keyboard", 800.0);
    }

    public void minusButtonAction(View view) {
        q= q -1;
        if(q<0){
            q = 0;
        }
        TextView quantityTextNumber = findViewById(R.id.quantityNumber);
        quantityTextNumber.setText(""+q);
        setPrice();


    }

    private void setPrice() {
        TextView amountTextView = findViewById(R.id.amount);
        amountTextView.setText(""+q*price);
    }

    public void plusButtonAction(View view) {
        q = q + 1;
        TextView quantityTextNumber = findViewById(R.id.quantityNumber);
        quantityTextNumber.setText(""+q);
        TextView amountTextView = findViewById(R.id.amount);
        amountTextView.setText(""+q*price);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double) musicGoods.get(goodsName);
        TextView amountTextView = findViewById(R.id.amount);
        amountTextView.setText(""+q*price);

        ImageView goodsImageView = findViewById(R.id.imageView4);
        switch(goodsName){
            case "guitar" : goodsImageView.setImageResource(R.drawable.ibanez);
            break;
            case "drums" : goodsImageView.setImageResource(R.drawable.drums);
                break;
            case "keyboard" : goodsImageView.setImageResource(R.drawable.keyboard);
                break;
            default: goodsImageView.setImageResource(R.drawable.ibanez);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCart(View view) {
        Order order = new Order();
        order.userName = userNameEditText.getText().toString();
        order.goodsName = goodsName;
        order.quantity = q;
        order.orderPrice = q*price;

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);
        orderIntent.putExtra("userNameForOrder", order.userName);
        orderIntent.putExtra("goodsForOrder", order.goodsName);
        orderIntent.putExtra("quantityForOrder", order.quantity);
        orderIntent.putExtra("orderPriceForOrder", order.orderPrice);
        orderIntent.putExtra("pricePerOne", price);

        startActivity(orderIntent);


    }
}
