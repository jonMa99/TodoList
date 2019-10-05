package test;

import model.NormalToDo;
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
        todo = new NormalToDo();
    }

    @Test
    public void testSave() throws IOException {
        FileWriter save = new FileWriter("testoutput.txt");
        save.close();
        todo.addToDo("CPSC");
        todo.addToDo("MATH");
        todo.addToDo("PSYC");
        testSave(todo);
        List<String> todos = Files.readAllLines(Paths.get("testoutput.txt"));  // CPSC 210 FileReaderWriter
        assertEquals("CPSC", todos.get(0));
        assertEquals("MATH", todos.get(1));
        assertEquals("PSYC", todos.get(2));
        assertEquals(3, todos.size());
    }

    public void testSave(Saveable s) throws IOException {
        s.save("testoutput.txt", "testurgentoutput.txt","testoutputremoved.txt");
    }
}
