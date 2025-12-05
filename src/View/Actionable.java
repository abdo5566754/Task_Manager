package View;

public interface Actionable {
    void add(); //   1) Add task

    void update(); //  2) Update task

    void delete(); //  3) Delete task

    void deleteAll(); //   4) Delete all tasks

    void markAsComplete(); //  5) Mark as complete;

    void search(); //  6) Searching for a task

    void displayAll(); // 7) Display all tasks

    void exit(); //    8) Exit
}
