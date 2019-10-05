package test;

import model.Loadable;
import model.ToDoList;
import model.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLoadable {
    private ToDoList toDoList;

    @BeforeEach
    public void setUp() throws IOException {
        toDoList = new ToDoList();
    }

    @Test
    public void testLoad() throws IOException {
        testLoad(toDoList);
        ArrayList<ToDo> toDoList = this.toDoList.getToDoList();
        assertEquals("CPSC 210", toDoList.get(0).getToDoName());
        assertEquals("CPSC 110", toDoList.get(1).getToDoName());
        assertEquals("MATH 200", toDoList.get(2).getToDoName());
        assertEquals(3, toDoList.size());
    }

    public void testLoad(Loadable l) throws IOException {
        l.load("testinput.txt", "testinputremoved.txt");
    }
}
