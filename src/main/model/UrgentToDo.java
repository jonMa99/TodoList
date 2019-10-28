//package model;
//
//import exception.EmptyNormalToDoListException;
//import exception.EmptyUrgentToDoListException;
//import exception.TooManyToDosException;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class UrgentToDo extends ToDoList {
//
//    public UrgentToDo() throws IOException {
//        urgenttoDoList = new ArrayList<ToDo>();
//        typeoflist = "urgent";
////        load("urgenttoDoListoutput.txt", "removeToDoListoutput.txt");
//    }
//
//    @Override
//    // MODIFIES: this
//    // EFFECT: adds urgenttodo into the urgenttoDoList
//    public void addToDo(String todo) throws TooManyToDosException {
//        if (urgenttoDoList.size() >= MAXTODOLISTSIZE) {
//            throw new TooManyToDosException();
//        }
//        urgenttoDoList.add(new UrgentItem(todo));
//    }
//
//    @Override
//    // EFFECT: removes specified urgenttodo and moves it to removedToDo
//    public void removeToDo(int removeNum) throws EmptyUrgentToDoListException {
//        if (urgenttoDoList.size() == 0) {
//            throw new EmptyUrgentToDoListException();
//        }
//        System.out.println("You have removed: " + urgenttoDoList.get(removeNum - 1).getToDoName());
//        moveToDoToRemovedToDo(removeNum);
//    }
//
//    @Override
//    // REQUIRES: urgenttoDoList has atleast 1 todo
//    // MODIFIES: this
//    // EFFECT: moves urgenttodo from toDoList to removedToDoList
//    public void moveToDoToRemovedToDo(int removeNum) {
//        ToDo moveToRemove = urgenttoDoList.get(removeNum - 1);
//        urgenttoDoList.remove(removeNum - 1);
//        removedToDoList.add(moveToRemove);
//    }
//
//    @Override
//    public void printToDoList() {
//        System.out.println("Current Urgent ToDos");
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!");
//        for (int i = 1; i <= urgenttoDoList.size(); i++) {
//            System.out.println(i + " : " + (urgenttoDoList.get(i - 1).getToDoName()) + "   !!!!!");
//        }
//        System.out.println("!!!!!!!!!!!!!!!!!!!!!!");
//        System.out.println("");
//    }
//
//
//    // EFFECT: returns urgenttoDoList
//    public ArrayList<ToDo> getToDoList() {
//        return urgenttoDoList;
//    }
//}
