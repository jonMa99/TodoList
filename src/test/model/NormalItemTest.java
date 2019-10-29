package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class NormalItemTest {
    private NormalItem normalItem;

    @BeforeEach
    public void setUp() {
        normalItem = new NormalItem("Test");
    }

    @Test
    public void testConstructor() {
        assertEquals("Test", normalItem.getToDoName());
    }

    @Test
    public void test2ParamConstructor() {
        normalItem = new NormalItem("Test1", "There");
        assertEquals("Test1", normalItem.getToDoName());
        assertEquals("There", normalItem.getLocation().getLocationName());
    }
}
