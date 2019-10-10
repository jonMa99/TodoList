package ui;

import exception.EmptyToDoListException;
import exception.TooManyToDosException;
import exception.WrongCommandInputException;

import java.io.IOException;
import java.util.Scanner;

public class UserPrompts {
    public static final int MAXTODOLISTSIZE = 10;

    private model.ToDoList normaltoDoList;
    private model.ToDoList urgenttoDoList;
    private Scanner scanner;                 //CPSC 210 B04-SimpleCalculatorSolutionLecLab

    public UserPrompts() throws IOException {
        normaltoDoList = new model.NormalToDo();
        urgenttoDoList = new model.UrgentToDo();
        scanner = new Scanner(System.in);
        whatToDo();
    }

    // MODIFIES: checkCommand
    // EFFECT: Asks user what they want to do
    public void whatToDo() throws IOException {

        while (true) {
            printOutMenu();

            int command = scanner.nextInt();      //CPSC 210 B04-SimpleCalculatorSolutionLecLab

            if (command == 6) {
                normaltoDoList.save("./data/toDoListoutput.txt", "./data/urgenttoDoListoutput.txt",
                        "./data/removeToDoListoutput.txt");
                break;
            }
            try {
                checkCommand(command);
            } catch (WrongCommandInputException e) {
                System.out.println("Sorry, can you type a number 1 - 6");
            }
        }
        System.out.println("Goodbye!");
    }

    private void printOutMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1: Add something to your todo list");
        System.out.println("2: Add something to your urgent todo list");
        System.out.println("3: Delete a todo");
        System.out.println("4: Delete an urgenttodo");
        System.out.println("5: Show todos and urgent todos");
        System.out.println("6: Quit the program");
        System.out.println("");
    }

    private void checkOutListSize() throws TooManyToDosException {
        if (normaltoDoList.getToDoList().size() == MAXTODOLISTSIZE) {
            throw new TooManyToDosException();
        }
        if (normaltoDoList.getToDoList().size() == MAXTODOLISTSIZE) {
            throw new TooManyToDosException();
        }
    }

    // MODIFIES: todo.addToDo, todo.removeToDo
    // EFFECT: takes user input and advances program based on user input
    public void checkCommand(int command) throws WrongCommandInputException {
        if (command < 1 || command > 6) {
            throw new WrongCommandInputException();
        }
        if (command == 1) {
            String todo1 = repeatToDo();
            System.out.println("You have typed: " + todo1);
            normaltoDoList.addToDo(todo1);
        } else if (command == 2) {
            String urgenttodo = repeatToDo();
            System.out.println("You have typed: " + urgenttodo);
            urgenttoDoList.addToDo(urgenttodo);
        } else if (command == 3) {
            askremoveToDo();
        } else if (command == 4) {
            askremoveUrgentToDo();
        } else {
            printBothToDos();
        }
    }

    private void printBothToDos() {
        normaltoDoList.printToDoList();
        urgenttoDoList.printToDoList();
    }

    // MODIFIES: removeToDo
    // EFFECT: asks user what todo they want to remove
    public void askremoveToDo() {
        System.out.println("Which ToDo would you like to remove?");
        normaltoDoList.printToDoList();
        int removeNum = scanner.nextInt();
        try {
            normaltoDoList.removeToDo(removeNum);
        } catch (EmptyToDoListException e) {
            System.out.println("The NormalToDoList is empty. You have nothing to remove!");
        }
    }

    // MODIFIES: removeToDo
    // EFFECT: asks user what urgenttodo they want to remove
    public void askremoveUrgentToDo() {
        System.out.println("Which Urgent ToDo would you like to remove?");
        urgenttoDoList.printToDoList();
        int removeNum = scanner.nextInt();
        try {
            urgenttoDoList.removeToDo(removeNum);
        } catch (EmptyToDoListException e) {
            System.out.println("The UrgentToDoList is empty. You have nothing to remove!");
        }
    }

//    // todo try to refactor this
//    //EFFECT: prints a list of todos
//    public void printToDoList() {
//        System.out.println("Current ToDos");
//        for (int i = 1; i <= toDoList.getToDoList().size(); i++) {
//            System.out.println(i + " : " + (toDoList.getToDoList().get(i - 1).getToDoName()));
//        }
//        System.out.println("");
//        System.out.println("Current Urgent ToDos");
//        System.out.println("!!!!!");s
//        for (int i = 1; i <= urgenttoDoList.getToDoList().size(); i++) {
//            System.out.println(i + " : " + (urgenttoDoList.getToDoList().get(i - 1).getToDoName()));
//        }
//        System.out.println("!!!!!");
//        System.out.println("");
//    }

    // EFFECT: returns what the user typed
    public String repeatToDo() {
        scanner = new Scanner(System.in);        //CPSC 210 B04-SimpleCalculatorSolutionLecLab
        System.out.println("Enter the ToDo:");
        String todo = scanner.nextLine();        //CPSC 210 B04-SimpleCalculatorSolutionLecLab
        return todo;
    }
}
