package test;

import model.NormalToDo;
import model.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class NormalToDoTest {
    private NormalToDo todo;

    @BeforeEach
    public void setUp() throws IOException {
        todo = new NormalToDo();
    }

    @Test
    public void testToDo() {
        ArrayList<Item> todoList = todo.getToDoList();
        assertEquals(0, todoList.size());
        ArrayList<Item> removedToDoList = todo.getRemovedToDoList();
        assertEquals(0, removedToDoList.size());
    }

    @Test
    public void testAddToDo() {
        todo.addToDo("CPSC-210");
        ArrayList<Item> toDoList = todo.getToDoList();
        assertEquals(1, toDoList.size());
        assertEquals("CPSC-210",toDoList.get(0).getToDoName());
    }

    @Test
    public void testAddToDoLots() {
        todo.addToDo("CPSC-210");
        todo.addToDo("MATH-221");
        todo.addToDo("MATH-200");
        ArrayList<Item> toDoList = todo.getToDoList();
        assertEquals(3, toDoList.size());
        assertEquals("CPSC-210", toDoList.get(0).getToDoName());
        assertEquals("MATH-221", toDoList.get(1).getToDoName());
        assertEquals("MATH-200", toDoList.get(2).getToDoName());
    }

    @Test
    public void testRemovedToDo() {
        todo.addToDo("Testing todo");
        todo.addToDo("Another testing todo");
        ArrayList<Item> todoList = todo.getToDoList();
        assertEquals(2, todoList.size());
        assertEquals("Testing todo", todoList.get(0).getToDoName());
        assertEquals("Another testing todo", todoList.get(1).getToDoName());
        todo.removeToDo(1);
        assertEquals(1, todoList.size());
        assertEquals("Another testing todo", todoList.get(0).getToDoName());
    }

    @Test
    public void testMoveToDoToRemovedToDo() {
        ArrayList<Item> todoList = todo.getToDoList();
        ArrayList<Item> removedToDoList = todo.getRemovedToDoList();
        assertEquals(0, todoList.size());
        assertEquals(0, removedToDoList.size());
        todoList.add(new Item("CPSC"));
        todoList.add(new Item("MATH"));
        todoList.add(new Item("ENGL"));
        todoList.add(new Item("PSYC"));
        assertEquals(4, todoList.size());
        assertTrue(searchThroughListForName(todoList, "ENGL"));
        assertEquals(0, removedToDoList.size());
        todo.moveToDoToRemovedToDo(3);
        assertEquals(3, todoList.size());
        assertEquals(1, removedToDoList.size());
        assertFalse(searchThroughListForName(todoList, "ENGL"));
        assertEquals("ENGL", removedToDoList.get(0).getToDoName());
    }

    @Test
    public void testGetToDoList() {
        ArrayList<Item> todoList = todo.getToDoList();
        assertEquals(0, todoList.size());
        todo.addToDo("CPSC");
        todo.addToDo("MATH");
        assertEquals(2, todoList.size());
        assertEquals("CPSC", todoList.get(0).getToDoName());
        assertEquals("MATH", todoList.get(1).getToDoName());
    }

    @Test
    public void testGetRemovedToDoList() {
        ArrayList<Item> removedToDoList = todo.getRemovedToDoList();
        assertEquals(0, removedToDoList.size());
        removedToDoList.add(new Item("CPSC"));
        removedToDoList.add(new Item("MATH"));
        assertEquals(2, removedToDoList.size());
        assertEquals("CPSC", removedToDoList.get(0).getToDoName());
        assertEquals("MATH", removedToDoList.get(1).getToDoName());
    }

    public boolean searchThroughListForName(ArrayList<Item> todolist, String s) {
        for (Item td : todolist) {
            if (td.getToDoName() == s) {
                return true;
            }
        }
        return false;
    }
}

