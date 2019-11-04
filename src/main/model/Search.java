package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Search {
    private ToDoList toDoList;
    private HashMap<Location, ArrayList<ToDo>> search;

    public Search() {
        search = new HashMap<>();
    }

    // EFFECT: makes set of locations from ToDos in toDoList
    public Set<Location> getKeysAndMakeList(ToDoList toDoList) {
        Set<Location> listOfLocation = new HashSet<Location>();
        for (ToDo td : toDoList.getToDoList()) {
            listOfLocation.add(td.getLocation());
        }
        return listOfLocation;
    }

    //EFFECT: makes a hashmap using keys and empty ArrayList
    public HashMap<Location, ArrayList<ToDo>> makeKeyAndEmptyValueHashMap(ToDoList toDoList) {
        for (Location l : getKeysAndMakeList(toDoList)) {
            search.put(l, new ArrayList<ToDo>());
        }
        return search;
    }

    // EFFECT: produces hashmap with location as key and ArrayList<ToDo> as values
    public HashMap<Location, ArrayList<ToDo>> fillHashMap(ToDoList toDoList) {
        HashMap<Location, ArrayList<ToDo>> search = makeKeyAndEmptyValueHashMap(toDoList);
        for (ToDo t : toDoList.getToDoList()) {
            ArrayList<ToDo> list = search.get(t.getLocation());
            list.add(t);
        }
        return search;
    }
}
