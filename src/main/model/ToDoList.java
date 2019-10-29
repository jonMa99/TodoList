package model;

import exception.EmptyNormalToDoListException;
import exception.TooManyToDosException;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ToDoList implements Saveable, Loadable {
    public static final int MAXTODOLISTSIZE = 10;

    protected ArrayList<ToDo> toDoList;
    protected ArrayList<ToDo> removedToDoList;
    protected HashMap<Location, ArrayList<ToDo>> search;

    // EFFECT: Creates new ToDo
    public ToDoList() throws IOException {
        toDoList = new ArrayList<ToDo>();
        removedToDoList = new ArrayList<>();
        load("./data/toDoListoutput.txt", "./data/urgenttoDoListoutput.txt",
                "./data/removeToDoListoutput.txt");
    }

    // MODIFIES: this
    // EFFECT: adds todo into the toDoList
    public void addToDo(String todo) throws TooManyToDosException {
        if (toDoList.size() >= MAXTODOLISTSIZE) {
            throw new TooManyToDosException();
        }
        toDoList.add(new NormalItem(todo));
    }

    // MODIFIES: this
    // EFFECT: adds todo with location into the toDoList
    public void addToDo(String todo, String location) throws TooManyToDosException {
        if (toDoList.size() >= MAXTODOLISTSIZE) {
            throw new TooManyToDosException();
        }
        toDoList.add(new NormalItem(todo, location));
    }


    // MODIFIES: this
    // EFFECT: adds urgenttodo into the toDoList
    public void addUrgentToDo(String todo) throws TooManyToDosException {
        if (toDoList.size() >= MAXTODOLISTSIZE) {
            throw new TooManyToDosException();
        }
        toDoList.add(new UrgentItem(todo));
    }

    // MODIFIES: this
    // EFFECT: adds urgenttodo with location into the toDoList
    public void addUrgentToDo(String todo, String location) throws TooManyToDosException {
        if (toDoList.size() >= MAXTODOLISTSIZE) {
            throw new TooManyToDosException();
        }
        toDoList.add(new UrgentItem(todo, location));
    }


    // MODIFIES: this
    // EFFECT: removes specified normaltodo and moves it to removedToDo
    public void removeToDo(int removeNum) throws EmptyNormalToDoListException {
        if (toDoList.size() == 0) {
            throw new EmptyNormalToDoListException();
        }
        System.out.println("You have removed: " + toDoList.get(removeNum - 1).getToDoName());
        moveToDoToRemovedToDo(removeNum);
    }

    // REQUIRES: normaltoDoList has atleast 1 normaltodo
    // MODIFIES: this
    // EFFECT: moves normaltodo from normaltoDoList to removedToDoList
    public void moveToDoToRemovedToDo(int removeNum) {
        ToDo moveToRemove = toDoList.get(removeNum - 1);
        toDoList.remove(removeNum - 1);
        removedToDoList.add(moveToRemove);
    }

    //EFFECT: prints a list of normaltodos
    public void printToDoList() {
        System.out.println("Current ToDos");
        for (int i = 1; i <= toDoList.size(); i++) {
            System.out.println(i + " : " + (toDoList.get(i - 1).getToDoName())
                    + " --- " + toDoList.get(i - 1).getLocation().getLocationName());
        }
        System.out.println("");
    }

    // EFFECT: returns normaltoDoList
    public ArrayList<ToDo> getToDoList() {
        return toDoList;
    }

    // EFFECT: returns removeToDoList
    public ArrayList<ToDo> getRemovedToDoList() {
        return removedToDoList;
    }

    @Override
    // MODIFIES: removedToDoList, toDoList
    // EFFECT: loads removeToDoListoutput.txt and toDoListoutput.txt files
    public void load(String toDo, String urgenttoDo, String removeList) throws IOException {
        List<String> todos = Files.readAllLines(Paths.get(toDo));   // CPSC 210 FileReaderWriter
        for (String s : todos) {
            toDoList.add(new NormalItem(s));
        }
//        List<String> urgents = Files.readAllLines(Paths.get(urgenttoDo));   // CPSC 210 FileReaderWriter
//        for (String s : urgents) {
//            urgenttoDoList.add(new UrgentItem(s));
//        }
        List<String> removes = Files.readAllLines(Paths.get(removeList));   // CPSC 210 FileReaderWriter
        for (String s : removes) {
            removedToDoList.add(new NormalItem(s));
        }
        printToDoList();
    }

    @Override
    // MODIFIES: removeToDoListoutput.txt, toDoListoutput.txt
    // EFFECT: saves toDos inside toDoList and removedToDoList into removedToDoListoutput.txt and toDoListoutput.txt
    public void save(String toDo, String urgenttoDo, String removeList) throws IOException {                                        // https://stackoverflow.com/questions/6548157/how-to-write-an-arraylist-of-strings-into-a-text-file
        FileWriter todo = new FileWriter(toDo, true);
        for (ToDo td : toDoList) {
            todo.write(td.getToDoName() + "\n");
        }
        todo.close();
//        FileWriter urgent = new FileWriter(urgenttoDo, true);
//        for (ToDo utd : urgenttoDoList) {
//            urgent.write(utd.getToDoName() + "\n");
//        }
//        urgent.close();
        FileWriter remove = new FileWriter(removeList, true);
        for (ToDo rtd : removedToDoList) {
            remove.write(rtd.getToDoName() + "\n");
        }
        remove.close();
    }

    public HashMap<Location, ArrayList<ToDo>> makeHashMap() {
        search = new HashMap<>();
        for (ToDo t : toDoList) {
            search.put(t.getLocation(), new ArrayList<ToDo>());
            ArrayList<ToDo> locationtodo = search.get(t.getLocation());
            locationtodo.add(t);
        }
        return search;
    }
}


