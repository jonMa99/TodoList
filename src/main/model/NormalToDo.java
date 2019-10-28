//package model;
//
//import exception.EmptyNormalToDoListException;
//import exception.TooManyToDosException;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class NormalToDo extends ToDoList {
//
//    public NormalToDo() throws IOException {
//        toDoList = new ArrayList<ToDo>();
//        typeoflist = "normal";
////        load("toDoListoutput.txt", "removeToDoListoutput.txt");
//    }
//
//    @Override
//    // MODIFIES: this
//    // EFFECT: adds normaltodo into the normaltoDoList
//    public void addToDo(String todo) throws TooManyToDosException {
//        if (toDoList.size() >= MAXTODOLISTSIZE) {
//            throw new TooManyToDosException();
//        }
//        toDoList.add(new NormalItem(todo));
//    }
//
//    @Override
//    // EFFECT: removes specified normaltodo and moves it to removedToDo
//    public void removeToDo(int removeNum) throws EmptyNormalToDoListException {
//        if (toDoList.size() == 0) {
//            throw new EmptyNormalToDoListException();
//        }
//        System.out.println("You have removed: " + toDoList.get(removeNum - 1).getToDoName());
//        moveToDoToRemovedToDo(removeNum);
//    }
//
//    @Override
//    // REQUIRES: normaltoDoList has atleast 1 normaltodo
//    // MODIFIES: this
//    // EFFECT: moves normaltodo from normaltoDoList to removedToDoList
//    public void moveToDoToRemovedToDo(int removeNum) {
//        ToDo moveToRemove = toDoList.get(removeNum - 1);
//        toDoList.remove(removeNum - 1);
//        removedToDoList.add(moveToRemove);
//    }
//
//    @Override
//    //EFFECT: prints a list of normaltodos
//    public void printToDoList() {
//        System.out.println("Current ToDos");
//        for (int i = 1; i <= toDoList.size(); i++) {
//            System.out.println(i + " : " + (toDoList.get(i - 1).getToDoName()));
//        }
//        System.out.println("");
//    }
//
//
//    // EFFECT: returns normaltoDoList
//    public ArrayList<ToDo> getToDoList() {
//        return toDoList;
//    }
//}
//
