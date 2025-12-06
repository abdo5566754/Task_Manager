package View.cui;

enum Messages {
    ENTER_ID("Please enter ID : "),
    ENTER_TASK_NAME("Enter task name : "),
    ENTER_TASK_DESCRIPTION("Enter task description : "),
    ENTER_ALERT_DATE("Enter alert date of task example -> dd-mm-yyyy : "),
    ENTER_ALERT_TIME("Enter alert time of task example -> HH:MM : "),
    INVALID_PAST_TIME("You cannot set a time from the past..."),


    INVALID_ID("invalid ID..."),
    INVALID_NUMBER("Invalid number"),
    INVALID_PAST_DATE("You cannot set a date from the past..."),
    INVALID_DATE_FORMAT("A valid date format must be entered example (dd-mm-yyyy)..."),
    INVALID_TIME_FORMAT("A valid time format must be entered example (HH:MM)..."),
    INVALID_DATE("Invalid date"),
    INVALID_TIME("Invalid time"),

    MARK_AS_COMPLETE("Mark as complete..."),
    MARK_REMOVED("Remove mark as complete..."),
    CHOSE_NUMBER("Please enter the selection number : "),
    TRY_AGAIN("Please try again"),

    EXIT_SUCCESSFUL("Exit successful..."),

    EXIT_QUESTION("Do you actually want to exit? If yes, enter yes. If no, enter no : "),

    REMOVE_ALL_QUESTION("Do you actually want to remove all tasks ? If yes, enter y. If no, enter n : "),

    LIST_IS_EMPTY("The list is empty..."),

    SUCCESSFUL_DELETE("Successfully deleted..."),

    SUCCESSFUL_ADDED("Successfully added..."),

    ID_NOT_FOUND("ID not found..."),

    DELETED("Deleted..."),

    BACK("Back"),

    ADDITION_FAILED("addition failed..."),

    ONLY_NUMBER_CAN_BE_ENTERED("Only numbers can be entered"),

    NAME_LENGTH("The name must be longer than or equal to 3 characters"),
    DESCRIPTION_LENGTH("The description must be longer than or equal to 6 characters"),
    FAILED_MODIFY("The attempt to modify the task failed..."),

    ALERT_DATE_CHANGE_FAILED("Alert date change failed..."),
    ALERT_DATE_CHANGE_SUCCESSFUL("Alert date change successful..."),
    DESCRIPTION_CHANGE_SUCCESSFUL("Description change successful..."),
    DESCRIPTION_CHANGE_FAILED("Description change failed..."),
    NAME_CHANGE_SUCCESSFUL("Name change successful..."),
    NAME_CHANGE_FAILED("Name change failed..."),
    IMPORT_SUCCESSFUL("Importing resources..."),
    IMPORT_FAILED("Failure to import resources..."),
    SOURCES_NOT_FOUND("No sources were found..."),
    SAVE("Saved..."),
    SAVIN_FAILED("Saving failed..."),


    MAIN_MENU("""
            1) Add task
            2) Update task
            3) Delete task
            4) Delete all tasks
            5) Mark as complete
            6) Searching for a task
            7) Display all tasks
            8) Exit
            """),

    MENU_OF_UPDATE("""
            1) Update of name
            2) Update of description
            3) Update of alert date
            4) Back
            """),

    LINE("____________________________________________________________");


    private static final String BASE_COLOR = ConsoleColor.WHITE_BRIGHT; // <- Base color WHITE_BRIGHT

    private final String message;

    Messages(String message) {
        this.message = message;
    }

    static void mainMenu() {
        System.out.println(BASE_COLOR + MAIN_MENU.getMessage());
        showAlertMessage(CHOSE_NUMBER.getMessage());
    }

    static void menuOfUpdate() {
        System.out.println(BASE_COLOR + MENU_OF_UPDATE);
        showAlertMessage(CHOSE_NUMBER.getMessage());
    }

    static void showAlertMessage(String s) {
        System.out.print(ConsoleColor.YELLOW_BRIGHT + "\n" + s + BASE_COLOR);
    }

    static void showErrorMessage(String s) {
        System.out.printf("%s%n%s%n%s%n%s%n%n%s", ConsoleColor.RED_BRIGHT, LINE.getMessage(), s, LINE.getMessage(), BASE_COLOR);
    }

    static void showCompleteMessage(String s) {
        System.out.printf("%s%n%s%n%s%n%s%n%n%s", ConsoleColor.GREEN_BRIGHT, LINE.getMessage(), s, LINE.getMessage(), BASE_COLOR);

    }

    public String getMessage() {
        return message;
    }
}

