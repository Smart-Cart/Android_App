package com.sourcey.materiallogindemo;

import android.content.DialogInterface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CartInfo extends AppCompatActivity implements SensorEventListener, StepListener{

    private TextView weight_text;
    private StepDetector simpleStepDetector;
    private SensorManager sensorManager;
    private Sensor accel;
    private Handler mHandler = new Handler();
    private static final String TEXT_NUM_STEPS = "Number of Steps: ";
    private int numSteps = 0;
    String direction = "Straight";
    private Runnable mToastRunnable;
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
        weight_text = (TextView) findViewById(R.id.weight_text);

        // Get an instance of the SensorManager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        accel = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        simpleStepDetector = new StepDetector();
        simpleStepDetector.registerListener(this);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to order a new cart?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Toast.makeText(CartInfo.this, "New Cart Ordered", Toast.LENGTH_SHORT).show();
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
                //mHandler.postDelayed(mToastRunnable,5000);
                numSteps = 0;
                sensorManager.registerListener(CartInfo.this, accel, SensorManager.SENSOR_DELAY_FASTEST);
                mToastRunnable.run();
            }
        });

        btn_stop_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sensorManager.unregisterListener(CartInfo.this);
                mHandler.removeCallbacks(mToastRunnable);
            }
        });

        btn_left_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(CartInfo.this, "Left Button pressed", Toast.LENGTH_SHORT).show();
                direction = "Left";
            }
        });

        btn_right_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(CartInfo.this, "Right button pressed", Toast.LENGTH_SHORT).show();
                direction = "Right";
            }
        });

        mToastRunnable = new Runnable() {
            @Override
            public void run() {
                Toast.makeText(CartInfo.this, direction + ": " +numSteps, Toast.LENGTH_SHORT).show();
                numSteps = 0;
                direction = "Straight";
                mHandler.postDelayed(mToastRunnable,5000);
            }
        };


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            simpleStepDetector.updateAccel(
                    event.timestamp, event.values[0], event.values[1], event.values[2]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void step(long timeNs) {
        numSteps++;
    }
}
