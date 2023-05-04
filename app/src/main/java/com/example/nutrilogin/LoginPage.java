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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginPage extends AppCompatActivity {
    Data data = Data.getInstance();
    FirebaseFirestore db;


    //UI Elements
    EditText EmailfromLogin, PasswordfromLogin;
    Button LoginButton;
    FirebaseAuth fireAuth;
    Float objective;


    public void toMainScreen(){
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        //Binding XML associates to Java
        EmailfromLogin = (EditText) findViewById(R.id.editTextEmailLogin);
        PasswordfromLogin = (EditText) findViewById(R.id.editTextPasswordLogin);
        LoginButton = (Button) findViewById(R.id.LoginButton);
        //firebase linker; acquires instance of firebase db
        fireAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();






        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    String email = EmailfromLogin.getText().toString().trim();
                    String password = PasswordfromLogin.getText().toString().trim();

                    if(TextUtils.isEmpty(email)){
                        EmailfromLogin.setError("Please enter your email");
                        return;
                    }
                    if(TextUtils.isEmpty(password)){
                        PasswordfromLogin.setError("Please enter your password");
                        return;
                    }
                    //checking password length
                    if(password.length() < 5){
                        PasswordfromLogin.setError("Password must be 5 characters or more");
                        return;
                    }

                    //authenticating the user
                    fireAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(LoginPage.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();


                                String userId = fireAuth.getCurrentUser().getUid();
                                DocumentReference docref = db.collection("User Details").document(userId);

                                //retrieve user goal to reach
                                docref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                        if(task.isSuccessful()){
                                            DocumentSnapshot document = task.getResult();
                                            if(document.exists()){
                                                objective =  Float.parseFloat(document.getData().get("Calories to gain").toString());
                                                data.setObjectiveCal(objective);
                                                Toast.makeText(LoginPage.this, "Objective from db "+objective, Toast.LENGTH_LONG).show();
                                                Toast.makeText(LoginPage.this, "Objective sent to dataclass "+data.getObjectiveCal(), Toast.LENGTH_LONG).show();

                                            }else{
                                                Log.d(TAG, "No such document");
                                            }
                                        }else{
                                            Log.d(TAG, "get failed with ", task.getException());
                                        }
                                    }
                                });

                                //Direct to main screen after login
                                toMainScreen();

                            }else{
                                Toast.makeText(LoginPage.this, "Log-in failed! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                /*//get current user ID
                String userId = fireAuth.getCurrentUser().getUid();
                DocumentReference docref = db.collection("User Details").document(userId);

                //retrieve user goal to reach
                docref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()){
                                objective =  Float.parseFloat(document.getData().get("Calories to gain").toString());
                                data.setObjectiveCal(objective);
                                Toast.makeText(LoginPage.this, "Objective from db "+objective, Toast.LENGTH_LONG).show();

                            }else{
                                Log.d(TAG, "No such document");
                            }
                        }else{
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });*/


                }





            });



}
}
