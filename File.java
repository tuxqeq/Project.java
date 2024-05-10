import java.util.ArrayList;

public abstract class File {
    static ArrayList<File> files = new ArrayList<>();
    abstract String getPath();
    static void addToCollection(File file) {
        files.add(file);
    }
    static void removeFromCollection(File file) {
        files.remove(file);
    }

}
