package com.rzepecki.adminApp;

import com.rzepecki.dao.ExcerciseDAO;
import com.rzepecki.dao.SolutionDAO;
import com.rzepecki.dao.UserDAO;
import com.rzepecki.model.Exercise;
import com.rzepecki.model.Solution;
import com.rzepecki.model.User;

import java.util.ArrayList;
import java.util.Scanner;

public class ExerciseAssignment extends Application {

    public void solutionApp() {
        UserDAO userDAO = new UserDAO();
        SolutionDAO solutionDAO = new SolutionDAO();
        ExcerciseDAO excerciseDAO = new ExcerciseDAO();
        Scanner scanner= new Scanner(System.in);
        while(true){
            //pobranie wszystkich group_id
            ArrayList userIList = getUserId(userDAO);
            //pobranie wszystkich zadań
            ArrayList exerciseIDList = getExerciseId(excerciseDAO);

            System.out.println("\nChoose one of the options below:");
            System.out.println("add\n" +
                    "view\n" +
                    "quit");

            System.out.print("\n-->");
            String sc = scanner.nextLine();

            if(sc.equalsIgnoreCase("add")){
                addFunctionSolution(solutionDAO, userIList, exerciseIDList, userDAO, excerciseDAO);
            }else if (sc.equalsIgnoreCase("view")){
                viewFunctionSolution(solutionDAO, userIList);
            }else if (sc.equalsIgnoreCase("quit")){
                System.out.println("exit");
                break;
            }else{
                System.out.println("WARNING: incorrect option, choose one more time");
            }

        }
    }

    private static void addFunctionSolution(SolutionDAO solutionDAO, ArrayList userIList, ArrayList exerciseIDList, UserDAO userDAO, ExcerciseDAO excerciseDAO){
        System.out.println("Users list:");
        for (User user: userDAO.findAll()) {
            System.out.println(user.printToString());
        }
        int userId = scannerInt("user", userIList);
        System.out.println("Exercise list:");
        for (Exercise exercise: excerciseDAO.findAll()) {
            System.out.println(exercise.toString());
        }
        int exerciseId = scannerInt("exercise", exerciseIDList);
        Solution solution = new Solution(exerciseId, userId);
        solutionDAO.create(solution);
        System.out.println("Solution created");
        System.out.println("-----------------------------");
    }

    private static void viewFunctionSolution(SolutionDAO solutionDAO, ArrayList userIList) {
        int userId = scannerInt("user", userIList);
        ArrayList<Solution> list = solutionDAO.findAllByUserId(userId);
        for(Solution solution:list){
            System.out.println(solution.toString());
        }
        System.out.println("-----------------------------");
    }
}
