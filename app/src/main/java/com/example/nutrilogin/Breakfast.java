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

public class Breakfast extends AppCompatActivity {
    CalorieBalance CalBal = CalorieBalance.getInstance();
    Data data = Data.getInstance();

    FirebaseFirestore db;

    int addedValue;

    Button addOatmeal, addCornMeal, addTomBrown, addTea, addHausaCocoa, addKenkey, addWaakye, addPancakes, addEggs, addBanku;
    TextView Food, Exercises, Report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast);

        addOatmeal = (Button) findViewById(R.id.add_oat_meal);
        addCornMeal = (Button) findViewById(R.id.add_corn_meal);
        addTomBrown = (Button) findViewById(R.id.add_tom_brown);
        addTea = (Button) findViewById(R.id.add_tea);
        addTea = (Button) findViewById(R.id.add_tea);
        addHausaCocoa = (Button) findViewById(R.id.add_hausa_cocoa);
        addKenkey = (Button) findViewById(R.id.add_kenkey);
        addWaakye = (Button) findViewById(R.id.add_waakye);
        addPancakes = (Button) findViewById(R.id.add_pancake);
        addEggs = (Button) findViewById(R.id.add_eggs);
        addBanku = (Button) findViewById(R.id.add_banku);
        Exercises = (TextView) findViewById(R.id.activity_tab);
        Food = (TextView) findViewById(R.id.food_tab);
        Report = (TextView) findViewById(R.id.report_tab);

        db = FirebaseFirestore.getInstance();


        Exercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Breakfast.this, PhysicalActivity.class);
                startActivity(intent);
            }
        });

        Food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Breakfast.this, MainScreen.class);
                startActivity(intent);
            }
        });

        Report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Breakfast.this, Report.class);
                startActivity(intent);
            }
        });

        //add value from oatmeal
        addOatmeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //collect calorie value from database
                DocumentReference docRef = db.collection("Food Intake").document("Breakfast");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                addedValue =  Integer.parseInt(document.getData().get("Oatmeal").toString());
                                //Toast.makeText(Breakfast.this, "Added Value "+addedValue, Toast.LENGTH_LONG).show();
                                CalBal.addCalBal(addedValue);
                                //Toast.makeText(Breakfast.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                Toast.makeText(Breakfast.this, addedValue+" added to your balance today", Toast.LENGTH_LONG).show();




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

        //add value from kenkey
        addKenkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //collect calorie value from database
                DocumentReference docRef = db.collection("Food Intake").document("Breakfast");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                addedValue =  Integer.parseInt(document.getData().get("kenkey").toString());
                                //Toast.makeText(Breakfast.this, "Added Value "+addedValue, Toast.LENGTH_LONG).show();
                                CalBal.addCalBal(addedValue);
                                //Toast.makeText(Breakfast.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                Toast.makeText(Breakfast.this, addedValue+" added to your balance today", Toast.LENGTH_LONG).show();




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

        //add value from cornmeal
        addCornMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //collect calorie value from database
                DocumentReference docRef = db.collection("Food Intake").document("Breakfast");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                addedValue =  Integer.parseInt(document.getData().get("Cornmeal porridge").toString());
                                //Toast.makeText(Breakfast.this, "Added Value "+addedValue, Toast.LENGTH_LONG).show();
                                CalBal.addCalBal(addedValue);
                                //Toast.makeText(Breakfast.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                Toast.makeText(Breakfast.this, addedValue+" added to your balance today", Toast.LENGTH_LONG).show();




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

        //add value from Tombrown
        addTomBrown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //collect calorie value from database
                DocumentReference docRef = db.collection("Food Intake").document("Breakfast");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                addedValue =  Integer.parseInt(document.getData().get("Tom brown").toString());
                                //Toast.makeText(Breakfast.this, "Added Value "+addedValue, Toast.LENGTH_LONG).show();
                                CalBal.addCalBal(addedValue);
                                //Toast.makeText(Breakfast.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                Toast.makeText(Breakfast.this, addedValue+" added to your balance today", Toast.LENGTH_LONG).show();




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

        //add value from Tea
        addTea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //collect calorie value from database
                DocumentReference docRef = db.collection("Food Intake").document("Breakfast");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                addedValue =  Integer.parseInt(document.getData().get("Tea").toString());
                                //Toast.makeText(Breakfast.this, "Added Value "+addedValue, Toast.LENGTH_LONG).show();
                                CalBal.addCalBal(addedValue);
                                //Toast.makeText(Breakfast.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                Toast.makeText(Breakfast.this, addedValue+" added to your balance today", Toast.LENGTH_LONG).show();




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

        //add value from kooko
        addHausaCocoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //collect calorie value from database
                DocumentReference docRef = db.collection("Food Intake").document("Breakfast");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                addedValue =  Integer.parseInt(document.getData().get("hausa koko").toString());
                                Toast.makeText(Breakfast.this, "Added Value "+addedValue, Toast.LENGTH_LONG).show();
                                CalBal.addCalBal(addedValue);
                                //Toast.makeText(Breakfast.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                Toast.makeText(Breakfast.this, addedValue+" added to your balance today", Toast.LENGTH_LONG).show();




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
                DocumentReference docRef = db.collection("Food Intake").document("Breakfast");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                addedValue =  Integer.parseInt(document.getData().get("waakye").toString());
                                //Toast.makeText(Breakfast.this, "Added Value "+addedValue, Toast.LENGTH_LONG).show();
                                CalBal.addCalBal(addedValue);
                                //Toast.makeText(Breakfast.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                Toast.makeText(Breakfast.this, addedValue+" added to your balance today", Toast.LENGTH_LONG).show();




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

        //add value from pancake
        addPancakes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //collect calorie value from database
                DocumentReference docRef = db.collection("Food Intake").document("Breakfast");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                addedValue =  Integer.parseInt(document.getData().get("Pancakes").toString());
                                //Toast.makeText(Breakfast.this, "Added Value "+addedValue, Toast.LENGTH_LONG).show();
                                CalBal.addCalBal(addedValue);
                                //Toast.makeText(Breakfast.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                Toast.makeText(Breakfast.this, addedValue+" added to your balance today", Toast.LENGTH_LONG).show();




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

        //add value from eggs
        addEggs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //collect calorie value from database
                DocumentReference docRef = db.collection("Food Intake").document("Breakfast");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                addedValue =  Integer.parseInt(document.getData().get("Eggs").toString());
                                //Toast.makeText(Breakfast.this, "Added Value "+addedValue, Toast.LENGTH_LONG).show();
                                CalBal.addCalBal(addedValue);
                                //Toast.makeText(Breakfast.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                Toast.makeText(Breakfast.this, addedValue+" added to your balance today", Toast.LENGTH_LONG).show();




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

        //add value from baanku
        addBanku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //collect calorie value from database
                DocumentReference docRef = db.collection("Food Intake").document("Breakfast");
                docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if (document.exists()){
                                Log.d(TAG, "DocumentSnapshot data: "+document.getData());
                                //Toast.makeText(MinimalActivity.this, "Calorie read "+document.getData().get("Sitting for long periods"), Toast.LENGTH_LONG).show();
                                addedValue =  Integer.parseInt(document.getData().get("Banku").toString());
                                //Toast.makeText(Breakfast.this, "Added Value "+addedValue, Toast.LENGTH_LONG).show();
                                CalBal.addCalBal(addedValue);
                                //Toast.makeText(Breakfast.this, "new calbal= "+CalBal.getCalBal(), Toast.LENGTH_LONG).show();
                                Toast.makeText(Breakfast.this, addedValue+" added to your balance today", Toast.LENGTH_LONG).show();




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