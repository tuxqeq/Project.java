import java.util.Scanner;

public class NewProcess {
    public NewProcess(){
        CommandImp commandImp = new CommandImp();

        Scanner scanner = new Scanner(System.in);
        String inp;

        System.out.println("""
                Input "new test" to create new test
                Input "open file" to open new file
                Input "go to checking module" to go to checking module
                Input "exit" to exit
                """);

        inp = scanner.nextLine();

        Command command = commandImp.getCommand(inp);
        if(command != null) {
            command.execute();
        }
        if (inp.equals(Inputs.EXIT.getCommand())) {
            System.exit(0);
        }
        if(command == null){
            System.out.println("Invalid command, please try again");
        }

    }
}

