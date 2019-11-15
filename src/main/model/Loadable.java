package model;

import java.io.IOException;

public interface Loadable {

    // MODIFIES: removeToDoList, toDoList, urgentToDoList
    // EFFECT: loads removeToDoListoutput.txt and toDoListoutput.txt files
    public void load(String toDo, String urgenttoDo, String removeList) throws IOException;
}
