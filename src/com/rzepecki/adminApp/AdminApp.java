package com.rzepecki.adminApp;

public class AdminApp {

    public static void main(String[] args) {

        ExerciseAssignment eA = new ExerciseAssignment();
        ExerciseManagment eM = new ExerciseManagment();
        GroupManagment gM = new GroupManagment();
        UserManagment uM = new UserManagment();

        //eA.solutionApp();
        eM.exerciseApp();
        //gM.groupApp();
        //uM.userApp();
    }
}
