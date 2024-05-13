import java.util.Scanner;

public class EditModMenu {
    public EditModMenu(){
        CommandImp cmdImp = new CommandImp();

        System.out.println("""
                You are in Editing Module.
                Choose what you want to do
                Input "new test" to create new test
                Input "open test" to open test
                Input "main menu" and press enter two times to go to main menu
                Input "exit" to exit during program execution
                """);

        Scanner scanner = new Scanner(System.in);
        String inp = scanner.nextLine();

        /*Command command = cmdImp.getCommand(inp);
        if(command != null) {
            command.execute();
        }
        if(command == null){
            System.out.println("Invalid command, please try again");
        }*/

        switch (Inputs.toEnum(inp)) {
            case NEW_TEST:{
                new NewTest();
                break;
            }
            case OPEN_TEST: {
                new OpenTest();
                break;
            }
            case MAIN_MENU: {
                break;
            }
            default: {
                System.out.println("Invalid command, please try again");
                new EditModMenu();
            }
        }

    }
}

