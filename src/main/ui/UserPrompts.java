package ui;

import java.io.IOException;
import java.util.Scanner;

public class UserPrompts {
    private model.ToDoList toDoList;
    private model.ToDoList urgenttoDoList;
    private Scanner scanner;                 //CPSC 210 B04-SimpleCalculatorSolutionLecLab

    public UserPrompts() throws IOException {
        toDoList = new model.NormalToDo();
        urgenttoDoList = new model.UrgentToDo();
        scanner = new Scanner(System.in);
        whatToDo();
    }

    // MODIFIES: checkCommand
    // EFFECT: Asks user what they want to do
    public void whatToDo() throws IOException {

        while (true) {
            System.out.println("What would you like to do?");
            System.out.println("1: Add something to your todo list");
            System.out.println("2: Add something to your urgent todo list");
            System.out.println("3: Delete a todo");
            System.out.println("4: Delete an urgenttodo");
            System.out.println("5: Show todos and urgent todos");
            System.out.println("6: Quit the program");
            System.out.println("");

            int command = scanner.nextInt();      //CPSC 210 B04-SimpleCalculatorSolutionLecLab

            if (command == 6) {
                toDoList.save("./data/toDoListoutput.txt", "./data/urgenttoDoListoutput.txt",
                        "./data/removeToDoListoutput.txt");
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
            toDoList.addToDo(todo1);
            System.out.println("");
        } else if (command == 2) {
            String urgenttodo = repeatToDo();
            System.out.println("You have typed: " + urgenttodo);
            urgenttoDoList.addToDo(urgenttodo);
        } else if (command == 3) {
            askremoveToDo();
        } else if (command == 4) {
            askremoveUrgentToDo();
        } else if (command == 5) {
            printToDoList();
        } else {
            System.out.println("Sorry, can you type 1, 2, 3, 4, 5 or 6");
        }
    }

    // MODIFIES: removeToDo
    // EFFECT: asks user what todo they want to remove
    public void askremoveToDo() {
        System.out.println("Which ToDo would you like to remove?");
        printToDoList();
        int removeNum = scanner.nextInt();
        toDoList.removeToDo(removeNum);
    }

    // MODIFIES: removeToDo
    // EFFECT: asks user what urgenttodo they want to remove
    public void askremoveUrgentToDo() {
        System.out.println("Which Urgent ToDo would you like to remove?");
        printToDoList();
        int removeNum = scanner.nextInt();
        urgenttoDoList.removeToDo(removeNum);
    }

    // todo try to refactor this
    //EFFECT: prints a list of todos
    public void printToDoList() {
        System.out.println("Current ToDos");
        for (int i = 1; i <= toDoList.getToDoList().size(); i++) {
            System.out.println(i + " : " + (toDoList.getToDoList().get(i - 1).getToDoName()));
        }
        System.out.println("");
        System.out.println("Current Urgent ToDos");
        System.out.println("!!!!!");
        for (int i = 1; i <= urgenttoDoList.getToDoList().size(); i++) {
            System.out.println(i + " : " + (urgenttoDoList.getToDoList().get(i - 1).getToDoName()));
        }
        System.out.println("!!!!!");
        System.out.println("");
    }

    // EFFECT: returns what the user typed
    public String repeatToDo() {
        scanner = new Scanner(System.in);        //CPSC 210 B04-SimpleCalculatorSolutionLecLab
        System.out.println("Enter the ToDo:");
        String todo = scanner.nextLine();        //CPSC 210 B04-SimpleCalculatorSolutionLecLab
        return todo;
    }
}
