package conroller;

import model.AlertManager;
import util.Status;
import model.Task;
import model.TaskManager;
import model.TaskRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskController implements TaskService {
    private final TaskManager memoryTaskManager;


    public TaskController() {
        this.memoryTaskManager = new TaskManager();
        new AlertManager(memoryTaskManager.getAllTasks()).start();
    }


    @Override
    public boolean add(Task t) {
        return memoryTaskManager.addTask(t);
    }

    @Override
    public List<Task> display() {
        return memoryTaskManager.getAllTasks();
    }

    @Override
    public Status remove(int id) {
        return memoryTaskManager.removeTask(id);
    }

    @Override
    public Status deleteAll() {
        return memoryTaskManager.removeAll();
    }

    @Override
    public boolean checkID(int id) {
        return memoryTaskManager.checkID(id);
    }

    @Override
    public void setName(int id, String taskName) {
        memoryTaskManager.setName(id, taskName);
    }

    @Override
    public void setDescription(int id, String taskDescription) {
        memoryTaskManager.setDescription(id, taskDescription);
    }

    @Override
    public void setAlertDate(int id, int year, int month, int day) {
        memoryTaskManager.setAlertDate(id, year, month, day);
    }

    @Override
    public Status markAsComplete(int id) {
        return memoryTaskManager.markAsComplete(id);
    }

    @Override
    public String search(int id) {
        return memoryTaskManager.search(id);
    }


    public Status exportList() {
        return TaskRepository.writeList(new ArrayList<>(memoryTaskManager.getAllTasks()));
    }

    public Status importList() {
        try {
//            return memoryTaskManager.addList(TaskRepository.readList());
            return memoryTaskManager.addList(TaskRepository.readList());
        } catch (IOException | ClassNotFoundException e) {
            return Status.ERROR;
        }
    }

    public void setTaskId() {
        memoryTaskManager.checkLastId();
    }
}
