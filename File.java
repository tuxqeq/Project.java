import java.util.ArrayList;

public abstract class File {
    static ArrayList<File> files = new ArrayList<>();
    abstract String getPath();
}
