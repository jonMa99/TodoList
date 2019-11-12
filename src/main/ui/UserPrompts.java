package ui;

import exception.EmptyNormalToDoListException;
import exception.TooManyToDosException;
import exception.WrongCommandInputException;
import model.Location;
import model.ToDo;
import model.ToDoList;
import network.ReadWeather;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class UserPrompts {
    private model.ToDoList toDoList;
    private Scanner scanner;                 //CPSC 210 B04-SimpleCalculatorSolutionLecLab
    private ReadWeather readWeather;

    public UserPrompts() throws IOException {
        toDoList = new ToDoList();
        scanner = new Scanner(System.in);
        readWeather = new ReadWeather();
        whatToDo();
    }

    // MODIFIES: checkCommand
    // EFFECT: Asks user what they want to do
    public void whatToDo() throws IOException {
        while (true) {
            printOutMenu();
            int command = scanner.nextInt();      //CPSC 210 B04-SimpleCalculatorSolutionLecLab
            if (command == 6) {
                saveToDoList(toDoList);
                break;
            }
            try {
                checkCommand(command);
            } catch (WrongCommandInputException e) {
                System.out.println("Sorry, can you type a number 1 - 6");
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
        System.out.println("1: Add a todo");
        System.out.println("2: Add a urgent todo");
        System.out.println("3: Delete a todo");
        System.out.println("4: Show todos and urgent todos");
        System.out.println("5: Search todos by location");
        System.out.println("6: Quit the program");
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
        } else if (command == 5) {
            searchByLocation();
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
        String todo1 = printToDo();
        String location = askLocation();
        if (location.equals("")) {
            toDoList.addToDo(todo1);
        } else {
            toDoList.addToDo(todo1, location);
        }
    }


    private void addUrgentToDo() throws TooManyToDosException {
        String todo1 = printToDo();
        String location = askLocation();
        if (location.equals("")) {
            toDoList.addUrgentToDo(todo1);
        } else {
            toDoList.addUrgentToDo(todo1, location);
        }
    }

    private String askLocation() {
        System.out.println("Location (default is Nowhere)");
        return scanner.nextLine();
    }

    private String printToDo() {
        String todo1 = repeatToDo();
//        System.out.println("You have typed: " + todo1);
        return todo1;
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


    // EFFECT: returns what the user typed
    public String repeatToDo() {
        scanner = new Scanner(System.in);        //CPSC 210 B04-SimpleCalculatorSolutionLecLab
        System.out.println("Enter the ToDo:");
        String todo = scanner.nextLine();        //CPSC 210 B04-SimpleCalculatorSolutionLecLab
        return todo;
    }

    public void searchByLocation() {
        HashMap<Location, ArrayList<ToDo>> hashmap = toDoList.fillHashMap();
        scanner = new Scanner(System.in);
        System.out.println("What location are you looking for?");
        String location = scanner.nextLine();
        ArrayList<ToDo> listtodo = hashmap.get(new Location(location));
        System.out.println("ToDos at " + location);
        int i = 1;
        for (ToDo td : listtodo) {
            System.out.println(i + ". " + td.getToDoName());
            i++;
        }
    }
}
