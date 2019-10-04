package test;

import model.Loadable;
import model.NormalToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLoadable {
    private NormalToDo normalToDo;

    @BeforeEach
    public void setUp() throws IOException {
        normalToDo = new NormalToDo();
    }

    @Test
    public void testLoad() throws IOException {
        testLoad(normalToDo);
        ArrayList<String> toDoList = normalToDo.getToDoList();
        assertTrue(toDoList.contains("CPSC 110"));
        assertTrue(toDoList.contains("CPSC 210"));
        assertTrue(toDoList.contains("MATH 200"));
        assertEquals("CPSC 210", toDoList.get(0));
        assertEquals("CPSC 110", toDoList.get(1));
        assertEquals("MATH 200", toDoList.get(2));
        assertEquals(3, toDoList.size());
    }

    public void testLoad(Loadable l) throws IOException {
        l.load("testinput.txt", "testinputremoved.txt");
    }
}
