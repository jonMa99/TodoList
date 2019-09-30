package test;

import model.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoTest {
    private ToDo todo;

    @BeforeEach
    public void setUp() throws IOException {
        todo = new ToDo();
    }

    @Test
    public void testToDo() {
        ArrayList<String> todoList = todo.getToDoList();
        assertEquals(0, todoList.size());
        ArrayList<String> removedToDoList = todo.getRemovedToDoList();
        assertEquals(0, removedToDoList.size());
    }

    @Test
    public void testAddToDo() {
        todo.addToDo("CPSC-210");
        ArrayList<String> toDoList = todo.getToDoList();
        assertEquals(1, toDoList.size());
        assertEquals("CPSC-210",toDoList.get(0));
    }

    @Test
    public void testAddToDoLots() {
        todo.addToDo("CPSC-210");
        todo.addToDo("MATH-221");
        todo.addToDo("MATH-200");
        ArrayList<String> toDoList = todo.getToDoList();
        assertEquals(3, toDoList.size());
        assertEquals("CPSC-210", toDoList.get(0));
        assertEquals("MATH-221", toDoList.get(1));
        assertEquals("MATH-200", toDoList.get(2));
    }

//    @Test  //todo finish this up
//    public void testRemovedToDo() {
//        todo.addToDo("Testing todo");
//        todo.addToDo("Another testing todo");
//        ArrayList<String> todoList = todo.getToDoList();
//        assertEquals(2, todoList.size());
//        assertEquals("Testing todo", todoList.get(0));
//        assertEquals("Another testing todo", todoList.get(1));
//        todo.removeToDo();
//        assertEquals(1, todoList.size());
//        assertEquals("Another testing todo", todoList.get(0));
//    }

    @Test
    public void testMoveToDoToRemovedToDo() {
        ArrayList<String> todoList = todo.getToDoList();
        ArrayList<String> removedToDoList = todo.getRemovedToDoList();
        assertEquals(0, todoList.size());
        assertEquals(0, removedToDoList.size());
        todoList.add("CPSC");
        todoList.add("MATH");
        todoList.add("ENGL");
        todoList.add("PSYC");
        assertEquals(4, todoList.size());
        assertTrue(todoList.contains("ENGL"));
        assertEquals(0, removedToDoList.size());
        todo.moveToDoToRemovedToDo(3);
        assertEquals(3, todoList.size());
        assertEquals(1, removedToDoList.size());
        assertFalse(todoList.contains("ENGL"));
        assertEquals("ENGL", removedToDoList.get(0));
    }

    @Test
    public void testGetToDoList() {
        ArrayList<String> todoList = todo.getToDoList();
        assertEquals(0, todoList.size());
        todo.addToDo("CPSC");
        todo.addToDo("MATH");
        assertEquals(2, todoList.size());
        assertEquals("CPSC", todoList.get(0));
        assertEquals("MATH", todoList.get(1));
    }

    @Test
    public void testGetRemovedToDoList() {
        ArrayList<String> removedToDoList = todo.getRemovedToDoList();
        assertEquals(0, removedToDoList.size());
        removedToDoList.add("CPSC");
        removedToDoList.add("MATH");
        assertEquals(2, removedToDoList.size());
        assertEquals("CPSC", removedToDoList.get(0));
        assertEquals("MATH", removedToDoList.get(1));
    }
}

