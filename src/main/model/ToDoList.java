package model;

import java.util.ArrayList;
import java.util.Scanner;
import model.ToDo;

public class ToDoList {
    private ToDo todo;
    private Scanner scanner;                 //CPSC 210 B04-SimpleCalculatorSolutionLecLab

    public ToDoList() {
        todo = new ToDo();
        scanner = new Scanner(System.in);
        whatToDo();
    }

    // EFFECT: Asks user what they want to do
    private void whatToDo() {

        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("1: Add something to your todo list");
            System.out.println("2: Delete a todo");
            System.out.println("3: Show todos");
            System.out.println("4: Quit the program");
            System.out.println("");

            int command = scanner.nextInt();      //CPSC 210 B04-SimpleCalculatorSolutionLecLab

            if (command == 4) {
                break;
            }
            checkCommand(command);
        }
        System.out.println("Goodbye!");
    }

    // REQUIRES: input only be numbers
    //EFFECT: takes user input and advances program based on user input
    private void checkCommand(int command) {
        if (command == 1) {
            String todo1 = repeatToDo();
            System.out.println("You have typed: " + todo1);
            todo.addToDo(todo1);
            System.out.println("");
        } else if (command == 2) {
            todo.removeToDo();
        } else if (command == 3) {
            todo.printToDoList();
        } else {
            System.out.println("Sorry, can you type 1, 2, 3 or 4");
        }
    }

    // REQUIRES: can't input 1, 2, 3, 4 and words with spaces
    // EFFECT: returns what the user typed
    private String repeatToDo() {
        System.out.println("Enter the ToDo:");
        String todo = scanner.next();        //CPSC 210 B04-SimpleCalculatorSolutionLecLab
        System.out.println("");
        return todo;
    }
}
