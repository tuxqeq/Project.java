public class EditModMenu  implements Menus{
    public EditModMenu(){
        displayMenu();
    }

    @Override
    public void displayMenu() {
        PrettyOutput.printHeader("You are in Editing Module Menu");
        PrettyOutput.printEnums(new Inputs[]{Inputs.NEW_TEST, Inputs.OPEN_TEST, Inputs.MAIN_MENU, Inputs.EXIT});
        menuSwitch(PrettyOutput.nextLine());
    }

    @Override
    public void menuSwitch(String input) {
        switch (Inputs.toEnum(input)) {
            case NEW_TEST:{
                new NewTest();
                break;
            }
            case OPEN_TEST: {
                new OpenTest();
                break;
            }
            case MAIN_MENU: {
                PrettyOutput.printInfo("Press enter to proceed");
                break;
            }
            default: {
                System.out.println("Invalid command, please try again");
                new EditModMenu();
            }
        }
    }
}

