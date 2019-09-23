package model;

import java.util.ArrayList;
import java.util.Scanner;
import model.ToDoList;

public class ToDo {
    private ArrayList<String> toDoList;
    private ArrayList<String> removedToDoList;
    private Scanner scanner;                 //CPSC 210 B04-SimpleCalculatorSolutionLecLab

    // EFFECT: Creates new ToDo
    public ToDo() {
        toDoList = new ArrayList<>();
        removedToDoList = new ArrayList<>();
        scanner = new Scanner(System.in);   //CPSC 210 B04-SimpleCalculatorSolutionLecLab
    }

//    // EFFECT: Asks user what they want to do
//    public void whatToDo() {
//
//        while (true) {
//            System.out.println("What would you like to do?");
//            System.out.println("1: Add something to your todo list");
//            System.out.println("2: Delete a todo");
//            System.out.println("3: Show todos");
//            System.out.println("4: Quit the program");
//            System.out.println("");
//
//            int command = scanner.nextInt();      //CPSC 210 B04-SimpleCalculatorSolutionLecLab
//
//            if (command == 4) {
//                break;
//            }
//            checkCommand(command);
//        }
//        System.out.println("Goodbye!");
//    }
//
//    //EFFECT: takes user input and
//    public void checkCommand(int command) {
//        if (command == 1) {
//            String todo = repeatToDo();
//            System.out.println("You have typed: " + todo);
//            addToDo(todo);
//            System.out.println("");
//        } else if (command == 2) {
//            removeToDo();
//        } else if (command == 3) {
//            printToDoList();
//        } else {
//            System.out.println("Sorry, can you type 1, 2, 3 or 4");
//        }
//    }
//
//    //EFFECT: prints a list of todos
//    public void printToDoList() {
//        System.out.println("Current ToDos");
//        for (int i = 1; i <= toDoList.size(); i++) {
//            System.out.println(i + " : " + toDoList.get(i - 1));
//        }
//        System.out.println("");
//    }
//
//    // EFFECT: returns what the user typed
//    public String repeatToDo() {
//        System.out.println("Enter the ToDo:");
//        String todo = scanner.next();        //CPSC 210 B04-SimpleCalculatorSolutionLecLab
//        System.out.println("");
//        return todo;
//    }

    // MODIFIES: this
    // EFFECT: adds todo into the toDoList
    public void addToDo(String todo) {
        toDoList.add(todo);
    }

    // REQUIRES: toDoList has atleast 1 todo
    // EFFECT: askes user what todo they want to remove
    public void removeToDo() {
        System.out.println("Which ToDo would you like to remove?");
        printToDoList();
        int removeNum = scanner.nextInt();
        moveToDoToRemovedToDo(removeNum);
    }

    //EFFECT: prints a list of todos
    public void printToDoList() {
//        ArrayList<String> toDoList = getToDoList();
        System.out.println("Current ToDos");
        for (int i = 1; i <= toDoList.size(); i++) {
            System.out.println(i + " : " + toDoList.get(i - 1));
        }
        System.out.println("");
    }

    // REQUIRES: toDoList has atleast 1 todo
    // MODIFIES: this
    // EFFECT: moves todo from toDoList to removedToDoList
    public void moveToDoToRemovedToDo(int removeNum) {
        String moveToRemove = toDoList.get(removeNum - 1);
        toDoList.remove(removeNum - 1);
        removedToDoList.add(moveToRemove);
    }

    // EFFECT: returns toDoList
    public ArrayList<String> getToDoList() {
        return toDoList;
    }

    // EFFECT: returns removeToDoList
    public ArrayList<String> getRemovedToDoList() {
        return removedToDoList;
    }
}
