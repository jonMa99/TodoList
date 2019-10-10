package model;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLoadable {
    private ToDoList normaltoDoList;
    private ToDoList urgenttoDoList;

    @BeforeEach
    public void setUp() throws IOException {
        normaltoDoList = new NormalToDo();
        urgenttoDoList = new UrgentToDo();
    }

    @Test
    public void testLoad() throws IOException {
        testLoad(normaltoDoList);
        ArrayList<ToDo> toDoList = normaltoDoList.getToDoList();
        assertEquals("CPSC 210", toDoList.get(0).getToDoName());
        assertEquals("CPSC 110", toDoList.get(1).getToDoName());
        assertEquals("MATH 200", toDoList.get(2).getToDoName());
        assertEquals(3, toDoList.size());
    }

    public void testLoad(Loadable l) throws IOException {
        l.load("./data/testinput.txt", "./data/urgenttestinput.txt", "./data/testinputremoved.txt");
    }
}
