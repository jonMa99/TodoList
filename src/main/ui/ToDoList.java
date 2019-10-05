package ui;

import model.NormalToDo;

import java.io.IOException;
import java.util.Scanner;

public class ToDoList {
    private NormalToDo normalToDo;
    private Scanner scanner;                 //CPSC 210 B04-SimpleCalculatorSolutionLecLab

    public ToDoList() throws IOException {
        normalToDo = new NormalToDo();
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
                normalToDo.save("toDoListoutput.txt", "removeToDoListoutput.txt");
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
            normalToDo.addToDo(todo1);
            System.out.println("");
        } else if (command == 2) {
            askremoveToDo();
        } else if (command == 3) {
            normalToDo.printToDoList();
        } else {
            System.out.println("Sorry, can you type 1, 2, 3 or 4");
        }
    }

    // MODIFIES: removeToDo
    // EFFECT: asks user what todo they want to remove
    public void askremoveToDo() {
        System.out.println("Which ToDo would you like to remove?");
        int removeNum = scanner.nextInt();
        normalToDo.removeToDo(removeNum);
    }

    // EFFECT: returns what the user typed
    public String repeatToDo() {
        scanner = new Scanner(System.in);        //CPSC 210 B04-SimpleCalculatorSolutionLecLab
        System.out.println("Enter the ToDo:");
        String todo = scanner.nextLine();        //CPSC 210 B04-SimpleCalculatorSolutionLecLab
        return todo;
    }
}
