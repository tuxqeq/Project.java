import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class StudentModule {
    String name;
    Solutions solution;
    Path pathToCurrentFolder;
    ArrayList<String> files;

    public StudentModule() {
        System.out.println("You are in Student Module");
        this.name = newName();
        this.solution = findSol(name);
        execute();
    }

    public void execute() {
        menuStd();
        //System.out.println("StdModule");
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

    public void menuStd() {
        System.out.println("You chosed '" + name + "'" +
                """
                        Please choose what you want to do
                        If you want to to get information about number of tasks submitted by this student input "info about"
                        If you want to open files of this student input "open"
                        """);
        Scanner scanner = new Scanner(System.in);
        switch (scanner.nextLine()) {
            case "info about":
                System.out.println("Student '" + name + "' Provided this number of solutions: " + infoAbout());
            case "open":
                open();
                break;
            default:
                System.out.println("Wrong command, try again");
                menuStd();
                break;
        }
    }

    public int infoAbout() {
        return this.solution.getLen();
    }

    public void open() {
        System.out.println("Please choose a folder\n" +
                "(they are listed lower)");
        this.solution.getSolutions().stream().forEach(System.out::println);
        Scanner scanner = new Scanner(System.in);
        String folderName = scanner.nextLine();
        //openFolder(folderName);
        Path folderPath = Paths.get("./Students/" + name + "/" + folderName);
        this.pathToCurrentFolder = folderPath;
        if (Files.exists(folderPath)) {
            openFiles(folderPath);
        } else {
            System.out.println("Folder does not exist, please try again");
            open();
        }
    }

    public Solutions findSol(String name) {
        String path = "./Students/" + name + "/";
        Solutions foundSolution = (Solutions) File.files.stream()
                .filter(iter -> path.equals(iter.getPath()))
                .findFirst()
                .orElse(null);

        if (foundSolution == null) {
            System.out.println("No solution found for this student");
        }
        return foundSolution;
    }

    /*public void openFolder(String folderName) {
        Path folderPath = Paths.get("./Students/" + name + "/" + folderName);
        this.pathToCurrentFolder = folderPath;
        if (Files.exists(folderPath)) {
            openFile(folderPath);
        } else {
            System.out.println("Folder does not exist, please try again");
            open();
        }
    }*/

    public void openFiles(Path path) {
        System.out.println("Please choose a file to open\n" +
                "They are listed lower");
        ArrayList<String> names = new ArrayList<>();
        try {
            Files.list(path)
                    .map(Path::getFileName)
                    .forEach(iter -> names.add(iter.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.files = names;
        if(names.isEmpty()) {
            System.out.println("There is no solutions in this folder, please choose another one");
            open();
        }else {
            names.stream().forEach(System.out::println);
            System.out.println("(you can enter either filename or filename with '.java')");
            Scanner scanner = new Scanner(System.in);
            String filename = checkJAVA(scanner.nextLine());
            Path path1 = Path.of(path + "/" + filename);
            if (Files.exists(path1)) {
                soutFIle(path1);
            } else {
                System.out.println("There is no file with specified filename, try again");
                openFiles(path);
            }
        }
    }

    public void soutFIle(Path path) {
        try {
            Files.lines(path).forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        nextPrevious(path);
    }

    String checkJAVA(String name) {
        if (name.endsWith(".java")) {
            return name;
        } else {
            return name + ".java";
        }
    }


    void nextPrevious(Path path) {
        System.out.println("If you want to go to the next file, please input \"next\"\n" +
                "And if you want to go to the previous file, please input \"previous\"\n" +
                "If you want to go back to choosing folders, please input \"back\"");
        Scanner scanner = new Scanner(System.in);
        switch (Inputs.toEnum(scanner.next())) {
            case NEXT:
                next(path);
                break;
            case PREVIOUS:
                previous(path);
                break;
            case BACK:
                open();
            default:
                System.out.println("invalid command, try again");
                nextPrevious(path);
        }
    }

    void next(Path path) {
        String nameCurrent = path.getFileName().toString();
        int currentIndex = files.indexOf(nameCurrent);
        if (currentIndex < files.size() - 1) {
            String nextFile = files.get(currentIndex + 1);
            Path nextPath = Paths.get(path.getParent().toString(), nextFile);
            System.out.println(nextFile);
            soutFIle(nextPath);
        } else {
            System.out.println("This is the last file, you can moove only backwards");
            nextPrevious(path);
        }
    }
    void previous(Path path) {
        System.out.println("prevoius");
        String nameCurrent = path.getFileName().toString();
        int currentIndex = files.indexOf(nameCurrent);
        if (currentIndex > 0) {
            String nextFile = files.get(currentIndex - 1);
            Path nextPath = Paths.get(path.getParent().toString(), nextFile);
            System.out.println(nextFile);
            soutFIle(nextPath);
        } else {
            System.out.println("This is the first file in folder, you can move only forward");
            nextPrevious(path);
        }
    }
}
