package model;

public class PrintToDo implements Observer {
    @Override
    public void update(ToDo todo) {
        System.out.println("You have added: " + todo.getToDoName());
        System.out.println("");
    }
}
