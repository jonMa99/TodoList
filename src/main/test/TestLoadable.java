package test;

import model.Loadable;
import model.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLoadable {
    private ToDo toDo;

    @BeforeEach
    public void setUp() throws IOException {
        toDo = new ToDo();
        new FileWriter("toDoListoutput.txt", false).close();  //https://www.baeldung.com/java-delete-file-contents
    }

    @Test
    public void testLoad() throws IOException {
        PrintWriter output = new PrintWriter("toDoListoutput.txt");
        output.println("CPSC 110");
        output.println("CPSC 210");
        output.println("MATH 200");
        output.close();
        testLoad(toDo);
        ArrayList<String> toDoList = toDo.getToDoList();
        assertTrue(toDoList.contains("CPSC 110"));
        assertTrue(toDoList.contains("CPSC 210"));
        assertTrue(toDoList.contains("MATH 200"));
    }

    public void testLoad(Loadable l) throws IOException {
        l.load();
    }
}
