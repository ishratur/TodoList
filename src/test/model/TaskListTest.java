package model;

import exception.DuplicateTaskException;
import exception.EmptyTaskNameException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskListTest {
    TaskList taskList = new TaskList();

    @Test
    void runDeleteTask() throws EmptyTaskNameException, DuplicateTaskException {
        taskList.createTask("t1");
        taskList.createTask("t2");
        taskList.createTask("t3");
        assertEquals(taskList.isTaskExist("t2").getName(),"t2");
        taskList.runDeleteTask("t2");
        assertEquals(taskList.isTaskExist("t2"),null);
    }

    @Test
    void createTask() throws EmptyTaskNameException, DuplicateTaskException {
        taskList.createTask("t1");
        assertEquals(taskList.isTaskExist("t1").getName(),"t1");
    }

    @Test
    void createEmptyTask(){
        Exception e = assertThrows(EmptyTaskNameException.class, () ->{
            taskList.createTask("");
        });
        assertEquals(e.getMessage(),"Task has no character");
    }

    @Test
    void createDuplicateTask(){
        Exception e = assertThrows(DuplicateTaskException.class,() ->{
            taskList.createTask("t1");
            taskList.createTask("t1");
        });
        assertEquals(e.getMessage(),"Can't add duplicate task");
    }

    @Test
    void runCompleteTask() throws EmptyTaskNameException, DuplicateTaskException {
        taskList.createTask("t1");
        taskList.runCompleteTask("t1");
        assertTrue(taskList.isTaskExist("t1").getStatus());

    }

    @Test
    void writeList() throws EmptyTaskNameException, DuplicateTaskException {
        taskList.createTask("t1");
        taskList.createTask("t2");
        taskList.writeList();

    }

    @Test
    void readList() throws EmptyTaskNameException, DuplicateTaskException {
        taskList.createTask("t1");
        taskList.createTask("t2");
        taskList.writeList();
        taskList.readList();
    }

    @Test
    void listSize() throws EmptyTaskNameException, DuplicateTaskException {
        taskList.createTask("t1");
        taskList.createTask("t2");
        assertEquals(taskList.listSize(),2);

    }

    @Test
    void getTask() throws EmptyTaskNameException, DuplicateTaskException {
        taskList.createTask("t1");
        assertEquals(taskList.getTask(0).getName(),"t1");


    }

    @Test
    void isTaskExist() throws EmptyTaskNameException, DuplicateTaskException {
        taskList.createTask("t1");
        assertEquals(taskList.isTaskExist("t1").getName(),"t1");
    }

    @Test
    void isTaskExistNull(){
        assertEquals(taskList.isTaskExist("t2"),null);
    }
}