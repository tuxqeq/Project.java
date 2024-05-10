public class CommandImp {
    public void execute() {
    }
    public Command getCommand(String commandName){
        if(commandName.equals(Inputs.NEW_TEST
                .getCommand())){
            return new NewTest();
        }
        if(commandName.equals(Inputs.OPEN_TEST
                .getCommand())){
            return new OpenTest();
        }
        if(commandName.equals(Inputs.GO_TO_CHECKING_MOD
                .getCommand())){
            return new GoToCheckMod();
        }
        return null;
    }
}
