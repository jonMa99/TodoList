//package model;
//
//import exception.EmptyNormalToDoListException;
//import exception.EmptyUrgentToDoListException;
//import exception.TooManyToDosException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class UrgentToDoListTest {
//    private ToDoList urgenttodo;
//
//    @BeforeEach
//    public void setUp() throws IOException {
//        urgenttodo = new UrgentToDo();
//    }
//
//    @Test
//    public void testToDo() {
//        ArrayList<ToDo> todoList = urgenttodo.getUrgentToDoList();
//        assertEquals(0, todoList.size());
//        ArrayList<ToDo> removedToDoList = urgenttodo.getRemovedToDoList();
//        assertEquals(0, removedToDoList.size());
//    }
//
//    @Test
//    public void testAddToDo() {
//        try {
//            urgenttodo.addToDo("CPSC-210");
//        } catch (TooManyToDosException e) {
//            fail("No exceptions thrown");
//        }
//        ArrayList<ToDo> toDoList = urgenttodo.getUrgentToDoList();
//        assertEquals(1, toDoList.size());
//        assertEquals("CPSC-210", toDoList.get(0).getToDoName());
//    }
//
//    @Test
//    public void testAddToDoLots() {
//        try {
//            urgenttodo.addToDo("CPSC-210");
//            urgenttodo.addToDo("MATH-221");
//            urgenttodo.addToDo("MATH-200");
//        } catch (TooManyToDosException e) {
//            fail("No exceptions thrown");
//        }
//        ArrayList<ToDo> toDoList = urgenttodo.getUrgentToDoList();
//        assertEquals(3, toDoList.size());
//        assertEquals("CPSC-210", toDoList.get(0).getToDoName());
//        assertEquals("MATH-221", toDoList.get(1).getToDoName());
//        assertEquals("MATH-200", toDoList.get(2).getToDoName());
//    }
//
//    @Test
//    public void testAddTooManyUrgentToDo() {
//        try {
//            urgenttodo.addToDo("1");
//            urgenttodo.addToDo("2");
//            urgenttodo.addToDo("3");
//            urgenttodo.addToDo("4");
//            urgenttodo.addToDo("5");
//            urgenttodo.addToDo("6");
//            urgenttodo.addToDo("7");
//            urgenttodo.addToDo("8");
//            urgenttodo.addToDo("9");
//            urgenttodo.addToDo("10");
//            urgenttodo.addToDo("11");
//            fail("Too many todos!");
//        } catch (TooManyToDosException e) {
//
//        }
//    }
//
//    @Test
//    public void testMoveToDoToRemovedToDo() {
//        ArrayList<ToDo> todoList = urgenttodo.getUrgentToDoList();
//        ArrayList<ToDo> removedToDoList = urgenttodo.getRemovedToDoList();
//        assertEquals(0, todoList.size());
//        assertEquals(0, removedToDoList.size());
//        todoList.add(new UrgentItem("CPSC"));
//        todoList.add(new UrgentItem("MATH"));
//        todoList.add(new UrgentItem("ENGL"));
//        todoList.add(new UrgentItem("PSYC"));
//        assertEquals(4, todoList.size());
//        assertTrue(searchThroughListForName(todoList, "ENGL"));
//        assertEquals(0, removedToDoList.size());
//        urgenttodo.moveToDoToRemovedToDo(3);
//        assertEquals(3, todoList.size());
//        assertEquals(1, removedToDoList.size());
//        assertFalse(searchThroughListForName(todoList, "ENGL"));
//        assertEquals("ENGL", removedToDoList.get(0).getToDoName());
//    }
//
//    @Test
//    public void testGetToDoList() {
//        ArrayList<ToDo> todoList = urgenttodo.getUrgentToDoList();
//        assertEquals(0, todoList.size());
//        try {
//            urgenttodo.addToDo("CPSC");
//            urgenttodo.addToDo("MATH");
//        } catch (TooManyToDosException e) {
//            fail("No exceptions thrown");
//        }
//        assertEquals(2, todoList.size());
//        assertEquals("CPSC", todoList.get(0).getToDoName());
//        assertEquals("MATH", todoList.get(1).getToDoName());
//    }
//
//    @Test
//    public void testRemovedToDo() throws EmptyNormalToDoListException, EmptyUrgentToDoListException {
//        try {
//            urgenttodo.addToDo("Testing todo");
//            urgenttodo.addToDo("Another testing todo");
//        } catch (TooManyToDosException e) {
//            fail("No exceptions thrown");
//        }
//        ArrayList<ToDo> todoList = urgenttodo.getUrgentToDoList();
//        assertEquals(2, todoList.size());
//        assertEquals("Testing todo", todoList.get(0).getToDoName());
//        assertEquals("Another testing todo", todoList.get(1).getToDoName());
//        urgenttodo.removeToDo(1);
//        assertEquals(1, todoList.size());
//        assertEquals("Another testing todo", todoList.get(0).getToDoName());
//    }
//
//    @Test
//    public void testRemoveToDoEmptyThrowEmptyUrgentToDoListException() {
//        try {
//            urgenttodo.removeToDo(0);
//            fail("Suppose to throw EmptyUrgentToDoListException");
//        } catch (EmptyUrgentToDoListException e) {
//
//        } catch (EmptyNormalToDoListException e) {
//            fail("Wrong type of expception thrown");
//        }
//    }
//
//    @Test
//    public void testGetRemovedToDoList() {
//        ArrayList<ToDo> removedToDoList = urgenttodo.getRemovedToDoList();
//        assertEquals(0, removedToDoList.size());
//        removedToDoList.add(new UrgentItem("CPSC"));
//        removedToDoList.add(new UrgentItem("MATH"));
//        assertEquals(2, removedToDoList.size());
//        assertEquals("CPSC", removedToDoList.get(0).getToDoName());
//        assertEquals("MATH", removedToDoList.get(1).getToDoName());
//    }
//
//    public boolean searchThroughListForName(ArrayList<ToDo> todolist, String s) {
//        for (ToDo td : todolist) {
//            if (td.getToDoName() == s) {
//                return true;
//            }
//        }
//        return false;
//    }
//}
