import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

public class NewTest implements Command{
    String name;
    private Scanner scanner = new Scanner(System.in);
    public NewTest() {
        createDir("tests");
        name = newName();
    }
    @Override
    public void execute() {
        //Scanner scanner = new Scanner(System.in);
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
    }

    public void savingToFile(String content) {
        String path = "./tests/" + name + ".txt";
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(content);
            writer.close();
            new Tests(this.name + ".txt", new Content(content));
            System.out.println("File saved successfully!");
        } catch (IOException e) {
            System.out.println("saving new test java");
        }
    }


/*
    public static boolean IfNotAvaliable(String nameToCheck, String path) {
        Path startPath = Paths.get(path);
        String fileToFind = nameToCheck;

        try (Stream<Path> stream = Files.walk(startPath)) {
            return stream
                    .anyMatch(iter -> iter.getFileName().toString().equals(fileToFind));

        } catch (IOException e) {
            System.out.println("ifNotAvaliable newtest");
        }
        return false;
    }
*/


    public String newName(){
        //Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the name for a test:\n"+
                "(if you print a space only everything before it will be treated as filename)");
        String input = scanner.next();
        name = input;
        if(Command.ifNotAvaliable(name + ".txt", "./tests/")) {
            System.out.println("File with that name already exists.\n"+
                    "If you want to overwrite the file with that name input \"overwrite\"\n"+
                    "Otherwise press enter and input new name for your file");
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
}
