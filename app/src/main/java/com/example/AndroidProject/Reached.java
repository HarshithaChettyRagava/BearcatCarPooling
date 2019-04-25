package com.example.AndroidProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Reached extends AppCompatActivity {
    Button payCash;
    Button card;
    TextView cost;
    TextView dist_trav;
    TextView Trav_price;
    TextView Tax;
    TextView Total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reached);

        payCash = (Button) findViewById(R.id.Pay_Cash);
        card = (Button) findViewById(R.id.Card);
        cost=(TextView) findViewById(R.id.TV_S_R);
        dist_trav=(TextView) findViewById(R.id.DT_T_R2);
        Trav_price=(TextView) findViewById(R.id.DT_T_R1);
        Tax=(TextView) findViewById(R.id.DT_T1);
        Total=(TextView) findViewById(R.id.TOT);




        //est_cost
        Intent intent = getIntent();
        String str = intent.getStringExtra("est_cost");
        String str1 = intent.getStringExtra("Dist_Travel");
        String str2 = intent.getStringExtra("Travel_price");
        String str3 = intent.getStringExtra("tax");
        String str4 = intent.getStringExtra("est_cost");
        cost.setText(str);
        dist_trav.setText(str1);
        Trav_price.setText(str2);
        Tax.setText(str3);
        Total.setText(str4);


    }

    public void Pay_Cash(View v){
        Intent intt = new Intent(this, FeedbackPage.class);
        startActivity(intt);
    }

    public void Pay_Card(View v){
        Intent intt = new Intent(this, FeedbackPage.class);
        startActivity(intt);
    }
}

