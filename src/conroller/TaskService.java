package conroller;

import model.Status;
import model.Task;

import java.util.List;

public interface TaskService {
    public boolean add(Task t);

    public List<Task> display();

    public Status remove(int id);

    public Status deleteAll();

    public boolean checkID(int id);

    public void setName(int id, String taskName);

    public void setDescription(int id, String taskDescription);

    public void setAlertDate(int id, int year, int month, int day);

    public Status markAsComplete(int id);

    public String search(int id);

}
