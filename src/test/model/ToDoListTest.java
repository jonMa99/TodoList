package model;

import exception.EmptyNormalToDoListException;
import exception.EmptyUrgentToDoListException;
import exception.TooManyToDosException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

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
            assertEquals(todo.MAXTODOLISTSIZE, todo.getToDoList().size());
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
    
    @Test
    public void testAddTo2Param() {
        try {
            todo.addToDo("Test", "Here");
        } catch (TooManyToDosException e) {
            fail("Not supposed to be thrown");
        }
    }

    @Test
    public void testAddOverMaxToDo2Param() {
        try {
            todo.addToDo("1", "1");
            todo.addToDo("2", "1");
            todo.addToDo("3", "1");
            todo.addToDo("4", "1");
            todo.addToDo("5", "1");
            todo.addToDo("6", "1");
            todo.addToDo("7", "1");
            todo.addToDo("8", "1");
            todo.addToDo("9", "1");
            todo.addToDo("10", "1");
            todo.addToDo("11", "1");
            fail("Over max todo list size");
        } catch (TooManyToDosException e) {
            assertEquals(todo.MAXTODOLISTSIZE, todo.getToDoList().size());
        }
    }

    @Test
    public void testAddUrgentToDo() {
        try {
            todo.addUrgentToDo("Test");
            assertEquals(1, todo.getToDoList().size());
            assertEquals("!!!!! Test !!!!!", todo.getToDoList().get(0).getToDoName());
            assertEquals("Nowhere", todo.getToDoList().get(0).getLocation().getLocationName());
        } catch (TooManyToDosException e) {
            fail("Not suppose to throw");
        }
    }

    @Test
    public void testAddUrgentToDo2Param() {
        try {
            todo.addUrgentToDo("Tester", "Library");
            assertEquals(1, todo.getToDoList().size());
            assertEquals("!!!!! Tester !!!!!", todo.getToDoList().get(0).getToDoName());
            assertEquals("Library", todo.getToDoList().get(0).getLocation().getLocationName());
        } catch (TooManyToDosException e) {
            fail("Not suppose to be thrown");
        }
    }

    @Test
    public void testAddOverMaxUrgentToDo() {
        try {
            todo.addUrgentToDo("1");
            todo.addUrgentToDo("2");
            todo.addUrgentToDo("3");
            todo.addUrgentToDo("4");
            todo.addUrgentToDo("5");
            todo.addUrgentToDo("6");
            todo.addUrgentToDo("7");
            todo.addUrgentToDo("8");
            todo.addUrgentToDo("9");
            todo.addUrgentToDo("10");
            todo.addUrgentToDo("11");
            fail("Over max todo list size");
        } catch (TooManyToDosException e) {
            assertEquals(todo.MAXTODOLISTSIZE, todo.getToDoList().size());
        }
    }

    @Test
    public void testAddOverMaxUrgentToDo2Param() {
        try {
            todo.addUrgentToDo("1", "1");
            todo.addUrgentToDo("2", "1");
            todo.addUrgentToDo("3", "1");
            todo.addUrgentToDo("4", "1");
            todo.addUrgentToDo("5", "1");
            todo.addUrgentToDo("6", "1");
            todo.addUrgentToDo("7", "1");
            todo.addUrgentToDo("8", "1");
            todo.addUrgentToDo("9", "1");
            todo.addUrgentToDo("10", "1");
            todo.addUrgentToDo("11", "1");
            fail("Over max todo list size");
        } catch (TooManyToDosException e) {
            assertEquals(todo.MAXTODOLISTSIZE, todo.getToDoList().size());
        }
    }

    @Test
    public void testGetKeysAndMakeList() {
        try {
            todo.addToDo("1", "1");
            todo.addToDo("2", "1");
            todo.addToDo("3", "2");
            todo.addToDo("4", "3");
            Set<Location> keylist = todo.getKeysAndMakeList();
            assertEquals(3, keylist.size());
            ArrayList<String> list = new ArrayList<>();
            for (Location l : keylist) {
                list.add(l.getLocationName());
            }
            assertTrue(list.contains("1"));
            assertTrue(list.contains("2"));
            assertTrue(list.contains("3"));
        } catch (TooManyToDosException e) {
            fail("Not suppose to be thrown");
        }
    }

    @Test
    public void testMakeKeyAndEmptyValueHashMapOne() {
        try {
            todo.addToDo("1", "Pizza");
        } catch (TooManyToDosException e) {
            fail("No exception thrown");
        }
        HashMap<Location,ArrayList<ToDo>> testmap = todo.makeKeyAndEmptyValueHashMap();
        assertEquals(1, testmap.size());
        assertEquals(null, testmap.get("Pizza"));
    }

    @Test
    public void testMakeKeyAndEmptyValueHashMapMany() {
        try {
            todo.addToDo("1", "Pizza");
            todo.addToDo("2", "Subway");
        } catch (TooManyToDosException e) {
            fail("No exception thrown");
        }
        HashMap<Location,ArrayList<ToDo>> testmap = todo.makeKeyAndEmptyValueHashMap();
        assertEquals(2, testmap.size());
        assertEquals(null, testmap.get("Pizza"));
        assertEquals(null, testmap.get("Subway"));
    }

    @Test
    public void testMakeKeyAndEmptyValueHashMapCopy() {
        try {
            todo.addToDo("1", "Pizza");
            todo.addToDo("2", "Pizza");
        } catch (TooManyToDosException e) {
            fail("No exception thrown");
        }
        HashMap<Location,ArrayList<ToDo>> testmap = todo.makeKeyAndEmptyValueHashMap();
        assertEquals(1, testmap.size());
        assertEquals(null, testmap.get("Pizza"));
    }

//    @Test
//    public void testfillHashMap() {
//        try {
//            todo.addToDo("1", "Pizza");
//            todo.addToDo("2", "Pizza");
//            todo.addToDo("3", "Subway");
//            todo.addToDo("4", "Test");
//            todo.addToDo("5", "Pizza");
//        } catch (TooManyToDosException e) {
//            fail("No exception thrown");
//        }
//        HashMap<Location, ArrayList<ToDo>> testmap = todo.fillHashMap();
//        assertEquals(3, testmap.size());
//        assertEquals(3, testmap.get("Pizza").size());
//        assertTrue(testmap.get("Pizza").contains("1"));
//        assertTrue(testmap.get("Pizza").contains("2"));
//        assertTrue(testmap.get("Pizza").contains("5"));
//        assertEquals(1, testmap.get("Subway"));
//        assertTrue(testmap.get("Subway").contains("3"));
//        assertEquals(1, testmap.get("Test"));
//        assertTrue(testmap.get("Test").contains("3"));
//    }


    public boolean searchThroughListForName(ArrayList<ToDo> todolist, String s) {
        for (ToDo td : todolist) {
            if (td.getToDoName() == s) {
                return true;
            }
        }
        return false;
    }
}

