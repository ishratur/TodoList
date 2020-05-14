package persistence;


import model.Task;

import java.io.FileInputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ReadFile {
    ArrayList<Task> allTask;

    public ArrayList<Task> fetchList() {
        try {
            FileInputStream fis = new FileInputStream("./data/myFile.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            allTask = (ArrayList<Task>) ois.readObject();
            ois.close();


        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error! Read unsuccessful");
        }
        return allTask;
    }

}
