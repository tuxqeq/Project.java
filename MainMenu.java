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
                while(true){
                    new EditModMenu();
                    if (Inputs.toEnum(PrettyOutput.nextLine()) == Inputs.MAIN_MENU){
                        break;
                    }
                }
            }
            case CHECKING_MOD -> {
                while (true) {
                    new CheckModMenu();
                    if (Inputs.toEnum(PrettyOutput.nextLine()) == Inputs.MAIN_MENU){
                        break;
                    }
                }
            }
            default -> PrettyOutput.printWarning("Wrong command, try again");
        }
    }
}
