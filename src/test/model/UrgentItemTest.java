package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UrgentItemTest {
    private UrgentItem urgentItem;

    @BeforeEach
    public void setUp() {
        urgentItem = new UrgentItem("Test");
    }

    @Test
    public void testConstructor() {
        assertEquals("!!!!! Test !!!!!", urgentItem.getToDoName());
    }

    @Test
    public void test2ParamConstructor() {
        urgentItem = new UrgentItem("Test1", "There");
        assertEquals("!!!!! Test1 !!!!!", urgentItem.getToDoName());
        assertEquals("There", urgentItem.getLocation().getLocationName());
    }
}
