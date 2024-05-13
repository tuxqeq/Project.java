import java.util.Scanner;

public class MainMenu {
    public MainMenu(){
        System.out.println("""
                You are in Main Menu.
                Choose what you want to do
                Input "editing module" to go to editing module
                Input "checking module" to go to checking module
                Input "exit" to exit during program execution
                """);

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        switch (Inputs.toEnum(input)){
            case EDITING_MOD -> {
                while(true){
                    new EditModMenu();
                    if (Inputs.toEnum(scanner.nextLine()) == Inputs.MAIN_MENU){
                        break;
                    }
                }
            }
            case CHECKING_MOD -> {
                while (true) {
                    new CheckModMenu();
                    if (Inputs.toEnum(scanner.nextLine()) == Inputs.MAIN_MENU){
                        break;
                    }
                }
            }
        }
    }
}
