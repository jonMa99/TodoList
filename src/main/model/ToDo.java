package model;

import java.util.Objects;

public class ToDo {
    private String todoName;
    private Location location;

    // EFFECT: constructs a todo with s as todoName and null location
    public ToDo(String s) {
        todoName = s;
        location = new Location("Nowhere");
    }

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

    @Override
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
    public int hashCode() {
        return Objects.hash(todoName, location);
    }
}
