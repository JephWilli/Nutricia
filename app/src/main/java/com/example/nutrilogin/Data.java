package com.example.nutrilogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Data  {
    private String name, healthObjective, gender, email;
    private int age, objectiveValue;
    private float weight, height;
    private float bmr, objectiveCal;

    //Singleton Support
    private static Data instance;
    private Data(){
        //private constructor to deny any class from creating instance of class
    }




    public static Data getInstance(){
        //provides instance if none exists in class already; Provides the same instance to all classes
        if(instance == null){
            instance = new Data();
        }
            return instance;
    }


//setters
    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHealthObjective(String healthObjective) {
        this.healthObjective = healthObjective;
    }

    public void setObjectiveValue(int objectiveValue) {
        this.objectiveValue = objectiveValue;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void setBmr(float bmr) {
        this.bmr = bmr;
    }

    public void setObjectiveCal(float objectiveCal) {
        this.objectiveCal = objectiveCal;
    }

    //getters
    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getHealthObjective() {
        return healthObjective;
    }

    public int getObjectiveValue() {
        return objectiveValue;
    }

    public float getWeight() {
        return weight;
    }

    public float getHeight() {
        return height;
    }

    public String getEmail() {
        return email;
    }
    public float getBmr() {
        return bmr;
    }
    public float getObjectiveCal() {
        return objectiveCal;
    }
}