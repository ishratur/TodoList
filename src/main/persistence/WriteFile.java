package persistence;

import model.Task;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class WriteFile {

    public void saveList(ArrayList<Task> taskList) {
        try {
            FileOutputStream fos = new FileOutputStream("./data/myFile.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(taskList);
            oos.close();
            System.out.println("File Saved");

        } catch (IOException e) {
            System.out.println("Error!! Save unsuccessful");

        }

    }
}