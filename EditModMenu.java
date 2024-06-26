public class EditModMenu  implements Menus{
    private boolean dontstop = true;
    public EditModMenu(){
        displayMenu();
    }

    @Override
    public void displayMenu() {
        while(dontstop){
        PrettyOutput.printHeader("You are in Editing Module Menu");
        PrettyOutput.printEnums(new Inputs[]{Inputs.NEW_TEST, Inputs.OPEN_TEST, Inputs.MAIN_MENU, Inputs.EXIT});
        menuSwitch(PrettyOutput.nextLine());
        }
    }

    @Override
    public void menuSwitch(String input) {
        switch (Inputs.toEnum(input)) {
            case NEW_TEST:
                new NewTest();
                break;

            case OPEN_TEST:
                new OpenTest();
                break;

            case MAIN_MENU:
                this.dontstop = false;
                break;

            default:
                PrettyOutput.printWarning("Invalid command, please try again");
                new EditModMenu();

        }
    }
}

