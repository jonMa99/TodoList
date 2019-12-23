package model;

public class PrintToDo implements Observer {
    @Override
    //EFFECT: prints message to console that todo has been added
    public void update(ToDo todo) {
        System.out.println("You have added: " + todo.getToDoName());
        System.out.println("");
    }
}
