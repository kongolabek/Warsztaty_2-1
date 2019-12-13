package com.rzepecki.adminApp;

import com.rzepecki.dao.GroupDAO;
import com.rzepecki.model.Group;

import java.util.ArrayList;
import java.util.Scanner;

public class GroupManagment extends Application {

    public void groupApp() {
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
                editFunctionGroup(groupDAO, groupIdList);
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

    private static void editFunctionGroup(GroupDAO groupDAO, ArrayList groupIdList) {
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
}
