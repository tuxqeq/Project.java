import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NewTest extends WrkngFiles{
    //String name;
    //Tests test;
    public NewTest() {
        PrettyOutput.printHeader("You started creating new test");
        newName();
        createDir("tests");
        //name = newName();
        inputtingFile();
    }

    public void inputtingFile(){
        StringBuilder sb = new StringBuilder();
        System.out.println("Thanks! Now you can start inputing content:\n"+
                "(to stop inputing type \"stop\" in a new line and press enter)");
        while (true) {
            String line = PrettyOutput.nextLine();
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
        PrettyOutput.printInfo("Enter the name for a test:");
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

    /*private boolean testExists(String s) {
        for(File i : File.files){
            if(i.toString().equals("\"" + s + "\" ")){
                return true;
            }
        }
        return false;
    }*/

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
    /*public void acceptingLink(){
        PrettyOutput.printEnums(new Inputs[]{Inputs.LINK, Inputs.STOP});
        Scanner scan = new Scanner(System.in);
        switch (Inputs.toEnum(scan.nextLine())){
            case Inputs.LINK:
                linkToFolder();
                break;
            case Inputs.STOP:
                PrettyOutput.printInfo("Press enter to process");
                break;
            default:
                PrettyOutput.printWarning("Wrong command, try again");
                acceptingLink();
        }
    }*/
    /*public void linkToFolder(){
        String inp = PrettyOutput.printBfInp("Please enter the name of the student to which folder you want to link this test\n"+
                PrettyOutput.formatInp(new Inputs[]{Inputs.STOP}));
        if ((Inputs.toEnum(inp)) != Inputs.STOP) {
            String path = "./Students/" + inp;
            if(!fileExists(path)){
                PrettyOutput.printWarning("Student does not exist");
                linkToFolder();
            }
            if(fileExists(path + "/" + name)){
                this.test.link(inp + "/" + this.name);
                System.out.println("linked to " + this.test + "to " + path + "/" + this.name);
                linkToFolder();
            }else{
                PrettyOutput.printWarning("Student has not provided solution for this task");
                linkToFolder();
            }
        }else{
            PrettyOutput.printInfo("Press enter to proceed");
        }
    }*/
    /*public static boolean fileExists(String pathToF){
        Path path = Paths.get(pathToF);
        return Files.exists(path);
    }*/
}
