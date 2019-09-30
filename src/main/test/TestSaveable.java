package test;

import model.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestSaveable {
    private ToDo todo;

    @BeforeEach
    public void setUp() throws IOException {
        todo = new ToDo();
        new FileWriter("toDoListoutput.txt", false).close();  //https://www.baeldung.com/java-delete-file-contents
    }

    @Test
    public void testSave() throws IOException {
        todo.addToDo("CPSC");
        todo.addToDo("MATH");
        todo.addToDo("PSYC");
        todo.save();
        List<String> todos = Files.readAllLines(Paths.get("toDoListoutput.txt"));  // CPSC 210 FileReaderWriter
        assertTrue(todos.contains("CPSC"));
        assertTrue(todos.contains("MATH"));
        assertTrue(todos.contains("PSYC"));
    }
}
