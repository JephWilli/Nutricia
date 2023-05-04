package com.example.nutrilogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp2 extends AppCompatActivity {

    Data data = Data.getInstance();

    float Bmrbaseline;
    EditText Weight, Height;
    Button ToSignUp1From2, ToSignUp3From2;


    //method to pass to signup screen 3
    public void openSignup3(){
        Intent intentto3 = new Intent(this, SignUp3.class);
        startActivity(intentto3);
    }

    //method to pass to signup screen 1
    public void openSignup1(){
        Intent intent = new Intent(this, SignUp1.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_2);

        //Intent intentto2 = getIntent();

        Height = (EditText) findViewById(R.id.editTextHeight);
        Weight = (EditText) findViewById(R.id.editTextWeight);
        ToSignUp1From2 = (Button) findViewById(R.id.toSIgnUp1From2);
        ToSignUp3From2 = (Button) findViewById(R.id.toSignUp3From2);



        ToSignUp3From2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intentto3 = new Intent(SignUp2.this, SignUp3.class);

                float weight  = Float.parseFloat(Weight.getText().toString());
                String weightstr = Weight.getText().toString();
                if(TextUtils.isEmpty(weightstr)){
                    Weight.setError("Please enter your weight");
                    return;
                }

                float height  = Float.parseFloat(Height.getText().toString());
                String heightstr = Weight.getText().toString();
                if(TextUtils.isEmpty(heightstr)){
                    Weight.setError("Please enter your height");
                    return;
                }

                //calculate user basal metabolic ratio
                if(data.getGender()=="Male"){
                    Bmrbaseline = (float) ((((10*weight)+(6.25*height)-(5*data.getAge())+5)*1.2)/1000);
                    data.setBmr(Bmrbaseline);
                }else if(data.getGender()=="Female"){
                    Bmrbaseline = (float) ((((10*weight)+(6.25*height)-(5*data.getAge())-161)*1.2)/1000);
                    data.setBmr(Bmrbaseline);
                }

                data.setWeight(weight);
                data.setHeight(height);

                //debug lines for data capture
                /*Toast.makeText(SignUp2.this, "data kept 1 = "+ data.getWeight(), Toast.LENGTH_LONG).show();
                Toast.makeText(SignUp2.this, "data kept 2 = "+ data.getHeight(), Toast.LENGTH_LONG).show();
                Toast.makeText(SignUp2.this, "bmr = "+ data.getBmr(), Toast.LENGTH_LONG).show();*/

                /*intentto3.putExtra("weight", weight);
                intentto3.putExtra("height", height);*/

                //startActivity(intentto3);
                openSignup3();
            }
        });

        ToSignUp1From2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignup1();
            }
        });
    }

}