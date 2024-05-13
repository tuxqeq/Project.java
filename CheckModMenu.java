import java.util.Scanner;

public class CheckModMenu {

    public CheckModMenu() {
        CommandImp cmdImp = new CommandImp();

        System.out.println("""
                You are in a Checking module.
                Choose what you want to do
                Input "go to task" to get info and access tasks in a sequence
                Input "go to student module" if you want to check info and solutions of a student
                Input "main menu" to go to main menu
                """);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        /*Command command = cmdImp.getChModCommand(input);
        if(command != null){
            command.execute();
        }
        if(command == null){
            System.out.println("Invalid command, please try again");
        }*/
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
