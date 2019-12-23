package model;

import java.io.IOException;

public interface Loadable {

    // MODIFIES: removeToDoList, toDoList, urgentToDoList
    // EFFECT: loads removeToDoListoutput.txt, toDoListoutput.txt files locationOutput.txt
    public void load(String toDo, String urgenttoDo, String removeList, String locationOutput) throws IOException;
}
