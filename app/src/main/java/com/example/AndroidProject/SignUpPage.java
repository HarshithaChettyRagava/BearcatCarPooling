package com.example.AndroidProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpPage extends AppCompatActivity {
    MyHelper helper;

    public EditText nameET;
    public EditText emailIdET;
    public EditText addressET;
    public EditText phoneNoET;
    public EditText passwordET;
    public EditText confirmET;

    private SQLiteDatabase mSQLiteDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        nameET = findViewById(R.id.fullNameET);
        emailIdET = findViewById(R.id.emailIdET);
        addressET = findViewById(R.id.addressET);
        phoneNoET = findViewById(R.id.phoneNoET);
        passwordET = findViewById(R.id.passwordET);
        confirmET = findViewById(R.id.confrimPwdET);
        //helper= new MyHelper(SignUpPage.this);

//        Button submitBTN = findViewById(R.id.submitBTN);
//        submitBTN.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ParseObject rec1 =new ParseObject("Details");
//                rec1.put("Name","admin");
//                rec1.put("Email","S533569@nwmissouri.edu");
//                rec1.put("Address","1121 N college drive");
//                rec1.put("Phone","6605419000");
//                rec1.put("Password","admin");
//                rec1.put("ConfirmPwd","admin");
//
//                rec1.saveInBackground(new SaveCallback() {
//                    @Override
//                    public void done(ParseException e) {
//                        Log.d("Parse","rec1 saved?"+e);
//                    }
//                });
//            }
//        });
//    }
    }

    public void submitButton(View v) {
        final String newEntry1 = nameET.getText().toString();
        String newEntry2 = emailIdET.getText().toString();
        String newEntry3 = addressET.getText().toString();
        String newEntry4 = phoneNoET.getText().toString();
        final String newEntry5 = passwordET.getText().toString();
        final String newEntry6 = confirmET.getText().toString();

        if (newEntry1.length() == 0 || newEntry2.length() == 0 || newEntry3.length() == 0 || newEntry4.length() == 0
                || newEntry5.length() == 0 || newEntry6.length() == 0) {
            Toast.makeText(this, "please fill all the mandatory fields!", Toast.LENGTH_SHORT).show();
        } else {
            nameET.setText("");
            emailIdET.setText("");
            addressET.setText("");
            phoneNoET.setText("");
            passwordET.setText("");
            confirmET.setText("");

            Intent innt = new Intent(this, CarBooking.class);
            startActivity(innt);
        }
    }
}
