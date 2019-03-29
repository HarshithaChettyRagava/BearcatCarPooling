package com.example.AndroidProject;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignInPage extends AppCompatActivity {

    private EditText EmailId;
    private EditText UserPwd;
    MyHelper db;
    String Username;
    String Password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);
        EmailId = findViewById(R.id.passwordET);
        UserPwd = findViewById(R.id.nameET);
    }


    public void buttonLogin(View v){
        db = new MyHelper(this);
        Cursor cursor = db.allData();
        if(cursor.getCount()==0){
            Toast.makeText(getApplicationContext(),"No rows found"+cursor.getCount(),Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(),"No of rows found"+cursor.getCount(),Toast.LENGTH_SHORT).show();
            Log.d("No of rows are"," "+cursor.getCount());
            cursor.moveToFirst();
            while(cursor.moveToNext()){
                Toast.makeText(getApplicationContext(),"UserName is :"+cursor.getString(0),Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),"Password is :"+cursor.getString(1),Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),"Address is :"+cursor.getString(2),Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),"Phone No is :"+cursor.getString(3),Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),"Password is :"+cursor.getString(4),Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),"ConfirmPwd is :"+cursor.getString(5),Toast.LENGTH_SHORT).show();
                Username = cursor.getString(0);
                Password = cursor.getString(1);
            }
        }
//        Intent inn = new Intent(this,CarBooking.class);
//        startActivity(inn);
    }
}