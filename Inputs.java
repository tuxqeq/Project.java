public enum Inputs {
    NEW_TEST("new test"),
    OPEN_TEST("open test"),
    GO_TO_CHECKING_MOD("go to checking module"),
    EXIT("exit"),
    STOP("stop"),
    SAVE("save"),
    OVWRT("overwrite"),
    READ("read"),
    EDIT("edit"),
    INVALID("invalid"),
    CONTINUE("continue"),
    LINK("link")
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
