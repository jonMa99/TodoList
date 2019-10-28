package ui;

import exception.EmptyNormalToDoListException;
import exception.EmptyUrgentToDoListException;
import exception.TooManyToDosException;
import exception.WrongCommandInputException;
import model.ToDoList;

import java.io.IOException;
import java.util.Scanner;

public class UserPrompts {
    private model.ToDoList toDoList;
    private model.ToDoList urgenttoDoList;
    private Scanner scanner;                 //CPSC 210 B04-SimpleCalculatorSolutionLecLab

    public UserPrompts() throws IOException {
        toDoList = new ToDoList();
        scanner = new Scanner(System.in);
        whatToDo();
    }

    // MODIFIES: checkCommand
    // EFFECT: Asks user what they want to do
    public void whatToDo() throws IOException {
        while (true) {
            printOutMenu();
            int command = scanner.nextInt();      //CPSC 210 B04-SimpleCalculatorSolutionLecLab
            if (command == 5) {
                saveToDoList(toDoList);
                break;
            }
            try {
                checkCommand(command);
            } catch (WrongCommandInputException e) {
                System.out.println("Sorry, can you type a number 1 - 5");
            } catch (TooManyToDosException e) {
                System.out.println("Sorry you have too many todos. Can you remove some before adding a new one?");
            } finally {
                System.out.println("Thanks for using our todo list! \n");
            }
        }
        System.out.println("Goodbye!");
    }

    private void saveToDoList(ToDoList tdl) throws IOException {
        tdl.save("./data/toDoListoutput.txt", "./data/urgenttoDoListoutput.txt",
                "./data/removeToDoListoutput.txt");
    }

    private void printOutMenu() {
        System.out.println("What would you like to do?");
        System.out.println("1: Add something to your todo list");
        System.out.println("2: Add something to your urgent todo list");
        System.out.println("3: Delete a todo");
        System.out.println("4: Show todos and urgent todos");
        System.out.println("5: Quit the program");
        System.out.println("");
    }


    // MODIFIES: todo.addToDo, todo.removeToDo
    // EFFECT: takes user input and advances program based on user input
    public void checkCommand(int command) throws WrongCommandInputException, TooManyToDosException {
        if (command < 1 || command > 6) {
            throw new WrongCommandInputException();
        }
        if (command == 1) {
            addToDo();
        } else if (command == 2) {
            addUrgentToDo();
        } else if (command == 3) {
            askremoveToDo();
        } else {
            toDoList.printToDoList();
        }
    }

//    private void addToDo(String s) {
//        try {
//            toDoList.addToDo();
//        } catch (TooManyToDosException e) {
//            System.out.println(s);
//        }
//    }

    private void addToDo() throws TooManyToDosException {
        String todo1 = repeatToDo();
        System.out.println("You have typed: " + todo1);
        toDoList.addToDo(todo1);
    }

    private void addUrgentToDo() throws TooManyToDosException {
        String todo1 = repeatToDo();
        System.out.println("You have typed: " + todo1);
        toDoList.addUrgentToDo(todo1);
    }

    // MODIFIES: removeToDo
    // EFFECT: asks user what todo they want to remove
    public void askremoveToDo() {
        System.out.println("Which ToDo would you like to remove?");
        toDoList.printToDoList();
        int removeNum = scanner.nextInt();
        try {
            toDoList.removeToDo(removeNum);
        } catch (EmptyNormalToDoListException e) {
            System.out.println("The NormalToDoList is empty. You have nothing to remove!");
        }
    }

//    // MODIFIES: removeToDo
//    // EFFECT: asks user what urgenttodo they want to remove
//    public void askremoveUrgentToDo() {
//        System.out.println("Which Urgent ToDo would you like to remove?");
//        urgenttoDoList.printToDoList();
//        int removeNum = scanner.nextInt();
//        try {
//            urgenttoDoList.removeToDo(removeNum);
//        } catch (EmptyUrgentToDoListException e) {
//            System.out.println("The UrgentToDoList is empty. You have nothing to remove!");
//        } catch (EmptyNormalToDoListException e) {
//            // Should not be called
//        }
//    }

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
