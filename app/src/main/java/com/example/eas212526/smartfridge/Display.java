package com.example.eas212526.smartfridge;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


public class Display extends AppCompatActivity {
String serverUrl = "http://192.168.43.87/SmartFridgeServer/Server.php";
    String number;
    String Data="";
    TextView textView;
    int flag=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Intent i = getIntent();
        number = i.getStringExtra("sensorNo");
        DownFromDB(number);
        //Data = DownFromDB
        textView = (TextView)findViewById(R.id.currentText);
    }
    private void DownFromDB(String a) {
        final String i = a;
        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, serverUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();
                Data = response;
                String items[] = Data.split("\n");
                int SrNo =0;
                int d= items.length-1;
                TableLayout tv=(TableLayout) findViewById(R.id.table);
                tv.removeAllViewsInLayout();
                for(int j=-2;j<items.length;j+=2){
                    TableRow tr=new TableRow(Display.this);

                    tr.setLayoutParams(new TableRow.LayoutParams(
                            TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT));

                    // this will be executed once
                    if(flag==1){

                        TextView b3=new TextView(Display.this);
                        b3.setText("Sr No");
                        b3.setTextColor(Color.BLUE);
                        b3.setTextSize(15);
                        tr.addView(b3);

                        TextView b4=new TextView(Display.this);
                        b4.setPadding(1, 0, 0, 0);
                        b4.setTextSize(15);
                        b4.setText("Weight");
                        b4.setTextColor(Color.BLUE);
                        tr.addView(b4);

                        TextView b5=new TextView(Display.this);
                        b5.setPadding(1, 0, 0, 0);
                        b5.setText("Time stamp");
                        b5.setTextColor(Color.BLUE);
                        b5.setTextSize(15);
                        tr.addView(b5);
                        tv.addView(tr);

                        final View vline = new View(Display.this);
                        vline.setLayoutParams(new
                                TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, 2));
                        vline.setBackgroundColor(Color.BLUE);
                        tv.addView(vline); // add line below heading
                        flag=0;
                    } else {
                        SrNo++;
                        TextView b=new TextView(Display.this);
                        String str=String.valueOf(SrNo);
                        b.setText(str);
                        b.setTextColor(Color.RED);
                        b.setTextSize(15);
                        tr.addView(b);

                        TextView b1=new TextView(Display.this);
                        b1.setPadding(1, 0, 0, 0);
                        b1.setTextSize(15);
                        String str1= items[j].toString();
                        b1.setText(str1);
                        b1.setTextColor(Color.RED);
                        tr.addView(b1);

                        TextView b2=new TextView(Display.this);
                        b2.setPadding(1, 0, 0, 0);
                        String str2=items[j+1].toString();
                        b2.setText(str2);
                        b2.setTextColor(Color.RED);
                        b2.setTextSize(15);
                        tr.addView(b2);
                        tv.addView(tr);
                        final View vline1 = new View(Display.this);
                        vline1.setLayoutParams(new
                                TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 1));
                        vline1.setBackgroundColor(Color.WHITE);
                        tv.addView(vline1);  // add line below each row
                    }
                }
                textView.setText(items[0]);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("sensorNo",i.toString());
                return params;
            }
        };
        Singleton.getSingletonInstance(getApplicationContext()).addToRequestQueue(stringRequest1);
    }

}
