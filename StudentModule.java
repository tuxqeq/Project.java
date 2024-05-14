import java.util.Scanner;

public class StudentModule {
    String name;
    Solutions solution;
    public StudentModule() {
        System.out.println("You are in Student Module");
        this.name = newName();
        this.solution = findSol(name);
        execute();
    }

    public void execute() {
        menuStd();
        System.out.println("StdModule");
    }

    public String newName() {
        System.out.println("Existing students are printed lower:");
        File.files.stream()
                .filter(file -> file.getPath().startsWith("./Students/"))
                .forEach(System.out::println);
        System.out.println("Please enter a name of a student:");
        Scanner scanner = new Scanner(System.in);
        String nameOfStd = scanner.nextLine();
        if (OpenTest.fileExists("./Students/" + nameOfStd)) {
            return nameOfStd;
        } else {
            System.out.println("There is no student with that name\n" +
                    "Try inputing new name");
            newName();
        }
        return null;
    }
    public void menuStd(){
        System.out.println("You chosed '" + name + "'" +
                """
                        Please choose what you want to do
                        If you want to to get information about number of tasks submitted by this student input "info about"
                        If you want to open files of this student input "open"
                        """);
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextLine()) {
            case "info about":
                System.out.println("Student" + name + "Provided this number of solutions" + infoAbout());
                //infoAbout();
            case "open":
                open();
                break;
            default:
                System.out.println("Wrong command, try again");
                menuStd();
                break;
        }
    }
    public int infoAbout(){
        return this.solution.getLen();
    }
    public void open(){

    }
    public Solutions findSol(String name){
        String path = "./Students/" + name;
        return (Solutions) File.files.stream()
               .filter(iter -> path.equals(iter.getPath()))
               .findFirst()
               .orElse(null);
    }
}
