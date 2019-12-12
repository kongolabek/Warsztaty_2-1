/**
 * Validacja maila, zabezpieczenie przed edytowanien , usuwaniem nieistniejących urzytkowników
 * zabezpieczenie przed ustawianiem nieistniejących group_id
 */
package com.rzepecki;

import com.rzepecki.dao.ExcerciseDAO;
import com.rzepecki.dao.GroupDAO;
import com.rzepecki.dao.SolutionDAO;
import com.rzepecki.dao.UserDAO;
import com.rzepecki.model.Exercise;
import com.rzepecki.model.Group;
import com.rzepecki.model.Solution;
import com.rzepecki.model.User;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.ArrayList;
import java.util.Scanner;


public class Application {
    public static void main(String[] args) {

        userApp();
        exerciseApp();
        groupApp();
        solutionApp();

    }

    private static void solutionApp() {
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
        System.out.println("Solution");
        for(Solution solution:list){
            System.out.println(solution.toString());
        }
        System.out.println("-----------------------------");
    }

    private static void groupApp() {
        GroupDAO groupDAO = new GroupDAO();
        Scanner scanner= new Scanner(System.in);
        while(true){
            System.out.println("Group list:");
            for (Group group: groupDAO.findAll()) {
                System.out.println(group.toString());
            }
            //pobranie wszystkich group_id
            ArrayList groupIdList = getGroupId(groupDAO);

            System.out.println("\nChoose one of the options below:");
            System.out.println("add\n" +
                    "edit\n" +
                    "delete\n" +
                    "quit");

            System.out.print("\n-->");
            String sc = scanner.nextLine();

            if(sc.equalsIgnoreCase("add")){
                addFunctionGroup(groupDAO);
            }else if (sc.equalsIgnoreCase("edit")){
                ediFtunctionGroup(groupDAO, groupIdList);
            }else if (sc.equalsIgnoreCase("delete")){
                deleteFunctionGroup(groupDAO, groupIdList);
            }else if (sc.equalsIgnoreCase("quit")){
                System.out.println("exit");
                break;
            }else{
                System.out.println("WARNING: incorrect option, choose one more time");
            }

        }
    }

    private static void addFunctionGroup(GroupDAO groupDAO) {
        String name = scannerString("name");
        Group group = new Group(name);
        groupDAO.create(group);
        System.out.println("Group added");
        System.out.println("-----------------------------");
    }

    private static void ediFtunctionGroup(GroupDAO groupDAO, ArrayList groupIdList) {
        int groupId = scannerInt("group", groupIdList);
        String name = scannerString("name");
        Group group = new Group(name);
        groupDAO.update(group, groupId);
        System.out.println("Group updated");
        System.out.println("-----------------------------");
    }


    private static void deleteFunctionGroup(GroupDAO groupDAO ,ArrayList groupIdList){
        int groupId = scannerInt("group", groupIdList);
        System.out.println("groupID "+groupId);
        groupDAO.delete(groupId);
        System.out.println("Group deleted");
        System.out.println("-----------------------------");
    }

    private static void exerciseApp() {
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
                ediFtunctionExercise(exerciseDAO, exerciseIdList);
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

    private static void ediFtunctionExercise(ExcerciseDAO exerciseDAO, ArrayList exerciseIdList) {
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

    private static ArrayList getExerciseId(ExcerciseDAO exerciseDAO) {
        ArrayList idList = new ArrayList();
        for (Exercise exercise: exerciseDAO.findAll()) {
            idList.add(exercise.getId());
        }
        return idList;
    }

    private static void userApp() {
        UserDAO userDAO = new UserDAO();
        GroupDAO groupDAO =new GroupDAO();
        Scanner scanner= new Scanner(System.in);
        while(true){
            System.out.println("Users list:");
            for (User user: userDAO.findAll()) {
                System.out.println(user.printToString());
            }
            //pobranie wszystkich group_id
            ArrayList groupIList = getGroupId(groupDAO);
            //pobranie wszystkich user_id
            ArrayList userIdList = getUserId(userDAO);
            //pobranie adresow email
            ArrayList emailList = getEmail(userDAO);

            System.out.println("\nChoose one of the options below:");
            System.out.println("add\n" +
                    "edit\n" +
                    "delete\n" +
                    "quit");

            System.out.print("\n-->");
            String sc = scanner.nextLine();

            if(sc.equalsIgnoreCase("add")){
                addFunction(userDAO, groupIList, emailList);
            }else if (sc.equalsIgnoreCase("edit")){
                editFunction(userDAO, userIdList, groupIList, emailList);
            }else if (sc.equalsIgnoreCase("delete")){
                deleteFunction(userDAO, userIdList);
            }else if (sc.equalsIgnoreCase("quit")){
                System.out.println("exit");
                break;
            }else{
                System.out.println("WARNING: incorrect option, choose one more time");
            }

        }
    }

    private static ArrayList getEmail(UserDAO userDAO) {
        ArrayList idList = new ArrayList();
        for (User user: userDAO.findAll()) {
            idList.add(user.getEmail());
        }
        return idList;
    }

    private static ArrayList getUserId(UserDAO userDAO) {
        ArrayList idList = new ArrayList();
        for (User user: userDAO.findAll()) {
            idList.add(user.getId());
        }
        return idList;
    }

    private static ArrayList getGroupId(GroupDAO groupDAO) {
        ArrayList idList = new ArrayList();
        for (Group group: groupDAO.findAll()) {
            idList.add(group.getId());
        }
        return idList;
    }

    private static void deleteFunction(UserDAO userDAO, ArrayList userIdList) {

        int userId = scannerInt("user", userIdList);
        System.out.println("userId "+userId);

        userDAO.delete(userId);
        System.out.println("Users deleted");
        System.out.println("-----------------------------");

    }


    private static void editFunction (UserDAO userDAO, ArrayList userIdList, ArrayList groupIList, ArrayList emailList) {
        int userId = scannerInt("user", userIdList);
        String userName = scannerString("userName");
        String email = scannerEmail(emailList);
        String password = scannerPassoword();
        int user_group_id = scannerInt("group", groupIList);
        User user = new User(userName, email, password, user_group_id);
        userDAO.update(user, userId);
        System.out.println("Users updated");
        System.out.println("-----------------------------");
    }

    private static void addFunction(UserDAO userDAO, ArrayList groupIList, ArrayList emailList) {

        String userName = scannerString("userName");
        String email = scannerEmail(emailList);
        String password = scannerPassoword();
        int user_group_id = scannerInt("group", groupIList);
        User user = new User(userName, email, password, user_group_id);
        userDAO.create(user);
        System.out.println("Users added");
        System.out.println("-----------------------------");
    }

    private static String scannerPassoword() {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter password");
        while (true){
            System.out.print("-->");
            String password = scanner.nextLine();
            if (password.length()>=8){
                return password;
            }else{
                System.out.println("WARNING: password is too short (at least 8 characters), enter again");
            }
        }
    }

    private static String scannerEmail(ArrayList emailList) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter email");
        while (true){
            try {
                System.out.print("-->");
                String mail = scanner.nextLine();
                InternetAddress internetAddress = new InternetAddress(mail);
                internetAddress.validate();
                if(!emailList.contains(mail)){
                    return mail;
                }else {
                    System.out.println("WARNING: email exists, enter another one");
                }
            } catch (AddressException e) {
                System.out.println("WARNING: wrong email format, enter again");
            }
        }
        /*while (true){
            System.out.print("-->");
            String mail = scanner.nextLine();
            Pattern p = Pattern.compile("^[A-Za-z0-9-]+(\\-[A-Za-z0-9._])*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9])");
            Matcher m = p.matcher(mail);
            if (m.find()){
                return mail;
            }else{
                System.out.println("WARNING: wrong email format, enter again");
            }
        }
         */
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
