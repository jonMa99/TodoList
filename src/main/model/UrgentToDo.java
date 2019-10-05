package model;

import java.io.IOException;
import java.util.ArrayList;

public class UrgentToDo extends ToDoList {

    public UrgentToDo() throws IOException {
        urgenttoDoList = new ArrayList<ToDo>();
//        load("urgenttoDoListoutput.txt", "removeToDoListoutput.txt");
    }

    @Override
    // MODIFIES: this
    // EFFECT: adds urgenttodo into the urgenttoDoList
    public void addToDo(String todo) {
        urgenttoDoList.add(new ToDo(todo));
    }

    @Override
    // REQUIRES: urgenttoDoList has atleast 1 urgenttodo
    // EFFECT: removes specified urgenttodo and moves it to removedToDo
    public void removeToDo(int removeNum) {
        System.out.println("You have removed: " + urgenttoDoList.get(removeNum - 1).getToDoName());
        moveToDoToRemovedToDo(removeNum);
    }

    @Override
    public void printToDoList() {
        System.out.println("Current Urgent ToDos");
        System.out.println("!!!!!");
        for (int i = 1; i <= urgenttoDoList.size(); i++) {
            System.out.println(i + " : " + (urgenttoDoList.get(i - 1).getToDoName()));
        }
        System.out.println("!!!!!");
        System.out.println("");
    }

    @Override
    // REQUIRES: urgenttoDoList has atleast 1 todo
    // MODIFIES: this
    // EFFECT: moves urgenttodo from toDoList to removedToDoList
    public void moveToDoToRemovedToDo(int removeNum) {
        ToDo moveToRemove = urgenttoDoList.get(removeNum - 1);
        urgenttoDoList.remove(removeNum - 1);
        removedToDoList.add(moveToRemove);
    }

    @Override
    // EFFECT: returns urgenttoDoList
    public ArrayList<ToDo> getToDoList() {
        return urgenttoDoList;
    }
}
