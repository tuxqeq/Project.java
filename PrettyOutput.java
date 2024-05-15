import java.util.Scanner;

interface PrettyOutput {
    static void printWarning(String s){
        System.out.println("\n!!! " + s + " !!!\n");
    }
    static void printEnums(Inputs[] enums){
        System.out.println("||| You have " + enums.length + " options, please choose: |||");
        System.out.println(formatInp(enums));

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
        StringBuilder sb = new StringBuilder();
        for (Inputs e : enums) {
            sb.append("• If you want to ").append(e.getCommand()).append(" input \"").append(e.getCommand()).append("\"\n");
        }
        return sb.toString();
    }
    static void printHeader(String s){
        System.out.println("\n–––– " + s + " ––––\n");
    }
    static void printInfo(String s){
        System.out.println("– " + s + " –");
    }
}
