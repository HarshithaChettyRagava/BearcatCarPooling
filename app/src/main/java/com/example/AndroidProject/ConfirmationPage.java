package com.example.AndroidProject;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;
import java.util.concurrent.TimeUnit;

import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.Random;

public class ConfirmationPage extends AppCompatActivity implements BookingConfirmedAlert.Helper {
    TextView Tv_s;
    Button Btn_min;
    Button Btn_SUV;
    Button Btn_PRI;
    TextView Est_cst;
    TextView Est_cst1;
    TextView Est_cst2;
    double est_cost;
    double per_mile;
    double no_mile;
    double no_ppl;
    double Tax;
    double Total_cost;

    LatLng objLatLng;
    LatLng objLatLng1;


    int rand_1;
    public int rand(int car)
    {
        Random ran= new Random();
        rand_1=ran.nextInt(car);
        return rand_1;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_page);
        final TextView Tv_s= (TextView) findViewById(R.id.TV_S);

//        //Get the bundle
//        Bundle bundle = getIntent().getExtras();
//
//        //Extract the data
//        String lat1 = bundle.getString("latitude1");
//        String lon1 = bundle.getString("longitude1");
//
//        Log.d("latitude", "LAtitude is: "+lat1);
//        Log.d("latitude", "Longitude is: "+lon1);
//
//        Log.d("Confirmation","lat1 and long1 are"+lat1+" "+lon1);

         objLatLng = getIntent().getExtras().getParcelable("LatLng1");
        Log.d("Confirmation","lat1 and long1 are"+objLatLng.latitude+" "+objLatLng.longitude);



         objLatLng1 = getIntent().getExtras().getParcelable("LatLng2");
        Log.d("Confirmation","lat2 and long2 are"+objLatLng1.latitude+" "+objLatLng.longitude);

        Location loc1 = new Location("Google");
        loc1.setLatitude(objLatLng.latitude);
        loc1.setLongitude(objLatLng.longitude);

        Location loc2 = new Location("Google");
        loc2.setLatitude(objLatLng1.latitude);
        loc2.setLongitude(objLatLng1.longitude);

        float distanceInMeters = loc1.distanceTo(loc2);
        Log.d("Confirmation","Distance is: "+distanceInMeters);
        no_mile = Math.round(distanceInMeters*0.00062137);
        Log.d("Confirmation","Distance in miles is: "+no_mile);


        Btn_min= (Button) findViewById(R.id.BTN_MINI);
        Btn_SUV= (Button) findViewById(R.id.BTN_SUV);
        Btn_PRI= (Button) findViewById(R.id.BTN_PRIME);
        Est_cst=(TextView) findViewById(R.id.TV_EST_CST);
        Est_cst1=(TextView) findViewById(R.id.TV_E_C);
        Est_cst2=(TextView) findViewById(R.id.TV_S_R);




        Btn_min.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                rand(4);
                Tv_s.setText(rand(4)+"");
                per_mile=0.2;
            }
        });


        Btn_SUV.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Tv_s.setText(rand(5)+"");
                per_mile=0.3;
            }
        });

        Btn_PRI.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Tv_s.setText(rand(7)+"");
                per_mile=0.4;
            }
        });




        ((EditText)findViewById(R.id.TV_S_R)).setOnEditorActionListener(
                new EditText.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                                actionId == EditorInfo.IME_ACTION_DONE ||
                                event != null &&
                                        event.getAction() == KeyEvent.ACTION_DOWN &&
                                        event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
                            if (event == null || !event.isShiftPressed()) {

                                int seats_available=Integer.parseInt(Tv_s.getText().toString());
                                int seats_requested =Integer.parseInt(Est_cst2.getText().toString());
                                if(seats_requested<=seats_available) {

                                    //  Est_cst1.setText(Est_cst2.getText().toString());
                                    String est_cst = Est_cst2.getText().toString();
                                    est_cost = Double.parseDouble(est_cst) * no_mile * per_mile;
                                    Tax = est_cost * 10 / 100;
                                    Total_cost = est_cost + (est_cost * 10 / 100);
                                    // Total_cost=40+(40*10/100);
                                    Est_cst1.setText("$" + Total_cost);
                                }
                                else
                                {

                                    MoreSeatsActivity TY = new MoreSeatsActivity();
                                    TY.show(getSupportFragmentManager(), "TAG");

                                }

                                return true; // consume.
                            }
                        }
                        return false; // pass on to other listeners.
                    }
                }
        );
    }

    public void confrimButton(View v){
        BookingConfirmedAlert TY = new BookingConfirmedAlert();
        TY.show(getSupportFragmentManager(), "TAG");

    }

    public void callmain()
    {
        Intent intt = new Intent(this, MainActivity.class);
        startActivity(intt);
    }


    public void callstop()
    {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    public void callReached()
    {

        Intent intt = new Intent(this, Reached.class);
        intt.putExtra("est_cost","$"+Total_cost);
        intt.putExtra("Dist_Travel",no_mile+"");
        intt.putExtra("Travel_price","$"+est_cost);
        intt.putExtra("tax","$"+Tax);
        intt.putExtra("Total","$"+Tax);
        startActivity(intt);
    }

//    public double function getDistanceFromLatLonInKm(lat,lon1,lat2,lon2) {
//        var R = 6371; // Radius of the earth in km
//        var dLat = deg2rad(lat2-lat1);  // deg2rad below
//        var dLon = deg2rad(lon2-lon1);
//        var a =
//                Math.sin(dLat/2) * Math.sin(dLat/2) +
//                        Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
//                                Math.sin(dLon/2) * Math.sin(dLon/2)
//                ;
//        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
//        var d = R * c; // Distance in km
//        return d;
//    }
//
//    public double function deg2rad(deg) {
//        return deg * (Math.PI/180)
//    }


    public void CancelBtn(View v){
        Toast.makeText(getApplicationContext(),"you have canceled the Operation",Toast.LENGTH_LONG).show();
//        Intent intt = new Intent(this,ConfirmationPage.class);
//        startActivity(intt);
    }
}
