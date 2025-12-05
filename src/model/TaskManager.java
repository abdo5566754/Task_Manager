package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();


    public TaskManager() {
    }

    public Status addList(List list) {
        if (list == null || list.isEmpty()) return Status.EMPTY;

        tasks.addAll(list);

        return Status.COMPLETE;
    }

    public boolean addTask(Task t) {
        return tasks.add(t);
    }

    public Status removeTask(int id) {

        if (tasks.isEmpty())
            return Status.EMPTY;// <- tasks is empty

        if (!tasks.removeIf(e -> e.getId() == id))
            return Status.NOT_FOUND; // <- found

        if (tasks.isEmpty())
            TaskRepository.removeSources();

        return Status.COMPLETE;
    }


    public List<Task> getAllTasks() {
        return Collections.unmodifiableList(tasks);
    }

    public Status removeAll() {
        if (tasks.isEmpty()) return Status.EMPTY;

        tasks.clear();
        TaskRepository.removeSources();

        return Status.COMPLETE;
    }

    public boolean checkID(int id) {
        boolean flag = false;
        for (Task t : tasks) {
            if (t.getId() == id) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public void setName(int id, String taskName) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setName(taskName);
                return;
            }
        }
    }

    public void setDescription(int id, String taskDescription) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setDescription(taskDescription);
            }
        }
    }

    public void setAlertDate(int id, int year, int month, int day) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.setAlertDate(year, month, day);
                return;
            }
        }
    }

    public Status markAsComplete(int id) {

        if (tasks.isEmpty()) return Status.EMPTY;

        for (Task t : tasks) {
            if (t.getId() == id) {
                if ((t.isComplete())) {
                    t.setComplete(false);
                    return Status.UNDONE;
                } else {
                    t.setComplete(true);
                    return Status.DONE;
                }
            }
        }
        return Status.NOT_FOUND;
    }

    public String search(int id) {
        for (Task t : tasks) {
            if (t.getId() == id)
                return t.toString();
        }
        return null;
    }

    public void checkLastId() {
        int temp = 0;
        for (Task t : tasks) {
            if (t.getId() > temp)
                temp = t.getId();
        }

        Task.setNextId(temp);
    }
}
