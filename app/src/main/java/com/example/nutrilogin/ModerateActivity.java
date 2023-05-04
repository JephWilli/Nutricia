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

public class ModerateActivity extends AppCompatActivity {
    CalorieBalance CalBal = CalorieBalance.getInstance();
    Data data = Data.getInstance();

    Button deductCycling, deductDancing, deductBriskWalking;
    TextView Food, Exercises, Report, cyclingValue, dancingValue, briskwalkingValue;

    FirebaseAuth auth;
    FirebaseFirestore db;

    int deductedValue;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moderate);

        deductCycling = (Button) findViewById(R.id.deductJogging);
        deductDancing = (Button) findViewById(R.id.deductFootball);
        deductBriskWalking = (Button) findViewById(R.id.deductSwimming);
        Exercises = (TextView) findViewById(R.id.activity_tab);
        Food = (TextView) findViewById(R.id.food_tab);
        cyclingValue = (TextView) findViewById(R.id.cyclingValue);
        dancingValue = (TextView) findViewById(R.id.dancingValue);
        briskwalkingValue = (TextView) findViewById(R.id.briskWalking);
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
                        //Toast.makeText(ModerateActivity.this, "User gender: "+gender, Toast.LENGTH_LONG).show();
                        //Toast.makeText(ModerateActivity.this, "set for class "+data.getGender(), Toast.LENGTH_LONG).show();

                        switch(gender){
                            case("Male"):
                                cyclingValue.setText("560");

                                //deduct value from cycling(male)
                                deductCycling.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        //collect calorie value from database
                                        DocumentReference docRef = db.collection("Active Level").document("Male").collection("Moderately Active").document("Mod_Activ");
                                        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                if(task.isSuccessful()){
                                                    DocumentSnapshot document = task.getResult();
                                                    if (document.exists()){
                                                        Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                                        //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                                        deductedValue =  Integer.parseInt(document.getData().get("Cycling").toString());
                                                        //Toast.makeText(ModerateActivity.this, "Deducted Value "+deductedValue, Toast.LENGTH_LONG).show();
                                                        CalBal.deductCalBal(deductedValue);
                                                        //Toast.makeText(ModerateActivity.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                                        Toast.makeText(ModerateActivity.this, deductedValue+" deducted from your balance today", Toast.LENGTH_LONG).show();




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
                Intent intent=new Intent(ModerateActivity.this, PhysicalActivity.class);
                startActivity(intent);
            }
        });

        Food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ModerateActivity.this, MainScreen.class);
                startActivity(intent);
            }
        });

        Report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ModerateActivity.this, Report.class);
                startActivity(intent);
            }
        });

        //deduct value from cycling
        deductCycling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //collect calorie value from database
                DocumentReference docRef = db.collection("Active Level").document("Female").collection("Moderately Active").document("Mod_active");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                deductedValue =  Integer.parseInt(document.getData().get("Cycling").toString());
                                //Toast.makeText(ModerateActivity.this, "Deducted Value "+deductedValue, Toast.LENGTH_LONG).show();
                                CalBal.deductCalBal(deductedValue);
                                //Toast.makeText(ModerateActivity.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                Toast.makeText(ModerateActivity.this, deductedValue+" deducted from your balance today", Toast.LENGTH_LONG).show();




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


        //deduct value from dancing
        deductDancing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //collect calorie value from database
                DocumentReference docRef = db.collection("Active Level").document("Female").collection("Moderately Active").document("Mod_active");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                deductedValue =  Integer.parseInt(document.getData().get("Dancing").toString());
                                //Toast.makeText(ModerateActivity.this, "Deducted Value "+deductedValue, Toast.LENGTH_LONG).show();
                                CalBal.deductCalBal(deductedValue);
                                //Toast.makeText(ModerateActivity.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                Toast.makeText(ModerateActivity.this, deductedValue+" deducted from your balance today", Toast.LENGTH_LONG).show();


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

        //deduct value from brisk walking
        deductBriskWalking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //collect calorie value from database
                DocumentReference docRef = db.collection("Active Level").document("Female").collection("Moderately Active").document("Mod_active");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                deductedValue =  Integer.parseInt(document.getData().get("Walking briskly").toString());
                                //Toast.makeText(ModerateActivity.this, "Deducted Value "+deductedValue, Toast.LENGTH_LONG).show();
                                CalBal.deductCalBal(deductedValue);
                                //Toast.makeText(ModerateActivity.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                Toast.makeText(ModerateActivity.this, deductedValue+" deducted from your balance today", Toast.LENGTH_LONG).show();


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