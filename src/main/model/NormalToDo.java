package model;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class NormalToDo implements Saveable, Loadable {
    private ArrayList<Item> toDoList;
    private ArrayList<Item> removedToDoList;

    // EFFECT: Creates new ToDo
    public NormalToDo() throws IOException {
        toDoList = new ArrayList<>();
        removedToDoList = new ArrayList<>();
        load("toDoListoutput.txt", "removeToDoListoutput.txt");
    }

    // MODIFIES: this
    // EFFECT: adds todo into the toDoList
    public void addToDo(String todo) {
        toDoList.add(new Item(todo));
    }

    // REQUIRES: toDoList has atleast 1 todo
    // EFFECT: removes specified todo and moves it to removedToDo
    public void removeToDo(int removeNum) {
        System.out.println("You have removed: " + toDoList.get(removeNum - 1).getToDoName());
        moveToDoToRemovedToDo(removeNum);
    }

    //EFFECT: prints a list of todos
    public void printToDoList() {
//        ArrayList<String> toDoList = getToDoList();
        System.out.println("Current ToDos");
        for (int i = 1; i <= toDoList.size(); i++) {
            System.out.println(i + " : " + (toDoList.get(i - 1).getToDoName()));
        }
        System.out.println("");
    }

    // REQUIRES: toDoList has atleast 1 todo
    // MODIFIES: this
    // EFFECT: moves todo from toDoList to removedToDoList
    public void moveToDoToRemovedToDo(int removeNum) {
        Item moveToRemove = toDoList.get(removeNum - 1);
        toDoList.remove(removeNum - 1);
        removedToDoList.add(moveToRemove);
    }

    // EFFECT: returns toDoList
    public ArrayList<Item> getToDoList() {
        return toDoList;
    }

    // EFFECT: returns removeToDoList
    public ArrayList<Item> getRemovedToDoList() {
        return removedToDoList;
    }

    @Override
    // MODIFIES: removedToDoList, toDoList
    // EFFECT: loads removeToDoListoutput.txt and toDoListoutput.txt files
    public void load(String toDo, String removeList) throws IOException {
        List<String> todos = Files.readAllLines(Paths.get(toDo));   // CPSC 210 FileReaderWriter
        for (String s : todos) {
            toDoList.add(new Item(s));
        }
        List<String> removes = Files.readAllLines(Paths.get("removeToDoListoutput.txt"));   // CPSC 210 FileReaderWriter
        for (String s : removes) {
            removedToDoList.add(new Item(s));
        }
        printToDoList();
    }

    @Override
    // MODIFIES: removeToDoListoutput.txt, toDoListoutput.txt
    // EFFECT: saves toDos inside toDoList and removedToDoList into removedToDoListoutput.txt and toDoListoutput.txt
    public void save(String toDo, String removeList) throws IOException {                                        // https://stackoverflow.com/questions/6548157/how-to-write-an-arraylist-of-strings-into-a-text-file
        FileWriter todo = new FileWriter(toDo);
        for (Item td : toDoList) {
            todo.write(td.getToDoName() + "\n");
        }
        todo.close();
        FileWriter remove = new FileWriter(removeList);
        for (Item rtd : removedToDoList) {
            remove.write(rtd.getToDoName() + "\n");
        }
        remove.close();
    }
}


