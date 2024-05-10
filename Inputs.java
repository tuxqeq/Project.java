public enum Inputs {
    NEW_TEST("new test"),
    OPEN_TEST("open test"),
    GO_TO_CHECKING_MOD("go to checking module"),
    EXIT("exit"),
    STOP("stop"),
    SAVE("save"),
    OVWRT("overwrite")
    ;

    private final String command;

    Inputs(String command) {
        this.command = command;
    }
    public String getCommand() {
        return command;
    }
}
