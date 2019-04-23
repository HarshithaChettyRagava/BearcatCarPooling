package com.example.AndroidProject;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.parse.FindCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import java.util.List;

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
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.icon);

        EmailId = (EditText) findViewById(R.id.emailIdET);
        UserPwd = (EditText) findViewById(R.id.passwordET);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                // if defined
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );

        //ParseUser.enableRevocableSessionInBackground();
        //ParseUser.getCurrentUser().logOut();

        // Save the current Installation to Back4App
        ParseInstallation.getCurrentInstallation().saveInBackground();
    }


    public void buttonLogin(View v){
        String userNameIn = EmailId.getText().toString();
        String userPwdIn = UserPwd.getText().toString();


//        ParseQuery<ParseObject> allQuery = ParseQuery.getQuery("User");
////        allQuery.whereEqualTo("username",userNameIn);
////        allQuery.whereEqualTo("password",userPwdIn);
////        allQuery.findInBackground(new FindCallback<ParseObject>() {
////            @Override
////            public void done(List<ParseObject> objects, ParseException e) {
////
////            }
////        });

        ParseUser.logInInBackground(userNameIn,userPwdIn, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                Log.d("Parse","Login Result: "+e);
                if(e!=null){
                    Toast.makeText(getApplicationContext(),"Error: "+e,Toast.LENGTH_LONG).show();
                    Log.d("Parse","Error: "+e.getMessage());
                    EmailId.setText("");
                    UserPwd.setText("");
                }
                else{
                    Log.d("Parse","Successfully logged in");
                    EmailId.setText("");
                    UserPwd.setText("");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            SignInPage.this.finish();
                        }
                    }, 10000);

                    Intent inn = new Intent(getApplicationContext(),MapsActivity.class);
                    startActivity(inn);
                }
            }
        });

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                SignInPage.this.finish();
//            }
//        }, 10000);


    }
}