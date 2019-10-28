package model;

import exception.EmptyNormalToDoListException;
import exception.EmptyUrgentToDoListException;
import exception.TooManyToDosException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoListTest {
    private ToDoList todo;

    @BeforeEach
    public void setUp() throws IOException {
        todo = new ToDoList();
    }

    @Test
    public void testToDo() {
        ArrayList<ToDo> todoList = todo.getToDoList();
        assertEquals(0, todoList.size());
        ArrayList<ToDo> removedToDoList = todo.getRemovedToDoList();
        assertEquals(0, removedToDoList.size());
    }

    @Test
    public void testAddToDo() {
        try {
            todo.addToDo("CPSC-210");
        } catch (TooManyToDosException e) {
            fail("No exceptions thrown");
        }
        ArrayList<ToDo> toDoList = todo.getToDoList();
        assertEquals(1, toDoList.size());
        assertEquals("CPSC-210",toDoList.get(0).getToDoName());
    }

    @Test
    public void testAddToDoLots() {
        try {
            todo.addToDo("CPSC-210");
            todo.addToDo("MATH-221");
            todo.addToDo("MATH-200");
        } catch (TooManyToDosException e) {
            fail("No over max size");
        }
        ArrayList<ToDo> toDoList = todo.getToDoList();
        assertEquals(3, toDoList.size());
        assertEquals("CPSC-210", toDoList.get(0).getToDoName());
        assertEquals("MATH-221", toDoList.get(1).getToDoName());
        assertEquals("MATH-200", toDoList.get(2).getToDoName());
    }

    @Test
    public void testAddOverMaxToDo() {
        try {
            todo.addToDo("1");
            todo.addToDo("2");
            todo.addToDo("3");
            todo.addToDo("4");
            todo.addToDo("5");
            todo.addToDo("6");
            todo.addToDo("7");
            todo.addToDo("8");
            todo.addToDo("9");
            todo.addToDo("10");
            todo.addToDo("11");
            fail("Over max todo list size");
        } catch (TooManyToDosException e) {

        }
    }

    @Test
    public void testRemovedToDo() throws EmptyNormalToDoListException, EmptyUrgentToDoListException {
        try {
            todo.addToDo("Testing todo");
            todo.addToDo("Another testing todo");
        } catch (TooManyToDosException e) {
            fail("No exceptions thrown");
        }
        ArrayList<ToDo> todoList = todo.getToDoList();
        assertEquals(2, todoList.size());
        assertEquals("Testing todo", todoList.get(0).getToDoName());
        assertEquals("Another testing todo", todoList.get(1).getToDoName());
        todo.removeToDo(1);
        assertEquals(1, todoList.size());
        assertEquals("Another testing todo", todoList.get(0).getToDoName());
    }

    @Test
    public void testRemovedToDoEmptyThrowEmptyNormalToDoListException() {
        try {
            todo.removeToDo(0);
            fail("Should throw EmptyNormalToDoListException");
        } catch (EmptyNormalToDoListException e) {

        }
    }


        @Test
    public void testMoveToDoToRemovedToDo() {
        ArrayList<ToDo> todoList = todo.getToDoList();
        ArrayList<ToDo> removedToDoList = todo.getRemovedToDoList();
        assertEquals(0, todoList.size());
        assertEquals(0, removedToDoList.size());
        todoList.add(new NormalItem("CPSC"));
        todoList.add(new NormalItem("MATH"));
        todoList.add(new NormalItem("ENGL"));
        todoList.add(new NormalItem("PSYC"));
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
        ArrayList<ToDo> todoList = todo.getToDoList();
        assertEquals(0, todoList.size());
        try {
            todo.addToDo("CPSC");
            todo.addToDo("MATH");
        } catch (TooManyToDosException e) {
            fail("Not over max size");
        }
        assertEquals(2, todoList.size());
        assertEquals("CPSC", todoList.get(0).getToDoName());
        assertEquals("MATH", todoList.get(1).getToDoName());
    }

    @Test
    public void testGetRemovedToDoList() {
        ArrayList<ToDo> removedToDoList = todo.getRemovedToDoList();
        assertEquals(0, removedToDoList.size());
        removedToDoList.add(new NormalItem("CPSC"));
        removedToDoList.add(new NormalItem("MATH"));
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

