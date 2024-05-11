public class CommandImp {
    public void execute() {
    }
    public Command getCommand(String commandName){
        return switch (Inputs.toEnum(commandName)) {
            case Inputs.NEW_TEST -> new NewTest();
            case Inputs.OPEN_TEST -> new OpenTest();
            case Inputs.GO_TO_CHECKING_MOD -> new GoToCheckMod();
            default -> null;
        };
    }
}
