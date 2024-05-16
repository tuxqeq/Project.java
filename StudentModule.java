import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class StudentModule {
    String name;
    Solutions solution;
    String pathToCurrentFolder;
    ArrayList<String> files;

    public StudentModule() {
        PrettyOutput.printHeader("You are in Student Module");
        newName();
        menuStd();
    }

    public void newName() {
        PrettyOutput.printInfoNL("Existing students are printed lower:");
        File.files.stream()
                .filter(file -> file.getPath().startsWith("./Students/"))
                .forEach(iter -> PrettyOutput.print("- " + iter));
        PrettyOutput.printInfo("Please enter a name of a student");
        String inp = PrettyOutput.nextLine();
        if (WrkngFiles.fileExists("./Students/" + inp)) {
            this.name = inp;
            this.solution = findSol(name);
        } else {
            PrettyOutput.printWarning("There is no student with that name");
            PrettyOutput.printInfo("Try inputing new name");
            newName();
        }
    }

    public void menuStd() {
        PrettyOutput.printInfo("You chosed " + name + " ");
        new Menus(){
            private boolean dontstop = true;
            @Override
            public void displayMenu() {
                while (dontstop){
                    PrettyOutput.printEnums(new Inputs[]{Inputs.INFO_ABOUT, Inputs.READ_SOL_FILES, Inputs.CHECKING_MOD});
                    menuSwitch(PrettyOutput.nextLine());
                }
            }

            @Override
            public void menuSwitch(String input) {
                switch (Inputs.toEnum(input)) {
                    case Inputs.INFO_ABOUT:
                        PrettyOutput.printInfoNL("Student '" + name + "' Provided this number of solutions: " + infoAbout());
                        menuStd();
                        break;
                    case Inputs.READ_SOL_FILES:
                        open();
                        break;
                    case Inputs.CHECKING_MOD:
                        dontstop =false;
                        break;
                    default:
                        PrettyOutput.printWarning("Wrong command, try again");
                        menuStd();
                        break;
                }
            }
        }.displayMenu();

    }

    public int infoAbout() {
        return this.solution.getLen();
    }

    public void open() {
        PrettyOutput.NLprintInfo("Please choose a folder");
        PrettyOutput.print(PrettyOutput.formatInp(new Inputs[]{Inputs.GO_TO_ANOTHER_STD}) + "(they are listed lower)");
        this.solution.getSolutions().forEach(iter -> PrettyOutput.print("- - " + iter.getName()));

        String folderName = PrettyOutput.nextLine();
        if(Inputs.toEnum(folderName).equals(Inputs.GO_TO_ANOTHER_STD)){
            newName();
        }else {
            if (this.solution.contains(folderName)) {
                this.pathToCurrentFolder = this.solution.getPath() + folderName;
                openFiles(folderName);
            } else {
                PrettyOutput.printWarning("Folder does not exist, please try again");
                open();
            }
        }
    }

    public Solutions findSol(String name) {
        String path = "./Students/" + name + "/";
        Solutions foundSolution = (Solutions) File.files.stream()
                .filter(iter -> path.equals(iter.getPath()))
                .findFirst()
                .orElse(null);
        if (foundSolution == null) {
            PrettyOutput.printWarning("No solutions found for this student");
        }
        return foundSolution;
    }

    public void openFiles(String FldName) {
        ArrayList<String> names;
        names = this.solution.findfiles(FldName);
        this.files = names;
        if(names.isEmpty()) {
            PrettyOutput.printWarning("There is no solutions in this folder");
            PrettyOutput.printInfoNNL("Please choose another one");
            open();
        }else {
            PrettyOutput.NLprintInfo("Please choose a file to open");
            PrettyOutput.print("They are listed lower:");
            names.forEach(s -> PrettyOutput.print(" - - - " + s));
            String filename = checkJAVA(
                    PrettyOutput.printBfInp("(you can enter either filename or filename with '.java')")
            );
            if (names.contains(filename)) {
                Path pathToFile = Path.of(this.solution.getPath() + FldName + "/" + filename);
                soutFIle(pathToFile);
            } else {
                PrettyOutput.printWarning("There is no file with specified filename, try again");
                openFiles(FldName);
            }
        }
    }

    public void soutFIle(Path path) {
        try {
            Files.lines(path).forEach(System.out::println);
        } catch (IOException e) {
            PrettyOutput.print("souting file");
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
        PrettyOutput.printEnums(new Inputs[]{Inputs.NEXT, Inputs.PREVIOUS, Inputs.GO_TO_CHOOSING_FOLD, Inputs.GO_TO_ANOTHER_STD});
        switch (Inputs.toEnum(PrettyOutput.nextLine())) {
            case NEXT:
                next(path);
                break;
            case PREVIOUS:
                previous(path);
                break;
            case GO_TO_CHOOSING_FOLD:
                open();
                break;
            case GO_TO_ANOTHER_STD:
                newName();
                break;
            default:
                PrettyOutput.printWarning("Invalid command, try again");
                nextPrevious(path);
        }
    }

    void next(Path path) {
        String nameCurrent = path.getFileName().toString();
        int currentIndex = files.indexOf(checkJAVA(nameCurrent));
        if (currentIndex < files.size() - 1) {
            String nextFile = files.get(currentIndex + 1);
            System.out.println(pathToCurrentFolder);
            Path nextPath = Paths.get(pathToCurrentFolder, nextFile);
            System.out.println(nextFile);
            soutFIle(nextPath);
        } else {
            PrettyOutput.printWarning("This is the last file, you can move only backwards");
            nextPrevious(path);
        }
    }
    void previous(Path path) {
        String nameCurrent = path.getFileName().toString();
        int currentIndex = files.indexOf(checkJAVA(nameCurrent));
        if (currentIndex > 0) {
            String prevFile = files.get(currentIndex - 1);
            Path prevPath = Paths.get(pathToCurrentFolder, prevFile);
            System.out.println(prevFile);
            soutFIle(prevPath);
        } else {
            PrettyOutput.printWarning("This is the first file in folder, you can move only forward");
            nextPrevious(path);
        }
    }
}
