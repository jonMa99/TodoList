package model;

public abstract class ToDo {
    private String todoName;

    // EFFECT: constructs a todo with s as todoName
    public ToDo(String s) {
        todoName = s;
    }

    // EFFECT: returns todoName
    public String getToDoName() {
        return todoName;
    }
}
