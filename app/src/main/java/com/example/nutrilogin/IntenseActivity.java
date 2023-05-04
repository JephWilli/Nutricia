package com.example.nutrilogin;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class IntenseActivity extends AppCompatActivity {
    CalorieBalance CalBal = CalorieBalance.getInstance();
    Data data = Data.getInstance();

    Button deductJogging, deductFootball, deductSwimming;
    TextView Food, Exercises, Report, joggingValue, footballValue, swimmingValue;

    FirebaseFirestore db;
    FirebaseAuth auth;

    int deductedValue;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intense);

        deductJogging = (Button) findViewById(R.id.deductJogging);
        deductFootball = (Button) findViewById(R.id.deductFootball);
        deductSwimming = (Button) findViewById(R.id.deductSwimming);
        Exercises = (TextView) findViewById(R.id.activity_tab);
        Food = (TextView) findViewById(R.id.food_tab);
        joggingValue = (TextView) findViewById(R.id.joggingValue);
        footballValue = (TextView) findViewById(R.id.footballValue);
        swimmingValue = (TextView) findViewById(R.id.swimmingValue);
        Report = (TextView) findViewById(R.id.report_tab);

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();


        //get current user ID
        String userId = auth.getCurrentUser().getUid();
        DocumentReference docref = db.collection("User Details").document(userId);



        //retrieve user gender
        docref.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        gender =  document.getData().get("Gender").toString();
                        data.setGender(gender);
                        //Toast.makeText(IntenseActivity.this, "User gender: "+gender, Toast.LENGTH_LONG).show();
                        //Toast.makeText(IntenseActivity.this, "set for class "+data.getGender(), Toast.LENGTH_LONG).show();

                        switch(gender){
                            case("Male"):
                                joggingValue.setText("700");
                                footballValue.setText("730");
                                swimmingValue.setText("710");

                                //deduct value from jogging(male)
                                deductJogging.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        //collect calorie value from database
                                        DocumentReference docRef = db.collection("Active Level").document("Male").collection("Intensely Active").document("Int_Activ");
                                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if(task.isSuccessful()){
                                                    DocumentSnapshot document = task.getResult();
                                                    if (document.exists()){
                                                        Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                                        //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                                        deductedValue =  Integer.parseInt(document.getData().get("Jogging").toString());
                                                        //Toast.makeText(IntenseActivity.this, "Deducted Value "+deductedValue, Toast.LENGTH_LONG).show();
                                                        CalBal.deductCalBal(deductedValue);
                                                        //Toast.makeText(IntenseActivity.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                                        Toast.makeText(IntenseActivity.this, deductedValue+" deducted from your balance today", Toast.LENGTH_LONG).show();




                                                    }else{
                                                        Log.d(TAG, "No such document");
                                                    }
                                                }else{
                                                    Log.d(TAG, "get failed with ", task.getException());
                                                }
                                            }
                                        });

                                    }
                                });

                                //deduct value from playing football(male)
                                deductFootball.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        //collect calorie value from database
                                        DocumentReference docRef = db.collection("Active Level").document("Male").collection("Intensely Active").document("Int_Activ");
                                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if(task.isSuccessful()){
                                                    DocumentSnapshot document = task.getResult();
                                                    if (document.exists()){
                                                        Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                                        //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                                        deductedValue =  Integer.parseInt(document.getData().get("Playing football").toString());
                                                        //Toast.makeText(IntenseActivity.this, "Deducted Value "+deductedValue, Toast.LENGTH_LONG).show();
                                                        CalBal.deductCalBal(deductedValue);
                                                        //Toast.makeText(IntenseActivity.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                                        Toast.makeText(IntenseActivity.this, deductedValue+" deducted from your balance today", Toast.LENGTH_LONG).show();




                                                    }else{
                                                        Log.d(TAG, "No such document");
                                                    }
                                                }else{
                                                    Log.d(TAG, "get failed with ", task.getException());
                                                }
                                            }
                                        });

                                    }
                                });

                                //deduct value from swimming(male)
                                deductSwimming.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        //collect calorie value from database
                                        DocumentReference docRef = db.collection("Active Level").document("Male").collection("Intensely Active").document("Int_Activ");
                                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if(task.isSuccessful()){
                                                    DocumentSnapshot document = task.getResult();
                                                    if (document.exists()){
                                                        Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                                        //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                                        deductedValue =  Integer.parseInt(document.getData().get("Swimming").toString());
                                                        //Toast.makeText(IntenseActivity.this, "Deducted Value "+deductedValue, Toast.LENGTH_LONG).show();
                                                        CalBal.deductCalBal(deductedValue);
                                                        //Toast.makeText(IntenseActivity.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                                        Toast.makeText(IntenseActivity.this, deductedValue+" deducted from your balance today", Toast.LENGTH_LONG).show();




                                                    }else{
                                                        Log.d(TAG, "No such document");
                                                    }
                                                }else{
                                                    Log.d(TAG, "get failed with ", task.getException());
                                                }
                                            }
                                        });

                                    }
                                });

                                break;

                        }

                    }else{
                        Log.d(TAG, "No such document");
                    }
                }else{
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        Exercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(IntenseActivity.this, PhysicalActivity.class);
                startActivity(intent);
            }
        });

        Food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(IntenseActivity.this, MainScreen.class);
                startActivity(intent);
            }
        });

        //deduct value from jogging
        deductJogging.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //collect calorie value from database
                DocumentReference docRef = db.collection("Active Level").document("Female").collection("Intensely Active").document("Int_activ");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                deductedValue =  Integer.parseInt(document.getData().get("Jogging").toString());
                                //Toast.makeText(IntenseActivity.this, "Deducted Value "+deductedValue, Toast.LENGTH_LONG).show();
                                CalBal.deductCalBal(deductedValue);
                                //Toast.makeText(IntenseActivity.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                Toast.makeText(IntenseActivity.this, deductedValue+" deducted from your balance today", Toast.LENGTH_LONG).show();




                            }else{
                                Log.d(TAG, "No such document");
                            }
                        }else{
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });

            }
        });

        //deduct value from football
        deductFootball.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //collect calorie value from database
                DocumentReference docRef = db.collection("Active Level").document("Female").collection("Intensely Active").document("Int_activ");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                deductedValue =  Integer.parseInt(document.getData().get("Football").toString());
                                //Toast.makeText(IntenseActivity.this, "Deducted Value "+deductedValue, Toast.LENGTH_LONG).show();
                                CalBal.deductCalBal(deductedValue);
                                //Toast.makeText(IntenseActivity.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                Toast.makeText(IntenseActivity.this, deductedValue+" deducted from your balance today", Toast.LENGTH_LONG).show();




                            }else{
                                Log.d(TAG, "No such document");
                            }
                        }else{
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });

            }
        });


        //deduct value from swimming
        deductSwimming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //collect calorie value from database
                DocumentReference docRef = db.collection("Active Level").document("Female").collection("Intensely Active").document("Int_activ");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                deductedValue =  Integer.parseInt(document.getData().get("Swimming").toString());
                                //Toast.makeText(IntenseActivity.this, "Deducted Value "+deductedValue, Toast.LENGTH_LONG).show();
                                CalBal.deductCalBal(deductedValue);
                                //Toast.makeText(IntenseActivity.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                Toast.makeText(IntenseActivity.this, deductedValue+" deducted from your balance today", Toast.LENGTH_LONG).show();




                            }else{
                                Log.d(TAG, "No such document");
                            }
                        }else{
                            Log.d(TAG, "get failed with ", task.getException());
                        }
                    }
                });

            }
        });


    }


}