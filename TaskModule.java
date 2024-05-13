import java.util.Scanner;

public class TaskModule{
    String nameWithTXT;
    Tests test;
    public TaskModule(){
        System.out.println("You are in a Task module");
        this.nameWithTXT = newName();
        this.test = findTest(nameWithTXT);
        execute();
    }
    public void execute() {
    }

    public String newName(){
        System.out.println("Existing files are printed lower\n");
        File.files.stream()
                .filter(file -> file.getPath().startsWith("./tests/"))
                .forEach(System.out::println);
        System.out.println("""
                Please enter name of the test for which you want to get info
                (you can write either type just filename or with ".txt")""");
        Scanner scanner = new Scanner(System.in);
        String nameWithTXT =  OpenTest.checkTXT(scanner.next());
        if(OpenTest.fileExists("./tests/" + nameWithTXT)){
            return nameWithTXT;
        }else{
            System.out.println("File does not exist. Please enter a valid file name.");
            newName();
        }
        return null;
    }
    public Tests findTest(String path){
        String pathToSearch = "./tests/" + path;
        return (Tests) File.files.stream()
                .filter(iter -> pathToSearch.equals(iter.getPath()))
                .findFirst()
                .orElse(null);
    }


}
