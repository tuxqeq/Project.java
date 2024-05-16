public enum Inputs {
    NEW_TEST("create new test"),
    OPEN_TEST("open test"),
    EDITING_MOD("go to editing module"),
    CHECKING_MOD("go to checking module"),
    EXIT("exit"),
    STOP("stop"),
    SAVE("save"),
    OVWRT("overwrite the file"),
    READ("read"),
    EDIT("edit"),
    INVALID("invalid"),
    CONTINUE("continue"),
    LINK("link"),
    GO_TO_TASK("go to task"),
    GO_TO_STUDENT_MOD("go to student module"),
    MAIN_MENU("go to main menu"),
    NUM_OF_SOLUTIONS("get number of solutions"),
    NEXT("go to next"),
    INFO_ABOUT("info about"),
    PREVIOUS("go to previous"),
    GO_TO_CHOOSING_FOLD("go to choosing folders"),
    READ_SOL_FILES("read solution files"),
    GO_TO_ANOTHER_STD("go to another student")
    ;

    private final String command;

    Inputs(String command) {
        this.command = command;
    }
    public String getCommand() {
        return command;
    }

    public static Inputs toEnum(String inp) {
        if(inp.equals(EXIT.getCommand())) {
            System.out.println("left the program");
            System.exit(0);
        }
        for (Inputs input : Inputs.values()) {
            if (input.getCommand().equals(inp)) {
                return input;
            }
        }
        //System.out.println("invalid commmand");
        return INVALID;
    }
}
