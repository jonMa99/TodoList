package model;

import java.io.IOException;

public interface Saveable {

    // MODIFIES: removeToDoListoutput.txt, toDoListoutput.txt
    // EFFECT: saves strings inside toDoList and removedToDoList into removedToDoListoutput.txt and toDoListoutput.txt
    public void save(String toDo, String removeList) throws IOException;
}
