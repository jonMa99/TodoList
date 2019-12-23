package model;

import java.util.Objects;

public class ToDo {
    private String todoName;
    private Location location;

    // EFFECT: constructs a todo with s as todoName and Nowhere location
    public ToDo(String s) {
        todoName = s;
        location = new Location("Nowhere");
    }

    // EFFECT: constructs a todo with s as todoName and l as location
    public ToDo(String s, String l) {
        todoName = s;
        location = new Location(l);
    }

    // EFFECT: returns todoName
    public String getToDoName() {
        return todoName;
    }

    // EFFECT: returns location
    public Location getLocation() {
        return location;
    }

    // MODIFIES: this, l
    // EFFECT: adds location to ToDo and adds this to location if location != l, else do nothing
    public void addLocation(Location l) {
        if (location != l) {
            location = l;
            l.addToDo(this);
        }
    }

    //MODIFIES: this
    //EFFECT: sets location to "Nowhere" and removes this from location if location contains this todo, else do nothing
    public void removeLocation() {
        if (!getLocation().equals("Nowhere")) {
            location.removeToDo(this);
            location = new Location("Nowhere");
        }
    }

    @Override
    // EFFECT: return true if todo == object based on todoName and location
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ToDo toDo = (ToDo) o;
        return todoName.equals(toDo.todoName)
                && location.equals(toDo.location);
    }

    @Override
    // EFFECT: returns hashcode based on todoName and location
    public int hashCode() {
        return Objects.hash(todoName, location);
    }
}
