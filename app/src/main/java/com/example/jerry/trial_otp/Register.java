package com.example.jerry.trial_otp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class Register extends AppCompatActivity implements OnItemSelectedListener {

    EditText name, password, email,mobileNo;
    String Name, Password, Email,MobileNo;
    String Flag = "False";
    Context ctx=this;
    Button register;
    String userType[] = { "Patient", "Doctor"};
    String uType = null;
    Spinner spinner;
    private static final String TAG = "Register";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(Register.this,android.R.layout.simple_spinner_item,userType);

        List<String> userTypes = new ArrayList<>();
        userTypes.add("Patient");
        userTypes.add("Doctor");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Register.this,android.R.layout.simple_spinner_item,userTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        name = (EditText) findViewById(R.id.register_name);
        password = (EditText) findViewById(R.id.register_password);
        email = (EditText) findViewById(R.id.register_email);
        mobileNo = (EditText) findViewById(R.id.register_mobile);
        register = (Button) findViewById(R.id.register_register);

        spinner= (Spinner) findViewById(R.id.spinner2);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //public void register_register(View v){

      //  register.setEnabled(false);

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (spinner != null && spinner.getSelectedItem() != null) {
                    uType = (String) spinner.getSelectedItem();                       // uType = spinner.getSelectedItem().toString();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Name = name.getText().toString();
                Password = password.getText().toString();
                Email = email.getText().toString();
                MobileNo = mobileNo.getText().toString();


                Log.d("Register","Tyoe: " + uType) ;

                Intent ver = new Intent(ctx, Verification.class);
                ver.putExtra("name", Name);
                ver.putExtra("password", Password);
                ver.putExtra("email", Email);
                ver.putExtra("mobileno", MobileNo);
                ver.putExtra("flag", Flag);
                ver.putExtra("userType",uType);
                startActivity(ver);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    /*
        BackGround b = new BackGround();
        b.execute(Name, Password, Email,MobileNo);
    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String name = params[0];
            String password = params[1];
            String email = params[2];
            String phno = params[3];
            String data="";
            int tmp;

            try {
                URL url = new URL("http://blending-jacks.000webhostapp.com/reglog_trial/register.php");
                String urlParams = "name="+name+"&password="+password+"&email="+email+"&phoneno="+phno;

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
            if(s.equals("")){
                s="Registered successfully.Please Login";
                Intent go_back = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(go_back);
            }
            Toast.makeText(ctx, s, Toast.LENGTH_LONG).show();
        }
    }
*/
}