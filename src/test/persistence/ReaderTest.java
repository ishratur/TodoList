package persistence;

import model.Task;
import org.junit.jupiter.api.Test;
import ui.*;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.ObjectInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ReaderTest {
    @Test
    public void testRead() throws IOException {
        ArrayList<Task> taskList = new ArrayList<Task>();

        Task a = new Task("Homework");
        Task b = new Task("clean");

        taskList.add(a);
        taskList.add(b);

        try {
            FileInputStream fis = new FileInputStream("./data/myTestFile.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            taskList = (ArrayList<Task>) ois.readObject();
            ois.close();


        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error! Read unsuccessful");
        }







        assertEquals(a.getName(), taskList.get(0).getName());
        assertEquals(b.getName(), taskList.get(1).getName());
    }




}
