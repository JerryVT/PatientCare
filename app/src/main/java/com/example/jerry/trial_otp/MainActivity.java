package com.example.jerry.trial_otp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//compile 'com.google.firebase:firebase-auth:10.0.1'
public class MainActivity extends Activity {

    EditText name, password;
    String Name, Password;
    Context ctx=this;
    String NAME=null, PASSWORD=null, EMAIL=null,FLAG=null,USERTYPE=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.main_name);
        password = (EditText) findViewById(R.id.main_password);
    }

    public void main_register(View v){
        startActivity(new Intent(this,Register.class));
    }

    public void main_login(View v){
        Name = name.getText().toString();
        Password = password.getText().toString();
        BackGround b = new BackGround();
        b.execute(Name, Password);
    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String password = params[1];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://blending-jacks.000webhostapp.com/reglog_trial/login.php");
                String urlParams = "name="+name+"&password="+password;

                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                os.write(urlParams.getBytes());
                os.flush();
                os.close();

                InputStream is = httpURLConnection.getInputStream();
                while((tmp=is.read())!=-1){
                    data+= (char)tmp;
                }

                is.close();
                httpURLConnection.disconnect();

                return data;
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Exception: "+e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            String err=null;
            try {
                JSONObject root = new JSONObject(s);
                JSONObject user_data = root.getJSONObject("user_data");
                NAME = user_data.getString("name");
                PASSWORD = user_data.getString("password");
                EMAIL = user_data.getString("email");
                USERTYPE = user_data.getString("userType");
                FLAG = user_data.getString("flag");
            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: "+e.getMessage();
            }

            if (USERTYPE = "Patient") {

                if (FLAG = "0") {       //More Details not provided
                    Intent i = new Intent(ctx, Reg_Patient.class);
                    i.putExtra("name", NAME);
                    i.putExtra("password", PASSWORD);
                    i.putExtra("email", EMAIL);
                    i.putExtra("err", err);
                    startActivity(i);
                }
                else {          //All Details were provided

                    Intent i = new Intent(ctx, Home_Patient.class);
                    i.putExtra("name", NAME);
                    i.putExtra("password", PASSWORD);
                    i.putExtra("email", EMAIL);
                    i.putExtra("err", err);
                    startActivity(i);
                }
            }

            else if (USERTYPE = "Doctor") {
                if (FLAG = "0") {
                    Intent i = new Intent(ctx, Reg_Doctor.class);
                    i.putExtra("name", NAME);
                    i.putExtra("password", PASSWORD);
                    i.putExtra("email", EMAIL);
                    i.putExtra("err", err);
                    startActivity(i);
                } else {
                    Intent i = new Intent(ctx, Home_Doctor.class);
                    i.putExtra("name", NAME);
                    i.putExtra("password", PASSWORD);
                    i.putExtra("email", EMAIL);
                    i.putExtra("err", err);
                    startActivity(i);
                }
            }


        }
    }
}