package model;

import exception.EmptyNormalToDoListException;
import exception.EmptyUrgentToDoListException;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class ToDoList implements Saveable, Loadable {
    protected ArrayList<ToDo> normalToDoList;
    protected ArrayList<ToDo> urgenttoDoList;
    protected ArrayList<ToDo> removedToDoList;
    protected String typeoflist;

    // EFFECT: Creates new ToDo
    public ToDoList() throws IOException {
        normalToDoList = new ArrayList<ToDo>();
        urgenttoDoList = new ArrayList<ToDo>();
        removedToDoList = new ArrayList<>();
        load("./data/toDoListoutput.txt", "./data/urgenttoDoListoutput.txt",
                "./data/removeToDoListoutput.txt");
    }

    // MODIFIES: this
    // EFFECT: adds todo into the toDoList
    public abstract void addToDo(String todo);
//    public void addToDo(String todo) {
//        if (typeoflist.equals("normal")) {
//            normalToDoList.add(new ToDo(todo));
//        } else if (typeoflist.equals("urgent")) {
//            urgenttoDoList.add(new ToDo(todo));
//        }
//    }

    // REQUIRES: toDoList has atleast 1 todo
    // EFFECT: removes specified todo and moves it to removedToDo
    public abstract void removeToDo(int removeNum) throws EmptyNormalToDoListException, EmptyUrgentToDoListException;
//    {
//        System.out.println("You have removed: " + normalToDoList.get(removeNum - 1).getToDoName());
//        moveToDoToRemovedToDo(removeNum);
//    }

    //EFFECT: prints a list of normaltodos and urgenttodos
    public abstract void printToDoList();
//    {
//        System.out.println("Current ToDos");
//        for (int i = 1; i <= normalToDoList.size(); i++) {
//            System.out.println(i + " : " + (normalToDoList.get(i - 1).getToDoName()));
//        }
//        System.out.println("");
//        System.out.println("Current Urgent ToDos");
//        System.out.println("!!!!!");
//        for (int i = 1; i <= urgenttoDoList.size(); i++) {
//            System.out.println(i + " : " + (urgenttoDoList.get(i - 1).getToDoName()));
//        }
//        System.out.println("!!!!!");
//    }

    // MODIFIES: this
    // EFFECT: moves todo from toDoList to removedToDoList
    public abstract void moveToDoToRemovedToDo(int removeNum);
//    {
//        ToDo moveToRemove = normalToDoList.get(removeNum - 1);
//        normalToDoList.remove(removeNum - 1);
//        removedToDoList.add(moveToRemove);
//    }

    // EFFECT: returns toDoList
    public abstract ArrayList<ToDo> getToDoList();

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
            normalToDoList.add(new ToDo(s));
        }
        List<String> urgents = Files.readAllLines(Paths.get(urgenttoDo));   // CPSC 210 FileReaderWriter
        for (String s : urgents) {
            urgenttoDoList.add(new ToDo(s));
        }
        List<String> removes = Files.readAllLines(Paths.get(removeList));   // CPSC 210 FileReaderWriter
        for (String s : removes) {
            removedToDoList.add(new ToDo(s));
        }
        printToDoList();
    }

    @Override
    // MODIFIES: removeToDoListoutput.txt, toDoListoutput.txt
    // EFFECT: saves toDos inside toDoList and removedToDoList into removedToDoListoutput.txt and toDoListoutput.txt
    public void save(String toDo, String urgenttoDo, String removeList) throws IOException {                                        // https://stackoverflow.com/questions/6548157/how-to-write-an-arraylist-of-strings-into-a-text-file
        FileWriter todo = new FileWriter(toDo, true);
        for (ToDo td : normalToDoList) {
            todo.write(td.getToDoName() + "\n");
        }
        todo.close();
        FileWriter urgent = new FileWriter(urgenttoDo, true);
        for (ToDo utd : urgenttoDoList) {
            urgent.write(utd.getToDoName() + "\n");
        }
        urgent.close();
        FileWriter remove = new FileWriter(removeList, true);
        for (ToDo rtd : removedToDoList) {
            remove.write(rtd.getToDoName() + "\n");
        }
        remove.close();
    }
}


