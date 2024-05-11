import java.util.Scanner;

public class NewProcess {
    public NewProcess(){
        CommandImp commandImp = new CommandImp();

        Scanner scanner = new Scanner(System.in);
        String inp;

        System.out.println("""
                Input "new test" to create new test
                Input "open test" to open test
                Input "go to checking module" to go to checking module
                Input "exit" to exit during program execution
                """);

        inp = scanner.nextLine();

        Command command = commandImp.getCommand(inp);
        if(command != null) {
            command.execute();
        }
        if(command == null){
            System.out.println("Invalid command, please try again");
        }

    }
}

