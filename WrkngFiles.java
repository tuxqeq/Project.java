import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

abstract class WrkngFiles {
    public String name;
    public Tests test;
    abstract void newName();
    public void acceptingLink(){
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
    }
    public void linkToFolder(){
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
    }
    public static boolean fileExists(String pathToF){
        Path path = Paths.get(pathToF);
        return Files.exists(path);
    }
    public boolean testExists(String s) {
        //System.out.println(s);
        for(File i : File.files){
            //System.out.println(i.toString());
            if(i.toString().equals("\"" + s + "\"")){
                return true;
            }
        }
        return false;
    }
}
