package ui;


import exception.DuplicateTaskException;
import exception.EmptyTaskNameException;
import model.Task;
import model.TaskList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class ToDoList extends JFrame {
    private JTextField newTask;
    private JList showList;
    private JPanel appPanel;
    private TaskList taskList;
    DefaultListModel model;
    private JButton addTaskBtn;
    private JButton completeTaskBtn;
    private JButton deleteTaskBtn;
    private JButton saveBtn;



    public ToDoList() {
        setFrame();
        taskList = new TaskList();
        model = new DefaultListModel();
        isFileExist();
        addTaskBtn.addActionListener(e -> addTaskBtnClick());
        saveBtn.addActionListener(e -> saveBtnClick());
        deleteTaskBtn.addActionListener(e -> deleteTaskBtnClick());
        completeTaskBtn.addActionListener(e -> completeTaskBtnClick());
    }

    public void setFrame() {
        JFrame frame = new JFrame("ToDoList");
        frame.setPreferredSize(new Dimension(600, 600));
        frame.setTitle("ToDoList");
        frame.setContentPane(appPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
//    https://stackoverflow.com/questions/9093448/how-to-capture-a-jframes-close-button-click-event
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame,
                        "Do you want to save your data?", "Close Window?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    taskList.writeList();
                    System.exit(0);
                }
            }
        });
    }


    private void completeTaskBtnClick() {
//       http://soundbible.com/1280-Click-On.html
        playSound("Click.wav");
        int index = showList.getSelectedIndex();
        if (index >= 0) {
            String s = "<html><strike>" + showList.getSelectedValue().toString() + "</strike></html>";
            String itemValue = showList.getSelectedValue().toString();
            taskList.runCompleteTask(itemValue);
            model.set(index, s);
        }
    }

    private void deleteTaskBtnClick() {
        playSound("Click.wav");
        int index = showList.getSelectedIndex();
        if (index >= 0) {
            String itemValue = showList.getSelectedValue().toString();

//          https://stackoverflow.com/questions/21445022/remove-multiple-words-from-string-in-one-statement
            itemValue = itemValue.replaceAll("<html><strike>|</strike></html>", "");
            //System.out.println(" value after " + itemValue);

            //Delete task from tasklist
            taskList.runDeleteTask(itemValue);
            //Removing from UI
            model.removeElementAt(index);

        }
    }



    private void saveBtnClick() {
        playSound("Click.wav");
        taskList.writeList();
    }


    private void addTaskBtnClick()  {
        playSound("Click.wav");
        String trimTask = newTask.getText().trim();

        if (trimTask.length() > 0 && (taskList.isTaskExist(trimTask)) == null) {
            model.addElement(trimTask);
            System.out.println("TASK ADDED IN UI");
        }
        try {
            taskList.createTask(trimTask);
        } catch (EmptyTaskNameException e){
            e.printStackTrace();

        }
        catch (DuplicateTaskException e){
            e.printStackTrace();
        }

    }

    public void isFileExist() {
        File file = new File("./data/myFile.txt");
        if (file.isFile()) {
            taskList.readList();
        }
        loadList();

    }

    public void loadList() {
        for (int i = 0; i < taskList.listSize(); i++) {
            if (taskList.getTask(i).getStatus()) {

                String s = "<html><strike>" + taskList.getTask(i).getName() + "</strike></html>";

                model.addElement(s);
            } else if (!taskList.getTask(i).getStatus()) {
                model.addElement(taskList.getTask(i).getName());
            }

        }
        showList.setModel(model);

    }


    //  https://stackoverflow.com/questions/36394909/i-created-a-jbutton-that-plays-a-sound-when-clicked-how-do-i-implement-that-on
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }


}
