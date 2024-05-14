import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Scanner;

public class NewTest{
    String name;
    Tests test;
    public NewTest() {
        PrettyOutput.printHeader("You started creating new test");
        createDir("tests");
        name = newName();
        inputtingFile();
        //execute();
    }
    public void execute() {
       /* StringBuilder sb = new StringBuilder();
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
        System.out.println("test: " + name + " was created\n"+
                "If you want to save it type in \"save\", otherwise press enter");
        if(PrettyOutput.nextLine().equals(Inputs.SAVE.getCommand())) {
            savingToFile(content);
        }
        acceptingLink();*/

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
        System.out.println("test: " + name + " was created\n"+
                "If you want to save it type in \"save\", otherwise press enter");
        if(PrettyOutput.nextLine().equals(Inputs.SAVE.getCommand())) {
            savingToFile(content);
        }
        acceptingLink();
    }

    public void savingToFile(String content) {
        String path = "./tests/" + name + ".txt";
        try {
            FileWriter writer = new FileWriter(path);
            writer.write(content);
            writer.close();
            this.test = new Tests(this.name + ".txt", new ContentAnalysed(content));
            System.out.println("File saved successfully!");
        } catch (IOException e) {
            System.out.println("saving new test java");
        }
    }

    public String newName(){
        System.out.println("Enter the name for a test:\n"+
                "(if you print a space only everything before it will be treated as filename)");
        name = PrettyOutput.next();
        if(Command.fileIsInDir(name + ".txt", "./tests/")) {
            PrettyOutput.printWarning("File with that name already exists.");
            PrettyOutput.printEnums(new Inputs[]{Inputs.OVWRT});
            String inp = PrettyOutput.printBfInp("Otherwise press enter and input new name for your file");
            if(inp.equals(Inputs.OVWRT.getCommand())) {
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
                PrettyOutput.printWarning("createDir newfile");
            }
        }
    }
    public void acceptingLink(){
        /*System.out.println("If you want to link the task to the folder where the locations are created input \"link\"\n"+
            "or input stop if you dont want to link files");*/
        PrettyOutput.printEnums(new Inputs[]{Inputs.LINK, Inputs.STOP});
        Scanner scan = new Scanner(System.in);
        switch (Inputs.toEnum(scan.nextLine())){
            case Inputs.LINK:
                linkToFolder();
                break;
            case Inputs.STOP:
                break;
            default:
                PrettyOutput.printWarning("Wrong command, try again");
                acceptingLink();
        }
    }
    public void linkToFolder(){
        String inp = PrettyOutput.printBfInp("Please enter the name of the student to which folder you want to link this test"+
                PrettyOutput.formatInp(new Inputs[]{Inputs.STOP}));
        if (Objects.requireNonNull(Inputs.toEnum(inp)) != Inputs.STOP) {
            String path = "./Students/" + inp;
            Solutions stud = new Solutions(inp);
            stud = fileExists(stud);
            if(stud == null){
                PrettyOutput.printWarning("\"Student does not exist\"");
                linkToFolder();
            }
            if(stud.getSolutions().isEmpty()){
                PrettyOutput.printWarning("Student has not provided solution for this task");
            }if(stud != null){
                this.test.link(stud.getPath() + this.name);
                System.out.println("linked to " + stud.getPath() + this.name);
                linkToFolder();
            }
        }
        /*String path = "./Students/" + inp;
        Solutions stud = new Solutions(inp);
        *//*if(!fileExists(path)){
            PrettyOutput.printWarning("Student does not exist");
            linkToFolder();
        }
        if(fileExists(path + "/" + name)){
            this.test.link(path + "/" + this.name);
            System.out.println("linked to " + this.test + "to " + path + "/" + this.name);
            linkToFolder();
        }else{
            PrettyOutput.printWarning("Student has not provided solution for this task");
            linkToFolder();
        }*//*
        stud = fileExists(stud);
        if(stud == null){
            PrettyOutput.printWarning("\"Student does not exist\"");
            linkToFolder();
        }
        if(stud.getSolutions().isEmpty()){
            PrettyOutput.printWarning("Student has not provided solution for this task");
        }if(stud != null){
            this.test.link(stud.getPath() + this.name);
            System.out.println("linked to " + stud.getPath() + this.name);
            linkToFolder();
        }*/
    }
    public Solutions fileExists(Solutions stud){
        return (Solutions) File.files.stream().filter(iter-> iter.equals(stud)).findFirst().orElse(null);
    }
}
