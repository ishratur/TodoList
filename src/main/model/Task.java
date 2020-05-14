package model;

import java.io.Serializable;
import java.util.Objects;

// A task containing task name and task status
public class Task implements Serializable {

    private String taskName;
    private boolean taskStatus;

/* MODIFIES: this
 EFFECTS: taskName is set to the name of the task
          and task status is set to false. */

    public Task(String taskName)  {
        if (taskName.trim().length() > 1) {
            this.taskName = taskName;
            this.taskStatus = false;

        }

    }
// EFFECTS: returns the name of the task.

    public String getName() {
        return taskName;
    }
// EFFECTS: return the status of the task.

    public boolean getStatus() {
        return taskStatus;
    }

//MODIFIES: this
//EFFECTS: changes the status of task when completed.

    public void changeTaskStatus() {
        this.taskStatus = true;

    }


}
