package model;

import java.util.List;

public abstract class Subject {
    private Observer observer;

    // EFFECT: sets observer
    public Subject() {
        observer = null;
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public void notifyObserver(ToDo todo) {
        observer.update(todo);
    }
}
