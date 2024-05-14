public enum Inputs {
    NEW_TEST("new test"),
    OPEN_TEST("open test"),
    EDITING_MOD("editing module"),
    CHECKING_MOD("checking module"),
    EXIT("exit"),
    STOP("stop"),
    SAVE("save"),
    OVWRT("overwrite"),
    READ("read"),
    EDIT("edit"),
    INVALID("invalid"),
    CONTINUE("continue"),
    LINK("link"),
    GO_TO_TASK("go to task"),
    GO_TO_STUDENT_MOD("go to student module"),
    MAIN_MENU("main menu"),
    NUM_OF_SOLUTIONS("number of solutions"),
    NEXT("next"),
    INFO_ABOUT("info about"),
    PREVIOUS("prevoious")
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
