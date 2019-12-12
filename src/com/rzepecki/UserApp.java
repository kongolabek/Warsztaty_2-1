package com.rzepecki;

import com.rzepecki.dao.ExcerciseDAO;
import com.rzepecki.dao.SolutionDAO;
import com.rzepecki.dao.UserDAO;
import com.rzepecki.model.Exercise;
import com.rzepecki.model.Solution;
import com.rzepecki.model.User;

import java.util.ArrayList;
import java.util.Scanner;

public class UserApp {
    public static void main(String[] args) {
        SolutionDAO solutionDAO = new SolutionDAO();
        UserDAO userDAO = new UserDAO();
        Scanner scanner= new Scanner(System.in);
        while(true){
            //pobranie wszystkich user_id
            ArrayList userIdList = getUserId(userDAO);
            int userId = scannerInt("user", userIdList);

            System.out.println("\nChoose one of the options below:");
            System.out.println("add\n" +
                    "view\n" +
                    "quit");

            System.out.print("\n-->");
            String sc = scanner.nextLine();

            if(sc.equalsIgnoreCase("add")){
                addSolution(solutionDAO, userId);
            }else if (sc.equalsIgnoreCase("view")){
                viewSolution(solutionDAO, userId);
            }else if (sc.equalsIgnoreCase("quit")){
                System.out.println("exit");
                break;
            }else{
                System.out.println("WARNING: incorrect option, choose one more time");
            }

        }
    }
    private static ArrayList getUserId(UserDAO userDAO) {
        ArrayList idList = new ArrayList();
        for (User user: userDAO.findAll()) {
            idList.add(user.getId());
        }
        return idList;
    }

    private static void addSolution(SolutionDAO solutionDAO, int userId) {

        ArrayList<Exercise> list = solutionDAO.findAllExerciseWithoutSolution(userId);
        ArrayList solutionIDList = new ArrayList();
        System.out.println("Exercises without solution:");
        for(Exercise exercise:list){
            System.out.println(exercise.toString());
            solutionIDList.add(exercise.getId());
        }
        System.out.println("-----------------------------");
        int exerciseID = scannerInt("exercise", solutionIDList);
        String description = scannerString("description");
        Solution solution=new Solution(description);
        solutionDAO.update(solution, exerciseID );
        System.out.println("Exercise solution added");
        System.out.println("-----------------------------");
    }

    private static void viewSolution(SolutionDAO solutionDAO, int userId) {
        ArrayList<Solution> list = solutionDAO.findAllByUserId(userId);

        for(Solution solution:list){
            System.out.println(solution.toString());
        }
        System.out.println("-----------------------------");

    }

    private static String scannerString(String text) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter "+text);
        System.out.print("-->");
        return scanner.nextLine();
    }

    private static int scannerInt(String text, ArrayList userIdList){
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter "+text+" ID");
        while(true){
            System.out.print("-->");
            while (!scanner.hasNextInt()){
                System.out.println("WARNING: wrong ID, enter ID again");
                System.out.print("-->");
                scanner.next();
            }
            int sc = scanner.nextInt();
            if (userIdList.contains(sc)){
                return sc;
            }else{
                System.out.println("WARNING: selected "+text+" ID does not exist");
            }

        }
    }
}
