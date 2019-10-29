package model;

import java.util.ArrayList;
import java.util.Objects;

public class Location {
    private String location;
    private ArrayList<ToDo> todos;

    // EFFECT: constructs Location with s as location and a new ArrayList for todos
    public Location(String s) {
        location = s;
        todos = new ArrayList<>();
    }

    // EFFECT: returns location name
    public String getLocationName() {
        return location;
    }

    // EFFECT: returns todos
    public ArrayList<ToDo> getTodos() {
        return todos;
    }

    //MODIFIES: this, t
    //EFFECT: adds t to todos and adds this to t if !todos.contains(t), else do nothing
    public void addToDo(ToDo t) {
        if (!todos.contains(t)) {
            todos.add(t);
            t.addLocation(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Location location1 = (Location) o;
        return location.equals(location1.location);
    }

    @Override
    public int hashCode() {
        return Objects.hash(location);
    }
}
