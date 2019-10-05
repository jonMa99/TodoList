package model;

import java.io.IOException;

public interface Saveable {

    // MODIFIES: removeToDoListoutput.txt, toDoListoutput.txt, urgentoDoListoutput.txt
    // EFFECT: saves strings inside toDoList, urgenttoDoList and removedToDoList
    //         into removedToDoListoutput.txt, toDoListoutput.txt and urgentoDoListoutput.txt
    public void save(String toDo, String urgenttoDo, String removeList) throws IOException;
}
