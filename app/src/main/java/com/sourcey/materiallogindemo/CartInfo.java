package com.sourcey.materiallogindemo;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CartInfo extends AppCompatActivity{

    Button btn_order_cart,btn_start_cart,btn_stop_cart,btn_left_cart,btn_right_cart;
    AlertDialog alert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_info);

        btn_order_cart = (Button) findViewById(R.id.btn_order_cart);
        btn_start_cart = (Button) findViewById(R.id.btn_start_cart);
        btn_stop_cart = (Button) findViewById(R.id.btn_stop_cart);
        btn_left_cart = (Button) findViewById(R.id.btn_left_cart);
        btn_right_cart = (Button) findViewById(R.id.btn_right_cart);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to order a new cart?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(CartInfo.this, "New Cart Ordered", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        alert = builder.create();


        btn_order_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.show();
            }
        });

        btn_start_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CartInfo.this, "Start button pressed", Toast.LENGTH_SHORT).show();
            }
        });

        btn_stop_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CartInfo.this, "Stop Button pressed", Toast.LENGTH_SHORT).show();
            }
        });

        btn_left_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CartInfo.this, "Left Button pressed", Toast.LENGTH_SHORT).show();
            }
        });

        btn_right_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CartInfo.this, "Right button pressed", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
