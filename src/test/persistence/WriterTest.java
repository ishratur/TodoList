package persistence;

import model.Task;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class WriterTest {
    @Test
    public void testWrite() {
        ArrayList<Task> taskList = new ArrayList<Task>();

        Task a = new Task("Homework");
        Task b = new Task("clean");

        taskList.add(a);
        taskList.add(b);

        try {
            FileOutputStream fos = new FileOutputStream("./data/myTestFile.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(taskList);
            oos.close();
            System.out.println("File Saved");

        } catch (IOException e) {
            System.out.println("Error!! Save unsuccessful");




            assertEquals(a.getName(), taskList.get(0).getName());
            assertEquals(b.getName(), taskList.get(1).getName());

        }
    }
}
