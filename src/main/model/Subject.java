package model;

import java.util.List;

public abstract class Subject {
    private Observer observer;

    public Subject() {
        observer = null;
    }

    //EFFECT: sets observer
    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    //EFFECT: notifies observer
    public void notifyObserver(ToDo todo) {
        observer.update(todo);
    }
}
