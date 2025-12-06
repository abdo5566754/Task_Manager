package View.cui;

import View.Actionable;
import conroller.TaskController;
import util.Status;
import model.Task;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CUI implements Actionable {

    // -> create a scanner object to read a values
    private final Scanner in = new Scanner(System.in);

    // -> create a TaskController to Connection with controller
    private final TaskController taskController = new TaskController();

    {
        switch (taskController.importList()) {
            case COMPLETE -> Messages.showCompleteMessage(Messages.IMPORT_SUCCESSFUL.getMessage());
            case ERROR -> Messages.showErrorMessage(Messages.IMPORT_FAILED.getMessage());
        }
        taskController.setTaskId();
    }

    public CUI() {
    }

    // -> Create an AdditionTask class to check if tasks have been added
    private static final class AdditionTask {

        static String addName(Scanner in) {
            for (int i = 1; i <= 3; i++) {
                Messages.showAlertMessage(Messages.ENTER_TASK_NAME.getMessage());
                String input = in.nextLine();

                if (input.trim().length() >= 3)
                    return input.trim();
                else
                    Messages.showErrorMessage(Messages.NAME_LENGTH.getMessage() + ", " + Messages.TRY_AGAIN.getMessage());
            }
            return null;
        }

        static String addDescription(Scanner in) {
            for (int i = 1; i <= 3; i++) {
                Messages.showAlertMessage(Messages.ENTER_TASK_DESCRIPTION.getMessage());
                String input = in.nextLine();

                if (input.trim().length() >= 6)
                    return input.trim();
                else
                    Messages.showErrorMessage(Messages.DESCRIPTION_LENGTH.getMessage() + ", " + Messages.TRY_AGAIN.getMessage());
            }
            return null;
        }

        static LocalDate addAlertDate(Scanner in) {
            for (int k = 1; k <= 3; k++) {
                Messages.showAlertMessage(Messages.ENTER_ALERT_DATE.getMessage());
                String input = in.nextLine();

                String[] parts = input.trim().split("-");

                try {
                    LocalDate alertDate = LocalDate.of(Integer.parseInt(parts[2]), Integer.parseInt(parts[1]), Integer.parseInt(parts[0]));

                    if (alertDate.isAfter(LocalDate.now()) || alertDate.isEqual(LocalDate.now()))
                        return alertDate;
                    else
                        Messages.showErrorMessage(Messages.INVALID_PAST_DATE.getMessage());

                } catch (NumberFormatException e) {
                    Messages.showErrorMessage(Messages.ONLY_NUMBER_CAN_BE_ENTERED.getMessage() + ", " + Messages.TRY_AGAIN.getMessage());
                } catch (DateTimeException e) {
                    Messages.showErrorMessage(Messages.INVALID_DATE.getMessage() + ", " + Messages.TRY_AGAIN.getMessage());
                } catch (ArrayIndexOutOfBoundsException e) {
                    Messages.showErrorMessage(Messages.INVALID_DATE_FORMAT.getMessage() + ", " + Messages.TRY_AGAIN.getMessage());
                }
            }
            return null;
        }

        static LocalTime addAlertTime(Scanner in) {
            for (int k = 1; k <= 3; k++) {
                Messages.showAlertMessage(Messages.ENTER_ALERT_TIME.getMessage());
                String input = in.nextLine();

                String[] parts = input.trim().split(":");

                try {
                    LocalTime alertTime = LocalTime.of(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));

                    if (alertTime.isAfter(LocalTime.now()))
                        return alertTime;
                    else
                        Messages.showErrorMessage(Messages.INVALID_PAST_TIME.getMessage());

                } catch (NumberFormatException e) {
                    Messages.showErrorMessage(Messages.ONLY_NUMBER_CAN_BE_ENTERED.getMessage() + ", " + Messages.TRY_AGAIN.getMessage());
                } catch (DateTimeException e) {
                    Messages.showErrorMessage(Messages.INVALID_TIME.getMessage() + ", " + Messages.TRY_AGAIN.getMessage());
                } catch (ArrayIndexOutOfBoundsException e) {
                    Messages.showErrorMessage(Messages.INVALID_TIME_FORMAT.getMessage() + ", " + Messages.TRY_AGAIN.getMessage());
                }
            }
            return null;
        }

    }


    // -> Create a run method to run application with (CUI)
    public void run() {
        userSelection();
    }

    private void userSelection() {

        while (true) {
            try {
                Messages.mainMenu();
                byte userInput = in.nextByte();
                in.nextLine(); // <-  buffer clearing

                switch (userInput) {
                    case 1 -> add();
                    case 2 -> update();
                    case 3 -> delete();
                    case 4 -> deleteAll();
                    case 5 -> markAsComplete();
                    case 6 -> search();
                    case 7 -> displayAll();
                    case 8 -> exit();
                    default ->
                            Messages.showErrorMessage(Messages.INVALID_NUMBER.getMessage() + ", " + Messages.TRY_AGAIN);
                }

            } catch (InputMismatchException e) {
                in.nextLine(); // <-  buffer clearing
                Messages.showErrorMessage(Messages.ONLY_NUMBER_CAN_BE_ENTERED.getMessage() + ", " + Messages.TRY_AGAIN.getMessage());
            }
        }

    }


    private int readingID() {
        Messages.showAlertMessage(Messages.ENTER_ID.getMessage());
        int id = in.nextInt();
        in.nextLine();

        return id;
    }

    @Override
    public void add() {

        boolean flag = false;

        String taskName = AdditionTask.addName(in);
        if (taskName != null) {
            String taskDescription = AdditionTask.addDescription(in);
            if (taskDescription != null) {
                LocalDate alertDate = AdditionTask.addAlertDate(in);
                if (alertDate != null) {
                    LocalTime alertTime = AdditionTask.addAlertTime(in);
                    if (alertTime != null)
                        flag = taskController.add(new Task(taskName, taskDescription, alertDate.getDayOfMonth(), alertDate.getMonthValue(), alertDate.getYear(), alertTime.getHour(), alertTime.getMinute()));
                }
            }
        }


        if (flag)
            Messages.showCompleteMessage(Messages.SUCCESSFUL_ADDED.getMessage());
        else
            Messages.showErrorMessage(Messages.ADDITION_FAILED.getMessage());
    }

    @Override
    public void update() {

        int id = readingID();

        if (id == -1) return;


        for (int i = 1; i <= 3; i++) {

            try {

                Messages.menuOfUpdate();

                byte userInput = in.nextByte();
                in.nextLine(); // <-  buffer clearing

                switch (userInput) {
                    case 1 -> {
                        String name = AdditionTask.addName(in);
                        if (name != null) {
                            taskController.setName(id, name);
                            Messages.showCompleteMessage(Messages.NAME_CHANGE_SUCCESSFUL.getMessage());
                            return;
                        } else
                            Messages.showErrorMessage(Messages.NAME_CHANGE_FAILED.getMessage());
                    }
                    case 2 -> {
                        String description = AdditionTask.addDescription(in);
                        if (description != null) {
                            taskController.setDescription(id, description);
                            Messages.showCompleteMessage(Messages.DESCRIPTION_CHANGE_SUCCESSFUL.getMessage());
                            return;
                        } else
                            Messages.showErrorMessage(Messages.DESCRIPTION_CHANGE_FAILED.getMessage());
                    }
                    case 3 -> {
                        LocalDate alertDate = AdditionTask.addAlertDate(in);
                        if (alertDate != null) {
                            taskController.setAlertDate(id, alertDate.getYear(), alertDate.getMonthValue(), alertDate.getDayOfMonth());
                            Messages.showCompleteMessage(Messages.ALERT_DATE_CHANGE_SUCCESSFUL.getMessage());
                            return;
                        } else
                            Messages.showErrorMessage(Messages.ALERT_DATE_CHANGE_FAILED.getMessage());
                    }
                    case 4 -> {
                        Messages.showCompleteMessage(Messages.BACK.getMessage());
                        return;
                    }
                    default ->
                            Messages.showErrorMessage(Messages.INVALID_NUMBER.getMessage() + ", " + Messages.TRY_AGAIN.getMessage());
                }

            } catch (InputMismatchException e) {
                in.nextLine(); // <-  buffer clearing
                Messages.showErrorMessage(Messages.ONLY_NUMBER_CAN_BE_ENTERED.getMessage() + ", " + Messages.TRY_AGAIN.getMessage());
            }
        }
        Messages.showErrorMessage(Messages.FAILED_MODIFY.getMessage());
    }


    public void delete() {
        int id = readingID();

        Status status = taskController.remove(id);

        switch (status) {
            case EMPTY -> Messages.showErrorMessage(Messages.LIST_IS_EMPTY.getMessage());
            case COMPLETE -> Messages.showCompleteMessage(Messages.DELETED.getMessage());
            case NOT_FOUND -> Messages.showErrorMessage(Messages.ID_NOT_FOUND.getMessage());
        }
    } // <- completed

    @Override
    public void deleteAll() {

        Messages.showAlertMessage(Messages.REMOVE_ALL_QUESTION.getMessage());

        char answer;

        while (true) {

            answer = in.next().toUpperCase().charAt(0);

            switch (answer) {

                case 'Y' -> {
                    switch (taskController.deleteAll()) {
                        case COMPLETE -> Messages.showCompleteMessage(Messages.SUCCESSFUL_DELETE.getMessage());
                        case EMPTY -> Messages.showErrorMessage(Messages.LIST_IS_EMPTY.getMessage());
                    }
                    return;
                }
                case 'N' -> {
                    return;
                }
                default -> Messages.showAlertMessage(Messages.TRY_AGAIN.getMessage() + " (y,n) : ");
            }

        }

    } // <- completed

    @Override
    public void markAsComplete() {

        int id = readingID();

        if (id == -1) return;

        switch (taskController.markAsComplete(id)) {
            case EMPTY -> Messages.showErrorMessage(Messages.LIST_IS_EMPTY.getMessage());
            case NOT_FOUND -> Messages.showErrorMessage(Messages.ID_NOT_FOUND.getMessage());
            case DONE -> Messages.showCompleteMessage(Messages.MARK_AS_COMPLETE.getMessage());
            case UNDONE -> Messages.showCompleteMessage(Messages.MARK_REMOVED.getMessage());
        }

    } // <- completed

    @Override
    public void search() {
        int id = readingID();

        if (id == -1) return;

        String tempTask = taskController.search(id);

        if (tempTask != null)
            System.out.print(tempTask + Messages.LINE.getMessage() + "\n\n");
        else
            Messages.showErrorMessage(Messages.INVALID_ID.getMessage());
    }

    @Override
    public void displayAll() {
        List<Task> list = taskController.display();

        if (list.isEmpty()) {
            Messages.showErrorMessage(Messages.LIST_IS_EMPTY.getMessage());
            return;
        }

        for (Task t : list)
            System.out.print(t);

        System.out.println(Messages.LINE.getMessage() + "\n\n");
    } // <- completed

    @Override
    public void exit() {

        Messages.showAlertMessage(Messages.EXIT_QUESTION.getMessage());

        char answer;

        while (true) {

            answer = in.next().toUpperCase().charAt(0);

            switch (answer) {
                case 'Y' -> {
                    switch (taskController.exportList()) {
                        case COMPLETE -> Messages.showCompleteMessage(Messages.SAVE.getMessage());
                        case ERROR -> Messages.showErrorMessage(Messages.SAVIN_FAILED.getMessage());
                        case EMPTY -> Messages.showErrorMessage(Messages.LIST_IS_EMPTY.getMessage());
                    }
                    Messages.showCompleteMessage(Messages.EXIT_SUCCESSFUL.getMessage());


                    System.exit(0);
                }
                case 'N' -> {
                    return;
                }
                default -> Messages.showAlertMessage(Messages.TRY_AGAIN.getMessage() + " (y,n) : ");
            }

        }

    }
}