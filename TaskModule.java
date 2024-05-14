import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class TaskModule{
    String nameWithTXT;
    String name;
    Tests test;
    public TaskModule(){
        System.out.println("You are in a Task module");
        this.nameWithTXT = newName();
        this.name = nameWithTXT.substring(0, nameWithTXT.lastIndexOf('.'));
        this.test = findTest(nameWithTXT);
        execute();
    }

    public void execute() {
        System.out.println(this.name);
        linkedNumAcess();

    }

    public String newName(){
        System.out.println("Existing files are printed lower\n");
        File.files.stream()
                .filter(file -> file.getPath().startsWith("./tests/"))
                .forEach(System.out::println);
        System.out.println("""
                Please enter name of the test which you want to choose
                (you can write either type just filename or with ".txt")""");
        Scanner scanner = new Scanner(System.in);
        String nameWithTXT =  OpenTest.checkTXT(scanner.next());
        System.out.println(nameWithTXT);
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

    public void printLinkedPaths() {
        System.out.println("Linked Paths:");
        for (String path : this.test.linkedPaths) {
            System.out.println(path);
        }
    }

    public void linkedNumAcess(){
        System.out.println("You choosed \"" + name + "\" test\n"+
                "Please choose what you want to do with it\n"+
                "If you want to get number of solutions for this task input \"number of solutions\"\n"+
                "If you want to access next task in 'tasks' folder press \"cmd\" + \"D\"");


        /*new JFrame() {{
            setSize(1, 1);

            JPanel contentPane = (JPanel) getContentPane();

            Action action = new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Ctrl + Shift + D pressed");
                    setVisible(false);
                    dispose();
                    System.out.println("afterDipose");
                    nxtTst();
                }
                public void nxtTst(){
                    dispose();
                    nextTest();
                }
            };

            String keyStrokeAndKey = "ctrl shift D";
            KeyStroke keyStroke = KeyStroke.getKeyStroke(keyStrokeAndKey);
            contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
                    .put(keyStroke, keyStrokeAndKey);
            contentPane.getActionMap().put(keyStrokeAndKey, action);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setVisible(true);

        }};*/
        Scanner scanner = new Scanner(System.in);
        switch(Inputs.toEnum(scanner.nextLine())){
            case NUM_OF_SOLUTIONS:
                System.out.println("Number of solutions for this Task: " + numOfSolutions());
                linkedNumAcess();
                break;
            case NEXT:
                nextTest();
                break;
            default:
                System.out.println("Wrong command, try again");
                linkedNumAcess();
                break;

        }
    }

    public int numOfSolutions(){
        Iterator<File> files = File.files.iterator();
        int count = 0;
        File current;
        while(files.hasNext()){
            current = files.next();
            if(current instanceof Solutions && containsSol((Solutions) current)) count++;
        }
        return count;
    }

    public boolean containsSol(Solutions solutions){
        ArrayList<String> sol = solutions.getSolutions();
        return sol.contains(this.name);
    }
    public void nextTest(){
        if(test.getNext() != null){
            this.test = test.getNext();
            this.nameWithTXT = test.getName();
            this.name = nameWithTXT.substring(0, nameWithTXT.lastIndexOf('.'));
        }else {
            System.out.println("You got to the end of the tests folder");
        }
    }

}
