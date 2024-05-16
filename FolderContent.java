import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FolderContent implements Comparable<FolderContent>{
    String name;
    ArrayList<String> files = new ArrayList<>();
    public FolderContent(Path folder) {
        this.name = folder.getFileName().toString();
        addingToCol(folder);
    }
    private void addingToCol(Path folder){
        try{
            Files.list(folder)
                    .map(Path::getFileName)
                    .forEach(iter -> files.add(iter.toString()));
        }catch (IOException e){
            System.out.println("FolderContent");
        }
    }
    @Override
    public String toString() {
        return "FolderContent{" +
                "name='" + name + '\'' +
                ", files=" + files.toString() +
                '}';
    }

    @Override
    public int compareTo(FolderContent o) {
        return this.name.compareTo(o.name);
    }

    public ArrayList<String> getFiles(){
        return files;
    }
    public String getName() {
        return name;
    }

}
