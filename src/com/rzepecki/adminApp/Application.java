/**
 * Validacja maila, zabezpieczenie przed edytowanien , usuwaniem nieistniejących urzytkowników
 * zabezpieczenie przed ustawianiem nieistniejących group_id
 */
package com.rzepecki.adminApp;

import com.rzepecki.dao.ExcerciseDAO;
import com.rzepecki.dao.GroupDAO;
import com.rzepecki.dao.UserDAO;
import com.rzepecki.model.Exercise;
import com.rzepecki.model.Group;
import com.rzepecki.model.User;

import java.util.ArrayList;
import java.util.Scanner;


public class Application {


    protected static ArrayList getExerciseId(ExcerciseDAO exerciseDAO) {
        ArrayList idList = new ArrayList();
        for (Exercise exercise: exerciseDAO.findAll()) {
            idList.add(exercise.getId());
        }
        return idList;
    }

    protected static ArrayList getUserId(UserDAO userDAO) {
        ArrayList idList = new ArrayList();
        for (User user: userDAO.findAll()) {
            idList.add(user.getId());
        }
        return idList;
    }

    protected static ArrayList getGroupId(GroupDAO groupDAO) {
        ArrayList idList = new ArrayList();
        for (Group group: groupDAO.findAll()) {
            idList.add(group.getId());
        }
        return idList;
    }

    protected static String scannerString(String text) {
        Scanner scanner= new Scanner(System.in);
        System.out.println("Enter "+text);
        System.out.print("-->");
        return scanner.nextLine();
    }

    protected static int scannerInt(String text, ArrayList userIdList){
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
