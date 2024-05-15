public class CheckModMenu implements Menus{

    public CheckModMenu() {
        displayMenu();
    }

    @Override
    public void displayMenu() {
        PrettyOutput.printHeader("You are in a Checking Module Menu");
        PrettyOutput.printEnums(new Inputs[]{Inputs.GO_TO_TASK, Inputs.GO_TO_STUDENT_MOD, Inputs.MAIN_MENU});
        menuSwitch(PrettyOutput.nextLine());
    }

    @Override
    public void menuSwitch(String input) {
        switch (Inputs.toEnum(input)){
            case GO_TO_TASK:{
                new TaskModule();
                break;
            }
            case GO_TO_STUDENT_MOD:{
                new StudentModule();
                break;
            }
            case MAIN_MENU:
                break;
            default:{
                System.out.println("Invalid command, please try again");
                new CheckModMenu();
            }
        }
    }
}
