package model;


import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public final class Task implements Serializable {
    private static int nextId = 0;
    private final int id;
    private String name;
    private String description;
    private boolean complete;
    private final LocalDate dateOfCreate = LocalDate.now();
    private LocalDate alertDate;

    {
        id = ++nextId;
    }


    public Task() {
    }
    public Task(String name, String description, int day, int month, int year) {
        this.name = name;
        this.description = description;
        alertDate = LocalDate.of(year, month, day); // composition
    }
    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public boolean isComplete() {
        return complete;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDateOfCreate() {
        return dateOfCreate;
    }


    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        Task.nextId = nextId;
    }

    public LocalDate getAlertDate() {
        return alertDate;
    }

    public void setAlertDate(int year, int month, int day) {
        this.alertDate = LocalDate.of(year, month, day);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;
        return id == task.id && name.equals(task.name) && description.equals(task.description) && dateOfCreate.equals(task.dateOfCreate) && alertDate.equals(task.alertDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, dateOfCreate, alertDate);
    }

    @Override
    public String toString() {
        return String.format("____________________________________________________________"
                + "%n|Task id          | %-39d|"
                + "%n|Task name        | %-39s|"
                + "%n|Task description | %-39s|"
                + "%n|Date of create   | %-39s|"
                + "%n|Alert date       | %-39s|"
                + "%n|Complete         | %-39s|"
                + "%n", id, name, description, dateOfCreate, alertDate, (isComplete() ? "Yes" : "No")
        );
    }
}


