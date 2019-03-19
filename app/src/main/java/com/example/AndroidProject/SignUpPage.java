package com.example.AndroidProject;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpPage extends AppCompatActivity {
    private EditText name;
    private EditText phone;
    private EditText email;
    private EditText address;
    private EditText password;
    private EditText confirm_password;
    private MyHelper mMyHelper;
    private SQLiteDatabase mSQLiteDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        name=(EditText) findViewById(R.id.name);
        phone=(EditText) findViewById(R.id.phone);
        email=(EditText) findViewById(R.id.email);
        address=(EditText) findViewById(R.id.address);
        password=(EditText) findViewById(R.id.password);
        confirm_password=(EditText) findViewById(R.id.confirm_password);
        mMyHelper= new MyHelper(SignUpPage.this,"CARREG",null,1);
        mSQLiteDB=mMyHelper.getWritableDatabase();
    }


    public void submitButton (View v){
        ContentValues cv= new ContentValues();
        cv.put("name",name.getText().toString());
        cv.put("name",phone.getText().toString());
        cv.put("name",email.getText().toString());
        cv.put("name",address.getText().toString());
        cv.put("name",password.getText().toString());
        cv.put("name",confirm_password.getText().toString());
        long id=mSQLiteDB.insert("Tb_User",null,cv);
        Toast.makeText(SignUpPage.this,String.valueOf(id),Toast.LENGTH_LONG).show();
     //   Intent inn = new Intent(this,CarBooking.class);
       // startActivity(inn);
    }

    public void ViewButton (View v){
        Cursor c=mSQLiteDB.query("Tb_User",null,null,null,null,null,null,null);
        while(c.moveToNext()){
            Toast.makeText(SignUpPage.this,c.getColumnIndex("name"),Toast.LENGTH_LONG).show();
        }
    }



}
