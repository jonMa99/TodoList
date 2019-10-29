package model;

public class UrgentItem extends ToDo {

    public UrgentItem(String s) {
        super("!!!!! " + s + " !!!!!");
    }

    public UrgentItem(String s, String l) {
        super("!!!!! " + s + " !!!!!", l);
    }
}
