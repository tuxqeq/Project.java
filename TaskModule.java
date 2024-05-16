import java.util.Iterator;

public class TaskModule extends WrkngFiles<Tests>{
    String nameWithTXT;
    Tests test;
    public TaskModule(){
        PrettyOutput.printHeader("You are in a Task module");
        newName();
    }


    public void newName(){
        PrettyOutput.printInfo("Existing files are printed lower");
        File.files.stream()
                .filter(file -> file.getPath().startsWith("./tests/"))
                .forEach(iter -> PrettyOutput.print(" - " + iter));
        PrettyOutput.printInfo("Please enter name of the test which you want to choose");
        String inp = removeTXT(PrettyOutput.printBfInp("(you can write either type just filename or with \".txt\""));
        if(testExists(inp)){
            this.name = inp;
            this.test = findTest(inp);
            linkedNumAcess();
        }else{
            PrettyOutput.printWarning("File does not exist");
            PrettyOutput.print("Please enter a valid file name");
            newName();
        }

    }

    @Override
    void ColImp(Tests obj) {}

    public void linkedNumAcess(){
        PrettyOutput.NLprintInfo("You chose \"" + name + "\" test");

        new Menus(){
            private boolean dontstop = true;
            @Override
            public void displayMenu() {
                if (dontstop) {
                    PrettyOutput.printEnums(new Inputs[]{Inputs.NUM_OF_SOLUTIONS, Inputs.NEXT, Inputs.CHECKING_MOD});
                    menuSwitch(PrettyOutput.nextLine());
                }
            }
            @Override
            public void menuSwitch(String input) {
                switch ((Inputs.toEnum(input))){
                    case NUM_OF_SOLUTIONS:
                        PrettyOutput.printInfo("Number of solutions for this Task: " + numOfSolutions());
                        linkedNumAcess();
                        break;
                    case NEXT:
                        nextTest();
                        break;
                    case CHECKING_MOD:
                        this.dontstop = false;
                        break;
                    default:
                        PrettyOutput.printWarning("Wrong command, try again");
                        linkedNumAcess();
                        break;
                }
            }
        }.displayMenu();

    }

    public int numOfSolutions(){
        Iterator<File> files = File.files.iterator();
        int count = 0;
        File current;
        while(files.hasNext()){
            current = files.next();
            if(current instanceof Solutions && ((Solutions) current).contains(this.name)) count++;
        }
        return count;
    }

    public void nextTest(){
        if(test.getNext() != null){
            this.test = test.getNext();
            this.nameWithTXT = test.getName();
            this.name = nameWithTXT.substring(0, nameWithTXT.lastIndexOf('.'));
            System.out.println(this.name);
            linkedNumAcess();
        }else {
            PrettyOutput.printWarning("You got to the end of the 'tests' folder, please choose another file");
            newName();
        }
    }
}
