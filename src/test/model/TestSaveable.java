package model;

import exception.TooManyToDosException;
import model.Saveable;
import model.ToDoList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSaveable {
    private ToDoList todo;

    @BeforeEach
    public void setUp() throws IOException {
        todo = new ToDoList();
    }

    @Test
    public void testSave() throws IOException, TooManyToDosException {
        FileWriter todosave = new FileWriter("./data/testoutput.txt");
        todosave.close();
        todo.addToDo("CPSC");
        todo.addToDo("MATH");
        todo.addToDo("PSYC");
//        FileWriter urgentsave = new FileWriter("./data/testurgentoutput.txt");
//        urgentsave.close();
//        urgent.addToDo("123");
//        urgent.addToDo("456");
//        urgent.addToDo("789");
        testSave(todo);
//        FileWriter removed = new FileWriter("./data/testoutputremoved.txt");
//        removed.close();
//        removed.ad
        List<String> todos = Files.readAllLines(Paths.get("./data/testoutput.txt"));  // CPSC 210 FileReaderWriter
        assertEquals("CPSC", todos.get(0));
        assertEquals("MATH", todos.get(1));
        assertEquals("PSYC", todos.get(2));
        assertEquals(3, todos.size());
//        List<String> urgents = Files.readAllLines(Paths.get("./data/testurgentoutput.txt"));
//        assertEquals("123", urgents.get(0));
//        assertEquals("456", urgents.get(1));
//        assertEquals("789", urgents.get(2));
//        assertEquals(3, urgents.size());
//        List<String> removed = Files.readAllLines(Paths.get("./data/testurgentoutput.txt"));
//        assertEquals("123", urgents.get(0));
//        assertEquals("456", urgents.get(1));
//        assertEquals("789", urgents.get(2));
//        assertEquals(3, urgents.size());
    }

    public void testSave(Saveable s) throws IOException {
        s.save("./data/testoutput.txt", "./data/testurgentoutput.txt",
                "./data/testoutputremoved.txt", "./data/locationtest.txt");
    }
}
