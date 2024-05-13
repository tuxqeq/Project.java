import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Solutions extends File{
    String studentName;
    ArrayList<String> solutions = new ArrayList<>();
    public Solutions(){
        /*Path studentFolderPath = Paths.get( "./Students/");
        this.studentName = studentFolderPath.getFileName().toString();
        try (Stream<Path> paths = Files.walk(studentFolderPath)) {
            paths
                    .filter(Files::isDirectory)
                    .forEach(folder -> this.solutions.add(folder.getFileName().toString()));
        }
        catch (IOException e){
            System.out.println("Solutions Constructor");
        }*/
        String studentsFolderPath = "./Students/";
        Path studentsPath = Paths.get(studentsFolderPath);
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(studentsPath)) {
            for (Path path : stream) {
                if (Files.isDirectory(path)) {
                     String studentName = path.getFileName().toString();
                    try(Stream<Path> paths = Files.walk(path)) {
                        paths.filter(Files::isDirectory).forEach(folder -> this.solutions.add(folder.getFileName().toString()));
                    }
                    File.addToCollection(this);
                }
            }
        }catch (IOException e){
            System.out.println("Solutions constructor");
        }
    }

    @Override
    String getPath() {
        return "";
    }

    @Override
    public String toString() {
        return "Solutions{" +
                "studentName='" + studentName + '\'' +
                ", solutions=" + solutions +
                '}';
    }
}
