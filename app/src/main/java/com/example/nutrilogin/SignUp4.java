package com.example.nutrilogin;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp4 extends AppCompatActivity {

    //String data;
    //Defining UI Elements
    EditText EmailSignup, PasswordSignup ;
    Button FinishSignup;
    FirebaseAuth fireAuth;
    FirebaseFirestore db;
    String userID;

    //Intent intentto4 = getIntent();


    Data data = Data.getInstance();

    public void toMainScreen(){
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_4);


        //Binding UI XML Elements to Java
        EmailSignup = (EditText) findViewById(R.id.editTextEmailSignup);
        PasswordSignup = (EditText) findViewById(R.id.editTextPasswordSignup);
        FinishSignup = (Button) findViewById(R.id.finishSignUp);


        fireAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        String userName = data.getName();
        String userGender = data.getGender();
        int userAge = data.getAge();
        String userHealthObjective = data.getHealthObjective();
        int userObjectiveValue = data.getObjectiveValue();
        float userWeight = data.getWeight();
        float userHeight = data.getHeight();
        float userCalorieGoal = data.getObjectiveCal();
        //String userEmail = data.getEmail();

        //debug to see if data was captured
        /*Toast.makeText(SignUp4.this, "Name: "+userName, Toast.LENGTH_SHORT).show();
        Toast.makeText(SignUp4.this, "Gender: "+userGender, Toast.LENGTH_SHORT).show();
        Toast.makeText(SignUp4.this, "Age: "+userAge, Toast.LENGTH_SHORT).show();
        Toast.makeText(SignUp4.this, "Weight: "+userWeight, Toast.LENGTH_SHORT).show();
        Toast.makeText(SignUp4.this, "Height: "+userHeight, Toast.LENGTH_SHORT).show();
        Toast.makeText(SignUp4.this, "Health Objective: "+userHealthObjective, Toast.LENGTH_SHORT).show();
        Toast.makeText(SignUp4.this, "Objective Value: "+userObjectiveValue, Toast.LENGTH_SHORT).show();
        Toast.makeText(SignUp4.this, "Calories to reach: "+userCalorieGoal, Toast.LENGTH_SHORT).show();*/






        FinishSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = EmailSignup.getText().toString().trim();
                data.setEmail(email);
                String password = PasswordSignup.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    EmailSignup.setError("Please enter your email");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    PasswordSignup.setError("Please enter your password");
                    return;
                }
                //checking password length
                if(password.length() < 5){
                    PasswordSignup.setError("Password must be 5 characters or more");
                    return;
                }
                //registering user details to firebase
                fireAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(SignUp4.this, "Welcome!", Toast.LENGTH_SHORT).show();
                            userID = fireAuth.getCurrentUser().getUid();
                            //creating a record (document) in user details collection (table) with user ID as identifier
                            DocumentReference documentReference = db.collection("User Details").document(userID);

                            //Hash-map stores user fields passed from previous activities
                            Map<String, Object> user = new HashMap<>();
                            user.put("Name", userName);
                            user.put("Age", userAge);
                            user.put("Gender", userGender);
                            user.put("Height", userHeight);
                            user.put("Weight", userWeight);
                            user.put("Health Objective", userHealthObjective);
                            user.put("Objective Value", userObjectiveValue);
                            user.put("Calories to gain", userCalorieGoal);



                            //checking if log-in was successful
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG, "onSuccess: Data stored "+userID);

                                }
                            });

                            //Link to Main screen here
                            toMainScreen();
                        }else{
                            Toast.makeText(SignUp4.this, "Error!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }



        });

    }
}