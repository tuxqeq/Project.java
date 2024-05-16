import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

abstract class WrkngFiles <T>{
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
        boolean local = true;
        while (local) {
            PrettyOutput.print("Please enter the name of the student to which folder you want to link this test\n"+
                    PrettyOutput.formatInp(new Inputs[]{Inputs.STOP}));
            String inp = (new Scanner(System.in)).nextLine();
            if (Inputs.toEnum(inp).equals(Inputs.STOP)) local = false;

            String path = "./Students/" + inp;
            if(!fileExists(path)){
                PrettyOutput.printWarning("Student does not exist");
                continue;
            }
            if(fileExists(path + "/" + name)){
                this.test.link(inp + "/" + this.name);
                PrettyOutput.printInfo("linked to " + this.test + " to " + path + "/" + this.name);
            }else{
                PrettyOutput.printWarning("Student has not provided solution for this task");
            }
        }
    }
    public static boolean fileExists(String pathToF){
        Path path = Paths.get(pathToF);
        return Files.exists(path);
    }
    public boolean testExists(String s) {
        String ss = "\"" + s + ".txt\"";
        for(File i : File.files){
            if(i.toString().equals(ss)){
                return true;
            }
        }
        return false;
    }
    public String testTXT(){
        return this.name + ".txt";
    }
    public Tests findTest(String path){
        String pathToSearch = "./tests/" + path + ".txt";
        return (Tests) File.files.stream()
                .filter(iter -> pathToSearch.equals(iter.getPath()))
                .findFirst()
                .orElse(null);
    }
    public String removeTXT(String filename) {
        if (filename.endsWith(".txt")) {
            return filename.substring(0, filename.length() - 4);
        }
        return filename;
    }
    public void addToCol(T obj){
        File.files.add((File) obj);
    }
    abstract void ColImp(T obj);
}
