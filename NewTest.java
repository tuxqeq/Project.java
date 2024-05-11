import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class NewTest implements Command{
    String name;
    private final Scanner scanner = new Scanner(System.in);
    Tests test;
    public NewTest() {
        createDir("tests");
        name = newName();
    }
    @Override
    public void execute() {
        StringBuilder sb = new StringBuilder();
        System.out.println("Thanks! Now you can start inputing content:\n"+
                "(to stop inputing type \"stop\" in a new line and press enter)");
        while (true) {
            String line = scanner.nextLine();
            if (line.equals(Inputs.STOP.getCommand())) {
                break;
            } else {
                sb.append(line).append("\n");
            }
        }
        String content = sb.toString();
        System.out.println("test: " + name + " was created\n"+
                "If you want to save it type in \"save\", otherwise press enter");
        if(scanner.nextLine().equals(Inputs.SAVE.getCommand())) {
            savingToFile(content);
        }
        System.out.println("If you want to link the task to the folder where the locations are created input \"link\"\n"+
                "or input stop if you dont want to link files");
        acceptingLink();
    }

    public void savingToFile(String content) {
        String path = "./tests/" + name + ".txt";
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(content);
            writer.close();
            this.test = new Tests(this.name + ".txt", new Content(content));
            System.out.println("File saved successfully!");
        } catch (IOException e) {
            System.out.println("saving new test java");
        }
    }

    public String newName(){
        System.out.println("Enter the name for a test:\n"+
                "(if you print a space only everything before it will be treated as filename)");
        name = scanner.next();
        if(Command.fileIsInDir(name + ".txt", "./tests/")) {
            System.out.println("""
                    File with that name already exists.
                    If you want to overwrite the file with that name input "overwrite"
                    Otherwise press enter and input new name for your file""");
            if(scanner.nextLine().equals(Inputs.OVWRT.getCommand())) {
                return name;
            }else{
                newName();
            }
        }
        return name;
    }
    public void createDir(String name){
        Path startPath = Paths.get("./"+name+"/");
        if (Files.notExists(startPath)) {
            try {
                Files.createDirectories(startPath);
            } catch (IOException e) {
                System.out.println("createDir newfile");
            }
        }
    }
    public void acceptingLink(){
        switch (Inputs.toEnum(scanner.next())){
            case LINK:
                linkToFolder();
                break;
            case STOP:
                break;
            default:
                System.out.println("Wrong command, try again");
                acceptingLink();
        }
    }
    public void linkToFolder(){
        System.out.println("Please enter the name of the student to which folder you want to link the test");
        String studentName = scanner.nextLine();
        String path = "./Students/" + studentName;
        if(Command.fileIsInDir(this.name, studentName)){
            this.test.link(studentName + "/" + this.name);
        }else{
            System.out.println("Student has not provided solution for this task");
        }
    }
}
