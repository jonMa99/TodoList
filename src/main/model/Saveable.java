package model;

import java.io.IOException;

public interface Saveable {

    // MODIFIES: removeToDoListoutput.txt, toDoListoutput.txt, urgentoDoListoutput.txt, locationOutput.txt
    // EFFECT: saves strings inside toDoList, urgenttoDoList and removedToDoList and locations from toDoList
    //         into removedToDoListoutput.txt, toDoListoutput.txt, urgentoDoListoutput.txt and locationOutput.txt
    public void save(String toDo, String urgenttoDo, String removeList, String locationList) throws IOException;
}
