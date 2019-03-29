package com.example.AndroidProject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.SQLException;

public class SignUpPage extends AppCompatActivity {
    MyHelper helper;

    public EditText nameET;
    public EditText emailIdET;
    public EditText addressET;
    public EditText phoneNoET;
    public EditText passwordET;
    public EditText confirmET;

//    private SQLiteDatabase mSQLiteDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        nameET = findViewById(R.id.nameET);
        emailIdET = findViewById(R.id.emailIdET);
        addressET = findViewById(R.id.addressET);
        phoneNoET = findViewById(R.id.phoneNoET);
        passwordET = findViewById(R.id.passwordET);
        confirmET = findViewById(R.id.confrimPwdET);
        helper= new MyHelper(SignUpPage.this);
//        mMyHelper= new MyHelper(SignUpPage.this,"CARREG",null,1);
//        mSQLiteDB=helper.getWritableDatabase();
    }


    public void submitButton (View v){
        String newEntry1 = nameET.getText().toString();
        String newEntry2 = emailIdET.getText().toString();
        String newEntry3 = addressET.getText().toString();
        String newEntry4 = phoneNoET.getText().toString();
        String newEntry5 = passwordET.getText().toString();
        String newEntry6 = confirmET.getText().toString();

        if(newEntry1.length()==0 || newEntry2.length()==0 || newEntry3.length()==0 || newEntry4.length()==0
                || newEntry5.length()==0 || newEntry6.length()==0){
            Toast.makeText(this,"please fill all the mandatory fields!",Toast.LENGTH_SHORT).show();
        }
        else{
            AddData(newEntry1, newEntry2, newEntry3, newEntry4, newEntry5, newEntry6);
            nameET.setText("");
            emailIdET.setText("");
            addressET.setText("");
            phoneNoET.setText("");
            passwordET.setText("");
            confirmET.setText("");
        }
    }

    public void AddData(String newEntry1, String newEntry2, String newEntry3, String newEntry4, String newEntry5, String newEntry6){
        boolean insertData;
        insertData = helper.addData(newEntry1, newEntry2, newEntry3, newEntry4, newEntry5, newEntry6);
        if(insertData){
            Toast.makeText(this, "Data Sucessfully Inserted!",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this,"Something went wrong..",Toast.LENGTH_SHORT).show();
        }
    }

//    public void ViewButton (View v){
//        Cursor c=mSQLiteDB.query("Tb_User",null,null,null,null,null,null,null);
//        while(c.moveToNext()){
//            Toast.makeText(SignUpPage.this,c.getColumnIndex("name"),Toast.LENGTH_LONG).show();
//        }
//    }
}
