package model;

import exception.EmptyNormalToDoListException;
import exception.EmptyUrgentToDoListException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class UrgentToDoListTest {
    private ToDoList urgenttodo;

    @BeforeEach
    public void setUp() throws IOException {
        urgenttodo = new UrgentToDo();
    }

    @Test
    public void testToDo() {
        ArrayList<ToDo> todoList = urgenttodo.getToDoList();
        assertEquals(0, todoList.size());
        ArrayList<ToDo> removedToDoList = urgenttodo.getRemovedToDoList();
        assertEquals(0, removedToDoList.size());
    }

    @Test
    public void testAddToDo() {
        urgenttodo.addToDo("CPSC-210");
        ArrayList<ToDo> toDoList = urgenttodo.getToDoList();
        assertEquals(1, toDoList.size());
        assertEquals("CPSC-210", toDoList.get(0).getToDoName());
    }

    @Test
    public void testAddToDoLots() {
        urgenttodo.addToDo("CPSC-210");
        urgenttodo.addToDo("MATH-221");
        urgenttodo.addToDo("MATH-200");
        ArrayList<ToDo> toDoList = urgenttodo.getToDoList();
        assertEquals(3, toDoList.size());
        assertEquals("CPSC-210", toDoList.get(0).getToDoName());
        assertEquals("MATH-221", toDoList.get(1).getToDoName());
        assertEquals("MATH-200", toDoList.get(2).getToDoName());
    }

    @Test
    public void testMoveToDoToRemovedToDo() {
        ArrayList<ToDo> todoList = urgenttodo.getToDoList();
        ArrayList<ToDo> removedToDoList = urgenttodo.getRemovedToDoList();
        assertEquals(0, todoList.size());
        assertEquals(0, removedToDoList.size());
        todoList.add(new ToDo("CPSC"));
        todoList.add(new ToDo("MATH"));
        todoList.add(new ToDo("ENGL"));
        todoList.add(new ToDo("PSYC"));
        assertEquals(4, todoList.size());
        assertTrue(searchThroughListForName(todoList, "ENGL"));
        assertEquals(0, removedToDoList.size());
        urgenttodo.moveToDoToRemovedToDo(3);
        assertEquals(3, todoList.size());
        assertEquals(1, removedToDoList.size());
        assertFalse(searchThroughListForName(todoList, "ENGL"));
        assertEquals("ENGL", removedToDoList.get(0).getToDoName());
    }

    @Test
    public void testGetToDoList() {
        ArrayList<ToDo> todoList = urgenttodo.getToDoList();
        assertEquals(0, todoList.size());
        urgenttodo.addToDo("CPSC");
        urgenttodo.addToDo("MATH");
        assertEquals(2, todoList.size());
        assertEquals("CPSC", todoList.get(0).getToDoName());
        assertEquals("MATH", todoList.get(1).getToDoName());
    }

    @Test
    public void testRemovedToDo() throws EmptyNormalToDoListException, EmptyUrgentToDoListException {
        urgenttodo.addToDo("Testing todo");
        urgenttodo.addToDo("Another testing todo");
        ArrayList<ToDo> todoList = urgenttodo.getToDoList();
        assertEquals(2, todoList.size());
        assertEquals("Testing todo", todoList.get(0).getToDoName());
        assertEquals("Another testing todo", todoList.get(1).getToDoName());
        urgenttodo.removeToDo(1);
        assertEquals(1, todoList.size());
        assertEquals("Another testing todo", todoList.get(0).getToDoName());
    }

    @Test
    public void testRemoveToDoEmptyThrowEmptyUrgentToDoListException() {
        try {
            urgenttodo.removeToDo(0);
            fail("Suppose to throw EmptyUrgentToDoListException");
        } catch (EmptyUrgentToDoListException e) {

        } catch (EmptyNormalToDoListException e) {
            fail("Wrong type of expception thrown");
        }
    }

    @Test
    public void testGetRemovedToDoList() {
        ArrayList<ToDo> removedToDoList = urgenttodo.getRemovedToDoList();
        assertEquals(0, removedToDoList.size());
        removedToDoList.add(new ToDo("CPSC"));
        removedToDoList.add(new ToDo("MATH"));
        assertEquals(2, removedToDoList.size());
        assertEquals("CPSC", removedToDoList.get(0).getToDoName());
        assertEquals("MATH", removedToDoList.get(1).getToDoName());
    }

    public boolean searchThroughListForName(ArrayList<ToDo> todolist, String s) {
        for (ToDo td : todolist) {
            if (td.getToDoName() == s) {
                return true;
            }
        }
        return false;
    }
}
