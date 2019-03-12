package com.example.AndroidProject;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void Button_SignUp (View v){
        Intent in = new Intent(this, SignUpPage.class);
        startActivity(in);
    }



    public void Button_SignIn(View v){
        Intent sn = new Intent(this,SignInPage.class);
        startActivity(sn);
    }
}
