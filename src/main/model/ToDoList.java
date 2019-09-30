package model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import model.ToDo;

public class ToDoList {
    private ToDo toDo;
    private Scanner scanner;                 //CPSC 210 B04-SimpleCalculatorSolutionLecLab

    public ToDoList() throws IOException {
        toDo = new ToDo();
        scanner = new Scanner(System.in);
        whatToDo();
    }

    // MODIFIES: checkCommand
    // EFFECT: Asks user what they want to do
    public void whatToDo() throws IOException {

        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("1: Add something to your todo list");
            System.out.println("2: Delete a todo");
            System.out.println("3: Show todos");
            System.out.println("4: Quit the program");
            System.out.println("");

            int command = scanner.nextInt();      //CPSC 210 B04-SimpleCalculatorSolutionLecLab

            if (command == 4) {
                toDo.save();
                break;
            }
            checkCommand(command);
        }
        System.out.println("Goodbye!");
    }

    // REQUIRES: input only be numbers
    // MODIFIES: todo.addToDo, todo.removeToDo
    // EFFECT: takes user input and advances program based on user input
    public void checkCommand(int command) {
        if (command == 1) {
            String todo1 = repeatToDo();
            System.out.println("You have typed: " + todo1);
            toDo.addToDo(todo1);
            System.out.println("");
        } else if (command == 2) {
            toDo.removeToDo();
        } else if (command == 3) {
            toDo.printToDoList();
        } else {
            System.out.println("Sorry, can you type 1, 2, 3 or 4");
        }
    }

    // EFFECT: returns what the user typed
    public String repeatToDo() {
        scanner = new Scanner(System.in);        //CPSC 210 B04-SimpleCalculatorSolutionLecLab
        System.out.println("Enter the ToDo:");
        String todo = scanner.nextLine();        //CPSC 210 B04-SimpleCalculatorSolutionLecLab
        return todo;
    }
}
