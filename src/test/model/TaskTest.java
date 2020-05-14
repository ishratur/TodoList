package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    Task task = new Task("t1");


    @Test
    void getName() {
        assertEquals(task.getName(),"t1");
    }

    @Test
    void getStatus() {
        assertFalse(task.getStatus());
    }

    @Test
    void changeTaskStatus() {
        task.changeTaskStatus();
        assertTrue(task.getStatus());
    }
}