package model;

import exception.DuplicateTaskException;
import exception.EmptyTaskNameException;
import persistence.ReadFile;
import persistence.WriteFile;

import java.util.ArrayList;

//EFFECTS: creates a taskList
public class TaskList {

    private ArrayList<Task> taskList;
    private WriteFile wr;
    private ReadFile rd;


    public TaskList() {
        taskList = new ArrayList<Task>();
        wr = new WriteFile();
        rd = new ReadFile();

    }


    //EFFECTS: deletes task user selects if task is found.
    public void  runDeleteTask(String taskInput) {

        Task tempTask = isTaskExist(taskInput);
//        System.out.println("taskInput ... " + taskInput);
        if (tempTask != null){
            System.out.println("DELETEING .....  " +   tempTask.getName());
            taskList.remove(tempTask);
        }

    }

    //EFFECTS: creates a task
    public void createTask(String task) throws EmptyTaskNameException, DuplicateTaskException {
        if (task.length() > 0 && isTaskExist(task) == null) {
            Task addTask = new Task(task);
            taskList.add(addTask);
            System.out.println("TASK ADDED .... " + addTask.getName());
        } else {
            if (task.length() < 1) {
                throw new EmptyTaskNameException("Task has no character");
            }
            throw new DuplicateTaskException("Can't add duplicate task");
        }
    }


    //EFFECTS: completes task user selects if task found
    public void runCompleteTask(String taskInput) {

        Task tempTask = isTaskExist(taskInput);
        if (tempTask != null && !(tempTask.getStatus())){
            tempTask.changeTaskStatus();
            System.out.println("STATUS CHANGED TO COMPLETE ..." + tempTask.getName());
        }

    }


    //https://howtodoinjava.com/java/collections/arraylist/serialize-deserialize-arraylist/
    //EFFECTS: writes task to the taskList
    public void writeList() {

        wr.saveList(taskList);

    }

    //EFFECTS: Reads task from the taskList
    public void readList() {

        taskList = rd.fetchList();

    }

    //EFFECTS: returns the size of the taskList
    public int listSize() {
        return taskList.size();
    }

    //EFFECTS: returns the task
    public Task getTask(int i) {
        return taskList.get(i);
    }

    //EFFECTS: checks if a task exist
    public Task isTaskExist(String taskInput){
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getName().toLowerCase().equals(taskInput.toLowerCase())) {
                //System.out.println("==== DUPLICATE TASK======");
                return taskList.get(i);
            }
        }
        //System.out.println("==== NEW TASK ========");
        return null;

    }



}
