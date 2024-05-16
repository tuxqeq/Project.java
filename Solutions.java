import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Stream;

public class Solutions extends File implements Comparable<Solutions>{
    String studentName;
    ArrayList<FolderContent> solutions = new ArrayList<>();
    public Solutions(){
        String studentsFolderPath = "./Students/";
        Path studentsPath = Paths.get(studentsFolderPath);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(studentsPath)) {
            for (Path path : stream) {
                if (Files.isDirectory(path)) {
                    String studentName = path.getFileName().toString();
                    try(Stream<Path> paths = Files.walk(path)) {
                        paths.filter(Files::isDirectory).forEach(folder -> {
                            Solutions sol = new Solutions(studentName);
                            if(File.files.contains(sol)){
                                Solutions found = (Solutions) File.files
                                        .stream()
                                        .filter(item -> item.equals(sol)).findFirst().get();
                                found.getSolutions().add(new FolderContent(folder));
                            }else {
                                new WrkngFiles<Solutions>(){
                                    @Override
                                    void newName() {}

                                    @Override
                                    void ColImp(Solutions obj) {
                                        File.files.add(obj);
                                    }
                                }.ColImp(sol);
                            }
                        });
                    }
                }
            }
        }catch (IOException e){
            System.out.println("Solutions constructor");
        }
    }
    public Solutions(String studentName){
        this.studentName = studentName;
    }

    @Override
    String getPath() {
        return "./Students/"+studentName +"/";
    }

    @Override
    public String toString() {
        return studentName;
    }

    public String getStudentName(){
        return studentName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Solutions solution)) return false;
        return Objects.equals(studentName, solution.studentName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(studentName);
    }

    @Override
    public int compareTo(Solutions o) {
        if(this.equals(o)) return 0;
        else return 1;
    }

    public ArrayList<FolderContent> getSolutions() {
        return solutions;
    }
    public int getLen(){
        int count = 0;
        for(FolderContent s : solutions){
            if(!s.getFiles().isEmpty()){
                count ++;
            }
        }
        return count;
    }
    public boolean contains(String solution){
        for(FolderContent f : solutions){
            if(f.getName().equals(solution)){
                return true;
            }
        }
        return false;
    }
    public ArrayList<String> findfiles(String FldName){
        for(FolderContent f : solutions){
            if(f.getName().equals(FldName)){
                return f.getFiles();
            }
        }
        return null;
    }

    public Object getName() {
        return studentName;
    }
}
