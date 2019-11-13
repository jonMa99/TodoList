package model;

public class PrintToDo implements Observer {
    @Override
    //EFFECT: prints message that todo has been added to console
    public void update(ToDo todo) {
        System.out.println("You have added: " + todo.getToDoName());
        System.out.println("");
    }
}
