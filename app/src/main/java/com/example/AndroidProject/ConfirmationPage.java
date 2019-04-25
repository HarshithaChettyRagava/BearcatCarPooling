package com.example.AndroidProject;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.concurrent.TimeUnit;

import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
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
    double no_mile=10;
    double no_ppl;
    double Tax;
    double Total_cost;

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
                per_mile=2;
            }
        });


        Btn_SUV.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Tv_s.setText(rand(5)+"");
                per_mile=3;
            }
        });

        Btn_PRI.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Tv_s.setText(rand(7)+"");
                per_mile=4;
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
        intt.putExtra("Dist_Travel",no_mile+" miles");
        intt.putExtra("Travel_price","$"+est_cost);
        intt.putExtra("tax","$"+Tax);
        intt.putExtra("Total","$"+Tax);
        startActivity(intt);
    }


    public void CancelBtn(View v){
        Toast.makeText(getApplicationContext(),"you have canceled the Operation",Toast.LENGTH_LONG).show();
        Intent intt = new Intent(this,ConfirmationPage.class);
        startActivity(intt);
    }

}
