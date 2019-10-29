package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LocationTest {
    private Location location;
    private Location location1;

    @BeforeEach
    public void setUp() {
        location = new Location("There");
        location1 = new Location("Here");
    }

    @Test
    public void testConstructor() {
        assertEquals("There", location.getLocationName());
        assertEquals(0, location.getTodos().size());
    }

    @Test
    public void testAddToDoSucessful() {
        ToDo td = new ToDo("Testing");
        location.addToDo(td);
        assertEquals(1, location.getTodos().size());
        assertEquals(td, location.getTodos().get(0));
    }

    @Test
    public void testAddToDoMore() {
        ToDo td = new ToDo("Testing");
        ToDo td1 = new ToDo("Bob");
        ToDo td2 = new ToDo("Ear");
        location.addToDo(td);
        location.addToDo(td1);
        location.addToDo(td2);
        assertEquals(3, location.getTodos().size());
        assertEquals(td, location.getTodos().get(0));
        assertEquals(td1, location.getTodos().get(1));
        assertEquals(td2, location.getTodos().get(2));
    }

    @Test
    public void testAddToDoSame() {
        ToDo td = new ToDo("Testing");
        ToDo td1 = new ToDo("Bob");
        location.addToDo(td);
        location.addToDo(td1);
        location.addToDo(td);
        assertEquals(2, location.getTodos().size());
        assertEquals(td, location.getTodos().get(0));
        assertEquals(td1, location.getTodos().get(1));
    }

    @Test
    public void testEqualsEqual() {
        location1 = new Location("There");
        assertTrue(location.equals(location1));
    }

    @Test
    public void testEqualsNotEqual() {
        assertFalse(location.equals(location1));
    }

    @Test
    public void testHashCodeEqual() {
        location1 = new Location("There");
        assertTrue(location.hashCode() == location1.hashCode());
    }

    @Test
    public void testHashCodeNotEqual() {
        assertFalse(location.hashCode() == location1.hashCode());
    }

    @Test
    public void testEqualsNotSameObject() {
        ToDo todo = new NormalItem("Urgent");
        assertFalse(location.equals(todo));
    }

    @Test
    public void testEqualsNullObject() {
        ToDo todo = null;
        assertFalse(location.equals(todo));
    }
}
