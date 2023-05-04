package com.example.nutrilogin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class SignUp1 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    //instance of data class to store data
    Data data = Data.getInstance();


    TextView datetext;
    EditText Name;
    Button ToSignUp2From1, dobbtn;
    RadioGroup Gender;
    RadioButton Male, Female;
    int age = 0;


    public void openSignup2(){
        Intent intentto2 = new Intent(this, SignUp2.class);
        startActivity(intentto2);
    }

    //method to calculate age
    private int getAge(int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        Integer ageInt = new Integer(age);

        return ageInt;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_1);

        Name = (EditText) findViewById(R.id.editTextName);
        Male = (RadioButton) findViewById(R.id.MaleButton);
        Female = (RadioButton) findViewById(R.id.FemaleButton);
        Gender = (RadioGroup) findViewById(R.id.radioGroupGender);
        ToSignUp2From1 = (Button) findViewById(R.id.toSignUp2From1);
        dobbtn = (Button)  findViewById(R.id.dobbtn);
        datetext = findViewById(R.id.datetext);


        Gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //get radiobutton that was checked
                RadioButton checkedRadioButton = (RadioButton) Gender.findViewById(checkedId);
                //true/false placed in the variable
                boolean isChecked = checkedRadioButton.isChecked();
                //execute action if checked
                if(isChecked){
                    //store checked gender in singleton class
                    switch (checkedId){
                        case R.id.MaleButton:
                            data.setGender("Male");
                            break;
                        case R.id.FemaleButton:
                            data.setGender("Female");
                            break;

                    }
                }
            }
        });

        dobbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datePicker();

            }
        });






        ToSignUp2From1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //capture user data
                String name = Name.getText().toString();
                if(TextUtils.isEmpty(name)){
                    Name.setError("Please enter your name");
                    return;
                }



                //store data in singleton class
                data.setName(name);





                //debug lines for data capture
                /*Toast.makeText(SignUp1.this, "data kept 1 = "+ data.getName(), Toast.LENGTH_LONG).show();
                Toast.makeText(SignUp1.this, "data kept 2 = "+ data.getGender(), Toast.LENGTH_LONG).show();
                Toast.makeText(SignUp1.this, "data kept 3 = "+ data.getAge(), Toast.LENGTH_LONG).show();*/


                //pass to following signup screen
                openSignup2();
            }
        });
    }
    //method to display date selection dialogue
    public void datePicker(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();


    }

    //capturing date selected
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = +year+"/"+(month+1)+"/"+dayOfMonth;
        datetext.setText(date);


        //get age from date selected
        age =getAge(year,month,dayOfMonth);
        data.setAge(age);

    }
}