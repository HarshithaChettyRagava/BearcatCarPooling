package com.example.AndroidProject;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ConfirmationPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation_page);
    }
    public void confrimButton(View v){
        Intent intt = new Intent(this,FeedbackPage.class);
        startActivity(intt);
    }
}
