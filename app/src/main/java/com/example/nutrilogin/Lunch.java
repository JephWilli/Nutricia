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
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Lunch extends AppCompatActivity {
    CalorieBalance CalBal = CalorieBalance.getInstance();
    Data data = Data.getInstance();

    FirebaseFirestore db;

    int addedValue;

    Button addJollof, addFufuandSoup, addBankuandTilapia, addTilapia, addWaakye, addPalawaSauce, addBoiledYam, addYamPorridge, addBoiledPLantainandstew;
    TextView Food, Exercises, Report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lunch);

        addJollof = (Button) findViewById(R.id.add_jollof_rice);
        addFufuandSoup = (Button) findViewById(R.id.add_fufu_and_soup);
        addBankuandTilapia = (Button) findViewById(R.id.add_banku);
        addTilapia = (Button) findViewById(R.id.add_tilapia);
        addPalawaSauce = (Button) findViewById(R.id.add_palawa_sauce);
        addBoiledYam = (Button) findViewById(R.id.add_boiled_yam);
        addYamPorridge = (Button) findViewById(R.id.add_yam_pottage);
        addWaakye = (Button) findViewById(R.id.add_waakye);
        addBoiledPLantainandstew = (Button) findViewById(R.id.add_boiled_plantain);
        Report = (TextView) findViewById(R.id.report_tab);

        Exercises = (TextView) findViewById(R.id.activity_tab);
        Food = (TextView) findViewById(R.id.food_tab);

        db = FirebaseFirestore.getInstance();

        Exercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Lunch.this, PhysicalActivity.class);
                startActivity(intent);
            }
        });

        Food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Lunch.this, MainScreen.class);
                startActivity(intent);
            }
        });

        Report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Lunch.this, Report.class);
                startActivity(intent);
            }
        });

        //add value from jollof
        addJollof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //collect calorie value from database
                DocumentReference docRef = db.collection("Food Intake").document("Lunch");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                addedValue =  Integer.parseInt(document.getData().get("Jollof rice & Chicken").toString());
                                //Toast.makeText(Lunch.this, "Added Value "+addedValue, Toast.LENGTH_LONG).show();
                                CalBal.addCalBal(addedValue);
                                //Toast.makeText(Lunch.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                Toast.makeText(Lunch.this, addedValue+" added to your balance today", Toast.LENGTH_LONG).show();




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


        //add value from palava sauce
        addPalawaSauce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //collect calorie value from database
                DocumentReference docRef = db.collection("Food Intake").document("Lunch");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                addedValue =  Integer.parseInt(document.getData().get("Palava sauce").toString());
                                //Toast.makeText(Lunch.this, "Added Value "+addedValue, Toast.LENGTH_LONG).show();
                                CalBal.addCalBal(addedValue);
                                //Toast.makeText(Lunch.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                Toast.makeText(Lunch.this, addedValue+" added to your balance today", Toast.LENGTH_LONG).show();




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

        //add value from tilapia
        addTilapia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //collect calorie value from database
                DocumentReference docRef = db.collection("Food Intake").document("Lunch");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                addedValue =  Integer.parseInt(document.getData().get("Tilapia").toString());
                                //Toast.makeText(Lunch.this, "Added Value "+addedValue, Toast.LENGTH_LONG).show();
                                CalBal.addCalBal(addedValue);
                                //Toast.makeText(Lunch.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                Toast.makeText(Lunch.this, addedValue+" added to your balance today", Toast.LENGTH_LONG).show();




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

        //add value from waakye
        addWaakye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //collect calorie value from database
                DocumentReference docRef = db.collection("Food Intake").document("Lunch");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                addedValue =  Integer.parseInt(document.getData().get("Waakye").toString());
                                //Toast.makeText(Lunch.this, "Added Value "+addedValue, Toast.LENGTH_LONG).show();
                                CalBal.addCalBal(addedValue);
                                //Toast.makeText(Lunch.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                Toast.makeText(Lunch.this, addedValue+" added to your balance today", Toast.LENGTH_LONG).show();




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

        //add value from banku&tilapia
        addBankuandTilapia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //collect calorie value from database
                DocumentReference docRef = db.collection("Food Intake").document("Lunch");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                addedValue =  Integer.parseInt(document.getData().get("banku & tilapia").toString());
                                //Toast.makeText(Lunch.this, "Added Value "+addedValue, Toast.LENGTH_LONG).show();
                                CalBal.addCalBal(addedValue);
                                //Toast.makeText(Lunch.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                Toast.makeText(Lunch.this, addedValue+" added to your balance today", Toast.LENGTH_LONG).show();




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

        //add value from boiled plantain & egg stew
        addBoiledPLantainandstew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //collect calorie value from database
                DocumentReference docRef = db.collection("Food Intake").document("Lunch");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                addedValue =  Integer.parseInt(document.getData().get("boiled plantain & egg stew").toString());
                                //Toast.makeText(Lunch.this, "Added Value "+addedValue, Toast.LENGTH_LONG).show();
                                CalBal.addCalBal(addedValue);
                                //Toast.makeText(Lunch.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                Toast.makeText(Lunch.this, addedValue+" added to your balance today", Toast.LENGTH_LONG).show();




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

        //add value from fufu and soup
        addFufuandSoup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //collect calorie value from database
                DocumentReference docRef = db.collection("Food Intake").document("Lunch");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                addedValue =  Integer.parseInt(document.getData().get("fufu and soup").toString());
                                //Toast.makeText(Lunch.this, "Added Value "+addedValue, Toast.LENGTH_LONG).show();
                                CalBal.addCalBal(addedValue);
                                //Toast.makeText(Lunch.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                Toast.makeText(Lunch.this, addedValue+" added to your balance today", Toast.LENGTH_LONG).show();




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

        //add value from yam porridge
        addYamPorridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //collect calorie value from database
                DocumentReference docRef = db.collection("Food Intake").document("Lunch");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                addedValue =  Integer.parseInt(document.getData().get("yam porridge").toString());
                                //Toast.makeText(Lunch.this, "Added Value "+addedValue, Toast.LENGTH_LONG).show();
                                CalBal.addCalBal(addedValue);
                                //Toast.makeText(Lunch.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                Toast.makeText(Lunch.this, addedValue+" added to your balance today", Toast.LENGTH_LONG).show();




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

        //add value from boiled yam
        addBoiledYam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //collect calorie value from database
                DocumentReference docRef = db.collection("Food Intake").document("Lunch");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                addedValue =  Integer.parseInt(document.getData().get("Boiled yam").toString());
                                //Toast.makeText(Lunch.this, "Added Value "+addedValue, Toast.LENGTH_LONG).show();
                                CalBal.addCalBal(addedValue);
                                //Toast.makeText(Lunch.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                Toast.makeText(Lunch.this, addedValue+" added to your balance today", Toast.LENGTH_LONG).show();




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