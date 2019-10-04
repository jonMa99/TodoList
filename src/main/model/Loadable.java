package model;

import java.io.IOException;

public interface Loadable {

    // EFFECT: loads removeToDoListoutput.txt and toDoListoutput.txt files
    public void load(String toDo, String removeList) throws IOException;
}
