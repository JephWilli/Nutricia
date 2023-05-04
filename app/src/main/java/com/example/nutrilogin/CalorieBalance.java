package com.example.nutrilogin;


public class CalorieBalance {
    private int CalBal;

    //Singleton Support
    private static CalorieBalance instance;
    private CalorieBalance(){
        //private constructor to deny any class from creating instance of class
    }
    public static CalorieBalance getInstance(){
        //provides instance if none exists in class already; Provides the same instance to all classes
        if(instance == null){
            instance = new CalorieBalance();
        }
        return instance;
    }

    public int getCalBal() {
        return CalBal;
    }

    public void setCalBal(int calBal) {
        CalBal = calBal;
    }

    //methods to deduct and add to the users calorie balance
    public int deductCalBal(int deductVal) {
        CalBal = CalBal-deductVal;

        return CalBal;
    }

    public int addCalBal(int addVal) {
        CalBal = CalBal+addVal;

        return CalBal;
    }
}
