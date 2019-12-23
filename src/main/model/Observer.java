package model;

public interface Observer {

    // EFFECT: updates based on todo
    public void update(ToDo todo);
}
