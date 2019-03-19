package com.example.AndroidProject;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
