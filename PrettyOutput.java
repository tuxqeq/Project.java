import java.util.Scanner;

interface PrettyOutput {
    static void printWarning(String s){
        System.out.println("!!! " + s + " !!!\n");
    }
    static void printEnums(Inputs[] enums){
        //System.out.println("\n");
        for (Inputs e : enums) {
            System.out.println("If you want to " + e.getCommand() + " input \"" + e.getCommand() + "\"");
        }
        //System.out.println("\n");
    }
    static String next(){
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }
    static String nextLine(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
    static String printBfInp(String s){
        System.out.println(s);
        return nextLine();
    }
    static String formatInp(Inputs[] enums){
        StringBuilder sb = new StringBuilder("");
        for (Inputs e : enums) {
            sb.append("\nIf you want to " + e.getCommand() + " input \"" + e.getCommand() + "\"");
        }
        return sb.toString();
    }
    static void printHeader(String s){
        System.out.println("--- " + s + " ---");
    }
}
