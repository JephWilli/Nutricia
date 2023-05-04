package com.example.nutrilogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Screen1 extends AppCompatActivity {
    //Defining UI elements
    Button Login, Signup;

    //method to pass to login screen
    public void openLogin(){
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }

    //method to pass to signup screen
    public void openSignup(){
        Intent intent = new Intent(this, SignUp1.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen1);

        //Binding XML UI elements to Java//
        Login = (Button) findViewById(R.id.LoginButtonToLoginScreen);
        Signup = (Button) findViewById(R.id.SignUpButton);



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLogin();
            }
        });

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignup();
            }
        });


    }
}