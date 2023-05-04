package com.example.nutrilogin;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class PhysicalActivity extends AppCompatActivity {
    Data data = Data.getInstance();
    CalorieBalance CalBal = CalorieBalance.getInstance();

    TextView Food, Report, CalTag, toObjectiveTag, Exercises, nameTag;
    float toObjective, objective;
    Button AddMinimal, AddModerate, AddIntense;

    //authentication and authentication objects
    FirebaseFirestore db;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical);


        nameTag = (TextView) findViewById(R.id.nameTag);
        toObjectiveTag = (TextView) findViewById(R.id.toObjectiveTag);
        Exercises = (TextView) findViewById(R.id.activity_tab);
        toObjectiveTag = (TextView) findViewById(R.id.toObjectiveTag);
        CalTag = (TextView) findViewById((R.id.calTag));
        Food =(TextView) findViewById(R.id. food_tab);
        Report =(TextView) findViewById(R.id. report_tab);
        AddMinimal =(Button) findViewById(R.id. add_minimal);
        AddModerate =(Button) findViewById(R.id. add_moderate);
        AddIntense =(Button) findViewById(R.id. add_intense);


        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        //get current user ID
        String userId = auth.getCurrentUser().getUid();
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

                        //Calories to gain to reach objective are given by
                        toObjective= data.getObjectiveCal()-CalBal.getCalBal();
                        //display calories to objective
                        toObjectiveTag.setText(new StringBuilder().append(toObjective).append(" Kcal to goal").toString());
                    }else{
                        Log.d(TAG, "No such document");
                    }
                }else{
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

        docref.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                nameTag.setText(new StringBuilder().append("Welcome ").append(value.getString("Name")).toString());
                //setUserName = "Welcome"+userName;
            }
        });


        CalTag.setText(new StringBuilder().append(CalBal.getCalBal()).append(" Kcal").toString());



        Food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PhysicalActivity.this, MainScreen.class);
                startActivity(intent);
            }
        });

        Exercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PhysicalActivity.this, PhysicalActivity.class);
                startActivity(intent);
            }
        });

        AddMinimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PhysicalActivity.this, MinimalActivity.class);
                startActivity(intent);
            }
        });


        AddModerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PhysicalActivity.this, ModerateActivity.class);
                startActivity(intent);
            }
        });

        AddIntense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(PhysicalActivity.this, IntenseActivity.class);
                startActivity(intent);
            }
        });

    }
}