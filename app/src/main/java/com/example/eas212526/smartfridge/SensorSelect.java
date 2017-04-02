package com.example.eas212526.smartfridge;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SensorSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_select);
        Button sensor1;
        sensor1 = (Button)findViewById(R.id.sensor1);
        sensor1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent ii = new Intent(getApplicationContext(),Display.class);
                ii.putExtra("sensorNo","1");
                startActivity(ii);
            }
        });
    }
}
