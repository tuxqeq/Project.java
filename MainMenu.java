public class MainMenu implements Menus {
    public MainMenu(){
        displayMenu();
    }
    @Override
    public void displayMenu(){
        PrettyOutput.printHeader("You are in Main Menu.");
        PrettyOutput.printEnums(new Inputs[]{Inputs.EDITING_MOD, Inputs.CHECKING_MOD, Inputs.EXIT});
        menuSwitch(PrettyOutput.nextLine());
    }
    @Override
    public void menuSwitch(String input){
        switch (Inputs.toEnum(input)){
            case EDITING_MOD -> {
                new EditModMenu();
            }
            case CHECKING_MOD -> {
                new CheckModMenu();
            }
            default -> PrettyOutput.printWarning("Wrong command, try again");
        }
    }
}
