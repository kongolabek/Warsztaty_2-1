package com.rzepecki.adminApp;

import com.rzepecki.dao.GroupDAO;
import com.rzepecki.dao.UserDAO;
import com.rzepecki.model.User;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.ArrayList;
import java.util.Scanner;

public class UserManagment extends Application {

    public void userApp() {
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
}
