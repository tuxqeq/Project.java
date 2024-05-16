import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class NewTest extends WrkngFiles<Tests>{
    public NewTest() {
        PrettyOutput.printHeader("You started creating new test");
        newName();
        createDir("tests");
        inputtingFile();
    }

    public void inputtingFile(){
        StringBuilder sb = new StringBuilder();
        System.out.println("Thanks! Now you can start inputing content:\n"+
                "(to stop inputing type \"stop\" in a new line and press enter)");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String line = scanner.nextLine();
            if (line.equals(Inputs.STOP.getCommand())) {
                break;
            } else {
                sb.append(line).append("\n");
            }
        }
        String content = sb.toString();
        PrettyOutput.printInfo("test: " + name + " was created");
        savingToFile(content);
        acceptingLink();
    }

    public void savingToFile(String content) {
        String path = "./tests/" + name + ".txt";
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(content);
            writer.close();
            this.test = new Tests(this.name + ".txt", new ContentAnalysed(content));
        } catch (IOException e) {
            System.out.println("saving new test java");
        }
    }

    public void newName(){
        PrettyOutput.printInfoNNL("Enter the name for a test:");
        String nameT = PrettyOutput.printBfInp("(if you print a space only everything before it will be treated as filename)");
        if(testExists(nameT + ".txt")) {
            PrettyOutput.printWarning("File with that name already exists.");
            PrettyOutput.printEnums(new Inputs[]{Inputs.OVWRT});
            String inp = PrettyOutput.printBfInp("Otherwise press enter and input new name for your file");
            if(inp.equals(Inputs.OVWRT.getCommand())) {
                this.name = nameT;
            }else{
                newName();
            }
        }
        this.name = nameT;
    }

    @Override
    void ColImp(Tests obj) {}

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
