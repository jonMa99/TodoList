//package model;
//
//import exception.TooManyToDosException;
//import exception.WrongCommandInputException;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import ui.UserPrompts;
//
//import java.io.IOException;
//
//import static org.junit.jupiter.api.Assertions.fail;
//
//public class ExceptionTest {
//    private ToDoList normal;
//    private ToDoList urgent;
//    private UserPrompts userprompt;
//
//    @BeforeEach
//    public void setUp() throws IOException {
////        normal = new NormalToDo();
////        urgent = new UrgentToDo();
////        userprompt = new UserPrompts();
//    }
//
//    @Test
//    public void testCheckOutListSize() {
//        normal.addToDo("1");
//        normal.addToDo("2");
//        normal.addToDo("3");
//        normal.addToDo("4");
//        normal.addToDo("5");
//        normal.addToDo("6");
//        normal.addToDo("7");
//        normal.addToDo("8");
//        normal.addToDo("9");
//        normal.addToDo("10");
//        try {
//            userprompt.checkCommand(1);
//            fail("Should throw TooManyToDosException");
//        } catch (TooManyToDosException e) {
//
//        } catch (WrongCommandInputException e) {
//            fail("1 is a right command");
//        }
//    }
//}
