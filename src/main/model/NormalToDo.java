package model;

import java.io.IOException;
import java.util.ArrayList;

public class NormalToDo extends ToDoList {

    public NormalToDo() throws IOException {
        normalToDoList = new ArrayList<ToDo>();
//        load("toDoListoutput.txt", "removeToDoListoutput.txt");
    }

    @Override
    // MODIFIES: this
    // EFFECT: adds normaltodo into the normaltoDoList
    public void addToDo(String todo) {
        normalToDoList.add(new ToDo(todo));
    }

    @Override
    // REQUIRES: normaltoDoList has atleast 1 normaltodo
    // EFFECT: removes specified normaltodo and moves it to removedToDo
    public void removeToDo(int removeNum) {
        System.out.println("You have removed: " + normalToDoList.get(removeNum - 1).getToDoName());
        moveToDoToRemovedToDo(removeNum);
    }

    @Override
    // REQUIRES: normaltoDoList has atleast 1 normaltodo
    // MODIFIES: this
    // EFFECT: moves normaltodo from normaltoDoList to removedToDoList
    public void moveToDoToRemovedToDo(int removeNum) {
        ToDo moveToRemove = normalToDoList.get(removeNum - 1);
        normalToDoList.remove(removeNum - 1);
        removedToDoList.add(moveToRemove);
    }

    @Override
    //EFFECT: prints a list of normaltodos
    public void printToDoList() {
        System.out.println("Current ToDos");
        for (int i = 1; i <= normalToDoList.size(); i++) {
            System.out.println(i + " : " + (normalToDoList.get(i - 1).getToDoName()));
        }
        System.out.println("");
    }

    @Override
    // EFFECT: returns normaltoDoList
    public ArrayList<ToDo> getToDoList() {
        return normalToDoList;
    }
}

