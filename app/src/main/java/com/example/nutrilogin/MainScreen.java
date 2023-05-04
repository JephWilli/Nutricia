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

public class MainScreen extends AppCompatActivity {
    Data data = Data.getInstance();
    CalorieBalance CalBal = CalorieBalance.getInstance();

    TextView exercises, report, CalTag, toObjectiveTag, nameTag;
    float toObjective, objective;
    Button AddBrunch, AddLunch, AddDinner;

    //authentication and authentication objects
    FirebaseFirestore db;
    FirebaseAuth auth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);





        nameTag = (TextView) findViewById(R.id.nameTag);
        toObjectiveTag = (TextView) findViewById(R.id.toObjectiveTag);
        exercises =(TextView) findViewById(R.id.activity_tab);
        report =(TextView) findViewById(R.id.report_tab);
        CalTag = (TextView) findViewById(R.id.calTag) ;
        AddBrunch = (Button) findViewById(R.id.add_breakfast) ;
        AddLunch = (Button) findViewById(R.id.add_lunch) ;
        AddDinner = (Button) findViewById(R.id.add_dinner) ;

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
                        //toObjective= data.getObjectiveCal()-Math.abs(CalBal.getCalBal());
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




        exercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainScreen.this, PhysicalActivity.class);
                startActivity(intent);
            }
        });

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainScreen.this, Report.class);
                startActivity(intent);
            }
        });

        AddBrunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainScreen.this, Breakfast.class);
                startActivity(intent);
            }
        });

        AddLunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainScreen.this, Lunch.class);
                startActivity(intent);
            }
        });

        AddDinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainScreen.this, Dinner.class);
                startActivity(intent);
            }
        });
    }
}