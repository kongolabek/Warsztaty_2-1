package com.rzepecki.adminApp;

import com.rzepecki.dao.ExcerciseDAO;
import com.rzepecki.model.Exercise;

import java.util.ArrayList;
import java.util.Scanner;

public class ExerciseManagment extends Application {

    public void exerciseApp() {
        ExcerciseDAO exerciseDAO = new ExcerciseDAO();
        Scanner scanner= new Scanner(System.in);
        while(true){
            System.out.println("Exercise list:");
            for (Exercise exercise: exerciseDAO.findAll()) {
                System.out.println(exercise.toString());
            }
            //pobranie wszystkich exercise_id
            ArrayList exerciseIdList = getExerciseId(exerciseDAO);

            System.out.println("\nChoose one of the options below:");
            System.out.println("add\n" +
                    "edit\n" +
                    "delete\n" +
                    "quit");

            System.out.print("\n-->");
            String sc = scanner.nextLine();

            if(sc.equalsIgnoreCase("add")){
                addFunctionExercise(exerciseDAO);
            }else if (sc.equalsIgnoreCase("edit")){
                editFunctionExercise(exerciseDAO, exerciseIdList);
            }else if (sc.equalsIgnoreCase("delete")){
                deleteFunctionExercise(exerciseDAO, exerciseIdList);
            }else if (sc.equalsIgnoreCase("quit")){
                System.out.println("exit");
                break;
            }else{
                System.out.println("WARNING: incorrect option, choose one more time");
            }

        }
    }

    private static void addFunctionExercise(ExcerciseDAO exerciseDAO) {
        String title = scannerString("title");
        String description = scannerString("description");

        Exercise exercise = new Exercise(title, description);
        exerciseDAO.create(exercise);
        System.out.println("Exercise added");
        System.out.println("-----------------------------");
    }

    private static void editFunctionExercise(ExcerciseDAO exerciseDAO, ArrayList exerciseIdList) {
        int exerciseId = scannerInt("exercise", exerciseIdList);
        String title = scannerString("title");
        String description = scannerString("description");

        Exercise exercise = new Exercise(title, description);
        exerciseDAO.update(exercise, exerciseId);
        System.out.println("Exercise updated");
        System.out.println("-----------------------------");
    }


    private static void deleteFunctionExercise(ExcerciseDAO exerciseDAO ,ArrayList exerciseIdList){
        int exerciseId = scannerInt("exercise", exerciseIdList);
        System.out.println("exerciseID "+exerciseId);

        exerciseDAO.delete(exerciseId);
        System.out.println("Exercise deleted");
        System.out.println("-----------------------------");
    }
}
