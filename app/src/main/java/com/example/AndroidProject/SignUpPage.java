package com.example.AndroidProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpPage extends AppCompatActivity {
    //MyHelper helper;

    public EditText nameET;
    public EditText emailIdET;
    public EditText addressET;
    public EditText phoneNoET;
    public EditText passwordET;
    public EditText confirmET;

    private Pattern pattern;
    private Matcher matcher;

    private static final String PASSWORD_PATTERN =
            "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";

//    private SQLiteDatabase mSQLiteDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.icon);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );

        ParseUser.enableRevocableSessionInBackground();
        ParseUser.getCurrentUser().logOut();

        // Save the current Installation to Back4App
        ParseInstallation.getCurrentInstallation().saveInBackground();

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
            if (validate(newEntry5)) {
                ParseUser user = new ParseUser();
                user.put("Name", newEntry1);
                user.setUsername(newEntry2);
                user.setEmail(newEntry2);
                user.put("Address", newEntry3);
                user.put("Phone", newEntry4);
                user.setPassword(newEntry5);
                user.put("ConfirmPwd", newEntry6);

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        Log.d("Parse", "Registration: " + e);
                        if (e == null) {
                            if (newEntry5.equals(newEntry6)) {
                                Toast.makeText(getApplicationContext(), "Thank You " + newEntry1 + " for registering with us!", Toast.LENGTH_LONG).show();
                                //thread.start();
                            } else {
                                Toast.makeText(getApplicationContext(), "Your passwords doesn't match!", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(getApplicationContext(), "Error: " + e, Toast.LENGTH_LONG).show();
                            //thread.start();
                        }
                    }
                });
                nameET.setText("");
                emailIdET.setText("");
                addressET.setText("");
                phoneNoET.setText("");
                passwordET.setText("");
                confirmET.setText("");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        SignUpPage.this.finish();
                    }
                }, 10000);

                Intent innt = new Intent(this, MapsActivity.class);
                startActivity(innt);

            }else{
                Toast.makeText(getApplicationContext(),"Password should contain atleast one uppercase, " +
                                "lowercase & special characters, numeric, min of 6, max of 20 characters password length",
                        Toast.LENGTH_LONG).show();
//              Toast.makeText(getApplicationContext(),"Password should be alphanumeric, atleast one special ",Toast.LENGTH_LONG).show();

            }
        }
    }

    Thread thread = new Thread(){
        @Override
        public void run() {
            try{
                Thread.sleep(Toast.LENGTH_LONG);
                SignUpPage.this.finish();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    /**
     * Validate password with regular expression
     * @param password password for validation
     * @return true valid password, false invalid password
     */
    public boolean validate(String password){
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }
}
