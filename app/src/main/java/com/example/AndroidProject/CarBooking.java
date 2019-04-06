package com.example.AndroidProject;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CarBooking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_booking);
    }

    public void BookButton(View v){
        Intent inn = new Intent(this, ConfirmationPage.class);
        startActivity(inn);
    }
}
