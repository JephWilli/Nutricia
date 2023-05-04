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

public class MinimalActivity extends AppCompatActivity {
    CalorieBalance CalBal = CalorieBalance.getInstance();
    Data data = Data.getInstance();

    Button deductSitting, deductTV, deductDriving;
    TextView Food, Exercises, Report, sittingValue, tvVlue, drvingValue;

    FirebaseAuth auth;
    FirebaseFirestore db;

    int deductedValue;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minimal);

        deductSitting = (Button) findViewById(R.id.deductJogging);
        deductTV = (Button) findViewById(R.id.deductFootball);
        deductDriving = (Button) findViewById(R.id.deductSwimming);
        Exercises = (TextView) findViewById(R.id.activity_tab);
        Food = (TextView) findViewById(R.id.food_tab);
        sittingValue = (TextView) findViewById(R.id.sittingValue);
        tvVlue = (TextView) findViewById(R.id.tvValue);
        drvingValue = (TextView) findViewById(R.id.drivingValue);
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
                        //Toast.makeText(MinimalActivity.this, "User gender: "+gender, Toast.LENGTH_LONG).show();
                        //Toast.makeText(MinimalActivity.this, "set for class "+data.getGender(), Toast.LENGTH_LONG).show();

                        switch(gender){
                            case("Male"):
                                sittingValue.setText("300");
                                tvVlue.setText("300");
                                drvingValue.setText("400");

                                //deduct value from sitting(male)
                                deductSitting.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        //collect calorie value from database
                                        DocumentReference docRef = db.collection("Active Level").document("Male").collection("Sedentarily Active").document("Sed_Activ");
                                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if(task.isSuccessful()){
                                                    DocumentSnapshot document = task.getResult();
                                                    if (document.exists()){
                                                        Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                                        //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                                        deductedValue =  Integer.parseInt(document.getData().get("Sitting for long periods").toString());
                                                        //Toast.makeText(MinimalActivity.this, "Deducted Value "+deductedValue, Toast.LENGTH_LONG).show();
                                                        CalBal.deductCalBal(deductedValue);
                                                        //Toast.makeText(MinimalActivity.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                                        Toast.makeText(MinimalActivity.this, deductedValue+" deducted from your balance today", Toast.LENGTH_LONG).show();




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

                                //deduct value from watching tv(male)
                                deductTV.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        //collect calorie value from database
                                        DocumentReference docRef = db.collection("Active Level").document("Male").collection("Sedentarily Active").document("Sed_Activ");
                                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if(task.isSuccessful()){
                                                    DocumentSnapshot document = task.getResult();
                                                    if (document.exists()){
                                                        Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                                        //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                                        deductedValue =  Integer.parseInt(document.getData().get("Watching Television").toString());
                                                        //Toast.makeText(MinimalActivity.this, "Deducted Value "+deductedValue, Toast.LENGTH_LONG).show();
                                                        CalBal.deductCalBal(deductedValue);
                                                        //Toast.makeText(MinimalActivity.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                                        Toast.makeText(MinimalActivity.this, deductedValue+" deducted from your balance today", Toast.LENGTH_LONG).show();




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

                                //deduct value from car riding(male)
                                deductDriving.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        //collect calorie value from database
                                        DocumentReference docRef = db.collection("Active Level").document("Male").collection("Sedentarily Active").document("Sed_Activ");
                                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if(task.isSuccessful()){
                                                    DocumentSnapshot document = task.getResult();
                                                    if (document.exists()){
                                                        Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                                        //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                                        deductedValue =  Integer.parseInt(document.getData().get("Riding in a bus or car").toString());
                                                        //Toast.makeText(MinimalActivity.this, "Deducted Value "+deductedValue, Toast.LENGTH_LONG).show();
                                                        CalBal.deductCalBal(deductedValue);
                                                        //Toast.makeText(MinimalActivity.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                                        Toast.makeText(MinimalActivity.this, deductedValue+" deducted from your balance today", Toast.LENGTH_LONG).show();




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
                Intent intent=new Intent(MinimalActivity.this, PhysicalActivity.class);
                startActivity(intent);
            }
        });

        Food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MinimalActivity.this, MainScreen.class);
                startActivity(intent);
            }
        });

        Report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MinimalActivity.this, Report.class);
                startActivity(intent);
            }
        });

        //deduct value from sitting
        deductSitting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //collect calorie value from database
                DocumentReference docRef = db.collection("Active Level").document("Female").collection("Sedentarily Active").document("Sed_active");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                deductedValue =  Integer.parseInt(document.getData().get("Sitting for long periods").toString());
                                //Toast.makeText(MinimalActivity.this, "Deducted Value "+deductedValue, Toast.LENGTH_LONG).show();
                                CalBal.deductCalBal(deductedValue);
                                //Toast.makeText(MinimalActivity.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                Toast.makeText(MinimalActivity.this, deductedValue+" deducted from your balance today", Toast.LENGTH_LONG).show();




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

        //deduct value from car-riding
        deductTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //collect calorie value from database
                DocumentReference docRef = db.collection("Active Level").document("Female").collection("Sedentarily Active").document("Sed_active");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Riding in a bus or car"), Toast.LENGTH_LONG).show();
                                deductedValue =  Integer.parseInt(document.getData().get("Riding in a bus or car").toString());
                                //Toast.makeText(MinimalActivity.this, "Deducted Value "+deductedValue, Toast.LENGTH_LONG).show();
                                CalBal.deductCalBal(deductedValue);
                                //Toast.makeText(MinimalActivity.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                Toast.makeText(MinimalActivity.this, deductedValue+" deducted from your balance today", Toast.LENGTH_LONG).show();


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

        //deduct value from watching TV
        deductTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //collect calorie value from database
                DocumentReference docRef = db.collection("Active Level").document("Female").collection("Sedentarily Active").document("Sed_active");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Watching Television"), Toast.LENGTH_LONG).show();
                                deductedValue =  Integer.parseInt(document.getData().get("Watching Television").toString());
                                //Toast.makeText(MinimalActivity.this, "Deducted Value "+deductedValue, Toast.LENGTH_LONG).show();
                                CalBal.deductCalBal(deductedValue);
                                //Toast.makeText(MinimalActivity.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                Toast.makeText(MinimalActivity.this, deductedValue+" deducted from your balance today", Toast.LENGTH_LONG).show();


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

        //deduct value from Vehicle-riding
        deductDriving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //collect calorie value from database
                DocumentReference docRef = db.collection("Active Level").document("Female").collection("Sedentarily Active").document("Sed_active");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Riding in a bus or car"), Toast.LENGTH_LONG).show();
                                deductedValue =  Integer.parseInt(document.getData().get("Riding in a bus or car").toString());
                                //Toast.makeText(MinimalActivity.this, "Deducted Value "+deductedValue, Toast.LENGTH_LONG).show();
                                CalBal.deductCalBal(deductedValue);
                                //Toast.makeText(MinimalActivity.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                Toast.makeText(MinimalActivity.this, deductedValue+" deducted from your balance today", Toast.LENGTH_LONG).show();


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

