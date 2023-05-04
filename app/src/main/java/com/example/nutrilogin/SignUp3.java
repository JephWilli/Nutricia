package com.example.nutrilogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SignUp3 extends AppCompatActivity {


    Data data = Data.getInstance();

    Button ToSignUp4From3, ToSignUp2From3;
    Spinner spinner1, spinner2;

    //variable that captures objective calories to reach at end of month
    float objectiveCal;

    //Intent intentto3 = getIntent();

    //method to pass to signup screen 4
    public void openSignup4(){
        Intent intentto4 = new Intent(this, SignUp4.class);
        startActivity(intentto4);
    }



    //method to pass to signup screen 2
    public void openSignup2(){
        Intent intent = new Intent(this, SignUp2.class);
        startActivity(intent);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_3);

        ToSignUp4From3 = (Button) findViewById(R.id.toSignUp4From3);
        ToSignUp2From3 = (Button) findViewById(R.id.toSignUp2From3);
        spinner1 = (Spinner) findViewById(R.id.SelectWeightGoal);
        spinner2 = (Spinner) findViewById(R.id.Objective);




        //adapter for the string array in strings.xml
        ArrayAdapter<CharSequence> options = ArrayAdapter.createFromResource(this,
                R.array.WeightGoalOptions, android.R.layout.simple_spinner_item);
        //layout of dropdown menu
        options.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //setting adapter to drop down for options to be visible
        spinner1.setAdapter(options);


        //disabling bottom drop down if "maintain" option is selected
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(position == 1){
                    spinner2.setEnabled(false);
                }else
                    spinner2.setEnabled(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(SignUp3.this, "Please enter your month's objective", Toast.LENGTH_SHORT).show();
            }
        });


        //Objective values
        //adapter for the string array in strings.xml
        ArrayAdapter<CharSequence> options2 = ArrayAdapter.createFromResource(this,
                R.array.ObjectiveValues, android.R.layout.simple_spinner_item);
        //layout of dropdown menu
        options.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //setting adapter to drop down for options to be visible
        spinner2.setAdapter(options2);

        ToSignUp4From3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intentto4 = new Intent(SignUp3.this, SignUp4.class);

                //capturing selected goal
                String SelectedGoal = spinner1.getSelectedItem().toString();
                String Selectedvalue = spinner2.getSelectedItem().toString();



                //store objectives and objective values
                data.setHealthObjective(SelectedGoal);


                //saving goals and objective values;
                //if maintain is selected objective value=0
                switch(SelectedGoal){
                    case "Maintain":
                        data.setObjectiveValue(0);
                        objectiveCal= (float) (data.getBmr()*31);
                        data.setObjectiveCal(objectiveCal);
                        break;
                    default:
                        switch (Selectedvalue) {
                            case "1 Kg":
                                data.setObjectiveValue(1);
                                break;
                            case "2 Kg":
                                data.setObjectiveValue(2);
                                break;
                            case "3 Kg":
                                data.setObjectiveValue(3);
                                break;
                            case "4 Kg":
                                data.setObjectiveValue(4);
                                break;
                        }
                }

                //objective calorie balance for users willing to lose
                switch(SelectedGoal){
                    case "Lose":
                    switch (Selectedvalue){
                        //baseline scaled to a month and St-Jeor values scaled to a month are also deducted
                        case "1 Kg":
                            objectiveCal= (float) ((data.getBmr()*31)-7.8);
                            data.setObjectiveCal(objectiveCal);
                            break;
                        case "2 Kg":
                            objectiveCal= (float) ((data.getBmr()*31)-15.5);
                            data.setObjectiveCal(objectiveCal);
                            break;
                        case "3 Kg":
                            objectiveCal= (float) ((data.getBmr()*31)-23.3);
                            data.setObjectiveCal(objectiveCal);
                            break;
                        case "4 Kg":
                            objectiveCal= (data.getBmr()*31)-31;
                            data.setObjectiveCal(objectiveCal);
                            break;
                    }
                    //objective calorie balance for users willing to gain
                }switch(SelectedGoal){
                    case "Gain":
                    switch (Selectedvalue){
                        //baseline scaled to a month and St-Jeor values scaled to a month are also added
                        case "1 Kg":
                            objectiveCal= (float) ((data.getBmr()*31)+7.8);
                            data.setObjectiveCal(objectiveCal);
                            break;
                        case "2 Kg":
                            objectiveCal= (float) ((data.getBmr()*31)+15.5);
                            data.setObjectiveCal(objectiveCal);
                            break;
                        case "3 Kg":
                            objectiveCal= (float) ((data.getBmr()*31)+23.3);
                            data.setObjectiveCal(objectiveCal);
                            break;
                        case "4 Kg":
                            objectiveCal= (data.getBmr()*31)+31;
                            data.setObjectiveCal(objectiveCal);
                            break;

                    }
                }




                //debug lines for data capture
                //Toast.makeText(SignUp3.this, "selected goal = "+SelectedGoal, Toast.LENGTH_LONG).show();
                /*Toast.makeText(SignUp3.this, "data kept 1 = "+ data.getHealthObjective(), Toast.LENGTH_LONG).show();
                Toast.makeText(SignUp3.this, "data kept 2 = "+ data.getObjectiveValue(), Toast.LENGTH_LONG).show();
                Toast.makeText(SignUp3.this, "Objective Cal = "+ data.getObjectiveCal(), Toast.LENGTH_LONG).show();*/

                openSignup4();
                //startActivity(intentto4);
            }
        });

        ToSignUp2From3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignup2();
            }
        });


    }
}