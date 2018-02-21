package com.example.jerry.trial_otp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Home extends Activity {

    String Name, password, Email, Err;
    TextView nameTV, emailTV, passwordTV, err;
    Button mkappointment,vwappointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        nameTV = (TextView) findViewById(R.id.home_name);
        //emailTV = (TextView) findViewById(R.id.home_email);
       // passwordTV = (TextView) findViewById(R.id.home_password);
        //err = (TextView) findViewById(R.id.err);

        Name = getIntent().getStringExtra("name");
       //  Password = getIntent().getStringExtra("password");
        Email = getIntent().getStringExtra("email");
      //  Err = getIntent().getStringExtra("err");

        nameTV.setText("Welcome "+Name);
       // passwordTV.setText("Your password is "+password);
        //emailTV.setText("Your email is "+email);
        //err.setText(Err);

        mkappointment=(Button) findViewById(R.id.makeapmnt);
        vwappointment=(Button) findViewById(R.id.viewapmnt);




    }
}