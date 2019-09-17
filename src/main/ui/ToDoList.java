package ui;

import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {
    private ArrayList<String> toDoList;
    private ArrayList<String> removedToDoList;
    private Scanner scanner;                 //CPSC 210 B04-SimpleCalculatorSolutionLecLab

    public ToDoList() {
        toDoList = new ArrayList<>();
        removedToDoList = new ArrayList();
        scanner = new Scanner(System.in);   //CPSC 210 B04-SimpleCalculatorSolutionLecLab
        whatToDo();
    }

    public static void main(String[] args) {
        new ToDoList();
    }

    public void whatToDo() {

        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("1: Add something to your todo list");
            System.out.println("2: Delete a todo");
            System.out.println("3: Show todos");
            System.out.println("4: Quit the program");

            int command = scanner.nextInt();      //CPSC 210 B04-SimpleCalculatorSolutionLecLab

            if (command == 4) {
                break;
            }
            checkCommand(command);
        }
        System.out.println("Goodbye!");
    }

    public void checkCommand(int command) {
        if (command == 1) {
            String todo = repeatToDo();
            System.out.println("You have typed: " + todo);
            toDoList.add(todo);
        } else if (command == 2) {
            removeToDo();
        } else if (command == 3) {
            System.out.println("ToDos: " + toDoList);
        } else {
            System.out.println("Sorry, can you type 1, 2, 3 or 4");
        }
    }


    public String repeatToDo() {
        System.out.println("Enter the ToDo:");
        String todo = scanner.next();              //CPSC 210 B04-SimpleCalculatorSolutionLecLab
        return todo;
    }

    public void removeToDo() {
        System.out.println("Which ToDo would you like to remove?");
        for (int i = 0; i <= (toDoList.size() - 1); i++) {
            System.out.println(i + " : " + toDoList.get(i));
        }
        int removeNum = scanner.nextInt();
        String moveToRemove = toDoList.get(removeNum);
        toDoList.remove(removeNum);
        removedToDoList.add(moveToRemove);
    }
}