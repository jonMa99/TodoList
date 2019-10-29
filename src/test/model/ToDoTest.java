package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoTest {
    private ToDo todo;
    private ToDo todo1;

    @BeforeEach
    public void setUp() {
        todo = new ToDo("Test");
        todo1 = new ToDo("Tester", "Somewhere");
    }

    @Test
    public void testConstructor() {
        assertEquals("Test", todo.getToDoName());
    }

    @Test
    public void test2ParamConstructor() {
        assertEquals("Tester", todo1.getToDoName());
        assertEquals("Somewhere", todo1.getLocation().getLocationName());
    }

    @Test
    public void testAddLocationSuccessful() {
        Location l = new Location("Van");
        todo.addLocation(l);
        assertEquals(l, todo.getLocation());
    }

    @Test
    public void testAddLocationFail() {
        Location l = new Location("Van");
        todo.addLocation(l);
        assertEquals(l, todo.getLocation());
        todo.addLocation(l);
        assertEquals(l, todo.getLocation());
    }

    @Test
    public void testEqualsEqual() {
        todo = new ToDo("Test");
        todo1 = new ToDo("Test");
        assertTrue(todo.equals(todo1));
    }

    @Test
    public void testEqualsNotEqual() {
        todo = new ToDo("Test", "Somewhere");
        todo1 = new ToDo("Test", "Elsewhere");
        assertFalse(todo.equals(todo1));
    }

    @Test
    public void testHashCodeEqual() {
        todo = new ToDo("Test");
        todo1 = new ToDo("Test");
        assertTrue(todo.hashCode() == todo1.hashCode());
    }

    @Test
    public void testHashCodeNotEqual() {
        todo = new ToDo("Test");
        todo1 = new ToDo("Test", "Somewhere");
        assertFalse(todo.hashCode() == todo1.hashCode());
    }

    @Test
    public void testEqualsNotSameObject() {
        Location l = new Location("There");
        assertFalse(todo.equals(l));
    }

    @Test
    public void testEqualNull() {
        todo1 = null;
        assertFalse(todo.equals(todo1));
    }
}
